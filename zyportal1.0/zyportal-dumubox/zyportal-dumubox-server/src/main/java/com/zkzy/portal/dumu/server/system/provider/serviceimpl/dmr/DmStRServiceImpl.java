package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dmr;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfNMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStnRMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.entity.dm.SSDeviceInfo;
import com.zkzy.zyportal.system.api.entity.dmr.DmStR;
import com.zkzy.zyportal.system.api.entity.dmr.DmStfN;
import com.zkzy.zyportal.system.api.entity.dmr.DmStfR;
import com.zkzy.zyportal.system.api.entity.dmr.DmStnR;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
import com.zkzy.zyportal.system.api.service.dmr.DmStfRService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/20.
 */
@Service
public class DmStRServiceImpl implements DmStfRService {


    private static final Logger LOGGER = LoggerFactory.getLogger(DmStRServiceImpl.class);


    private static final ExecutorService addpool = Executors.newFixedThreadPool(3);

    private static final ExecutorService delpool = Executors.newFixedThreadPool(3);


    /**
     * 度目缓存前缀
     */
    public static final String REDIS_PREFIX_DUMU = "dumu:";

    public static final String BASE_NAME = "base";

    /**
     * 度目实时信息前缀
     */

    public static final String REDIS_PREFIX_DUMU2 = "dm-";


    public static final String REDIS_PREFIX_IP = "ip-";

    @Autowired
    private DmBoxBMapper dmBoxBMapper;

    @Autowired
    private DmStRMapper dmStRMapper;

    @Autowired
    private DmStnRMapper dmStnRMapper;

    @Autowired
    private DmStfNMapper dmStfNMapper;

    @Autowired
    private PersonSetService personSetService;

    @Autowired
    private DmStfRService dmStRService;

    @Autowired
    private DmStfRMapper dmStfRMapper;

    @Autowired
    private NameListManageService nameListManageService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public SSDeviceInfo getDevicedSSinfo(String uuid) throws Exception {
        if (redisTemplate.hasKey(REDIS_PREFIX_DUMU2 + uuid)) {
            String jsonStr = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU2 + uuid);
            SSDeviceInfo ssDeviceInfo = JSON.parseObject(jsonStr, SSDeviceInfo.class);
            if (redisTemplate.hasKey(REDIS_PREFIX_DUMU + uuid)) {
                String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + uuid);
                ssDeviceInfo.setSession(value);
            }
            if (redisTemplate.hasKey(REDIS_PREFIX_IP + uuid)) {
                ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
            } else {
                DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(uuid);
                if (dmBoxB != null && StringUtils.isNoneEmpty(dmBoxB.getDeviceip())) {
                    redisTemplate.opsForValue().set(REDIS_PREFIX_IP + dmBoxB.getDeviceuuid(), dmBoxB.getDeviceip());
                    ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
                } else {
                    return null;
                }
            }
            return ssDeviceInfo;
        } else {
            return null;
        }
    }

    @Override
    public CodeObject stBindBoxes(String unid, String deviceuuids) {
        if (addpool != null) {
            addpool.submit(new Runnable() {
                @Override
                public void run() {
                    String[] deviceuuid = deviceuuids.split(",");
                    for (int i = 0; i < deviceuuid.length; i++) {
                        dmStRService.bindBox(unid, deviceuuid[i]);
                    }
                    while (true) {
                        //查询站点下所有的白名单列表
                        int num = dmStfNMapper.selectWhiteNum(unid, new BigDecimal(2));
                        DmStfR dmStfR = dmStfRMapper.selectByPrimaryKey(unid, new BigDecimal(2));
                        if (dmStfR != null) {
                            dmStfR.setNum(new BigDecimal(num));
                            int a = dmStfRMapper.updateByPrimaryKey(dmStfR);
                            if (a > 0) {
                                dmStfR.setLgs(uuid());
                                dmStfRMapper.updateLgs(dmStfR);
                                LOGGER.info("绑定完成");
                                break;
                            }
                        } else {
                            break;
                        }
                    }


                }
            });
        }
        return ReturnCode.TB004;
    }


    @Transactional(rollbackFor = Exception.class)
    public void bindBox(String unid, String deviceuuid) {
        try {
            String time = getDateTime();
            //服务端增加站点与盒子绑定
            DmStR dmStR = dmStRMapper.selectByPrimaryKey(unid, deviceuuid);
            if (dmStR == null) {
                dmStR = new DmStR();
                dmStR.setUnid(unid);
                dmStR.setDeviceuuid(deviceuuid);
                dmStR.setBindtime(time);
                dmStRMapper.insert(dmStR);
            }
            //查询站点下所有的白名单列表
            List<DmStfN> list = dmStfNMapper.selectWhiteList(unid, new BigDecimal(2));
            //站点与白名单库2绑定
            DmStnR dmStnR = dmStnRMapper.selectInfoByPrimaryKey(unid, deviceuuid, new BigDecimal(2));
            if (dmStnR == null) {
                dmStnR = new DmStnR();
                dmStnR.setUnid(unid);
                dmStnR.setDeviceuuid(deviceuuid);
                dmStnR.setPersontype(new BigDecimal(2));
                dmStnR.setBindtime(time);
                dmStnR.setNamelistfacenum(new BigDecimal(list.size()));
                dmStnRMapper.insert(dmStnR);
            } else {
                dmStnR = new DmStnR();
                dmStnR.setUnid(unid);
                dmStnR.setDeviceuuid(deviceuuid);
                dmStnR.setPersontype(new BigDecimal(2));
                dmStnR.setNamelistfacenum(new BigDecimal(list.size()));
                dmStnRMapper.updateByPrimaryKey(dmStnR);
            }
            //白名单入库
            for (DmStfN d : list) {
                //人员入库 并下发盒子
                personSetService.addPerson(deviceuuid, d.getUuid(), d.getPersontype().intValue(), d.getLimittime().intValue(), d.getSttm(), d.getEndtime(), d.getUnid());
            }
            //同步盒子
            nameListManageService.getNamelistInfo(deviceuuid);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public CodeObject unbind(String unid, String deviceuuid) {
        try {
            //删除盒子与站点的绑定关系
            int a = dmStRMapper.deleteByPrimaryKey(unid, deviceuuid);
            if (a > 0) {
                //删除成功
                //查询站点下所有的白名单列表
                List<DmStfN> list = dmStfNMapper.selectWhiteList(unid, new BigDecimal(2));
                //站点与白名单库2解绑
                dmStnRMapper.deleteByPrimaryKey(unid, deviceuuid, new BigDecimal(2));

                if (delpool != null) {
                    delpool.submit(new Runnable() {
                        @Override
                        public void run() {
                            //盒子库中 该站点的白名单清除 数据库关系清除
                            personSetService.dePersonListByWhiteList(deviceuuid, list, 2, unid);
                            //同步盒子
                            nameListManageService.getNamelistInfo(deviceuuid);
                        }
                    });
                }

                return ReturnCode.TB005;
            } else {
                return ReturnCode.NULL_OBJECT;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;
        }

    }

}

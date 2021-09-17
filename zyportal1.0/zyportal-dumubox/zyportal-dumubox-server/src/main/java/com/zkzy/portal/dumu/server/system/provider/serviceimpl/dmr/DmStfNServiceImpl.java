package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dmr;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmFaceBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmStationBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfNMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStnRMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;
import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;
import com.zkzy.zyportal.system.api.entity.dm.DmStationB;
import com.zkzy.zyportal.system.api.entity.dmr.*;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
import com.zkzy.zyportal.system.api.service.dmr.DmStfNService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/21.
 */
@Service
public class DmStfNServiceImpl implements DmStfNService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmStfNServiceImpl.class);

    @Autowired
    private DmStfNMapper dmStfNMapper;

    @Autowired
    private DmStationBMapper dmStationBMapper;

    @Autowired
    private DmFaceBMapper dmFaceBMapper;

    @Autowired
    private DmStRMapper dmStRMapper;

    @Autowired
    private PersonSetService personSetService;

    @Autowired
    private DmStfNService dmStfNService;

    @Autowired
    private DmStfRMapper dmStfRMapper;


    @Autowired
    private NameListManageService nameListManageService;

    @Autowired
    private DmStnRMapper dmStnRMapper;


    @Value("${dumu.face}")
    private String facePath;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String BASE_NAME = "base";

    private static final ExecutorService addpool = Executors.newFixedThreadPool(3);

    private static final ExecutorService delpool = Executors.newFixedThreadPool(3);


    @Override

    public CodeObject importWhiteList(String unid, String userids, Integer LimitTime, String sttm, String endtime) {
        if (addpool != null) {
            addpool.submit(new Runnable() {
                @Override
                public void run() {
                    String[] userid = userids.split(",");
                    for (int i = 0; i < userid.length; i++) {
                        dmStfNService.importPeople(unid, userid[i], LimitTime, sttm, endtime);
                    }

                    //查询站点下所有的白名单列表
                    int num = dmStfNMapper.selectWhiteNum(unid, new BigDecimal(2));
                    DmStfR dmStfR = null;

                    while (true) {
                        dmStfR = dmStfRMapper.selectByPrimaryKey(unid, new BigDecimal(2));
                        if (dmStfR != null) {
                            dmStfR.setNum(new BigDecimal(num));
                            int a = dmStfRMapper.updateByPrimaryKey(dmStfR);
                            if (a > 0) {
                                dmStfR.setLgs(uuid());
                                dmStfRMapper.updateLgs(dmStfR);
                                LOGGER.info("导入完成");
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    String time = getDateTime();

                    //查询出 该站点绑定的所有盒子
                    List<DmStR> list = dmStRMapper.selectBoxesByUnid(unid);
                    for (DmStR d : list) {
                        //同步盒子
                        nameListManageService.getNamelistInfo(d.getDeviceuuid());
                        //站点与白名单库2绑定
                        DmStnR dmStnR = dmStnRMapper.selectInfoByPrimaryKey(unid, d.getDeviceuuid(), new BigDecimal(2));
                        if (dmStnR == null) {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setBindtime(time);
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.insert(dmStnR);
                        } else {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.updateByPrimaryKey(dmStnR);
                        }
                    }

                }
            });
        }
        return ReturnCode.TB002;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importPeople(String unid, String userid, Integer LimitTime, String sttm, String endtime) {
        try {
            //查询站点id 与 人员 id 是否有效
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmFaceB dmFaceB = dmFaceBMapper.selectByPrimaryKey(userid);
            if (dmStationB != null && dmFaceB != null) {
                String time = getDateTime();
                //导入进站点名单库
                DmStfN dmStfN = new DmStfN();
                dmStfN.setUnid(unid);
                dmStfN.setPersontype(new BigDecimal(2));
                dmStfN.setDatatime(time);
                dmStfN.setLimittime(LimitTime.longValue());
                dmStfN.setSttm(sttm);
                dmStfN.setEndtime(endtime);
                dmStfN.setUuid(userid);
                int a = dmStfNMapper.updateByPrimaryKey(dmStfN);
                if (a == 0) {
                    dmStfNMapper.insert(dmStfN);
                }
                //查询出 该站点绑定的所有盒子
                List<DmStR> list = dmStRMapper.selectBoxesByUnid(unid);
                //将选中的人员入库 并下发 到盒子
                for (DmStR d : list) {
                    personSetService.addPerson(d.getDeviceuuid(), userid, 2, LimitTime, sttm, endtime, unid);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public CodeObject delWhiteList(String unid, String userids) {

        if (delpool != null) {
            delpool.submit(new Runnable() {
                @Override
                public void run() {
                    String[] userid = userids.split(",");
                    for (int i = 0; i < userid.length; i++) {
                        dmStfNService.delPeople(unid, userid[i]);
                    }

                    //查询站点下所有的白名单列表
                    int num = dmStfNMapper.selectWhiteNum(unid, new BigDecimal(2));
                    DmStfR dmStfR = null;
                    while (true) {
                        dmStfR = dmStfRMapper.selectByPrimaryKey(unid, new BigDecimal(2));
                        if (dmStfR != null) {
                            dmStfR.setNum(new BigDecimal(num));
                            int a = dmStfRMapper.updateByPrimaryKey(dmStfR);
                            if (a > 0) {
                                dmStfR.setLgs(uuid());
                                dmStfRMapper.updateLgs(dmStfR);
                                LOGGER.info("删除完成");
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    //查询出 该站点绑定的所有盒子
                    List<DmStR> list = dmStRMapper.selectBoxesByUnid(unid);
                    String time = getDateTime();
                    //下发并且入库
                    for (DmStR d : list) {
                        personSetService.dePersonList(d.getDeviceuuid(), userids, 2, unid);
                        //同步盒子
                        nameListManageService.getNamelistInfo(d.getDeviceuuid());

                        //站点与白名单库2绑定
                        DmStnR dmStnR = dmStnRMapper.selectInfoByPrimaryKey(unid, d.getDeviceuuid(), new BigDecimal(2));
                        if (dmStnR == null) {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setBindtime(time);
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.insert(dmStnR);
                        } else {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.updateByPrimaryKey(dmStnR);
                        }

                    }
                }
            });
        }
        return ReturnCode.TB003;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delPeople(String unid, String userid) {
        try {
            //查询站点id 与 人员 id 是否有效
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmFaceB dmFaceB = dmFaceBMapper.selectByPrimaryKey(userid);
            if (dmStationB != null && dmFaceB != null) {
                //删除站点名单库人员
                dmStfNMapper.deleteByPrimaryKey(unid, new BigDecimal(2), userid);
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public PageInfo getWhiteList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmWhiteList> list = dmStfNMapper.selectAll(param);
            String path = null;
            for (DmWhiteList a : list) {
                path = facePath + "/" + a.getUuid() + ".jpg";
                a.setStLabelstr(this.getStr(a.getStLabel()));
                a.setImgurl(Base64Util.imageToBase64Str(path));
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    public String getStr(String codes) {
        if (StringUtils.isNoneEmpty(codes)) {
            String codeStr = "";
            String[] code = codes.split(",");
            for (int i = 0; i < code.length; i++) {
                String codemyid = BASE_NAME + code[i];
                String str = redisTemplate.opsForValue().get(codemyid);
                if (i == 0 && StringUtils.isNoneEmpty(str)) {
                    codeStr += str;
                } else if (StringUtils.isNoneEmpty(str)) {
                    codeStr += "," + str;
                }

            }
            return codeStr;
        } else {
            return "";
        }
    }
}

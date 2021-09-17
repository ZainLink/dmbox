package com.zkzy.portal.dumu.server.system.provider.serviceimpl.Manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.FileUtil;
import com.zkzy.portal.common.utils.RandomHelper;
import com.zkzy.portal.dumu.client.common.constant.Message;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmNameBMapper;
import com.zkzy.portal.dumu.server.system.provider.serviceimpl.register.RegisterServiceImpl;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.*;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import com.zkzy.zyportal.system.api.util.DmHttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
@Service
public class NameListManageServiceImpl implements NameListManageService {


    private static final Logger LOGGER = LoggerFactory.getLogger(NameListManageServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 度目缓存前缀
     */
    public static final String REDIS_PREFIX_DUMU = "dumu:";


    /**
     * 度目实时信息前缀
     */

    public static final String REDIS_PREFIX_DUMU2 = "dm-";


    public static final String REDIS_PREFIX_IP = "ip-";

    public static final String REDIS_PREFIX_NAME = "dname-";


    @Autowired
    private DmNameBMapper dmNameBMapper;

    @Autowired
    private DmBoxBMapper dmBoxBMapper;

    private static final ExecutorService pool = Executors.newFixedThreadPool(2);


    public SSDeviceInfo getDevicedSSinfo(String uuid) throws Exception {
        if (redisTemplate.hasKey(REDIS_PREFIX_DUMU + uuid) && redisTemplate.hasKey(REDIS_PREFIX_DUMU2 + uuid)) {
            String jsonStr = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU2 + uuid);
            String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + uuid);
            SSDeviceInfo ssDeviceInfo = JSON.parseObject(jsonStr, SSDeviceInfo.class);
            ssDeviceInfo.setSession(value);
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
    public CodeObject getNamelistInfo(String uuid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(uuid);
            if (ssDeviceInfo != null) {
                NameListManage nameListManage = new NameListManage();
                nameListManage.setSession(ssDeviceInfo.getSession());
                nameListManage.setUUID(ssDeviceInfo.getDeviceUUID());
                nameListManage.getData().setAction("GetNamelistInfo");//获取列表
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(nameListManage).toString());
                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                if (StringUtils.isNoneEmpty(result)) {
                    NameListManageResponse nameLists = JSON.parseObject(result, NameListManageResponse.class);
                    List<NameLists> lists = nameLists.getData().getNameLists();
                    dmNameBMapper.deleteById(ssDeviceInfo.getDeviceUUID());
                                try {
                                    DmNameB dmNameB = null;
                                    String time = getDateTime();
                                    for (NameLists n : lists) {
                                        dmNameB = new DmNameB();
                                        dmNameB.setDeviceuuid(ssDeviceInfo.getDeviceUUID());
                                        dmNameB.setNamelistfacenum(new BigDecimal(n.getNameListFaceNum()));
                                        dmNameB.setPersontype(new BigDecimal(n.getPersonType()));
                                        dmNameB.setUpdatetime(time);
                                        dmNameB.setName(n.getName());
                                        switch (n.getPersonType()) {
                                            case 2://白名单
                                                dmNameBMapper.insert(dmNameB);
                                                break;
                                            default://默认
                                                break;
                                        }

                                    }
                                } catch (Exception e) {
                                    LOGGER.error(e.getMessage());
                                }
                }
                return ReturnCode.TB001;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ReturnCode.UNKNOWN_ERROR;
        }


    }


    public void getNamelistInfo2(String uuid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(uuid);
            if (ssDeviceInfo != null) {

                if (pool != null) {
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                NameListManage nameListManage = new NameListManage();
                                nameListManage.setSession(ssDeviceInfo.getSession());
                                nameListManage.setUUID(ssDeviceInfo.getDeviceUUID());
                                nameListManage.getData().setAction("GetNamelistInfo");//获取列表
                                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(nameListManage).toString());
                                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                                if (StringUtils.isNoneEmpty(result)) {
                                    NameListManageResponse nameLists = JSON.parseObject(result, NameListManageResponse.class);
                                    List<NameLists> lists = nameLists.getData().getNameLists();
                                    dmNameBMapper.deleteById(ssDeviceInfo.getDeviceUUID());
                                    DmNameB dmNameB = null;
                                    String time = getDateTime();
                                    for (NameLists n : lists) {
                                        dmNameB = new DmNameB();
                                        dmNameB.setDeviceuuid(ssDeviceInfo.getDeviceUUID());
                                        dmNameB.setNamelistfacenum(new BigDecimal(n.getNameListFaceNum()));
                                        dmNameB.setPersontype(new BigDecimal(n.getPersonType()));
                                        dmNameB.setUpdatetime(time);
                                        dmNameB.setName(n.getName());
                                        switch (n.getPersonType()) {
                                            case 2://白名单
                                                dmNameBMapper.insert(dmNameB);
                                                break;
                                            default://默认
                                                break;
                                        }

                                    }
                                }
                            } catch (Exception e) {
                                LOGGER.error(e.getMessage());
                            }

                        }
                    });
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }


    }


    @Override
    public String addNameList(String uuid, String name, String unid, Integer type) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(uuid);
            if (ssDeviceInfo != null) {
                //盒子库新增
                String nameId = RandomHelper.getRandNum(10);
                NameListManage nameListManage = new NameListManage();
                nameListManage.setSession(ssDeviceInfo.getSession());
                nameListManage.setUUID(ssDeviceInfo.getDeviceUUID());
                nameListManage.getData().setAction("AddNameList");//添加名单库
                nameListManage.getData().setName(nameId);//人脸库名称
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(nameListManage).toString());
                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                String str = net.sf.json.JSONObject.fromObject(result).getString("Code");
                //新增成功
                if ("1".equals(str)) {
                    //将人脸库对应的id 缓存入reids
                    NameRedis nameRedis = new NameRedis();
                    nameRedis.setName(name);
                    nameRedis.setType(type);
                    String jsonStr = JSONObject.toJSON(nameRedis).toString();
                    redisTemplate.opsForValue().set(REDIS_PREFIX_NAME + nameId, jsonStr);
                    this.getNamelistInfo2(uuid);
                }
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteNameList(String uuid, Integer personType) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(uuid);
            if (ssDeviceInfo != null) {
                NameListManage nameListManage = new NameListManage();
                nameListManage.setSession(ssDeviceInfo.getSession());
                nameListManage.setUUID(ssDeviceInfo.getDeviceUUID());
                nameListManage.getData().setAction("DeleteNameList");//删除名单库
                nameListManage.getData().setPersonType(personType);//人脸库id
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(nameListManage).toString());
                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                String str = net.sf.json.JSONObject.fromObject(result).getString("Code");
                if ("1".equals(str)) {

                    this.getNamelistInfo2(uuid);
                }
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String modifyNameList(String uuid, Integer personType, String name) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(uuid);
            if (ssDeviceInfo != null) {
                NameListManage nameListManage = new NameListManage();
                nameListManage.setSession(ssDeviceInfo.getSession());
                nameListManage.setUUID(ssDeviceInfo.getDeviceUUID());
                nameListManage.getData().setAction("ModifyNameList");//修改名单库
                nameListManage.getData().setPersonType(personType);//人脸库id
                nameListManage.getData().setName(name);//人脸库名称
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(nameListManage).toString());
                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                String str = net.sf.json.JSONObject.fromObject(result).getString("Code");
                if ("1".equals(str)) {
                    this.getNamelistInfo2(uuid);
                }
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String cleanNameList(String uuid, Integer personType) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(uuid);
            if (ssDeviceInfo != null) {
                NameListManage nameListManage = new NameListManage();
                nameListManage.setSession(ssDeviceInfo.getSession());
                nameListManage.setUUID(ssDeviceInfo.getDeviceUUID());
                nameListManage.getData().setAction("CleanNameList");//清空名单库
                nameListManage.getData().setPersonType(personType);//人脸库id
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(nameListManage).toString());
                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                String str = net.sf.json.JSONObject.fromObject(result).getString("Code");
                if ("1".equals(str)) {
                    this.getNamelistInfo2(uuid);
                }
                return result;
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PageInfo getNamelistInfos(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmNameB> list = dmNameBMapper.selectAll(param);
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    public static void main(String[] args) {

    }
}

package com.zkzy.portal.dumu.server.system.provider.serviceimpl.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.redis.RedisRepository;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmCameraBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmStationBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStnRMapper;
import com.zkzy.zyportal.system.api.entity.base.SystemCode;
import com.zkzy.zyportal.system.api.entity.base.SystemOrganization;
import com.zkzy.zyportal.system.api.entity.base.SystemParameter;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;
import com.zkzy.zyportal.system.api.entity.dm.DmStationB;
import com.zkzy.zyportal.system.api.entity.dmr.DmStnR;
import com.zkzy.zyportal.system.api.service.base.RedisService;
import com.zkzy.zyportal.system.api.service.base.SystemCodeService;
import com.zkzy.zyportal.system.api.service.base.SystemOrganizationService;
import com.zkzy.zyportal.system.api.service.base.SystemParameterService;
import com.zkzy.zyportal.system.api.viewModel.CacheSystemCodeChild;
import com.zkzy.zyportal.system.api.viewModel.CacheSystemCodeCur;
import com.zkzy.zyportal.system.api.viewModel.Json;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2017/4/17 0017.
 */


//@Component
@Service("redisService")
@Transactional(readOnly = true)
@Order(value = 1)
public class RedisServiceImpl implements RedisService, CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);

    public static final String BASE_NAME = "base";
    public static final String BASE_DEP_NAME = "dep_base";
    public static final String BASE_NAME_CODEID = "codeid_base";
    public static final String REDIS_SYSTEMCODE = "redis_systemcode:";

    @Autowired
    private RedisRepository redisRepository;


    //    @Autowired
//    private RedisUtil redisUtil
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private SystemCodeService systemCodeService;
//    @Autowired
//    private SystemParameterService systemParameterService;
//    @Autowired
//    private SystemOrganizationService systemOrganizationService;

    @Autowired
    private DmBoxBMapper dmBoxBMapper;

    public static final String REDIS_PREFIX_IP = "ip-";


    public static final String ST_ID = "st";

    @Autowired
    private DmCameraBMapper dmCameraBMapper;

    @Autowired
    private DmStationBMapper dmStationBMapper;


    @Override
    public void run(String... args) throws Exception {
        LOGGER.info(">>>>>>>>>>>>>>>服务启动执行，执行加载Redis数据操作开始<<<<<<<<<<<<<");
//        //操作数据字典
//        try {
//            LOGGER.info("操作数据字典,数据缓存进redis开始");
//            String param = " and status='A' ";
//            List<SystemCode> systemCodeLists = systemCodeService.selectAll(param);
//            List<SystemCode> childList = null;
//            for (SystemCode systemCode : systemCodeLists) {
//                String codeMyid = systemCode.getCodeMyid();
//                param = " and PARENT_ID in (select code_id from system_code "
//                        + " where CODE_MYID='" + codeMyid + "' and status='A' )  and status='A' order by SORT asc ";
//                childList = systemCodeService.selectAll(param);
//                /**存入自己的值到redis*/
//                redisTemplate.opsForValue().set(BASE_NAME + codeMyid, systemCode.getName());
//                redisTemplate.opsForValue().set(BASE_NAME_CODEID + codeMyid, String.valueOf(systemCode.getCodeId()));
//                /**
//                 * s是否有子元素
//                 * 如果存在子元素则存入缓存*/
//                CacheSystemCodeCur cacheSystemCodeCur = new CacheSystemCodeCur();
//                cacheSystemCodeCur.setCurCodeId(systemCode.getCodeId());
//                cacheSystemCodeCur.setCurCodeMyid(systemCode.getCodeMyid());
//                cacheSystemCodeCur.setCurName(systemCode.getName());
//
//                if (childList != null && childList.size() > 0) {
//                    Json json = new Json();
//                    json.setStatus(true);
//                    json.setSystemCodeList(childList);
//                    String jsonStr = JSONObject.toJSONString(json);
//                    redisTemplate.opsForValue().set(codeMyid, jsonStr);
//
//                    cacheSystemCodeCur.setHasChild(true);
//                    List<CacheSystemCodeChild> cacheChild = new ArrayList<>();
//                    for (SystemCode childCode : childList) {
//                        CacheSystemCodeChild cacheSystemCodeChild = new CacheSystemCodeChild();
//                        BeanUtils.copyProperties(cacheSystemCodeChild, childCode);
//                        cacheChild.add(cacheSystemCodeChild);
//                    }
//                    cacheSystemCodeCur.setCurChild(cacheChild);
//                    redisTemplate.opsForValue().set(REDIS_SYSTEMCODE + codeMyid, JSONObject.toJSONString(cacheSystemCodeCur));
//                } else {
//                    cacheSystemCodeCur.setHasChild(false);
//                    redisTemplate.opsForValue().set(REDIS_SYSTEMCODE + codeMyid, JSONObject.toJSONString(cacheSystemCodeCur));
//                }
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        } finally {
//            LOGGER.info("操作数据字典,数据缓存进redis结束");
//        }
//        //操作系统参数
//        try {
//            LOGGER.info("操作系统参数,数据缓存进redis开始");
//            String param = " and myid is not null and STATE='Y' AND STATUS='A' ";
//            List<SystemParameter> list = systemParameterService.selectAll(param);
//            for (SystemParameter parameter : list) {
//                String codeMyid = parameter.getMyid();
//                Json json = new Json();
//                json.setStatus(true);
//                json.setParameter(parameter);
//                String jsonStr = JSONObject.toJSONString(json);
//                /**存入自己的值到redis*/
//                redisTemplate.opsForValue().set(BASE_NAME + codeMyid, jsonStr);
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        } finally {
//            LOGGER.info("操作系统参数,数据缓存进redis结束");
//        }
//        //操作组织表
//        try {
//            LOGGER.info("操作组织表,数据缓存进redis开始");
//            String param = " and status='A' and myid is not null ";
//            List<SystemOrganization> list = systemOrganizationService.selectAll(param);
//            List<SystemOrganization> childList = null;
//            for (SystemOrganization o : list) {
//                String codeMyid = o.getMyid();
////                param=" and status='A' and myid = '"+codeMyid+"'";
//                childList = systemOrganizationService.queryOrganizationchildListByMyId(codeMyid);
//                /**存入自己的值到redis*/
//                redisTemplate.opsForValue().set(BASE_NAME + codeMyid, o.getFullName());
//                /**如果存在子元素则存入缓存*/
//                if (childList != null && childList.size() > 0) {
//                    Json json = new Json();
//                    json.setStatus(true);
//                    json.setOrganizationList(childList);
//                    String jsonStr = JSONObject.toJSONString(json);
//                    redisTemplate.opsForValue().set(codeMyid, jsonStr);
//                }
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        } finally {
//            LOGGER.info("操作组织表,数据缓存进redis结束");
//        }
        try {
            //盒子写入缓存
            List<DmBoxB> list = dmBoxBMapper.selectAll("");
            for (DmBoxB d : list) {
                redisTemplate.opsForValue().set(BASE_NAME + d.getDeviceuuid(), d.getName());
                redisTemplate.opsForValue().set(REDIS_PREFIX_IP + d.getDeviceuuid(), d.getDeviceip());
                LOGGER.info("盒子" + d.getDeviceuuid() + "写入缓存");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            LOGGER.info("操作盒子表,数据缓存进redis结束");
        }

        try {
            //摄像头写入缓存
            List<DmCameraB> clist = dmCameraBMapper.selectAll(" AND SUBDEVICEIP IS NOT NULL ");
            for (DmCameraB b : clist) {
                redisTemplate.opsForValue().set(ST_ID + b.getDeviceuuid() + "_" + b.getChannelno(), b.getUnid());
                redisTemplate.opsForValue().set(b.getDeviceuuid() + "_" + b.getChannelno(), b.getSubname());
                LOGGER.info("摄像头" + b.getSubname() + "写入缓存");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            LOGGER.info("操作摄像头表,数据缓存进redis结束");
        }
        try {
            //站点写入缓存
            List<DmStationB> zlist = dmStationBMapper.selectAll("");
            for (DmStationB a : zlist) {
                redisTemplate.opsForValue().set(BASE_NAME + a.getUnid(), a.getUname());
                LOGGER.info("站点" + a.getUname() + "写入缓存");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            LOGGER.info("操作站点表,数据缓存进redis结束");
        }

        LOGGER.info(">>>>>>>>>>>>>>>服务启动执行，执行加载Redis数据操作结束<<<<<<<<<<<<<");

    }

    /**
     * 根据code取出对应的系统参数值
     *
     * @param code
     * @return
     */
    @Override
    public String getParameterValueByCode(String code) throws Exception {
        /**从redis中取出数据*/
        String redisStr = "";
        String jsonStr = redisTemplate.opsForValue().get(this.BASE_NAME + code);
        Json json = JSONObject.parseObject(jsonStr, Json.class);
        if (json != null) {
            SystemParameter pp = json.getParameter();
            if (pp != null) {
                redisStr = pp.getValue();
            }
        }
        return redisStr;
    }

    /**
     * 根据code取出对应的子元素数据字典集合
     *
     * @param code
     * @return
     */
    @Override
    public String getSystemCodeListByCode(String code) throws Exception {
        StringBuilder sb = new StringBuilder();
        /**从redis中取出数据*/
        String jsonStr = redisTemplate.opsForValue().get(code);
        Json json = JSONObject.parseObject(jsonStr, Json.class);
        if (json != null) {
            List<SystemCode> list = json.getSystemCodeList();
            if (list != null && list.size() > 0) {
                for (SystemCode systemCode : list) {
                    sb.append("<option value=\"" + systemCode.getCodeMyid() + "\">" + systemCode.getName() + "</option>");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据code取出对应的数据字典名称
     *
     * @param code
     * @return
     */
    @Override
    public String getSystemCodeNameByCode(String code) throws Exception {
        String jsonStr = "";
        /**从redis中取出数据*/
        jsonStr = redisTemplate.opsForValue().get(this.BASE_NAME + code);
        return jsonStr;
    }

    @Override
    public String getSystemCodeIdByCode(String code) {
        String jsonStr = "";
        /**从redis中取出数据*/
        jsonStr = redisTemplate.opsForValue().get(this.BASE_NAME_CODEID + code);
        return jsonStr;
    }

    /**
     * 根据code取出对应的子元素组织机构集合
     *
     * @param code
     * @return
     */
    @Override
    public String getOrganizationListByCode(String code) throws Exception {
        StringBuilder sb = new StringBuilder();
        /**从redis中取出数据*/
        String jsonStr = redisTemplate.opsForValue().get(code);
        Json json = JSONObject.parseObject(jsonStr, Json.class);
        if (json != null) {
            List<SystemOrganization> list = json.getOrganizationList();
            if (list != null && list.size() > 0) {
                for (SystemOrganization o : list) {
                    sb.append("<option value=\"" + o.getMyid() + "\">" + o.getFullName() + "</option>");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 根据code取出对应的子元素组织机构集合LIST
     *
     * @param code
     * @return
     */
    @Override
    public List<SystemOrganization> getOrganizationListBeanByCode(String code) throws Exception {
        List<SystemOrganization> list = null;
        String jsonStr = redisTemplate.opsForValue().get(code);
        Json json = JSONObject.parseObject(jsonStr, Json.class);
        if (json != null) {
            list = json.getOrganizationList();
        }
        return list;
    }

    /**
     * 根据code取出对应的组织机构名称
     *
     * @param code
     * @return
     */
    @Override
    public String getOrganizationNameByCode(String code) throws Exception {
        String redisStr = "";
        /**从redis中取出数据*/
        String jsonStr = redisTemplate.opsForValue().get(this.BASE_NAME + code);
        if (jsonStr != null) {
            redisStr = jsonStr;
        }
        return redisStr;
    }


    @Override
    public Map<String, CacheSystemCodeCur> getCacheSystemCode() {
        Map<String, CacheSystemCodeCur> returnMap = new HashMap<>();
        String preKey = REDIS_SYSTEMCODE + "*";
        Set<String> keySet = redisTemplate.keys(preKey);
        if (keySet != null && keySet.size() > 0) {
            for (String key : keySet) {
                String jsonStr = redisRepository.get(key);
                if (jsonStr != null && !"".equals(jsonStr) && jsonStr.length() > 1) {
                    jsonStr = jsonStr.substring(1, jsonStr.length() - 1);//去掉多余的一堆引号
                    jsonStr = StringEscapeUtils.unescapeJson(jsonStr);
                    CacheSystemCodeCur cacheSystemCodeCur = JSON.parseObject(jsonStr, CacheSystemCodeCur.class);
                    if (cacheSystemCodeCur.getCurCodeMyid() != null) {
                        returnMap.put(cacheSystemCodeCur.getCurCodeMyid(), cacheSystemCodeCur);
                    }
                }
            }
        }
        return returnMap;
    }

    /**
     * 获取在线的用户信息List
     *
     * @param param
     * @return
     */
    @Override
    public String getOnlineUserList(String param) {
        //List<UserDetails> usrDetailsList=new ArrayList<UserDetails>();
//        String key = AbstractTokenUtil.REDIS_PREFIX_USER+param;
//        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//
//        byte[] keys = serializer.serialize(key);
//
//        byte[] values = connection.get(keys);
//        Object o =serializer.deserialize(values);
//
////        byte[] str=redisRepository.get(keys);
////        String jsonStr=redisTemplate.opsForValue().get(key);

//        String key = AbstractTokenUtil.REDIS_PREFIX_USER+param;
//        String str =redisRepository.get("user-details:root");

//        redisTemplate.opsForList().leftPop(param);

//        String str =redisTemplate.opsForList().leftPop("user-details*");

        return null;
    }


}



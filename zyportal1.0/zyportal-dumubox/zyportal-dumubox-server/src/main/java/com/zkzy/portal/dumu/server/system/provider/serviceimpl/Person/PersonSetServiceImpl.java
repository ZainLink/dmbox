package com.zkzy.portal.dumu.server.system.provider.serviceimpl.Person;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.common.utils.DateUtils;
import com.zkzy.portal.common.utils.MD5Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmFaceBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStnfRMapper;

import com.zkzy.zyportal.system.api.entity.dm.*;
import com.zkzy.zyportal.system.api.entity.dmr.DmStfN;
import com.zkzy.zyportal.system.api.entity.dmr.DmStnfR;
import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
import com.zkzy.zyportal.system.api.util.DmHttpUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
@Service
public class PersonSetServiceImpl implements PersonSetService {


    private static final Logger LOGGER = LoggerFactory.getLogger(PersonSetServiceImpl.class);

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

    @Autowired
    private DmFaceBMapper faceBMapper;

    @Autowired
    private DmStnfRMapper dmStnfRMapper;

    @Autowired
    private DmBoxBMapper dmBoxBMapper;


    @Value("${dumu.face}")
    private String facePath;

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
    @Transactional(rollbackFor = Exception.class)
    public String addPerson(String deviceUUid, String userId, Integer personType, Integer LimitTime, String sttm, String endtime, String unid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceUUid);
            if (ssDeviceInfo != null) {
                DmFaceB dmFaceB = faceBMapper.selectByPrimaryKey(userId);
                PersonListRequest personListRequest = new PersonListRequest();
                personListRequest.setUUID(deviceUUid);
                personListRequest.setSession(ssDeviceInfo.getSession());
                personListRequest.getData().setAction("addPerson"); //库添加人员
                personListRequest.getData().setPersonType(personType);
                personListRequest.getData().getPersonInfo().setPersonCover(1); //覆盖原来信息
                personListRequest.getData().getPersonInfo().setPersonId(dmFaceB.getUuid());
                personListRequest.getData().getPersonInfo().setPersonName(dmFaceB.getName());
                personListRequest.getData().getPersonInfo().setSex(Integer.valueOf(dmFaceB.getSex()));
                personListRequest.getData().getPersonInfo().setIDCard(dmFaceB.getId());
                personListRequest.getData().getPersonInfo().setNation(dmFaceB.getNation());
                personListRequest.getData().getPersonInfo().setBirthday(dmFaceB.getBirth());
                personListRequest.getData().getPersonInfo().setPhone(dmFaceB.getTel());
                personListRequest.getData().getPersonInfo().setAddress(dmFaceB.getAddress());
                if (LimitTime == 0) {
                    personListRequest.getData().getPersonInfo().setLimitTime(0);
                } else {
                    personListRequest.getData().getPersonInfo().setLimitTime(1);
                    personListRequest.getData().getPersonInfo().setStartTime(sttm);
                    personListRequest.getData().getPersonInfo().setEndTime(endtime);
                }
                String path = facePath + "/" + dmFaceB.getUuid() + ".jpg";
                personListRequest.getData().getPersonInfo().setPersonPhoto(Base64Util.imageToBase64Str2(path));
                JSONObject obj = JSONObject.parseObject(JSONObject.toJSON(personListRequest).toString());
                String result = DmHttpUtil.postJsonData(obj, ssDeviceInfo.getDeviceIP());
                String str = net.sf.json.JSONObject.fromObject(result).getString("Code");
                if ("1".equals(str)) {
                    String time = getDateTime();
                    DmStnfR dmStnfR = new DmStnfR();
                    dmStnfR.setDeviceuuid(deviceUUid);
                    dmStnfR.setPersontype(new BigDecimal(personType));
                    dmStnfR.setBindtime(time);
                    dmStnfR.setUnid(unid);
                    dmStnfR.setUuid(userId);
                    int a = dmStnfRMapper.updateByPrimaryKey(dmStnfR);
                    if (a <= 0) {
                        dmStnfRMapper.insert(dmStnfR);
                    }
                }
                return result;
            } else {
                return null;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delPerson(String deviceUUid, String userId, Integer personType, String unid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceUUid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "personListRequest");
                map.put("UUID", deviceUUid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "deletePerson");
                child.put("PersonType", personType);
                child.put("PersonId", userId);
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                System.out.println(json.toJSONString());
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = net.sf.json.JSONObject.fromObject(result).getString("Code");
                if ("1".equals(str)) {
                    int a = dmStnfRMapper.deleteByPrimaryKey(unid, deviceUUid, new BigDecimal(personType), userId);
                }
                return result;
            } else {
                return null;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String dePersonList(String deviceUUid, String userIds, Integer personType, String unid) {
        String result = null;
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceUUid);
            if (ssDeviceInfo != null) {
                String[] idlist = userIds.split(",");
                List<PersonId> list = new ArrayList<>();
                PersonId id = null;
                DmStnfR dmStnfR = null;
                for (int i = 0; i < idlist.length; i++) {
                    dmStnfR = dmStnfRMapper.selectByPrimaryKey(unid, deviceUUid, new BigDecimal(personType), idlist[i]);

                    //查询本站点的数据
                    dmStnfR = dmStnfRMapper.selectByPrimaryKey(unid, deviceUUid, new BigDecimal(personType), idlist[i]);
                    //查询非本站点的但是 是本盒子 本人的数据
                    List<DmStnfR> dmlist=dmStnfRMapper.selectListByParam(unid, deviceUUid, new BigDecimal(personType), idlist[i]);
                    if (dmStnfR != null) {
                        id = new PersonId();
                        id.setPersonId(dmStnfR.getUuid());
                        if(dmlist.size()==0){
                            list.add(id);
                        }

                        dmStnfRMapper.deleteByPrimaryKey(unid, deviceUUid, new BigDecimal(personType), idlist[i]);
                    }

                }
                if (list.size() > 0) {
                    Map<String, Object> map = new HashedMap();
                    map.put("Name", "personListRequest");
                    map.put("UUID", deviceUUid);
                    map.put("Session", ssDeviceInfo.getSession());
                    Map<String, Object> child = new HashedMap();
                    child.put("Action", "deletePersonListPart");
                    child.put("PersonType", personType);
                    JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                    child.put("PersonInfoList", array);
                    map.put("Data", child);
                    JSONObject json = new JSONObject(map);
                    System.out.println(json.toJSONString());
                    result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                }
                return result;
            } else {
                return result;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return result;
        }
    }

    @Override
    public void dePersonListByWhiteList(String deviceUUid, List<DmStfN> lists, Integer personType, String unid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceUUid);
            if (ssDeviceInfo != null) {

                List<PersonId> list = new ArrayList<>();
                PersonId id = null;
                DmStnfR dmStnfR = null;
                for (int i = 0; i < lists.size(); i++) {
                    //查询本站点的数据
                    dmStnfR = dmStnfRMapper.selectByPrimaryKey(unid, deviceUUid, new BigDecimal(personType), lists.get(i).getUuid());
                    //查询非本站点的但是 是本盒子 本人的数据
                    List<DmStnfR> dmlist=dmStnfRMapper.selectListByParam(unid, deviceUUid, new BigDecimal(personType), lists.get(i).getUuid());
                    if (dmStnfR != null) {
                        id = new PersonId();
                        id.setPersonId(dmStnfR.getUuid());
                        if(dmlist.size()==0){
                            list.add(id);
                        }

                        dmStnfRMapper.deleteByPrimaryKey(unid, deviceUUid, new BigDecimal(personType), lists.get(i).getUuid());
                    }

                }
                if (list.size() > 0) {
                    Map<String, Object> map = new HashedMap();
                    map.put("Name", "personListRequest");
                    map.put("UUID", deviceUUid);
                    map.put("Session", ssDeviceInfo.getSession());
                    Map<String, Object> child = new HashedMap();
                    child.put("Action", "deletePersonListPart");
                    child.put("PersonType", personType);
                    JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                    child.put("PersonInfoList", array);
                    map.put("Data", child);
                    JSONObject json = new JSONObject(map);
                    System.out.println(json.toJSONString());
                    DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                }

            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }


    public static void main(String[] args) {
        //UUID
        String uuid = "4C00D1DPAU32388";
        //UserName
        String username = "admin";
        String password = "zhiyuan@123";
        //TimeStamp
        Long time = DateUtils.dateToUnixTimestamp();
        String str = uuid + ":" + username + ":" + password + ":" + time;
        System.out.println(str);
        String Sign = MD5Util.md5(str);
        System.out.println(Sign);
        String times = getDateTime();
        Map<String, Object> map = new LinkedMap();
        map.put("Name", "timeSynchronizationRequest");
        map.put("UUID", uuid);
        map.put("Session", "umph00s5g469_1618351007");
        map.put("TimeStamp", time);
        map.put("Sign", Sign);
        Map<String, Object> child = new LinkedMap();
        child.put("TimeMode", 1);
        child.put("LocalTime", times);
        map.put("Data", child);
        JSONObject json = new JSONObject(map);
        System.out.println(json);
        try {
            String result = DmHttpUtil.postJsonData(json, "http://192.168.6.155:595");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

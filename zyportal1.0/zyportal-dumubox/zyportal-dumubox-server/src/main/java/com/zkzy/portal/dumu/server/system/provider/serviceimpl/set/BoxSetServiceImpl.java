package com.zkzy.portal.dumu.server.system.provider.serviceimpl.set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.dumu.server.system.provider.serviceimpl.Person.PersonSetServiceImpl;
import com.zkzy.zyportal.system.api.entity.dm.SSDeviceInfo;
import com.zkzy.zyportal.system.api.entity.set.FaceCompareManage;
import com.zkzy.zyportal.system.api.entity.set.FaceParametersRequest;
import com.zkzy.zyportal.system.api.entity.set.HTTPParametersRequest;
import com.zkzy.zyportal.system.api.service.set.BoxSetService;
import com.zkzy.zyportal.system.api.util.DmHttpUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
@Service
public class BoxSetServiceImpl implements BoxSetService {

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


    public SSDeviceInfo getDevicedSSinfo(String uuid) throws Exception {
        if (redisTemplate.hasKey(REDIS_PREFIX_DUMU + uuid) && redisTemplate.hasKey(REDIS_PREFIX_DUMU2 + uuid)) {
            String jsonStr = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU2 + uuid);
            String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + uuid);
            SSDeviceInfo ssDeviceInfo = JSON.parseObject(jsonStr, SSDeviceInfo.class);
            ssDeviceInfo.setSession(value);
            if (redisTemplate.hasKey(REDIS_PREFIX_IP + uuid)) {
                ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
            }
            return ssDeviceInfo;
        } else {
            return null;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getFaceParametersRequest(String deviceuuid, Integer channelNo) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "getFaceParametersRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("ChannelNo", channelNo);
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return JSONObject.parseObject(result).getString("Data");
                } else {
                    return null;
                }
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
    public String setFaceParametersRequest(FaceParametersRequest faceParametersRequest) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(faceParametersRequest.getUUID());
            faceParametersRequest.setName("setFaceParametersRequest");
            faceParametersRequest.setSession(ssDeviceInfo.getSession());
            String jsonstring = JSON.toJSONString(faceParametersRequest);
            String result = DmHttpUtil.postJsonData(JSONObject.parseObject(jsonstring), ssDeviceInfo.getDeviceIP());
            String str = JSONObject.parseObject(result).getString("Code");
            if ("1".equals(str)) {
                String session = JSONObject.parseObject(result).getString("Session");
                if (StringUtils.isNoneEmpty(session)) {
                    JSONObject object = JSONObject.parseObject(result);
                    object.remove("Session");
                    result = object.toJSONString();  //核心session 不展示给前端
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
    public String getFaceCompareManage(String deviceuuid, Integer channelNo) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "FaceCompareManage");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "getFaceCompareParametersRequest");
                child.put("ChannelNo", channelNo);
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return JSONObject.parseObject(result).getString("Data");
                } else {
                    return null;
                }
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
    public String setFaceCompareManage(FaceCompareManage faceCompareManage) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(faceCompareManage.getUUID());
            faceCompareManage.setName("FaceCompareManage");
            faceCompareManage.setSession(ssDeviceInfo.getSession());
            faceCompareManage.getData().setAction("setFaceCompareParametersRequest");
            String jsonstring = JSON.toJSONString(faceCompareManage);
            System.out.println(jsonstring);
            String result = DmHttpUtil.postJsonData(JSONObject.parseObject(jsonstring), ssDeviceInfo.getDeviceIP());
            String str = JSONObject.parseObject(result).getString("Code");
            if ("1".equals(str)) {
                String session = JSONObject.parseObject(result).getString("Session");
                if (StringUtils.isNoneEmpty(session)) {
                    JSONObject object = JSONObject.parseObject(result);
                    object.remove("Session");
                    result = object.toJSONString();  //核心session 不展示给前端
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
    public String getHTTPParametersRequest(String deviceuuid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "getHTTPParametersRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return JSONObject.parseObject(result).getString("Data");
                } else {
                    return null;
                }
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
    public String setHTTPParametersRequest(HTTPParametersRequest httpParametersRequest) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(httpParametersRequest.getUUID());
            httpParametersRequest.setSession(ssDeviceInfo.getSession());
            String jsonstring = JSON.toJSONString(httpParametersRequest);
            System.out.println(jsonstring);
            String result = DmHttpUtil.postJsonData(JSONObject.parseObject(jsonstring), ssDeviceInfo.getDeviceIP());
            String str = JSONObject.parseObject(result).getString("Code");
            if ("1".equals(str)) {
                return JSONObject.parseObject(result).getString("Data");
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
    public String restartBox(String deviceuuid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "restartRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    String session = JSONObject.parseObject(result).getString("Session");
                    if (StringUtils.isNoneEmpty(session)) {
                        JSONObject object = JSONObject.parseObject(result);
                        object.remove("Session");
                        result = object.toJSONString();  //核心session 不展示给前端
                    }
                    return result;
                } else {
                    return null;
                }
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
    public String getSubDevicesConnectStatus(String deviceuuid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "SubDevicesManageRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "GetSubDevicesConnectStatus");
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return JSONObject.parseObject(result).getString("Data");
                } else {
                    return null;
                }
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
    public String searchSubDevices(String deviceuuid) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "SubDevicesManageRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "SearchSubDevices");
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return JSONObject.parseObject(result).getString("Data");
                } else {
                    return null;
                }
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
    public String addSubDevice(String deviceuuid, int ChannelNo, String ip, int Port, String name, String password, String protocol) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "SubDevicesManageRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "AddSubDevice");
                child.put("ChannelNo", ChannelNo);
                child.put("IP", ip);
                child.put("Port", Port);
                child.put("Name", name);
                child.put("Password", password);
                child.put("Protocol", Integer.parseInt(protocol));
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return result;
                } else {
                    return null;
                }
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
    public String editSubDevice(String deviceuuid, int ChannelNo, String ip, int Port, String name, String password, String protocol) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "SubDevicesManageRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "EditSubDevice");
                child.put("ChannelNo", ChannelNo);
                child.put("IP", ip);
                child.put("Port", Port);
                child.put("Name", name);
                child.put("Password", password);
                child.put("Protocol", Integer.parseInt(protocol));
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return result;
                } else {
                    return null;
                }
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
    public String deleteSubDevice(String deviceuuid, int ChannelNo) {
        try {
            SSDeviceInfo ssDeviceInfo = this.getDevicedSSinfo(deviceuuid);
            if (ssDeviceInfo != null) {
                Map<String, Object> map = new HashedMap();
                map.put("Name", "SubDevicesManageRequest");
                map.put("UUID", deviceuuid);
                map.put("Session", ssDeviceInfo.getSession());
                Map<String, Object> child = new HashedMap();
                child.put("Action", "DeleteSubDevice");
                child.put("ChannelNo", ChannelNo);
                map.put("Data", child);
                JSONObject json = new JSONObject(map);
                String result = DmHttpUtil.postJsonData(json, ssDeviceInfo.getDeviceIP());
                String str = JSONObject.parseObject(result).getString("Code");
                if ("1".equals(str)) {
                    return result;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }


}

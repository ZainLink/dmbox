package com.zkzy.portal.dumu.server.system.provider.serviceimpl.register;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.common.utils.DateUtils;
import com.zkzy.portal.dumu.client.controller.register.RegisterController;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmCameraBMapper;
import com.zkzy.portal.dumu.server.system.provider.websocket.WebSocketServer;
import com.zkzy.zyportal.config.gas.Queues;
import com.zkzy.zyportal.system.api.entity.dm.*;
import com.zkzy.zyportal.system.api.service.register.RegisterService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.imageio.spi.RegisterableService;
import java.io.File;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/3/30.
 */
@Service
public class RegisterServiceImpl implements RegisterService {


    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);


    private static final String registerRequestStr = "registerRequest"; //注册请求

    private static final String registerResponseStr = "registerResponse"; //注册响应

    private static final String heartbeatRequestStr = "heartbeatRequest";//心跳请求

    private static final String heartbeatResponseStr = "heartbeatResponse";//心跳响应

    private static final String captureInfoRequestStr = "captureInfoRequest";//抓怕请求


    @Value("${dumu.black}")
    private String blackImg;

    @Value("${dumu.white}")
    private String whiteImg;

    @Value("${dumu.vip}")
    private String vipImg;

    @Value("${dumu.other}")
    private String other;

    @Value("${dumu.stranger}")
    private String stranger;


    public static final String ST_ID = "st";


    private static final String base64Img = "data:image/jpg;base64,";


    /**
     * 度目缓存前缀
     */
    public static final String REDIS_PREFIX_DUMU = "dumu:";

    /**
     * 度目实时信息前缀
     */

    public static final String REDIS_PREFIX_DUMU2 = "dm-";

    public static final String IPC = "ipc-";

    public static final String BASE_NAME = "base";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DmBoxBMapper dmBoxBMapper;

    @Autowired
    private DmCameraBMapper dmCameraBMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public Boolean register(RegisterRequest registerRequest) {
        Boolean a = true;
        try {
            //验证必须参数是否为空
            if (!StringUtils.isNoneEmpty(registerRequest.getName(), registerRequest.getTimeStamp() + "", registerRequest.getData().getDeviceInfo().getDeviceUUID())) {
                LOGGER.info("参数不全，注册失败");
                return false;
            }

            //验证是否为注册请求
            if (!registerRequest.getName().equals(registerRequestStr)) {
                LOGGER.info("固定头不正确，注册失败");
                return false;
            }

//            //验证请求时间是否超时
//            if (!DateUtils.sureTimestamp(registerRequest.getTimeStamp())) {
//                LOGGER.info("时间戳超时，注册失败");
//                return false;
//            }

            if (!redisTemplate.hasKey(REDIS_PREFIX_DUMU2 + registerRequest.getData().getDeviceInfo().getDeviceUUID())) {//注册成功后设备入库
                DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(registerRequest.getData().getDeviceInfo().getDeviceUUID());
                if (dmBoxB == null) {
                    dmBoxB = new DmBoxB();
                    dmBoxB.setDeviceuuid(registerRequest.getData().getDeviceInfo().getDeviceUUID());
                    dmBoxB.setDeviceid(registerRequest.getData().getDeviceInfo().getDeviceId());
                    dmBoxB.setDevicemac(registerRequest.getData().getDeviceInfo().getDeviceMac());
                    String time = getDateTime();
                    dmBoxB.setCreatetime(time);
                    dmBoxBMapper.insert(dmBoxB);
                }
                //八路摄像头
                for (int i = 0; i < 8; i++) {
                    dmCameraBMapper.insertCamera(registerRequest.getData().getDeviceInfo().getDeviceUUID(), i);
                }


            }

            return a;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("接口异常，注册失败");
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean heart(HeartbeatRequest heartbeatRequest) {
        Boolean a = true;
        try {
            //验证必须参数是否为空
            if (!StringUtils.isNoneEmpty(heartbeatRequest.getName(), heartbeatRequest.getTimeStamp() + "", heartbeatRequest.getData().getDeviceInfo().getDeviceUUID())) {
                LOGGER.info("参数不全，心跳失败");
                return false;
            }

            //验证是否为注册请求
            if (!heartbeatRequest.getName().equals(heartbeatRequestStr)) {
                LOGGER.info("固定头不正确，心跳失败");
                return false;
            }

//            //验证请求时间是否超时
//            if (!DateUtils.sureTimestamp(heartbeatRequest.getTimeStamp())) {
//                LOGGER.info("时间戳超时，心跳失败");
//                return false;
//            }


            String str = heartbeatRequest.getSession().substring(0, heartbeatRequest.getSession().indexOf("_"));
            if (!redisTemplate.hasKey(REDIS_PREFIX_DUMU + str)) {
                return false;
            } else {
                String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + str);
                if (value.equals(heartbeatRequest.getSession())) {
                    redisTemplate.expire(REDIS_PREFIX_DUMU + str, 120, TimeUnit.SECONDS);
                    //设备实时信息缓存
                    redisTemplate.opsForValue().set(REDIS_PREFIX_DUMU2 + str, JSON.toJSONString(heartbeatRequest.getData().getDeviceInfo()));
                    LOGGER.info("[redisTemplate redis]更新 缓存  url:{} ========缓存时间为{}秒", REDIS_PREFIX_DUMU + heartbeatRequest.getSession(), 120);
                } else {
                    return false;
                }

            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("接口异常，心跳失败");
            return false;
        }
        return a;
    }

    @Override
    public Boolean capture(CaptureInfoRequest captureInfoRequest) {
        Boolean a = true;
        try {

            //验证session
            String str = captureInfoRequest.getSession().substring(0, captureInfoRequest.getSession().indexOf("_"));
            if (!redisTemplate.hasKey(REDIS_PREFIX_DUMU + str)) {
                return false;
            } else {
                String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + str);
                if (!value.equals(captureInfoRequest.getSession())) {
                    return false;
                }

            }
            //验证必须参数是否为空
            if (!StringUtils.isNoneEmpty(captureInfoRequest.getName(), captureInfoRequest.getTimeStamp() + "", captureInfoRequest.getData().getDeviceInfo().getDeviceUUID())) {
                LOGGER.info("参数不全，抓拍失败");
                return false;
            }

            //验证是否为注册请求
            if (!captureInfoRequest.getName().equals(captureInfoRequestStr)) {
                LOGGER.info("固定头不正确，抓拍失败");
                return false;
            }

//            //验证请求时间是否超时
//            if (!DateUtils.sureTimestamp(captureInfoRequest.getTimeStamp())) {
//                LOGGER.info("时间戳超时，抓拍失败");
//                return false;
//            }

            this.putCaptureMessage(captureInfoRequest);

            this.updateCamera(captureInfoRequest);


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("接口异常，抓拍上传失败");
            return false;
        }
        return a;
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateCamera(CaptureInfoRequest captureInfoRequest) {
        String time = getDateTime();
        DmCameraB dmCameraB = null;
        try {
            if (!redisTemplate.hasKey(IPC + captureInfoRequest.getData().getDeviceInfo().getDeviceUUID()
                    + "_" + captureInfoRequest.getData().getDeviceInfo().getChannelNo())) {
                dmCameraB = new DmCameraB();
                dmCameraB.setDeviceuuid(captureInfoRequest.getData().getDeviceInfo().getDeviceUUID());
                dmCameraB.setChannelno(captureInfoRequest.getData().getDeviceInfo().getChannelNo());
                dmCameraB.setCreatetime(time);
                dmCameraB.setSubdeviceip(captureInfoRequest.getData().getDeviceInfo().getSubDeviceInfo().getSubDeviceIP());
                dmCameraB.setSubdeviceuuid(captureInfoRequest.getData().getDeviceInfo().getSubDeviceInfo().getSubDeviceUUID());
                redisTemplate.opsForValue().set(IPC + captureInfoRequest.getData().getDeviceInfo().getDeviceUUID() + "_" + captureInfoRequest.getData().getDeviceInfo().getChannelNo(), JSON.toJSONString(dmCameraB));
                dmCameraBMapper.updateByPrimaryKey(dmCameraB);
            } else {
                String param = redisTemplate.opsForValue().get(IPC + captureInfoRequest.getData().getDeviceInfo().getDeviceUUID() + "_" + captureInfoRequest.getData().getDeviceInfo().getChannelNo());
                if (StringUtils.isNoneEmpty(param)) {
                    dmCameraB = JSON.parseObject(param, DmCameraB.class);
                    if (dmCameraB != null &&
                            !dmCameraB.getSubdeviceip()
                                    .equals(captureInfoRequest.getData().getDeviceInfo().getSubDeviceInfo().getSubDeviceIP())
                            || !dmCameraB.getSubdeviceuuid()
                            .equals(captureInfoRequest.getData().getDeviceInfo().getSubDeviceInfo().getSubDeviceUUID())) {

                        dmCameraB.setDeviceuuid(captureInfoRequest.getData().getDeviceInfo().getDeviceUUID());
                        dmCameraB.setChannelno(captureInfoRequest.getData().getDeviceInfo().getChannelNo());
                        dmCameraB.setCreatetime(time);
                        dmCameraB.setSubdeviceip(captureInfoRequest.getData().getDeviceInfo().getSubDeviceInfo().getSubDeviceIP());
                        dmCameraB.setSubdeviceuuid(captureInfoRequest.getData().getDeviceInfo().getSubDeviceInfo().getSubDeviceUUID());
                        dmCameraBMapper.updateByPrimaryKey(dmCameraB);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            LOGGER.error("相机参数更新异常");
        }
    }

    public void putCaptureMessage(CaptureInfoRequest captureInfoRequest) {
        Map<String, Object> map = new HashedMap();
        try {
            String time = DateHelper.getDate();
            String times = DateHelper.getDateTime();
            String uid = uuid();
            int t = captureInfoRequest.getData().getCompareInfo().getPersonType();
            //目录结构
            String str = captureInfoRequest.getData().getDeviceInfo().getDeviceUUID()
                    + File.separator + captureInfoRequest.getData().getDeviceInfo().getChannelNo() + File.separator;
            //人脸id
            Integer faceid = captureInfoRequest.getData().getFaceInfo().getFaceId();
            //设备uuid
            String DeviceUUID = captureInfoRequest.getData().getDeviceInfo().getDeviceUUID();
            //通道号
            Integer ChannelNo = captureInfoRequest.getData().getDeviceInfo().getChannelNo();
            //预警发送时间
            String SendTime = captureInfoRequest.getData().getCaptureInfo().getSendTime();
            //抓拍时间
            String CaptureTime = captureInfoRequest.getData().getCaptureInfo().getCaptureTime();
            //摄像头名称
            String subname = StringUtils.isNoneEmpty(redisTemplate.opsForValue().get(DeviceUUID + "_" + ChannelNo))
                    ? redisTemplate.opsForValue().get(DeviceUUID + "_" + ChannelNo) : "未知";
            //站点id
            String unid = StringUtils.isNoneEmpty(redisTemplate.opsForValue().get(ST_ID + DeviceUUID + "_" + ChannelNo))
                    ? redisTemplate.opsForValue().get(ST_ID + DeviceUUID + "_" + ChannelNo) : "";
            //站点名称
            String uname = StringUtils.isNoneEmpty(redisTemplate.opsForValue().get(BASE_NAME + unid))
                    ? redisTemplate.opsForValue().get(BASE_NAME + unid) : "未知";
            String furl = null;
            String burl = null;
            String log = null;
            switch (t) {
                case 0://陌生人
                    furl = time + File.separator + str + "FacePicture" + File.separator + uid + ".jpg";
                    burl = time + File.separator + str + "BackgroundPicture" + File.separator + uid + ".jpg";
                    log = "北京时间:" + CaptureTime + "---" + uname + "的" + subname + "摄像头抓拍到" + "陌生人" + faceid + ",发送给服务器的时间为:" + SendTime + ",服务端接收时间为:" + times;
                    map.put("faceid", faceid);//人脸id
                    map.put("deviceuuid", DeviceUUID);//盒子设备id
                    map.put("channelno", ChannelNo);//通道号
                    map.put("furl", furl);//头像图
                    map.put("burl", burl);//背景图
                    map.put("sendtime", SendTime);//发送时间
                    map.put("capturetime", CaptureTime);//抓拍时间
                    map.put("receivetime", times);//接收时间
                    map.put("subname", subname);//摄像头名称
                    map.put("unid", unid);//单位id
                    map.put("uname", uname);//单位名称
                    map.put("remark", log);//单位名称
                    map.put("fbase", captureInfoRequest.getData().getCaptureInfo().getFacePicture());//Base64 图片
                    map.put("bbase", captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture());//base 64 图片
                    this.rabbitTemplate.convertAndSend(Queues.warnExChange, "", map);
                    break;
                case 2://白名单
                    String kqid =captureInfoRequest.getData().getCompareInfo().getPersonInfo().getPersonId();
                    map.put("kqtime", times);//考勤的时间 精确到时分秒
                    map.put("kqday", time);//考情的日子
                    map.put("uuid",kqid);
                    this.rabbitTemplate.convertAndSend(Queues.whiteExChange, "", map);
                    break;
                default:
                    LOGGER.info("抓拍其他名单人成功");
                    break;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }


//    public void putCaptureMessage(CaptureInfoRequest captureInfoRequest) {
//
//        try {
//            String time = DateHelper.getDate();
//            String uid = uuid();
//            int t = captureInfoRequest.getData().getCompareInfo().getPersonType();
//            String str = captureInfoRequest.getData().getDeviceInfo().getDeviceUUID() + File.separator + captureInfoRequest.getData().getDeviceInfo().getChannelNo() + File.separator;
//            String face = null;
//            String back = null;
//            switch (t) {
//                case 0://陌生人
//                    face = stranger + File.separator + time + File.separator + str + "FacePicture" + File.separator + uid + ".jpg";
//                    back = stranger + File.separator + time + File.separator + str + File.separator + "BackgroundPicture" + File.separator + uid + ".jpg";
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getFacePicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getFacePicture(), face);
//                    }
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture(), back);
//                    }
//
//                    LOGGER.info("抓拍陌生人照片生成");
//                    break;
//                case 1://黑名单
//                    face = blackImg + File.separator + time + File.separator + str + File.separator + "FacePicture" + File.separator + uid + ".jpg";
//                    back = blackImg + File.separator + time + File.separator + str + "BackgroundPicture" + File.separator + uid + ".jpg";
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getFacePicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getFacePicture(), face);
//                    }
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture(), back);
//                    }
//                    LOGGER.info("抓拍黑名单照片生成");
//                    break;
//                case 2://白名单
//                    face = whiteImg + File.separator + time + File.separator + str + "FacePicture" + File.separator + uid + ".jpg";
//                    back = whiteImg + File.separator + time + File.separator + str + "BackgroundPicture" + File.separator + uid + ".jpg";
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getFacePicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getFacePicture(), face);
//                    }
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture(), back);
//                    }
//                    LOGGER.info("抓拍白名单照片生成");
//                    break;
//                case 3://VIP
//                    face = vipImg + File.separator + time + File.separator + str + "FacePicture" + File.separator + uid + ".jpg";
//                    back = vipImg + File.separator + time + File.separator + str + "BackgroundPicture" + File.separator + uid + ".jpg";
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getFacePicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getFacePicture(), face);
//                    }
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture(), back);
//                    }
//                    LOGGER.info("抓拍VIP名单照片生成");
//                    break;
//                default:
//                    face = other + File.separator + time + File.separator + str + "FacePicture" + uid + ".jpg";
//                    back = other + File.separator + time + File.separator + str + "BackgroundPicture" + uid + ".jpg";
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getFacePicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getFacePicture(), face);
//                    }
//                    if (StringUtils.isNoneEmpty(captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture())) {
//                        Base64Util.base64StrToImage(base64Img + captureInfoRequest.getData().getCaptureInfo().getBackgroundPicture(), back);
//                    }
//                    LOGGER.info("抓拍其他名单照片生成");
//                    break;
//            }
//
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//
//    }
}

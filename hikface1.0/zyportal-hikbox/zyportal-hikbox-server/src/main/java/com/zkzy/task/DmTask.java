package com.zkzy.task;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import util.HttpClientUtil;
import util.dm.HeartbeatRequest;
import util.dm.HeartbeatResponse;
import util.dm.RegisterRequest;
import util.dm.RegisterResponse;

/**
 * Created by Thinkpad-W530 on 2021/12/2.
 */
@Configuration
public class DmTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmTask.class);

    public static String uuid = "umph00s5g469";

    public static volatile Boolean register = false;

    public static volatile String session;

    public static final String registerRequestStr = "registerRequest"; //注册请求

    public static final String heartbeatRequestStr = "heartbeatRequest";//心跳请求


    /**
     * 定时 每1分钟 请求注册
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void registerToServer() {
        try {
            this.register();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 定时 每1分钟 请求心跳
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void heartToServer() {
        try {
            this.heart();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void register() {
        if (!register) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setName(registerRequestStr);//固定头
            registerRequest.setTimeStamp(DateUtils.dateToUnixTimestamp());
            registerRequest.getData().getDeviceInfo().setDeviceUUID(uuid);
            String jsonstring = JSON.toJSONString(registerRequest);
            String result = "";
            try {
                result = HttpClientUtil.postJson(jsonstring, "http://192.168.1.233:8085/server/register");
            } catch (Exception e) {
                LOGGER.error("网络异常");
                register = false;
                session = "";
            }
            if (StringUtils.isNoneEmpty(result)) {
                RegisterResponse response = JSON.parseObject(result, RegisterResponse.class);
                if (response.getCode() == 1) {
                    session = response.getData().getSession();
                    register = true;
                    LOGGER.info("设备注册成功,session=" + session);
                    DmTask.heart();
                }
            }
        }
    }


    public static void heart() {
        if (register && StringUtils.isNoneEmpty(session)) {
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setName(heartbeatRequestStr);//固定头
            heartbeatRequest.setTimeStamp(DateUtils.dateToUnixTimestamp());
            heartbeatRequest.getData().getDeviceInfo().setDeviceUUID(uuid);
            heartbeatRequest.setSession(session);
            String jsonstring = JSON.toJSONString(heartbeatRequest);
            String result = "";
            try {
                result = HttpClientUtil.postJson(jsonstring, "http://192.168.1.233:8085/server/heart");
            } catch (Exception e) {
                LOGGER.error("网络异常");
                register = false;
                session = "";
            }

            if (StringUtils.isNoneEmpty(result)) {
                HeartbeatResponse response = JSON.parseObject(result, HeartbeatResponse.class);
                if (response.getCode() != 1) {
                    register = false;
                    session = "";

                } else {
                    LOGGER.info("设备心跳成功,session=" + session);
                }
            }

        }
    }


    public static void main(String[] args) throws Exception {


    }
}

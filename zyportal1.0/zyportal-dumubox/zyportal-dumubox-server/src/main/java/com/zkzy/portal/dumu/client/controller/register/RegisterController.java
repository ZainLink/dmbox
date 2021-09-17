package com.zkzy.portal.dumu.client.controller.register;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.common.utils.DateUtils;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.portal.dumu.client.security.utils.TokenUtil;
import com.zkzy.zyportal.system.api.entity.dm.*;
import com.zkzy.zyportal.system.api.service.register.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sun.tools.doclint.Entity.reg;

/**
 * Created by Thinkpad-W530 on 2021/3/29.
 */

@Validated
@RestController
@RequestMapping("/server")
@Api(tags = "服务端注册Controller")
public class RegisterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    private static final String registerRequestStr = "registerRequest"; //注册请求

    private static final String registerResponseStr = "registerResponse"; //注册响应

    private static final String heartbeatRequestStr = "heartbeatRequest";//心跳请求

    private static final String heartbeatResponseStr = "heartbeatResponse";//心跳响应

    @Autowired
    private TokenUtil jwtTokenUtil;

    @Autowired
    private RegisterService registerService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "盒子注册")
    public Map<String, Object> registerRequest(
            @RequestBody String param
    ) {
        RegisterResponse registerResponse = new RegisterResponse();
        Long time = DateUtils.dateToUnixTimestamp();
        registerResponse.setName(registerResponseStr);
        registerResponse.setTimeStamp(time);
        try {
            RegisterRequest reg = JSON.parseObject(param, RegisterRequest.class);
            if (registerService.register(reg)) {
                final String token = jwtTokenUtil.dumuToken(reg.getData().getDeviceInfo().getDeviceUUID(), reg.getData().getDeviceInfo().getDeviceUUID() + "_" + reg.getTimeStamp());
                registerResponse.getData().setSession(token);
                registerResponse.setCode(1);
                registerResponse.setMessage("成功");
            } else {
                registerResponse.setCode(1001);
                registerResponse.setMessage("注册失败");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            registerResponse.setCode(0);
            registerResponse.setMessage("失败");
        }
        LOGGER.info(registerResponse.toString());
        return makeRegisterMessage(registerResponse);
    }


    @PostMapping(value = "/heart", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "盒子心跳")
    public Map<String, Object> heartbeatRequest(
            @RequestBody String param
    ) {
        HeartbeatResponse heartRes = new HeartbeatResponse();
        Long time = DateUtils.dateToUnixTimestamp();
        heartRes.setName(heartbeatResponseStr);
        heartRes.setTimeStamp(time);
        try {
            HeartbeatRequest heartReq = JSON.parseObject(param, HeartbeatRequest.class);
            if (registerService.heart(heartReq)) {
                heartRes.setCode(1);
                heartRes.setMessage("成功");
                heartRes.setSession(heartReq.getSession());
            } else {
                heartRes.setCode(1001);
                heartRes.setMessage("设备未注册");
            }
        } catch (Exception e) {
            e.printStackTrace();
            heartRes.setCode(0);
            heartRes.setMessage("失败");
        }
        LOGGER.info(heartRes.toString());
        return makeHeartMessage(heartRes);
    }


    @PostMapping(value = "/capture", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "盒子抓拍")
    public CaptureInfoResponse capture(
            @RequestBody String param
    ) {
        CaptureInfoResponse captureInfoResponse = new CaptureInfoResponse();
        Long time = DateUtils.dateToUnixTimestamp();
        captureInfoResponse.setName(heartbeatResponseStr);
        captureInfoResponse.setTimeStamp(time);
        try {
            CaptureInfoRequest request = JSON.parseObject(param, CaptureInfoRequest.class);
            if (registerService.capture(request)) {
                captureInfoResponse.setCode(1);
                captureInfoResponse.setMessage("成功");
                captureInfoResponse.setSession(request.getSession());
            } else {
                captureInfoResponse.setCode(0);
                captureInfoResponse.setMessage("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            captureInfoResponse.setCode(0);
            captureInfoResponse.setMessage("失败");
        }
        LOGGER.info(captureInfoResponse.toString());
        return captureInfoResponse;
    }

}

package com.zkzy.portal.dumu.client.controller.set;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.set.*;
import com.zkzy.zyportal.system.api.service.dm.DmCameraBService;
import com.zkzy.zyportal.system.api.service.set.BoxSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
@Validated
@RestController
@RequestMapping("/boxSet")
@Api(tags = "盒子参数配置Controller")
public class BoxSetController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoxSetController.class);

    @Autowired
    private BoxSetService boxSetService;

    @Autowired
    private DmCameraBService dmCameraBService;

    @PostMapping(value = "/getFaceParametersRequest", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "后端抓拍参数获取")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getFaceParametersRequest(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "ChannelNo", required = true) Integer ChannelNo
    ) {
        try {
            return makeMessageToJsonResult(boxSetService.getFaceParametersRequest(deviceuuid, ChannelNo));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/getFaceCompareManage", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "人脸比对参数获取 ")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getFaceCompareManage(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "ChannelNo", required = true) Integer ChannelNo
    ) {
        try {
            return makeMessageToJsonResult(boxSetService.getFaceCompareManage(deviceuuid, ChannelNo));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/setFaceParametersRequest", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "后端抓拍参数设置")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> setFaceParametersRequest(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "channelNo", required = true) Integer channelNo, //通道号 (NVR 各通道，值128 为设置全部通道
            @RequestParam(name = "FaceEnable", required = true, defaultValue = "1") Integer FaceEnable,//人脸识别功能开关  0关闭 1开启
            @RequestParam(name = "MinFaceSize", required = false, defaultValue = "30") Integer MinFaceSize,//最小识别像素
            @RequestParam(name = "Enable1", required = false, defaultValue = "1") Integer Enable1,//布放时间段1  0关闭 1启用
            @RequestParam(name = "StartHour1", required = false, defaultValue = "0") Integer StartHour1,//时间段1的开始时间（小时） 0~23
            @RequestParam(name = "StartMin1", required = false, defaultValue = "0") Integer StartMin1,//时间段1的开始时间（分钟） 0~59
            @RequestParam(name = "EndHour1", required = false, defaultValue = "23") Integer EndHour1,//时间段1的结束的时间（小时） 0~23
            @RequestParam(name = "EndMin1", required = false, defaultValue = "59") Integer EndMin1,//时间段1的结束时间（分钟） 0~59
            @RequestParam(name = "Enable2", required = false, defaultValue = "0") Integer Enable2,//布放时间段2  0关闭 1启用
            @RequestParam(name = "StartHour2", required = false, defaultValue = "0") Integer StartHour2,//时间段2的开始时间（小时） 0~23
            @RequestParam(name = "StartMin2", required = false, defaultValue = "0") Integer StartMin2,//时间段2的开始时间（分钟） 0~59
            @RequestParam(name = "EndHour2", required = false, defaultValue = "23") Integer EndHour2,//时间段2的结束的时间（小时） 0~23
            @RequestParam(name = "EndMin2", required = false, defaultValue = "59") Integer EndMin2,//时间段2的结束时间（分钟） 0~59
            @RequestParam(name = "Sensitivity", required = false, defaultValue = "100") Integer Sensitivity,//人脸识别灵敏度
            @RequestParam(name = "PictureMode", required = false, defaultValue = "2") Integer PictureMode,//上传图片模式 0人脸 2人脸和原图
            @RequestParam(name = "SceneMode", required = false, defaultValue = "1") Integer SceneMode,//人脸识别场景模式 0常规场景 1大堂场景
            @RequestParam(name = "FaceTrackEnable", required = false, defaultValue = "1") Integer FaceTrackEnable,//人脸跟踪（显示跟踪框） 0关闭 1开启
            @RequestParam(name = "CaptureMode", required = false, defaultValue = "1") Integer CaptureMode,//抓拍模式 0：离开后抓拍（距离选优）1：快速抓拍2：间隔抓拍（以秒为单位）3：间隔抓拍（以帧为单位）4：单人模式5：离开后抓拍（质量选优）
            @RequestParam(name = "CaptureTimes", required = false, defaultValue = "1") Integer CaptureTimes,//抓拍次数 （CaptureMode=2/3/4） 特有参数，其他模式时该参数无效
            @RequestParam(name = "MaxCaptureTimes", required = false, defaultValue = "1") Integer MaxCaptureTimes,//最大抓拍次数 离开后抓拍需要
            @RequestParam(name = "FastCaptureInterval", required = false, defaultValue = "10") Integer FastCaptureInterval,//快速抓拍帧数
            @RequestParam(name = "IntervalTime", required = false, defaultValue = "10") Integer IntervalTime,//抓拍时间间隔 单位 秒/帧
            @RequestParam(name = "FrameCaptureModeIntervalFrames", required = false, defaultValue = "30") Integer FrameCaptureModeIntervalFrames,///抓拍时间间隔 单位帧 10-1500
            @RequestParam(name = "SingleModeIntervalFrames", required = false, defaultValue = "30") Integer SingleModeIntervalFrames,///单人模式的间隔帧数
            @RequestParam(name = "Clarity", required = false, defaultValue = "20") Integer Clarity,//人脸清晰度
            @RequestParam(name = "Brightness", required = false, defaultValue = "10") Integer Brightness,//人脸亮度
            @RequestParam(name = "MaskScore", required = false, defaultValue = "35") Integer MaskScore,//人脸遮挡
            @RequestParam(name = "FacePitch", required = false, defaultValue = "90") Integer FacePitch,//俯仰角
            @RequestParam(name = "FaceYaw", required = false, defaultValue = "90") Integer FaceYaw,//偏航角
            @RequestParam(name = "FaceRoll", required = false, defaultValue = "90") Integer FaceRoll//翻滚脚
    ) {
        try {
            FaceParametersRequest faceParametersRequest = new FaceParametersRequest();
            FaceParametersRequestData data = faceParametersRequest.getData();
            faceParametersRequest.setUUID(deviceuuid);
            data.setChannelNo(channelNo);
            data.setFaceEnable(FaceEnable);
            data.setMinFaceSize(MinFaceSize);
            TimeTable t = null;
            t = new TimeTable();
            t.setEnable(Enable1);
            t.setStartHour(StartHour1);
            t.setStartMin(StartMin1);
            t.setEndHour(EndHour1);
            t.setEndMin(EndMin1);
            data.getTimeTable().add(t);
            t = new TimeTable();
            t.setEnable(Enable2);
            t.setStartHour(StartHour2);
            t.setStartMin(StartMin2);
            t.setEndHour(EndHour2);
            t.setEndMin(EndMin2);
            data.getTimeTable().add(t);
            data.setSensitivity(Sensitivity);
            data.setPictureMode(PictureMode);
            data.setSceneMode(SceneMode);
            data.setFaceTrackEnable(FaceTrackEnable);
            data.setCaptureMode(CaptureMode);
            data.getCaptureModeParam().setCaptureTimes(CaptureTimes);
            data.getCaptureModeParam().setMaxCaptureTimes(MaxCaptureTimes);
            data.getCaptureModeParam().setFastCaptureInterval(FastCaptureInterval);
            data.getCaptureModeParam().setIntervalTime(IntervalTime);
            data.getCaptureModeParam().setFrameCaptureModeIntervalFrames(FrameCaptureModeIntervalFrames);
            data.getCaptureModeParam().setSingleModeIntervalFrames(SingleModeIntervalFrames);
            data.getAdvanceParam().setClarity(Clarity);
            data.getAdvanceParam().setBrightness(Brightness);
            data.getAdvanceParam().setMaskScore(MaskScore);
            data.getAdvanceParam().setFacePitch(FacePitch);
            data.getAdvanceParam().setFaceYaw(FaceYaw);
            data.getAdvanceParam().setFaceRoll(FaceRoll);
            return makeMessageToJsonResult(boxSetService.setFaceParametersRequest(faceParametersRequest));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/setFaceCompareManage", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "人脸比对参数设置")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> setFaceCompareManage(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "channelNo", required = true) Integer channelNo, //通道号 (NVR 各通道，值128 为设置全部通道
            @RequestParam(name = "Enable", required = true, defaultValue = "1") Integer Enable,//人脸识别功能开关  0关闭 1开启
            @RequestParam(name = "Similary", required = false, defaultValue = "65") Integer Similary,//最小识别像素
            @RequestParam(name = "Enable1", required = false, defaultValue = "1") Integer Enable1,//布放时间段1  0关闭 1启用
            @RequestParam(name = "StartHour1", required = false, defaultValue = "0") Integer StartHour1,//时间段1的开始时间（小时） 0~23
            @RequestParam(name = "StartMin1", required = false, defaultValue = "0") Integer StartMin1,//时间段1的开始时间（分钟） 0~59
            @RequestParam(name = "EndHour1", required = false, defaultValue = "23") Integer EndHour1,//时间段1的结束的时间（小时） 0~23
            @RequestParam(name = "EndMin1", required = false, defaultValue = "59") Integer EndMin1,//时间段1的结束时间（分钟） 0~59
            @RequestParam(name = "Enable2", required = false, defaultValue = "0") Integer Enable2,//布放时间段2  0关闭 1启用
            @RequestParam(name = "StartHour2", required = false, defaultValue = "0") Integer StartHour2,//时间段2的开始时间（小时） 0~23
            @RequestParam(name = "StartMin2", required = false, defaultValue = "0") Integer StartMin2,//时间段2的开始时间（分钟） 0~59
            @RequestParam(name = "EndHour2", required = false, defaultValue = "23") Integer EndHour2,//时间段2的结束的时间（小时） 0~23
            @RequestParam(name = "EndMin2", required = false, defaultValue = "59") Integer EndMin2//时间段2的结束时间（分钟） 0~59
    ) {
        try {
            FaceCompareManage faceCompareManage = new FaceCompareManage();
            FaceCompareManageData data = faceCompareManage.getData();
            faceCompareManage.setUUID(deviceuuid);
            data.setChannelNo(channelNo);
            data.setEnable(Enable);
            data.setSimilary(Similary);
            TimeTable t = null;
            t = new TimeTable();
            t.setEnable(Enable1);
            t.setStartHour(StartHour1);
            t.setStartMin(StartMin1);
            t.setEndHour(EndHour1);
            t.setEndMin(EndMin1);
            data.getCompareTime().add(t);
            t = new TimeTable();
            t.setEnable(Enable2);
            t.setStartHour(StartHour2);
            t.setStartMin(StartMin2);
            t.setEndHour(EndHour2);
            t.setEndMin(EndMin2);
            data.getCompareTime().add(t);
            CompareName name = null;
            name = new CompareName();
            name.setName("白名单");
            name.setPersonType(2);
            data.getCompareNameList().add(name);
            return makeMessageToJsonResult(boxSetService.setFaceCompareManage(faceCompareManage));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/getHTTPParametersRequest", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "http上传参数获取")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getHTTPParametersRequest(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid
    ) {
        try {
            return makeMessageToJsonResult(boxSetService.getHTTPParametersRequest(deviceuuid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/setHTTPParametersRequest", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "http上传参数设置")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> setHTTPParametersRequest(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "RegisterAddress", required = true) String RegisterAddress,
            @RequestParam(name = "HeartbeatAddress", required = true) String HeartbeatAddress,
            @RequestParam(name = "CaptureAddress", required = true) String CaptureAddress,
            @RequestParam(name = "ResendTimes", required = false, defaultValue = "5") Integer ResendTimes,
            @RequestParam(name = "HeartbeatInterval", required = false, defaultValue = "60") Integer HeartbeatInterval


    ) {
        try {
            HTTPParametersRequest httpParametersRequest = new HTTPParametersRequest();
            httpParametersRequest.setUUID(deviceuuid);
            httpParametersRequest.getData().setRegisterAddress(RegisterAddress);
            httpParametersRequest.getData().setCaptureAddress(CaptureAddress);
            httpParametersRequest.getData().setHeartbeatAddress(HeartbeatAddress);
            httpParametersRequest.getData().setResendTimes(ResendTimes);
            httpParametersRequest.getData().setHeartbeatInterval(HeartbeatInterval);
            return makeMessageToJsonResult(boxSetService.setHTTPParametersRequest(httpParametersRequest));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/restartBox", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "设备重启")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> restartBox(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid
    ) {
        try {
            return makeMessageToJsonResult(boxSetService.restartBox(deviceuuid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/getSubDevicesConnectStatus", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取设备通道状态")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getSubDevicesConnectStatus(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid
    ) {
        try {
            return makeMessageToJsonResult(boxSetService.getSubDevicesConnectStatus(deviceuuid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }

    @PostMapping(value = "/searchSubDevices", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "设备搜索")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> searchSubDevices(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid
    ) {
        try {
            return makeMessageToJsonResult(boxSetService.searchSubDevices(deviceuuid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }

    @PostMapping(value = "/updateProtocol", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "通道协议设置")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> updateProtocol(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "channelno", required = true) Integer channelno,
            @RequestParam(name = "protocol", required = true, defaultValue = "2") String protocol,
            @RequestParam(name = "ip", required = false, defaultValue = "") String ip,
            @RequestParam(name = "port", required = false, defaultValue = "") String port,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "password", required = false, defaultValue = "") String password
    ) {
        try {
            if ("1".equals(protocol) && StringUtils.isNoneEmpty(ip, port, name, password)) {
                return makeMessage(dmCameraBService.updateProtocol(deviceuuid, channelno, ip, Integer.valueOf(port), name, password, protocol));
            } else if ("2".equals(protocol)) {
                return makeMessage(dmCameraBService.updateProtocol(deviceuuid, channelno, "", 0, "", "", protocol));
            } else {
                return makeMessage(ReturnCode.FFCS);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


}

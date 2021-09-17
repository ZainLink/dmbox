package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
public class FaceParametersRequestData implements Serializable {

    private Integer ChannelNo; //通道号

    private Integer FaceEnable; //人脸识别功能开关 0 关闭 1开启

    private Integer MinFaceSize; //人脸识别最小像素  30 -300

    private List<TimeTable> TimeTable = new ArrayList<>(); //布放时间段

    private Integer Sensitivity; //识别人脸的灵敏度


    private Integer PictureMode; //上传图片模式


    private Integer SceneMode; //人脸识别场景模式

    private Integer FaceTrackEnable; //人脸跟踪

    private Integer CaptureMode;// 抓拍模式


    private CaptureModeParam CaptureModeParam = new CaptureModeParam();

    private AdvanceParam AdvanceParam = new AdvanceParam();


    public Integer getChannelNo() {
        return ChannelNo;
    }

    public void setChannelNo(Integer channelNo) {
        ChannelNo = channelNo;
    }

    public Integer getFaceEnable() {
        return FaceEnable;
    }

    public void setFaceEnable(Integer faceEnable) {
        FaceEnable = faceEnable;
    }

    public Integer getMinFaceSize() {
        return MinFaceSize;
    }

    public void setMinFaceSize(Integer minFaceSize) {
        MinFaceSize = minFaceSize;
    }

    public List<com.zkzy.zyportal.system.api.entity.set.TimeTable> getTimeTable() {
        return TimeTable;
    }

    public void setTimeTable(List<com.zkzy.zyportal.system.api.entity.set.TimeTable> timeTable) {
        TimeTable = timeTable;
    }

    public Integer getSensitivity() {
        return Sensitivity;
    }

    public void setSensitivity(Integer sensitivity) {
        Sensitivity = sensitivity;
    }

    public Integer getPictureMode() {
        return PictureMode;
    }

    public void setPictureMode(Integer pictureMode) {
        PictureMode = pictureMode;
    }

    public Integer getSceneMode() {
        return SceneMode;
    }

    public void setSceneMode(Integer sceneMode) {
        SceneMode = sceneMode;
    }

    public Integer getFaceTrackEnable() {
        return FaceTrackEnable;
    }

    public void setFaceTrackEnable(Integer faceTrackEnable) {
        FaceTrackEnable = faceTrackEnable;
    }

    public Integer getCaptureMode() {
        return CaptureMode;
    }

    public void setCaptureMode(Integer captureMode) {
        CaptureMode = captureMode;
    }

    public com.zkzy.zyportal.system.api.entity.set.CaptureModeParam getCaptureModeParam() {
        return CaptureModeParam;
    }

    public void setCaptureModeParam(com.zkzy.zyportal.system.api.entity.set.CaptureModeParam captureModeParam) {
        CaptureModeParam = captureModeParam;
    }

    public com.zkzy.zyportal.system.api.entity.set.AdvanceParam getAdvanceParam() {
        return AdvanceParam;
    }

    public void setAdvanceParam(com.zkzy.zyportal.system.api.entity.set.AdvanceParam advanceParam) {
        AdvanceParam = advanceParam;
    }
}

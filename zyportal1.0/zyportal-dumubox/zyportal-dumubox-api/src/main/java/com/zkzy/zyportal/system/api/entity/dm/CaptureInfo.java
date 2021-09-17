package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public class CaptureInfo implements Serializable {
    private String SendTime;

    private String CaptureTime;

    private Integer CaptureCount;

    private String FacePicture;

    private String BodyPicture;

    private String BackgroundPicture;

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }

    public String getCaptureTime() {
        return CaptureTime;
    }

    public void setCaptureTime(String captureTime) {
        CaptureTime = captureTime;
    }

    public Integer getCaptureCount() {
        return CaptureCount;
    }

    public void setCaptureCount(Integer captureCount) {
        CaptureCount = captureCount;
    }

    public String getFacePicture() {
        return FacePicture;
    }

    public void setFacePicture(String facePicture) {
        FacePicture = facePicture;
    }

    public String getBodyPicture() {
        return BodyPicture;
    }

    public void setBodyPicture(String bodyPicture) {
        BodyPicture = bodyPicture;
    }

    public String getBackgroundPicture() {
        return BackgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        BackgroundPicture = backgroundPicture;
    }
}

package com.zkzy.zyportal.system.api.entity.set;

import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/22.
 */
public class HTTPParametersRequestData implements Serializable {
    private Integer CaptureEnabled = 1;

    private String CaptureAddress;

    private Integer CaptureType = 0;

    private CaptureContent CaptureContent = new CaptureContent();

    private PictureData PictureData = new PictureData();

    private Integer ResendTimes;

    private Integer RegisterEnabled = 1;

    private String RegisterAddress;

    private Integer HeartbeatEnabled = 1;

    private String HeartbeatAddress;

    private Integer HeartbeatInterval;

    private Integer SignCheck = 0;

    private Integer HTTPVersion = 1;

    private Integer BCloudEnable = 0;

    public Integer getCaptureEnabled() {
        return CaptureEnabled;
    }

    public void setCaptureEnabled(Integer captureEnabled) {
        CaptureEnabled = captureEnabled;
    }

    public String getCaptureAddress() {
        return CaptureAddress;
    }

    public void setCaptureAddress(String captureAddress) {
        CaptureAddress = captureAddress;
    }

    public Integer getCaptureType() {
        return CaptureType;
    }

    public void setCaptureType(Integer captureType) {
        CaptureType = captureType;
    }

    public HTTPParametersRequestData.CaptureContent getCaptureContent() {
        return CaptureContent;
    }

    public void setCaptureContent(HTTPParametersRequestData.CaptureContent captureContent) {
        CaptureContent = captureContent;
    }

    public HTTPParametersRequestData.PictureData getPictureData() {
        return PictureData;
    }

    public void setPictureData(HTTPParametersRequestData.PictureData pictureData) {
        PictureData = pictureData;
    }

    public Integer getResendTimes() {
        return ResendTimes;
    }

    public void setResendTimes(Integer resendTimes) {
        ResendTimes = resendTimes;
    }

    public Integer getRegisterEnabled() {
        return RegisterEnabled;
    }

    public void setRegisterEnabled(Integer registerEnabled) {
        RegisterEnabled = registerEnabled;
    }

    public String getRegisterAddress() {
        return RegisterAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        RegisterAddress = registerAddress;
    }

    public Integer getHeartbeatEnabled() {
        return HeartbeatEnabled;
    }

    public void setHeartbeatEnabled(Integer heartbeatEnabled) {
        HeartbeatEnabled = heartbeatEnabled;
    }

    public String getHeartbeatAddress() {
        return HeartbeatAddress;
    }

    public void setHeartbeatAddress(String heartbeatAddress) {
        HeartbeatAddress = heartbeatAddress;
    }

    public Integer getHeartbeatInterval() {
        return HeartbeatInterval;
    }

    public void setHeartbeatInterval(Integer heartbeatInterval) {
        HeartbeatInterval = heartbeatInterval;
    }

    public Integer getSignCheck() {
        return SignCheck;
    }

    public void setSignCheck(Integer signCheck) {
        SignCheck = signCheck;
    }

    public Integer getHTTPVersion() {
        return HTTPVersion;
    }

    public void setHTTPVersion(Integer HTTPVersion) {
        this.HTTPVersion = HTTPVersion;
    }

    public Integer getBCloudEnable() {
        return BCloudEnable;
    }

    public void setBCloudEnable(Integer BCloudEnable) {
        this.BCloudEnable = BCloudEnable;
    }

    public class CaptureContent implements Serializable {
        private Integer FaceInfo = 1;

        private Integer CompareInfo = 1;

        public Integer getFaceInfo() {
            return FaceInfo;
        }

        public void setFaceInfo(Integer faceInfo) {
            FaceInfo = faceInfo;
        }

        public Integer getCompareInfo() {
            return CompareInfo;
        }

        public void setCompareInfo(Integer compareInfo) {
            CompareInfo = compareInfo;
        }
    }


    public class PictureData implements Serializable {
        private Integer FacePicture = 1;

        private Integer BackgroundPicture = 1;


        private Integer PersonPhoto = 1;

        public Integer getFacePicture() {
            return FacePicture;
        }

        public void setFacePicture(Integer facePicture) {
            FacePicture = facePicture;
        }

        public Integer getBackgroundPicture() {
            return BackgroundPicture;
        }

        public void setBackgroundPicture(Integer backgroundPicture) {
            BackgroundPicture = backgroundPicture;
        }

        public Integer getPersonPhoto() {
            return PersonPhoto;
        }

        public void setPersonPhoto(Integer personPhoto) {
            PersonPhoto = personPhoto;
        }
    }
}

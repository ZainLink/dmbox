package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public class CaptureInfoRequest implements Serializable {

    private String Name;

    private Long TimeStamp;

    private String Session;

    private Data Data = new Data();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public CaptureInfoRequest.Data getData() {
        return Data;
    }

    public void setData(CaptureInfoRequest.Data data) {
        Data = data;
    }

    public class Data implements Serializable {

        private DeviceInfo DeviceInfo = new DeviceInfo();

        private CaptureInfo CaptureInfo = new CaptureInfo();

        private FaceInfo FaceInfo = new FaceInfo();

        private CompareInfo CompareInfo = new CompareInfo();

        public com.zkzy.zyportal.system.api.entity.dm.DeviceInfo getDeviceInfo() {
            return DeviceInfo;
        }

        public void setDeviceInfo(com.zkzy.zyportal.system.api.entity.dm.DeviceInfo deviceInfo) {
            DeviceInfo = deviceInfo;
        }

        public com.zkzy.zyportal.system.api.entity.dm.CaptureInfo getCaptureInfo() {
            return CaptureInfo;
        }

        public void setCaptureInfo(com.zkzy.zyportal.system.api.entity.dm.CaptureInfo captureInfo) {
            CaptureInfo = captureInfo;
        }

        public com.zkzy.zyportal.system.api.entity.dm.FaceInfo getFaceInfo() {
            return FaceInfo;
        }

        public void setFaceInfo(com.zkzy.zyportal.system.api.entity.dm.FaceInfo faceInfo) {
            FaceInfo = faceInfo;
        }

        public com.zkzy.zyportal.system.api.entity.dm.CompareInfo getCompareInfo() {
            return CompareInfo;
        }

        public void setCompareInfo(com.zkzy.zyportal.system.api.entity.dm.CompareInfo compareInfo) {
            CompareInfo = compareInfo;
        }
    }
}

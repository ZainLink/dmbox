package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/30.
 */
public class HeartbeatRequest implements Serializable {

    private String Name;

    private Long TimeStamp;

    private String Session;

    private Data Data = new Data();

    public HeartbeatRequest.Data getData() {
        return Data;
    }

    public void setData(HeartbeatRequest.Data data) {
        Data = data;
    }

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

    public class Data implements Serializable {

        private String HTTPVersion;

        private String HTTPDate;

        private Integer HeartbeatCount;

        private Integer CaptureCount;

        private DeviceInfo deviceInfo=new DeviceInfo();

        public DeviceInfo getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(DeviceInfo deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public String getHTTPVersion() {
            return HTTPVersion;
        }

        public void setHTTPVersion(String HTTPVersion) {
            this.HTTPVersion = HTTPVersion;
        }

        public String getHTTPDate() {
            return HTTPDate;
        }

        public void setHTTPDate(String HTTPDate) {
            this.HTTPDate = HTTPDate;
        }

        public Integer getHeartbeatCount() {
            return HeartbeatCount;
        }

        public void setHeartbeatCount(Integer heartbeatCount) {
            HeartbeatCount = heartbeatCount;
        }

        public Integer getCaptureCount() {
            return CaptureCount;
        }

        public void setCaptureCount(Integer captureCount) {
            CaptureCount = captureCount;
        }

        public class DeviceInfo implements Serializable {

            private String DeviceId;
            private String DeviceUUID;
            private String DeviceMac;
            private String DeviceIP;
            private Integer ChannelNo;
            private String WebVersion;
            private String CoreVersion;
            private String VersionDate;


            public String getDeviceId() {
                return DeviceId;
            }

            public void setDeviceId(String deviceId) {
                DeviceId = deviceId;
            }

            public String getDeviceUUID() {
                return DeviceUUID;
            }

            public void setDeviceUUID(String deviceUUID) {
                DeviceUUID = deviceUUID;
            }

            public String getDeviceMac() {
                return DeviceMac;
            }

            public void setDeviceMac(String deviceMac) {
                DeviceMac = deviceMac;
            }

            public String getDeviceIP() {
                return DeviceIP;
            }

            public void setDeviceIP(String deviceIP) {
                DeviceIP = deviceIP;
            }

            public Integer getChannelNo() {
                return ChannelNo;
            }

            public void setChannelNo(Integer channelNo) {
                ChannelNo = channelNo;
            }

            public String getWebVersion() {
                return WebVersion;
            }

            public void setWebVersion(String webVersion) {
                WebVersion = webVersion;
            }

            public String getCoreVersion() {
                return CoreVersion;
            }

            public void setCoreVersion(String coreVersion) {
                CoreVersion = coreVersion;
            }

            public String getVersionDate() {
                return VersionDate;
            }

            public void setVersionDate(String versionDate) {
                VersionDate = versionDate;
            }
        }
    }
}

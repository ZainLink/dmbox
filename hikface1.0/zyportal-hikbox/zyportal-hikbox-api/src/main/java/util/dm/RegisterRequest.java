package util.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/29.
 */
public class RegisterRequest implements Serializable {

    private String Name;

    private Long TimeStamp;

    private Data Data = new Data();

    public Data getData() {
        return Data;
    }

    public void setData(RegisterRequest.Data data) {
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

    public class Data implements Serializable {

        private String HTTPVersion;

        private String HTTPDate;

        private DeviceInfo DeviceInfo = new DeviceInfo();

        public DeviceInfo getDeviceInfo() {
            return DeviceInfo;
        }

        public void setDeviceInfo(DeviceInfo deviceInfo) {
            DeviceInfo = deviceInfo;
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


        public class DeviceInfo implements Serializable {

            private String DeviceId;
            private String DeviceUUID;
            private String DeviceMac;
            private String DeviceIP;
            private Integer DeviceType;
            private Integer ChannelNum;
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

            public Integer getDeviceType() {
                return DeviceType;
            }

            public void setDeviceType(Integer deviceType) {
                DeviceType = deviceType;
            }

            public Integer getChannelNum() {
                return ChannelNum;
            }

            public void setChannelNum(Integer channelNum) {
                ChannelNum = channelNum;
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

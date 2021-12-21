package util.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public class DeviceInfo implements Serializable {

    private String DeviceId;

    private String DeviceUUID;

    private String DeviceMac;

    private String DeviceIP;

    private Integer ChannelNo;


    private SubDeviceInfo SubDeviceInfo = new SubDeviceInfo();

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

    public DeviceInfo.SubDeviceInfo getSubDeviceInfo() {
        return SubDeviceInfo;
    }

    public void setSubDeviceInfo(DeviceInfo.SubDeviceInfo subDeviceInfo) {
        SubDeviceInfo = subDeviceInfo;
    }

    public class SubDeviceInfo implements Serializable {

        private String SubDeviceIP;

        private String SubDeviceUUID;

        public String getSubDeviceIP() {
            return SubDeviceIP;
        }

        public void setSubDeviceIP(String subDeviceIP) {
            SubDeviceIP = subDeviceIP;
        }

        public String getSubDeviceUUID() {
            return SubDeviceUUID;
        }

        public void setSubDeviceUUID(String subDeviceUUID) {
            SubDeviceUUID = subDeviceUUID;
        }
    }
}

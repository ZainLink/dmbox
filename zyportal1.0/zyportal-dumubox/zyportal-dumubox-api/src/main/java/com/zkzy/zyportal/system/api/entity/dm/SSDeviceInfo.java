package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
public class SSDeviceInfo implements Serializable {

    private String DeviceId;
    private String DeviceUUID;
    private String DeviceMac;
    private String DeviceIP;



    private Integer ChannelNo;
    private String WebVersion;
    private String CoreVersion;
    private String VersionDate;

    private String Session;

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

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

package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

public class DmCameraB implements Serializable {
    private String deviceuuid;

    private Integer channelno;

    private String subdeviceip;

    private String subdeviceuuid;

    private String subname;

    private String createtime;

    private String remark;

    private String unid;

    private String uname;

    private String protocol;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDeviceuuid() {
        return deviceuuid;
    }

    public void setDeviceuuid(String deviceuuid) {
        this.deviceuuid = deviceuuid == null ? null : deviceuuid.trim();
    }

    public Integer getChannelno() {
        return channelno;
    }

    public void setChannelno(Integer channelno) {
        this.channelno = channelno;
    }

    public String getSubdeviceip() {
        return subdeviceip;
    }

    public void setSubdeviceip(String subdeviceip) {
        this.subdeviceip = subdeviceip == null ? null : subdeviceip.trim();
    }

    public String getSubdeviceuuid() {
        return subdeviceuuid;
    }

    public void setSubdeviceuuid(String subdeviceuuid) {
        this.subdeviceuuid = subdeviceuuid == null ? null : subdeviceuuid.trim();
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname == null ? null : subname.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid == null ? null : unid.trim();
    }
}
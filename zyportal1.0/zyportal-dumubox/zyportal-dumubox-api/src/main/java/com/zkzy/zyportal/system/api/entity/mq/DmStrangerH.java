package com.zkzy.zyportal.system.api.entity.mq;

import java.io.Serializable;
import java.math.BigDecimal;

public class DmStrangerH implements Serializable {
    private String id;

    private BigDecimal faceid;

    private Long channelno;

    private String furl;

    private String burl;

    private String sendtime;

    private String capturetime;

    private String receivetime;

    private String subname;

    private String unid;

    private String uname;

    private String remark;

    private String deviceuuid;

    private String fbase;

    private String bbase;

    private String imgstate;

    public String getImgstate() {
        return imgstate;
    }

    public void setImgstate(String imgstate) {
        this.imgstate = imgstate;
    }

    public String getFbase() {
        return fbase;
    }

    public void setFbase(String fbase) {
        this.fbase = fbase;
    }

    public String getBbase() {
        return bbase;
    }

    public void setBbase(String bbase) {
        this.bbase = bbase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getFaceid() {
        return faceid;
    }

    public void setFaceid(BigDecimal faceid) {
        this.faceid = faceid;
    }

    public Long getChannelno() {
        return channelno;
    }

    public void setChannelno(Long channelno) {
        this.channelno = channelno;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl == null ? null : furl.trim();
    }

    public String getBurl() {
        return burl;
    }

    public void setBurl(String burl) {
        this.burl = burl == null ? null : burl.trim();
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime == null ? null : sendtime.trim();
    }

    public String getCapturetime() {
        return capturetime;
    }

    public void setCapturetime(String capturetime) {
        this.capturetime = capturetime == null ? null : capturetime.trim();
    }

    public String getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime == null ? null : receivetime.trim();
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname == null ? null : subname.trim();
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid == null ? null : unid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDeviceuuid() {
        return deviceuuid;
    }

    public void setDeviceuuid(String deviceuuid) {
        this.deviceuuid = deviceuuid == null ? null : deviceuuid.trim();
    }
}
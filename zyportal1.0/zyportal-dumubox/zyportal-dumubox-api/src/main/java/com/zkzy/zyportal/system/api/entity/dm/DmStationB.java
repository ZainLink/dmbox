package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

public class DmStationB implements Serializable {
    private String unid;

    private String uname;

    private String lname;

    private String ltel;

    private String address;

    private String creater;

    private String modifyer;

    private String createdate;

    private String modifydate;

    private String areacode;

    private String areaname;

    private String account;

    private Integer whitenum;

    private Integer warnnum;

    private Integer boxnum;

    private String lng;

    private String lat;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getWhitenum() {
        return whitenum;
    }

    public void setWhitenum(Integer whitenum) {
        this.whitenum = whitenum;
    }

    public Integer getWarnnum() {
        return warnnum;
    }

    public void setWarnnum(Integer warnnum) {
        this.warnnum = warnnum;
    }

    public Integer getBoxnum() {
        return boxnum;
    }

    public void setBoxnum(Integer boxnum) {
        this.boxnum = boxnum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
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

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname == null ? null : lname.trim();
    }

    public String getLtel() {
        return ltel;
    }

    public void setLtel(String ltel) {
        this.ltel = ltel == null ? null : ltel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getModifyer() {
        return modifyer;
    }

    public void setModifyer(String modifyer) {
        this.modifyer = modifyer == null ? null : modifyer.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate == null ? null : modifydate.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }
}
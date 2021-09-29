package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

public class WxGasEntB implements Serializable {
    private String qid;

    private String areacode;

    private String qname;

    private String xydm;

    private String qleader;

    private String qaddress;

    private String qtel;

    private String lname;

    private String ltel;

    private String lng;

    private String lat;

    private String areaname;

    private Long orders;

    private String bm;

    private String remark;

    private String creater;

    private String modifyer;

    private String createdate;

    private String modifydate;

    private byte[] qint;

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid == null ? null : qid.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname == null ? null : qname.trim();
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm == null ? null : xydm.trim();
    }

    public String getQleader() {
        return qleader;
    }

    public void setQleader(String qleader) {
        this.qleader = qleader == null ? null : qleader.trim();
    }

    public String getQaddress() {
        return qaddress;
    }

    public void setQaddress(String qaddress) {
        this.qaddress = qaddress == null ? null : qaddress.trim();
    }

    public String getQtel() {
        return qtel;
    }

    public void setQtel(String qtel) {
        this.qtel = qtel == null ? null : qtel.trim();
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm == null ? null : bm.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public byte[] getQint() {
        return qint;
    }

    public void setQint(byte[] qint) {
        this.qint = qint;
    }
}
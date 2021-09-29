package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

public class WxGasDstB implements Serializable {
    private String cid;

    private String cname;

    private String caddress;

    private String cleader;

    private String ctel;

    private String creater;

    private String modifyer;

    private String createdate;

    private String modifydate;

    private String lng;

    private String lat;

    private String areacode;

    private String areaname;

    private String czjz;

    private String czjzstr;

    private String qid;

    private String qname;

    private String remark;

    private Long orders;

    private byte[] cint;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress == null ? null : caddress.trim();
    }

    public String getCleader() {
        return cleader;
    }

    public void setCleader(String cleader) {
        this.cleader = cleader == null ? null : cleader.trim();
    }

    public String getCtel() {
        return ctel;
    }

    public void setCtel(String ctel) {
        this.ctel = ctel == null ? null : ctel.trim();
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

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public String getCzjz() {
        return czjz;
    }

    public void setCzjz(String czjz) {
        this.czjz = czjz == null ? null : czjz.trim();
    }

    public String getCzjzstr() {
        return czjzstr;
    }

    public void setCzjzstr(String czjzstr) {
        this.czjzstr = czjzstr == null ? null : czjzstr.trim();
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid == null ? null : qid.trim();
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname == null ? null : qname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public byte[] getCint() {
        return cint;
    }

    public void setCint(byte[] cint) {
        this.cint = cint;
    }
}
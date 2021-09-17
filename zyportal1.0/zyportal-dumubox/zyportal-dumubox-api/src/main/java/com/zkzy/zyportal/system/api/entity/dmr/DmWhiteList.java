package com.zkzy.zyportal.system.api.entity.dmr;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Thinkpad-W530 on 2021/4/21.
 */
public class DmWhiteList implements Serializable {

    private String uuid;

    private String id;

    private String name;

    private String sex;

    private String tel;

    private String imgurl;

    private String nation;

    private String birth;

    private String address;

    private String stLabel;

    private String boxLabel;

    private String stLabelstr;

    private String boxLabelstr;

    private String unid;

    private BigDecimal persontype;

    private Long limittime;

    private String sttm;

    private String endtime;

    private String facename;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStLabel() {
        return stLabel;
    }

    public void setStLabel(String stLabel) {
        this.stLabel = stLabel;
    }

    public String getBoxLabel() {
        return boxLabel;
    }

    public void setBoxLabel(String boxLabel) {
        this.boxLabel = boxLabel;
    }

    public String getStLabelstr() {
        return stLabelstr;
    }

    public void setStLabelstr(String stLabelstr) {
        this.stLabelstr = stLabelstr;
    }

    public String getBoxLabelstr() {
        return boxLabelstr;
    }

    public void setBoxLabelstr(String boxLabelstr) {
        this.boxLabelstr = boxLabelstr;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public BigDecimal getPersontype() {
        return persontype;
    }

    public void setPersontype(BigDecimal persontype) {
        this.persontype = persontype;
    }

    public Long getLimittime() {
        return limittime;
    }

    public void setLimittime(Long limittime) {
        this.limittime = limittime;
    }

    public String getSttm() {
        return sttm;
    }

    public void setSttm(String sttm) {
        this.sttm = sttm;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getFacename() {
        return facename;
    }

    public void setFacename(String facename) {
        this.facename = facename;
    }
}

package com.zkzy.zyportal.system.api.entity.dmr;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Thinkpad-W530 on 2021/4/26.
 */
public class DmBoxWhiteList implements Serializable {


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

    private String uname;

    private String boxLabelstr;

    private String deviceuuid;

    private BigDecimal persontype;

    private String unid;

    private String bindtime;


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

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

    public String getDeviceuuid() {
        return deviceuuid;
    }

    public void setDeviceuuid(String deviceuuid) {
        this.deviceuuid = deviceuuid;
    }

    public BigDecimal getPersontype() {
        return persontype;
    }

    public void setPersontype(BigDecimal persontype) {
        this.persontype = persontype;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public String getBindtime() {
        return bindtime;
    }

    public void setBindtime(String bindtime) {
        this.bindtime = bindtime;
    }
}

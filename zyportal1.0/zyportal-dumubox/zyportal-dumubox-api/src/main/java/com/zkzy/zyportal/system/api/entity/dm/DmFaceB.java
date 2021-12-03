package com.zkzy.zyportal.system.api.entity.dm;

import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class DmFaceB implements Serializable {
    private String uuid;

    private String id;

    private String name;

    private String sex;

    private String tel;

    private String imgurl;

    private String createdate;

    private String modifydate;

    private String creater;

    private String modifyer;

    private String nation;

    private String birth;

    private String address;

    private String stLabel;

    private String boxLabel;

    private String stLabelstr;

    private String boxLabelstr;


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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }


    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getStLabel() {
        return stLabel;
    }

    public void setStLabel(String stLabel) {
        this.stLabel = stLabel == null ? null : stLabel.trim();
    }

    public String getBoxLabel() {
        return boxLabel;
    }

    public void setBoxLabel(String boxLabel) {
        this.boxLabel = boxLabel == null ? null : boxLabel.trim();
    }
}
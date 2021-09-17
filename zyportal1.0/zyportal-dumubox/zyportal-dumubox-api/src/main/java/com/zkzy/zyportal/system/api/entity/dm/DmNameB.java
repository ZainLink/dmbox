package com.zkzy.zyportal.system.api.entity.dm;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.math.BigDecimal;

public class DmNameB implements Serializable {
    private String deviceuuid;

    private BigDecimal persontype;

    private BigDecimal namelistfacenum;

    private String updatetime;

    private String name;

    private String nameid;

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNameid() {
        return nameid;
    }

    public void setNameid(String nameid) {
        this.nameid = nameid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceuuid() {
        return deviceuuid;
    }

    public void setDeviceuuid(String deviceuuid) {
        this.deviceuuid = deviceuuid == null ? null : deviceuuid.trim();
    }

    public BigDecimal getPersontype() {
        return persontype;
    }

    public void setPersontype(BigDecimal persontype) {
        this.persontype = persontype;
    }

    public BigDecimal getNamelistfacenum() {
        return namelistfacenum;
    }

    public void setNamelistfacenum(BigDecimal namelistfacenum) {
        this.namelistfacenum = namelistfacenum;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }
}
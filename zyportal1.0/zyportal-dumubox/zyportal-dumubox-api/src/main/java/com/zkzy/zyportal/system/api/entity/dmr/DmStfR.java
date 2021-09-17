package com.zkzy.zyportal.system.api.entity.dmr;

import java.io.Serializable;
import java.math.BigDecimal;

public class DmStfR implements Serializable {
    private String unid;

    private BigDecimal persontype;

    private String name;

    private BigDecimal num;

    private String lgs;

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid == null ? null : unid.trim();
    }

    public BigDecimal getPersontype() {
        return persontype;
    }

    public void setPersontype(BigDecimal persontype) {
        this.persontype = persontype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getLgs() {
        return lgs;
    }

    public void setLgs(String lgs) {
        this.lgs = lgs == null ? null : lgs.trim();
    }
}
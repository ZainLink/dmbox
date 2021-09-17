package com.zkzy.zyportal.system.api.entity.dmr;

import java.io.Serializable;
import java.math.BigDecimal;

public class DmStfN implements Serializable {
    private String unid;

    private BigDecimal persontype;

    private String uuid;

    private String datatime;

    private Long limittime;

    private String sttm;

    private String endtime;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime == null ? null : datatime.trim();
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
        this.sttm = sttm == null ? null : sttm.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }
}
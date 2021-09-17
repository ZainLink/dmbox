package com.zkzy.zyportal.system.api.entity.dmr;

import java.io.Serializable;
import java.math.BigDecimal;

public class DmStnfR implements Serializable{
    private String unid;

    private String deviceuuid;

    private BigDecimal persontype;

    private String uuid;

    private String bindtime;

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid == null ? null : unid.trim();
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getBindtime() {
        return bindtime;
    }

    public void setBindtime(String bindtime) {
        this.bindtime = bindtime == null ? null : bindtime.trim();
    }
}
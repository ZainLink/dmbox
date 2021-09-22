package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

public class RabbitmqErrors implements Serializable {
    private String id;

    private String queuename;

    private String jsondata;

    private String errortime;

    private String errorreason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQueuename() {
        return queuename;
    }

    public void setQueuename(String queuename) {
        this.queuename = queuename == null ? null : queuename.trim();
    }

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata == null ? null : jsondata.trim();
    }

    public String getErrortime() {
        return errortime;
    }

    public void setErrortime(String errortime) {
        this.errortime = errortime == null ? null : errortime.trim();
    }

    public String getErrorreason() {
        return errorreason;
    }

    public void setErrorreason(String errorreason) {
        this.errorreason = errorreason == null ? null : errorreason.trim();
    }
}
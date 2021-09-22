package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2019/7/10.
 */
public class ApiParam implements Serializable {
    private String type;

    private String name;

    private Boolean isFill;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFill() {
        return isFill;
    }

    public void setFill(Boolean fill) {
        isFill = fill;
    }
}

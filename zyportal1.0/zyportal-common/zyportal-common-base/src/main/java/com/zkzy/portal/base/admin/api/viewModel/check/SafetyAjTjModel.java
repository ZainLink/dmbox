package com.zkzy.portal.base.admin.api.viewModel.check;

import java.io.Serializable;

/**
 * @ClassName SafetyAjTjModel
 * @Description TODO
 * @Author gezb
 * @Date 2020-07-17 15:46
 **/
public class SafetyAjTjModel implements Serializable {
    private String areaname;
    private Integer zs;
    private Integer wwc;
    private Integer ywc;

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getZs() {
        return zs;
    }

    public void setZs(Integer zs) {
        this.zs = zs;
    }

    public Integer getWwc() {
        return wwc;
    }

    public void setWwc(Integer wwc) {
        this.wwc = wwc;
    }

    public Integer getYwc() {
        return ywc;
    }

    public void setYwc(Integer ywc) {
        this.ywc = ywc;
    }
}

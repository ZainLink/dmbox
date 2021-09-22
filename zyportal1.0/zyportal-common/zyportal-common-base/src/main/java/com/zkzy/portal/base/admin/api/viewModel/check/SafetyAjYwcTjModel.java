package com.zkzy.portal.base.admin.api.viewModel.check;

import java.io.Serializable;

/**
 * @ClassName SafetyAjYwcTjModel
 * @Description TODO
 * @Author gezb
 * @Date 2020-07-17 15:46
 **/
public class SafetyAjYwcTjModel implements Serializable {
    private String areaname;
    private Integer wwt;
    private Integer ywt;
    private Integer wxzg;
    private Integer dzg;
    private Integer yzg;

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Integer getWwt() {
        return wwt;
    }

    public void setWwt(Integer wwt) {
        this.wwt = wwt;
    }

    public Integer getYwt() {
        return ywt;
    }

    public void setYwt(Integer ywt) {
        this.ywt = ywt;
    }

    public Integer getWxzg() {
        return wxzg;
    }

    public void setWxzg(Integer wxzg) {
        this.wxzg = wxzg;
    }

    public Integer getDzg() {
        return dzg;
    }

    public void setDzg(Integer dzg) {
        this.dzg = dzg;
    }

    public Integer getYzg() {
        return yzg;
    }

    public void setYzg(Integer yzg) {
        this.yzg = yzg;
    }
}

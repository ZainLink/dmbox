package com.zkzy.portal.base.admin.api.viewModel.check;

import java.io.Serializable;

/**
 * @ClassName SafetyTjModel
 * @Description TODO
 * @Author gezb
 * @Date 2020-07-17 15:44
 **/
public class SafetyTjModel implements Serializable {
    private String name;
    private String areaname;
    private String duty;
    private Integer counts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}

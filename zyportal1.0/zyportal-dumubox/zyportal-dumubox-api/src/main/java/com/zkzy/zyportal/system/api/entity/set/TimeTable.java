package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
public class TimeTable implements Serializable {

    private  Integer Enable;

    private Integer StartHour;

    private  Integer StartMin;

    private Integer EndHour;

    private Integer  EndMin;


    public Integer getEnable() {
        return Enable;
    }

    public void setEnable(Integer enable) {
        Enable = enable;
    }

    public Integer getStartHour() {
        return StartHour;
    }

    public void setStartHour(Integer startHour) {
        StartHour = startHour;
    }

    public Integer getStartMin() {
        return StartMin;
    }

    public void setStartMin(Integer startMin) {
        StartMin = startMin;
    }

    public Integer getEndHour() {
        return EndHour;
    }

    public void setEndHour(Integer endHour) {
        EndHour = endHour;
    }

    public Integer getEndMin() {
        return EndMin;
    }

    public void setEndMin(Integer endMin) {
        EndMin = endMin;
    }
}

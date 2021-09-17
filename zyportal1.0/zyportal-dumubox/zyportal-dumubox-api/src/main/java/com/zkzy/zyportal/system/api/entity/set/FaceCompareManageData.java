package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/4/14.
 */
public class FaceCompareManageData implements Serializable {

    private String Action;

    private Integer ChannelNo;

    private Integer Enable;

    private Integer Similary;

    private List<TimeTable> CompareTime = new ArrayList<>();

    private List<CompareName> CompareNameList = new ArrayList<>();

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public Integer getChannelNo() {
        return ChannelNo;
    }

    public void setChannelNo(Integer channelNo) {
        ChannelNo = channelNo;
    }

    public Integer getEnable() {
        return Enable;
    }

    public void setEnable(Integer enable) {
        Enable = enable;
    }

    public Integer getSimilary() {
        return Similary;
    }

    public void setSimilary(Integer similary) {
        Similary = similary;
    }

    public List<TimeTable> getCompareTime() {
        return CompareTime;
    }

    public void setCompareTime(List<TimeTable> compareTime) {
        CompareTime = compareTime;
    }

    public List<CompareName> getCompareNameList() {
        return CompareNameList;
    }

    public void setCompareNameList(List<CompareName> compareNameList) {
        CompareNameList = compareNameList;
    }
}

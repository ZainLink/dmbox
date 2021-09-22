package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
public class EquipmentModel implements Serializable {
    private String mno;//设备编号(序列号)
    private String stcd;//站点编号
    private String linkedtime;//连接时间
    private String distime;//断开时间
    private String getdatatime;//数据获取时间
    private String data;//数据

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getLinkedtime() {
        return linkedtime;
    }

    public void setLinkedtime(String linkedtime) {
        this.linkedtime = linkedtime;
    }

    public String getDistime() {
        return distime;
    }

    public void setDistime(String distime) {
        this.distime = distime;
    }

    public String getGetdatatime() {
        return getdatatime;
    }

    public void setGetdatatime(String getdatatime) {
        this.getdatatime = getdatatime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

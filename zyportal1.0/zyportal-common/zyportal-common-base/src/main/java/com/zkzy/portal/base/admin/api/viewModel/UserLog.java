package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by admin on 2017/11/23.
 */
public class UserLog implements Serializable {
    private String ip;
    private String time;
    private String realname;
    private String name;
    private  String useragent;

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

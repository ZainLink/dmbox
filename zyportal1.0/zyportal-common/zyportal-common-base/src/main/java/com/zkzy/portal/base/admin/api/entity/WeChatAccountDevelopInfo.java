package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

public class WeChatAccountDevelopInfo implements Serializable {
    private String id;

    private String account;

    private String password;

    private String name;

    private String typecode;

    private String appid;

    private String appsecret;

    private String iplist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getIplist() {
        return iplist;
    }

    public void setIplist(String iplist) {
        this.iplist = iplist == null ? null : iplist.trim();
    }
}
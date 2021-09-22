package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/10.
 */
public class LogTop implements Serializable{
    private String permission;
    private String count;
    private String url;
    private String name;
    private String status;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LogTop{" +
                "permission='" + permission + '\'' +
                ", count='" + count + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

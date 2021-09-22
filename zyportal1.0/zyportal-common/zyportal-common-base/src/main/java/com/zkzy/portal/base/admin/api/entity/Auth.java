package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2019/7/10.
 */
public class Auth implements Serializable {

    private String authName;
    private String authUrl;
    private String authUniqueMark;
    private Date createTime;
    private String methodType;
    private String mainController;

    private List<ApiParam> apiParamList;

    public List<ApiParam> getApiParamList() {
        return apiParamList;
    }

    public void setApiParamList(List<ApiParam> apiParamList) {
        this.apiParamList = apiParamList;
    }

    public String getMainController() {
        return mainController;
    }

    public void setMainController(String mainController) {
        this.mainController = mainController;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getAuthUniqueMark() {
        return authUniqueMark;
    }

    public void setAuthUniqueMark(String authUniqueMark) {
        this.authUniqueMark = authUniqueMark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
}
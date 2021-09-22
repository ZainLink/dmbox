package com.zkzy.portal.base.admin.api.viewModel.check;

import java.io.Serializable;


public class CheckDepSafetyAutoTaskModel implements Serializable {
    private static final long serialVersionUID = -8930757930789824789L;
    public static final String CHECK_DEP_SAFETY_TASK_KEY = "checkDepSafetyTask";

    // 单位id
    private String companyId;
    // 创建时间
    private String createdTime;
    // 任务类型
    private String taskType;
    //任务类型名称
    private String taskTypeName;
    // 操作人
    private String operator;
    // 操作人id
    private String operatorId;
    // 任务名称
    private String taskName;
    // 任务描述
    private String description;
    // 商户名称
    private String shname;
    // 所属区域name
    private String areaname;
    // 所属区域code
    private String areacode;
    // 负责人
    private String uname;
    // 电话
    private String uphone;
    // 商户地址
    private String uaddress;
    // 商户id
    private String shid;
    // 巡检任务名称描述
    private String inspectionName;
    // 任务指派人类型
    private String taskAssignorType;

    @Override
    public String toString() {
        return "CheckDepSafetyAutoTaskModel{" +
                "companyId='" + companyId + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", taskType='" + taskType + '\'' +
                ", taskTypeName='" + taskTypeName + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", shname='" + shname + '\'' +
                ", areaname='" + areaname + '\'' +
                ", areacode='" + areacode + '\'' +
                ", uname='" + uname + '\'' +
                ", uphone='" + uphone + '\'' +
                ", uaddress='" + uaddress + '\'' +
                ", shid='" + shid + '\'' +
                ", inspectionName='" + inspectionName + '\'' +
                ", taskAssignorType='" + taskAssignorType + '\'' +
                '}';
    }

    public String getTaskAssignorType() {
        return taskAssignorType;
    }

    public void setTaskAssignorType(String taskAssignorType) {
        this.taskAssignorType = taskAssignorType;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShname() {
        return shname;
    }

    public void setShname(String shname) {
        this.shname = shname;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getShid() {
        return shid;
    }

    public void setShid(String shid) {
        this.shid = shid;
    }

    public String getInspectionName() {
        return inspectionName;
    }

    public void setInspectionName(String inspectionName) {
        this.inspectionName = inspectionName;
    }
}

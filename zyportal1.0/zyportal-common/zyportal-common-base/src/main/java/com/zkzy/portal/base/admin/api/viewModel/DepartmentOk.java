package com.zkzy.portal.base.admin.api.viewModel;

import java.util.Map;

public class DepartmentOk {
    private boolean isOk=true;
    private String departmentId=null;
    private Map<String,Object> message=null;

    public DepartmentOk(boolean isOk, String departmentId) {
        this.isOk = isOk;
        this.departmentId = departmentId;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}

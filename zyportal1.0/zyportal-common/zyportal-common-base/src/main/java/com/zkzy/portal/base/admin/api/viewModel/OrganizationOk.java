package com.zkzy.portal.base.admin.api.viewModel;

import java.util.Map;

public class OrganizationOk {
    private boolean isOk=true;
    private String areacodes=null;
    private Map<String,Object> message=null;

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getAreacodes() {
        return areacodes;
    }

    public void setAreacodes(String areacodes) {
        this.areacodes = areacodes;
    }

    public OrganizationOk(boolean isOk, String areacodes) {
        this.isOk = isOk;
        this.areacodes = areacodes;
    }

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
}

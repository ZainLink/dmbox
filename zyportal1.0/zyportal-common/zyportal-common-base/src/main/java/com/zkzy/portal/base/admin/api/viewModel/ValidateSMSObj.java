package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2018/11/19.
 */
public class ValidateSMSObj implements Serializable{
    private String restId;
    private String validateCode;
    private String validateTime;

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(String validateTime) {
        this.validateTime = validateTime;
    }
}

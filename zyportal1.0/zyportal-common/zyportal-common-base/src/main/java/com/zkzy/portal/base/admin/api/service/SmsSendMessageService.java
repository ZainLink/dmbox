package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;

/**
 * Created by Thinkpad on 2018/11/19.
 */
public interface SmsSendMessageService {
    CodeObject sendSMSVerification(String resId, String mobileNum);
    CodeObject validateSMS(String resId, String validateCode);
    void sendselMsg(String totels);
    Boolean validateCode(String mobileNum, String validateCode);
}

package com.zkzy.zyportal.system.api.exception;


import com.zkzy.zyportal.system.api.exception.base.BusinessException;

/**
 * 无效验证码
 *
 * @author admin
 */
public class InvalidCaptchaException extends BusinessException {

    public InvalidCaptchaException(String message) {
        super(message);
    }

}

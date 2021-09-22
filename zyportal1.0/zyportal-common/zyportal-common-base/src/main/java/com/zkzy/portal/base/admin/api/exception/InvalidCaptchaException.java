package com.zkzy.portal.base.admin.api.exception;


import com.zkzy.portal.base.admin.api.exception.base.BusinessException;

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

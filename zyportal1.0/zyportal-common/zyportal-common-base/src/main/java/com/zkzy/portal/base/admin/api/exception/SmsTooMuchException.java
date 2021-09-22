package com.zkzy.portal.base.admin.api.exception;


import com.zkzy.portal.base.admin.api.exception.base.BusinessException;

/**
 * 短信发送太频繁
 *
 * @author admin
 */
public class SmsTooMuchException extends BusinessException {

    public SmsTooMuchException(String message) {
        super(message);
    }

}

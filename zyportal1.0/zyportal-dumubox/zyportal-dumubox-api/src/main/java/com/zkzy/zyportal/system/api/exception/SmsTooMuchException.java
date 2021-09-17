package com.zkzy.zyportal.system.api.exception;


import com.zkzy.zyportal.system.api.exception.base.BusinessException;

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

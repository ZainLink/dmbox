package com.zkzy.zyportal.system.api.exception;


import com.zkzy.zyportal.system.api.exception.base.BusinessException;

/**
 * 手机号码不合法
 *
 * @author admin
 */
public class IllegalMobileException extends BusinessException {

    public IllegalMobileException(String message) {
        super(message);
    }

}

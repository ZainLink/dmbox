package com.zkzy.zyportal.system.api.exception;


import com.zkzy.zyportal.system.api.exception.base.BusinessException;

/**
 * 用户已存在
 *
 * @author admin
 */
public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }

}

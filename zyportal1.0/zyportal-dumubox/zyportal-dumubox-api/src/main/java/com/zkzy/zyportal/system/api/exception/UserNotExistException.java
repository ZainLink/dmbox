package com.zkzy.zyportal.system.api.exception;


import com.zkzy.zyportal.system.api.exception.base.BusinessException;

/**
 * 用户未存在
 *
 * @author admin
 */
public class UserNotExistException extends BusinessException {

    public UserNotExistException(String message) {
        super(message);
    }

}

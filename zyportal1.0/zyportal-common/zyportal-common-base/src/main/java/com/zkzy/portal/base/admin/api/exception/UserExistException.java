package com.zkzy.portal.base.admin.api.exception;


import com.zkzy.portal.base.admin.api.exception.base.BusinessException;

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

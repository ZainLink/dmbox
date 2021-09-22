package com.zkzy.portal.base.admin.api.exception;


import com.zkzy.portal.base.admin.api.exception.base.BusinessException;

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

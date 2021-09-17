package com.zkzy.zyportal.system.api.exception.base;

/**
 * 业务异常.
 *
 * @author admin
 */
public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}
package com.zkzy.portal.base.admin.api.exception;

/**
 * Created by WH on 2018/11/20.
 */
public class ExecuteResultException extends Exception {

    private int errcode;

    private String errmsg;

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public ExecuteResultException() {
        super();
    }

    public ExecuteResultException(String message) {
        super(message);
    }

    public ExecuteResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecuteResultException(Throwable cause) {
        super(cause);
    }

    protected ExecuteResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ExecuteResultException(int errcode,String errmsg, String message) {
        super(message);
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

}

package com.zkzy.portal.common.quartz;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
public class QuartzServiceException extends RuntimeException {
    private static final long serialVersionUID = 8624944628363400977L;

    public QuartzServiceException() {
        super();
    }

    public QuartzServiceException(String message, Throwable cause,
                            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public QuartzServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuartzServiceException(String message) {
        super(message);
    }

    public QuartzServiceException(Throwable cause) {
        super(cause);
    }
}

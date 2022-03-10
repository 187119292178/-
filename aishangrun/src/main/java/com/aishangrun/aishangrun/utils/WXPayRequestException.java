package com.aishangrun.aishangrun.utils;

public class WXPayRequestException extends RuntimeException {

    private static final long serialVersionUID = 1141965154035224547L;

    public WXPayRequestException() {
        super();
    }

    public WXPayRequestException(String message) {
        super(message);
    }

    public WXPayRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public WXPayRequestException(Throwable cause) {
        super(cause);
    }

    public WXPayRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

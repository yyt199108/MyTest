package com.test.mytest.api.exception;

/**
 * Created by admin on 2018-01-23.
 */

public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(Throwable throwable , int code, String message1) {
        super(throwable);
        this.code = code;
        this.message = message1;
    }

    public ApiException(Throwable throwable , int code ) {
        super(throwable);
        this.code = code;
    }
}

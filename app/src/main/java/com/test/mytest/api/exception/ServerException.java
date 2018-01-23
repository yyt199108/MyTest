package com.test.mytest.api.exception;

/**
 * Created by admin on 2018-01-23.
 */

public class ServerException extends RuntimeException {
    public int code;
    public String message;
    public ServerException(int code,String message){
        this.code=code;
        this.message=message;
    }
}

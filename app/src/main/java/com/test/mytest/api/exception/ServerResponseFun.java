package com.test.mytest.api.exception;

import com.test.mytest.api.response.BaseRes;

import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * Created by admin on 2018-01-23.
 */

public class ServerResponseFun<T> implements Function<BaseRes,T> {
    @Override
    public T apply(BaseRes baseRes) throws Exception {
        //返回码，如果不是0，在证明服务器端返回错误信息了，根据与服务器约定好的错误码去解析异常
        if(baseRes.result!=1){
            //如果服务器端有错误信息返回，那么抛出异常，让下面的方法去捕获异常做统一处理
            throw new ServerException(baseRes.result,baseRes.msg);
        }
        return (T) baseRes;
    }
}

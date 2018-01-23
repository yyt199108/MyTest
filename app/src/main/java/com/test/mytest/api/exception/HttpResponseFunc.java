package com.test.mytest.api.exception;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by admin on 2018-01-23.
 */

public class HttpResponseFunc<T> implements Function<Throwable,Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        //ExceptionEngine为处理异常的驱动器
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}

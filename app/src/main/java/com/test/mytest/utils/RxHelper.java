package com.test.mytest.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by admin on 2017-11-21.
 */

public class RxHelper {
    private RxHelper(){
        throw new AssertionError();
    }

    /**
     * 倒计时
     * @param time
     * @return
     */
    public static Observable<Integer> countdown(int time){
        if(time<0){
            time=0;
        }
        final int countTime=time;
        return Observable.interval(0,1, TimeUnit.SECONDS)
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime-increaseTime.intValue();
                    }
                });
    }
}

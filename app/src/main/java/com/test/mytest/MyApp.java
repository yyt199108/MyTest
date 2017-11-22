package com.test.mytest;

import android.app.Application;
import android.content.Context;

/**
 * Created by yyt19 on 2017/11/22.
 */

public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext=getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}

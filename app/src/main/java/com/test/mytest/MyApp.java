package com.test.mytest;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.utils.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by yyt19 on 2017/11/22.
 */

public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext=getApplicationContext();

        Utils.init(this);

        Fresco.initialize(this);
    }

    public static Context getContext(){
        return mContext;
    }
}

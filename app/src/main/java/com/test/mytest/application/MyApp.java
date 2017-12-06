package com.test.mytest.application;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.utils.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.test.mytest.api.RetrofitService;
import com.test.mytest.injector.components.ApplicationComponent;
import com.test.mytest.injector.components.DaggerApplicationComponent;
import com.test.mytest.injector.module.ApplicationModule;

/**
 * Created by yyt19 on 2017/11/22.
 */

public class MyApp extends Application {

    public static Context mContext;

    private static ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        _initRetrofit();
        _initInjector();
        _initConfig();

    }

    private void _initRetrofit() {
        RetrofitService.init();
    }

    private void _initInjector() {
        //这里不做注入操作，只提供一些全局单例数据
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }

    private void _initConfig() {

        Utils.init(this);

        Fresco.initialize(this);
    }

    public static Context getContext() {
        return mContext;
    }
}

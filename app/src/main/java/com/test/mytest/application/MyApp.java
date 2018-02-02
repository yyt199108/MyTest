package com.test.mytest.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.utils.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.test.mytest.api.RetrofitService;
import com.test.mytest.injector.components.ApplicationComponent;
import com.test.mytest.injector.components.DaggerApplicationComponent;
import com.test.mytest.injector.module.ApplicationModule;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.module.base.IBasePresenter;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.LinkedList;

/**
 * Created by yyt19 on 2017/11/22.
 */

public class MyApp extends Application {

    {
        Config.DEBUG = true;
//        PlatformConfig.setWeixin("","");
////        PlatformConfig.setWeixin("11111","11111");
//        PlatformConfig.setQQZone("2222","22222");
////        PlatformConfig.setSinaWeibo("","","");

        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

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

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private void _initConfig() {

        Utils.init(this);

        Fresco.initialize(this);

        UMShareAPI.get(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

    private static LinkedList<Activity> activities = new LinkedList<>();

    public static void add(Activity activity) {
        activities.add(activity);
    }

    public static void remove(Activity activity) {
        activities.remove(activity);
    }

    public static void clearAct() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
}

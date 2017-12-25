package com.test.mytest.api.model;

import com.test.mytest.api.ILoginApi;
import com.test.mytest.api.bean.UserBean;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.application.MyApp;
import com.test.mytest.injector.components.DaggerApplicationComponent;
import com.test.mytest.injector.module.ApplicationModule;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by admin on 2017-12-25.
 */

public class LoginModel {
    @Inject
    Retrofit retrofit;

    public LoginModel() {
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule((MyApp) MyApp.getContext())).build()
                .inject(this);
    }

    public Observable<BaseBeanRes<UserBean>> userLogin(Map<String, String> map) {
        return retrofit.create(ILoginApi.class).userLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

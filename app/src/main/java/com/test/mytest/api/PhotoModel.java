package com.test.mytest.api;

import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.application.MyApp;
import com.test.mytest.injector.components.DaggerApplicationComponent;
import com.test.mytest.injector.module.ApplicationModule;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2017-11-27.
 */

public class PhotoModel {
    @Inject
    Retrofit retrofit;

    public PhotoModel(){
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule((MyApp) MyApp.getContext())).build().inject(this);
    }

    public Observable<BaseListRes<PhotoInfoBean>> getMainPetPhotoList() {
        return retrofit.create(IPhotoApi.class).getMainPetPhotoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}

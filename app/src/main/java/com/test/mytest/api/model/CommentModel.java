package com.test.mytest.api.model;

import com.test.mytest.api.ICommentApi;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.application.MyApp;
import com.test.mytest.injector.components.DaggerApplicationComponent;
import com.test.mytest.injector.module.ApplicationModule;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by admin on 2017-11-29.
 */

public class CommentModel {
    @Inject
    Retrofit retrofit;

    public CommentModel() {
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule((MyApp) MyApp.getContext()))
                .build()
                .inject(this);
    }

    public Observable<BaseListRes<CommentBean>> getPhotoDetailCommentList(){
        return retrofit.create(ICommentApi.class).getPhotoDetailCommentList();
    }
}

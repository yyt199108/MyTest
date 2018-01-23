package com.test.mytest.api.model;

import com.test.mytest.api.IPhotoApi;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.exception.HttpResponseFunc;
import com.test.mytest.api.exception.ServerResponseFun;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.api.response.BaseRes;
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
 * Created by admin on 2017-11-27.
 */

public class PhotoModel {
    @Inject
    Retrofit retrofit;

    public PhotoModel(){
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule((MyApp) MyApp.getContext())).build().inject(this);
    }

    public Observable<BaseListRes<PhotoInfoBean>> getMainPetPhotoList(int pageNum,int pageSize,String userId,String token,String type) {
        return retrofit.create(IPhotoApi.class).getMainPetPhotoList(pageNum,pageSize,userId,token,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<BaseBeanRes<PhotoInfoBean>> getDetailPetPhoto(String userId,String token,int id){
        return retrofit.create(IPhotoApi.class).getDetailPetPhoto(userId,token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //拦截服务器返回的错误
                .map(new ServerResponseFun<BaseBeanRes<PhotoInfoBean>>())
                .onErrorResumeNext(new HttpResponseFunc());
    }

    //点赞
    public Observable<BaseRes> like(Map<String,String> map){
        return retrofit.create(IPhotoApi.class).like(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //拦截服务器返回的错误
                .map(new ServerResponseFun<BaseRes>())
                .onErrorResumeNext(new HttpResponseFunc());
    }
}

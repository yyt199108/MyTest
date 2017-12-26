package com.test.mytest.injector.module;

import android.content.Context;

import com.test.mytest.api.ILoginApi;
import com.test.mytest.api.IPhotoApi;
import com.test.mytest.api.RetrofitService;
import com.test.mytest.application.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by admin on 2017-11-24.
 */
@Module
public class ApplicationModule {

    private MyApp mApplication;

    public ApplicationModule(MyApp application) {
        this.mApplication = application;

    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return RetrofitService.init();
    }

//    @Singleton
//    @Provides
//    IPhotoApi provideIPhotoApi(Retrofit retrofit) {
//        return retrofit.create(IPhotoApi.class);
//    }
//
//    @Singleton
//    @Provides
//    ILoginApi provideILoginApi(Retrofit retrofit){
//        return retrofit.create(ILoginApi.class);
//    }
}

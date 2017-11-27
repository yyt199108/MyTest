package com.test.mytest.injector.components;

import android.content.Context;

import com.test.mytest.api.IPhotoApi;
import com.test.mytest.api.PhotoModel;
import com.test.mytest.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 2017-11-24.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    IPhotoApi getIPhotoApi();

    Context getContext();

    void inject(PhotoModel photoModel);
}

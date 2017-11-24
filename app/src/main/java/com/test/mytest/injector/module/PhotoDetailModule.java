package com.test.mytest.injector.module;

import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.photo.detail.PhotoDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-24.
 */
@Module
public class PhotoDetailModule {

    public PhotoDetailModule() {

    }

    @Provides
    IBasePresenter providePresenter() {
        return new PhotoDetailPresenter();
    }
}

package com.test.mytest.injector.module;

import com.test.mytest.module.base.IBasePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-22.
 * 图片列表界面 module
 */

@Module
public class PhotoListModule {

    @Provides
    public IBasePresenter providePresenter(){
        return new PhotoListPresenter(mView);
    }
}

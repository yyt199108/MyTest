package com.test.mytest.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.adapter.PhotoListAdapter;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.IBaseView;
import com.test.mytest.module.photo.main.PhotoListContract;
import com.test.mytest.module.photo.main.PhotoListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-22.
 * 图片列表界面 module
 */

@Module
public class PhotoListModule {

    private PhotoListContract.View mView;
    public PhotoListModule(PhotoListContract.View view){
        this.mView=view;
    }
    @Provides
    public IBasePresenter providePresenter(){
        return new PhotoListPresenter(mView);
    }

    @Provides
    public BaseQuickAdapter provideAdapter(){
        return new PhotoListAdapter();
    }
}

package com.test.mytest.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.adapter.CommentListAdapter;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.photo.detail.PhotoDetailContract;
import com.test.mytest.module.photo.detail.PhotoDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-24.
 */
@Module
public class PhotoDetailModule {

    private PhotoDetailContract.View mView;

    public PhotoDetailModule(PhotoDetailContract.View view) {
        this.mView = view;
    }
    @Provides
    BaseQuickAdapter provideAdapter(){
        return new CommentListAdapter();
    }

    @Provides
    IBasePresenter providePresenter() {
        return new PhotoDetailPresenter(mView);
    }
}

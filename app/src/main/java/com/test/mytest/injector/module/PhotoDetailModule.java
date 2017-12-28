package com.test.mytest.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.adapter.CommentListAdapter;
import com.test.mytest.api.model.PhotoModel;
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

    private CommentListAdapter.OnCommentClickListener mListener;
    private int mPhotoId;
    private PhotoDetailContract.View mView;

    public PhotoDetailModule(PhotoDetailContract.View view,int photoId, CommentListAdapter.OnCommentClickListener listener) {
        this.mView = view;
        this.mPhotoId=photoId;
        this.mListener=listener;
    }
    @Provides
    CommentListAdapter provideAdapter(){
        return new CommentListAdapter(mListener);
    }

    @Provides
    PhotoDetailContract.Presenter providePresenter() {
        return new PhotoDetailPresenter(mView,mPhotoId);
    }

    @Provides
    PhotoDetailContract.View provideView(){
        return mView;
    }
}

package com.test.mytest.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.adapter.CommentListAdapter;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.comment.CommentListContract;
import com.test.mytest.module.comment.CommentListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yyt19 on 2017/11/28.
 */
@Module
public class CommentListModule {
    private CommentListAdapter.OnCommentClickListener mListener;
    private int mPhotoId;
    private CommentListContract.View mView;

    public CommentListModule(CommentListContract.View view, int photoId, CommentListAdapter.OnCommentClickListener listener) {
        this.mView = view;
        this.mPhotoId=photoId;
        this.mListener=listener;
    }

    @Provides
    CommentListAdapter provideAdapter() {
        return new CommentListAdapter(mListener);
    }

    @Provides
    CommentListPresenter providePresenter() {
        return new CommentListPresenter(mView,mPhotoId);
    }
}

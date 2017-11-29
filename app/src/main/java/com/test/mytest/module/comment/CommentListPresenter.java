package com.test.mytest.module.comment;

import com.test.mytest.api.model.CommentModel;

/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListPresenter implements CommentListContract.Presenter {
    private CommentModel mModel;
    private CommentListContract.View mView;

    public CommentListPresenter(CommentListContract.View view) {
        this.mView = view;
        this.mModel = new CommentModel();
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}

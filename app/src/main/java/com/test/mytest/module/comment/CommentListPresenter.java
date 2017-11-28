package com.test.mytest.module.comment;

/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListPresenter implements CommentListContract.Presenter {
    private CommentListContract.View mView;

    public CommentListPresenter(CommentListContract.View view) {
        this.mView = view;
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}

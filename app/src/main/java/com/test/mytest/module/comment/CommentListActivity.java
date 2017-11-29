package com.test.mytest.module.comment;

import android.content.Context;
import android.content.Intent;

import com.test.mytest.R;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.injector.components.DaggerCommentListComponent;
import com.test.mytest.injector.module.CommentListModule;
import com.test.mytest.module.base.BaseActivity;

import java.util.List;

/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListActivity extends BaseActivity implements CommentListContract.View {
    private static final String TAG_COMMENT_ID = "commentIdTag";

    public static void startActivity(Context fromContext, String commentId) {
        Intent intent = new Intent(fromContext, CommentListActivity.class);
        intent.putExtra(TAG_COMMENT_ID, commentId);
        fromContext.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_comment_list;
    }

    @Override
    protected void initInjector() {
        DaggerCommentListComponent.builder()
                .commentListModule(new CommentListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initTitleView();
    }

    private void initTitleView() {
        if (mTitle != null) {
            mTitle.setText("全部评论");
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<CommentBean> data) {

    }

    @Override
    public void loadMoreData(List<CommentBean> data) {

    }

    @Override
    public void loadNoData() {

    }


}

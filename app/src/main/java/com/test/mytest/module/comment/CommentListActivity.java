package com.test.mytest.module.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.R;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.injector.components.DaggerCommentListComponent;
import com.test.mytest.injector.module.CommentListModule;
import com.test.mytest.module.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListActivity extends BaseActivity implements CommentListContract.View {
    private static final String TAG_COMMENT_ID = "commentIdTag";

    @Inject
    BaseQuickAdapter mAdapter;

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
        initRec();
    }

    private void initRec() {
        if (mRecyView != null) {
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyView.setLayoutManager(manager);
            mRecyView.setAdapter(mAdapter);
            //设置adapter
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
                    mPresenter.getMoreData();
                }
            }, mRecyView);
        }
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
        mAdapter.setNewData(data);
    }

    @Override
    public void loadMoreData(List<CommentBean> data) {

    }

    @Override
    public void loadNoData() {

    }

    @Override
    public void hasNoMoreData() {
        mAdapter.loadMoreEnd();
    }

    @OnClick({R.id.back_img_lay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_img_lay:
                finish();
                break;
        }
    }

}

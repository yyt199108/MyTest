package com.test.mytest.module.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.R;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.injector.components.DaggerCommentListComponent;
import com.test.mytest.injector.module.CommentListModule;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.utils.PrefUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListActivity extends BaseActivity implements CommentListContract.View {
    private static final String TAG_COMMENT_ID = "commentIdTag";
    private static final String TAG_COMMENT_CONTENT = "commentContentTag";

    @Inject
    BaseQuickAdapter mAdapter;

    public static void startCommentListActivity(Context fromContext, String commentId, String commentContent) {
        Intent intent = new Intent(fromContext, CommentListActivity.class);
        intent.putExtra(TAG_COMMENT_ID, commentId);
        intent.putExtra(TAG_COMMENT_CONTENT, commentContent);
        fromContext.startActivity(intent);
    }

    public static void startCommentListActivity(Context fromContext, String commentId) {
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
        if(getIntent().hasExtra(TAG_COMMENT_CONTENT)&& !TextUtils.isEmpty(getIntent().getStringExtra(TAG_COMMENT_CONTENT))){
            String commentContent = getIntent().getStringExtra(TAG_COMMENT_CONTENT);
            adapterAddComment(commentContent);
        }
    }

    private void adapterAddComment(String commentContent){
        CommentBean commentBean=(CommentBean)mAdapter.getItem(3);
        commentBean.showCommentTypeTag=false;
        commentBean.commentTypeName = "最新评论";



        CommentBean newComment=new CommentBean();
        newComment.showCommentTypeTag=true;
        newComment.commentTypeName = "最新评论";
        newComment.commentContent=commentContent;
        AccountBean accountBean = PrefUtils.getUserInfo();
        if(accountBean!=null){
            newComment.nickName=accountBean.petName;
            newComment.commentLocation=accountBean.location;
        }
        newComment.createTime = "刚刚";
        mAdapter.addData(3,newComment);
        mAdapter.notifyItemChanged(4);
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

    @BindView(R.id.et_apply_comment)
    EditText mEtComment;

    @OnClick({R.id.back_img_lay,R.id.tv_apply})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_img_lay:
                finish();
                break;
            case R.id.tv_apply:
                adapterAddComment(mEtComment.getText().toString());
                mEtComment.setText("");
                break;
        }
    }

}

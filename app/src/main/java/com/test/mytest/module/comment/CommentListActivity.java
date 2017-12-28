package com.test.mytest.module.comment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.utils.KeyboardUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.adapter.CommentListAdapter;
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

public class CommentListActivity extends BaseActivity<CommentListPresenter> implements CommentListContract.View, CommentListAdapter.OnCommentClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private static final String TAG_PHOTO_ID = "commentIdTag";
    private static final String TAG_COMMENT_CONTENT = "commentContentTag";
    private static final String TAG_DEFENDANT_NICK_NAME = "defendantNickName";
    private static final String TAG_DEFENDANT_ID = "defendantId";
    private int mPhotoId;

    private String mDefendantId;
    private String mDefendantName;

    @Inject
    CommentListAdapter mAdapter;

    public static void startCommentListActivity(Context fromContext, int photoId, String commentContent,String defendantId,String defendantNickName) {
        Intent intent = new Intent(fromContext, CommentListActivity.class);
        intent.putExtra(TAG_PHOTO_ID, photoId);
        intent.putExtra(TAG_COMMENT_CONTENT, commentContent);
        intent.putExtra(TAG_DEFENDANT_ID, defendantId);
        intent.putExtra(TAG_DEFENDANT_NICK_NAME, defendantNickName);
        fromContext.startActivity(intent);
    }

    public static void startCommentListActivity(Context fromContext, int photoId) {
        Intent intent = new Intent(fromContext, CommentListActivity.class);
        intent.putExtra(TAG_PHOTO_ID, photoId);
        fromContext.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_comment_list;
    }

    @Override
    protected void initInjector() {
        mPhotoId=getIntent().getIntExtra(TAG_PHOTO_ID, -1);
        DaggerCommentListComponent.builder()
                .commentListModule(new CommentListModule(this,mPhotoId,this))
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
            mAdapter.setOnItemChildClickListener(this);
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
            String defendantId = getIntent().getStringExtra(TAG_DEFENDANT_ID);
            String defendantNickName = getIntent().getStringExtra(TAG_DEFENDANT_NICK_NAME);
            adapterAddComment(commentContent,defendantId,defendantNickName);
        }
    }

    private void adapterAddComment(String commentContent,String defendantId,String defendantNickName){
//        CommentBean commentBean=(CommentBean)mAdapter.getItem(3);
//        commentBean.showCommentTypeTag=false;
//        commentBean.commentTypeName = "最新评论";
//
//        CommentBean newComment=new CommentBean();
//        newComment.showCommentTypeTag=true;
//        newComment.commentTypeName = "最新评论";
//        newComment.content =commentContent;
//        AccountBean accountBean = PrefUtils.getUserInfo();
//        if(accountBean!=null){
//            newComment.nickname =accountBean.petName;
//            newComment.commentLocation=accountBean.location;
//        }
//        newComment.createTime = "刚刚";
//        mAdapter.addData(3,newComment);
//        mAdapter.notifyItemChanged(4);

        mPresenter.addComment(String.valueOf(mPhotoId),commentContent,defendantId);
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
                adapterAddComment(mEtComment.getText().toString(),mDefendantId,mDefendantName);
                mEtComment.setText("");
                break;
        }
    }

    @Override
    public void onClickDefendant(String defendantId, String defendtantNickName) {
        ToastUtils.showLongToastSafe(defendantId+","+defendtantNickName);
    }

    @Override
    public void onClickComment(String commentUserId, String commentUserNickName) {
        setDefendantInfo(commentUserId,commentUserNickName);
    }

    @Override
    public void clearDefendantInfo() {
        mDefendantId=null;
        mDefendantName=null;
    }

    @Override
    public void setDefendantInfo(String defendantId, String defendantNickName) {
        mDefendantId=defendantId;
        mDefendantName=defendantNickName;
        mEtComment.setHint("@"+mDefendantName);
        mEtComment.setFocusableInTouchMode(true);
        mEtComment.setFocusable(true);
        mEtComment.requestFocus();
        if(!isSoftShowing()) {
            KeyboardUtils.showSoftInput(mEtComment);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int id = view.getId();
        switch (id){
            case R.id.tv_pet_nick://头像
                KLog.e("click item");
                break;
        }
    }
}

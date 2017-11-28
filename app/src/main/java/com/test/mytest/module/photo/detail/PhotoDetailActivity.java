package com.test.mytest.module.photo.detail;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.test.mytest.R;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoDetailBean;
import com.test.mytest.injector.components.DaggerPhotoDetailComponent;
import com.test.mytest.injector.module.PhotoDetailModule;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.module.comment.CommentListActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017-11-23.
 */

public class PhotoDetailActivity extends BaseActivity implements PhotoDetailContract.View {

    @Inject
    BaseQuickAdapter mAdapter;

    @BindView(R.id.sdv_pet_head_photo)
    SimpleDraweeView mSdvPetHead;
    @BindView(R.id.tv_pet_nick)
    TextView mTvPetNick;
    @BindView(R.id.tv_do_attention)
    TextView mTvAttention;
    @BindView(R.id.tv_photo_content)
    TextView mTvPhotoContent;
    @BindView(R.id.sdv_photo)
    SimpleDraweeView mSdvPhoto;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoDetailComponent.builder()
                .photoDetailModule(new PhotoDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initTitleView();
        initRecyView();
    }

    private void initTitleView() {
        if (mTitle != null) {
            mTitle.setText("图片详情");
        }
        if (mRightLay != null) {
            mRightLay.setVisibility(View.VISIBLE);
        }
        if (mRightTv != null) {
            mRightTv.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_comment_bg));
            mRightTv.setTextColor(Color.BLACK);
        }
    }

    private void initRecyView() {
        mRecyView.setNestedScrollingEnabled(false);
        mRecyView.setFocusableInTouchMode(false);
        mRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyView.setAdapter(mAdapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @OnClick({R.id.tv_like, R.id.sdv_pet_head_photo, R.id.tv_pet_nick, R.id.tv_do_attention,R.id.right_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_like:
                if (mPresenter != null) {
                    mPresenter.getData(false);
                } else {
                    ToastUtils.showLongToastSafe("null");
                }
                break;
            case R.id.sdv_pet_head_photo:
            case R.id.tv_pet_nick:
                //点击头像和名称，跳到详情
                break;
            case R.id.tv_do_attention:
                //关注
                if (mTvAttention.getText().toString().equals("+关注")) {
                    mTvAttention.setText("取消关注");
                } else {
                    mTvAttention.setText("+关注");
                }
                break;

            case R.id.back_img_lay:
                finish();
                break;
            case R.id.right_lay:
                startCommentListAct();
                break;
        }
    }

    @Override
    public void loadPhotoDetailView(PhotoDetailBean photoDetailBean) {
        mRightTv.setText(photoDetailBean.attentionCount + "条评论");
    }

    @Override
    public void loadCommentListView(List<CommentBean> commentBeanList) {
        mAdapter.addData(commentBeanList);
    }

    @Override
    public void showLookMoreComment() {
        View footView = View.inflate(this, R.layout.layout_loadmore_footer, null);
        mAdapter.addFooterView(footView);
        footView.findViewById(R.id.tv_loadmore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLongToast("加载更多");
                startCommentListAct();
            }
        });
    }

    private void startCommentListAct() {
        CommentListActivity.startActivity(this, "1");
    }
}

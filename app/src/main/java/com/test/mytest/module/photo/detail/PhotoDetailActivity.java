package com.test.mytest.module.photo.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.utils.DeviceUtils;
import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.blankj.utilcode.utils.TimeUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.adapter.CommentListAdapter;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoDetailBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.injector.components.DaggerPhotoDetailComponent;
import com.test.mytest.injector.module.PhotoDetailModule;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.module.comment.CommentListActivity;
import com.test.mytest.module.comment.CommentListContract;
import com.test.mytest.module.comment.CommentListPresenter;
import com.test.mytest.module.user.main.PetMainPageActivity;
import com.test.mytest.utils.DateStringUtil;
import com.test.mytest.widget.CommonBottomDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017-11-23.
 */

public class PhotoDetailActivity extends BaseActivity<PhotoDetailContract.Presenter> implements PhotoDetailContract.View, CommonBottomDialog.AddCommentLisetener, BaseQuickAdapter.OnItemChildClickListener {

    private static final String TAG_PHOTO_ID = "tagPhotoId";
    private int tagPhotoId;
    private String petUserId;

    @Inject
    CommentListAdapter mAdapter;

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
    @BindView(R.id.tv_like)
    TextView mTvLike;
    @BindView(R.id.tv_create_time)
    TextView mTvCreateTime;

    private int mPhotoId;
    private int mCreateUserId;


    public static void startPhotoDetailAct(Context context, int photoId) {
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra(TAG_PHOTO_ID, photoId);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initInjector() {
        tagPhotoId = getIntent().getIntExtra(TAG_PHOTO_ID, -1);
        DaggerPhotoDetailComponent.builder()
                .photoDetailModule(new PhotoDetailModule(this, tagPhotoId,null))
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
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
        mPresenter.getCommentList();
    }

    @OnClick({R.id.tv_like, R.id.sdv_pet_head_photo, R.id.tv_pet_nick, R.id.tv_do_attention
            , R.id.right_lay, R.id.back_img_lay, R.id.tv_comment, R.id.tv_share, R.id.tv_reward})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_like:
                mPresenter.like(String.valueOf(mPhotoId),String.valueOf(mCreateUserId));
                break;
            case R.id.sdv_pet_head_photo:
            case R.id.tv_pet_nick:
                //点击头像和名称，跳到详情
                PetMainPageActivity.startMainAct(this, petUserId);
                break;
            case R.id.tv_do_attention:
                //关注
                if (mTvAttention.getText().toString().equals("+关注")) {
                    mTvAttention.setText("已关注");
                    mTvAttention.setBackground(ContextCompat.getDrawable(this, R.drawable.gray_btn_selected));
                } else {
                    mTvAttention.setText("+关注");
                    mTvAttention.setBackground(ContextCompat.getDrawable(this, R.drawable.white_btn_selected));
                }
                break;
            case R.id.tv_comment:
                //评论
                showAddCommentDialog("","");
                break;
            case R.id.tv_share:
                //分享
                CommonBottomDialog.showShareDialog(this);
                break;
            case R.id.tv_reward:
                CommonBottomDialog.showRewardDialog(this);
                break;
            case R.id.back_img_lay:
                finish();
                break;
            case R.id.right_lay:
                startCommentListAct();
                break;
        }
    }

    private void showAddCommentDialog(String defendantId,String defendantNickName) {
        CommonBottomDialog.showAddCommentDialog(this, this,defendantId,defendantNickName);
    }

//    @BindView(R.id.tv_do_attention)
//    TextView mTvAttention;

    //    @BindView(R.id.tv_like)
//    TextView mTvLike;
    @Override
    public void loadPhotoDetailView(PhotoInfoBean photoDetailBean) {
        if (photoDetailBean != null) {
            mPhotoId=photoDetailBean.id;
            mCreateUserId=photoDetailBean.userId;
            mRightTv.setText(photoDetailBean.commentsNum + "条评论");
            //头像
            if (!TextUtils.isEmpty(photoDetailBean.avatar)) {
                mSdvPetHead.setImageURI(photoDetailBean.avatar);
            }
            petUserId=String.valueOf(photoDetailBean.userId);
            //发表时间
            if (!TextUtils.isEmpty(photoDetailBean.gmtCreated)) {
                mTvCreateTime.setText(DateStringUtil.getIntervalShort(TimeUtils.string2Date(photoDetailBean.gmtCreated)));
            }
            //昵称
            if (!TextUtils.isEmpty(photoDetailBean.nickname)) {
                mTvPetNick.setText(photoDetailBean.nickname);
            }
            //图像
            if (photoDetailBean.imageUrlList != null && photoDetailBean.imageUrlList.size() > 0 && !TextUtils.isEmpty(photoDetailBean.imageUrlList.get(0))) {
//                mSdvPhoto.setImageURI(photoDetailBean.imageUrlList.get(0));
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(photoDetailBean.imageUrlList.get(0))
                        .setControllerListener(listener)
                        .build();
                mSdvPhoto.setController(controller);
            }
            //内容
            if (!TextUtils.isEmpty(photoDetailBean.content)) {
                mTvPhotoContent.setText(photoDetailBean.content);
            }
            //点赞
            mTvLike.setText(photoDetailBean.like?getString(R.string.already_liked):getString(R.string.liked));
        }
    }

    @Override
    public void likeSuccess() {
        //点赞
        if (mTvLike.getText().toString().equals(getString(R.string.liked))) {
            mTvLike.setText(getString(R.string.already_liked));
            mTvLike.setTextColor(ContextCompat.getColor(this, R.color.gray));
        }
//        else {
//            mTvLike.setText(getString(R.string.liked));
//            mTvLike.setTextColor(ContextCompat.getColor(this, R.color.black));
//        }
    }

    void updateViewSize(@Nullable ImageInfo imageInfo) {
        if (imageInfo != null) {
            mSdvPhoto.getLayoutParams().width = ScreenUtils.getScreenWidth();
            mSdvPhoto.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mSdvPhoto.setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
        }
    }

    ControllerListener listener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            updateViewSize(imageInfo);
        }

        @Override
        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            updateViewSize(imageInfo);
        }
    };


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
                startCommentListAct();
            }
        });
    }

    private void startCommentListAct() {
        CommentListActivity.startCommentListActivity(this, tagPhotoId);
    }

    @Override
    public void addComment(String commentContent, String defendantId, String defendantNickName) {
        ToastUtils.showLongToastSafe(commentContent);
        CommentListActivity.startCommentListActivity(this, tagPhotoId, commentContent,defendantId,defendantNickName);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int id = view.getId();
        switch (id){
            //头像
            case R.id.tv_pet_nick:
                KLog.e("click item");
                break;
        }
    }
}

package com.test.mytest.module.user.main;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.injector.components.DaggerPetInfoComponent;
import com.test.mytest.injector.module.PetInfoModule;
import com.test.mytest.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017-12-05.
 */

public class PetMainPageActivity extends BaseActivity<PetInfoPresenter> implements PetInfoContract.View {
    private static final String TAG_USER_ID = "tagPetId";
    private String userId;

    @BindView(R.id.sdv_pet_head_photo)
    SimpleDraweeView sdvPetAvatar;
    @BindView(R.id.sdv_gender)
    SimpleDraweeView sdvPetGender;
    @BindView(R.id.tv_pet_nick)
    TextView mTvPetNick;
    @BindView(R.id.tv_pet_type)
    TextView mTvPetType;
    @BindView(R.id.tv_age)
    TextView mTvPetAge;
    @BindView(R.id.tv_address)
    TextView mTvAddress;


    public static void startMainAct(Context context, String userId) {
        Intent intent = new Intent(context, PetMainPageActivity.class);
        intent.putExtra(TAG_USER_ID, userId);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_user_main_page;
    }

    @Override
    protected void initInjector() {
        userId = getIntent().getStringExtra(TAG_USER_ID);
        DaggerPetInfoComponent.builder()
                .petInfoModule(new PetInfoModule(this, userId))
                .build()
                .inject(this);

    }

    @Override
    protected void initViews() {
        initTitle();
    }

    private void initTitle() {
        if (mTitle != null) {
            mTitle.setText("宠物详情");
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @OnClick({R.id.back_img_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_img_lay:
                finish();
                break;
        }
    }

    @Override
    public void showPetInfo(AccountBean data) {
        if(data!=null) {
            if(!TextUtils.isEmpty(data.avatar)) {
                sdvPetAvatar.setImageURI(data.avatar);
            }
            if(!TextUtils.isEmpty(data.nickname)){
                mTvPetNick.setText(data.nickname);
            }
            if(!TextUtils.isEmpty(data.gender)){
                sdvPetGender.setImageResource(TextUtils.equals(data.gender,"男")?R.drawable.ic_pet_male:R.drawable.ic_favor);
            }
        }
    }
}

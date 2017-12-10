package com.test.mytest.module.setting;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.allen.library.SuperTextView;
import com.blankj.utilcode.utils.LocationUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.model.TResult;
import com.test.mytest.R;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.module.base.BaseTakePhotoActivity;
import com.test.mytest.module.base.BaseTakePhotoFragment;
import com.test.mytest.utils.CustomTakePhotoHelper;
import com.test.mytest.utils.PrefUtils;
import com.test.mytest.widget.CommonBottomDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017-11-27.
 */

public class SettingFragment extends BaseTakePhotoFragment implements CommonBottomDialog.EditLisetener {

    private static final int TYPE_NAME = 1;
    private static final int TYPE_LOCATION = 2;
    private static final int TYPE_AGE = 3;
    private static final int TYPE_TYPE = 4;
    private static final int TYPE_GENDER = 5;

    @BindView(R.id.stv_name)
    SuperTextView mStvName;
    @BindView(R.id.stv_gender)
    SuperTextView mStvGender;
    @BindView(R.id.stv_age)
    SuperTextView mStvAge;
    @BindView(R.id.stv_type)
    SuperTextView mStvType;
    @BindView(R.id.stv_location)
    SuperTextView mStvLocation;
    private AccountBean accountBean;
    private String mLocationAddr;

    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitleView();
        accountBean = PrefUtils.getUserInfo();
        if (accountBean != null) {
            mStvName.setRightString(TextUtils.isEmpty(accountBean.petName) ? "" : accountBean.petName);
            mStvAge.setRightString(TextUtils.isEmpty(accountBean.petAge) ? "" : accountBean.petAge);
            mStvGender.setRightString(TextUtils.isEmpty(accountBean.petGender) ? "" : accountBean.petGender);
            mStvType.setRightString(TextUtils.isEmpty(accountBean.petType) ? "" : accountBean.petType);
            mStvLocation.setRightString(TextUtils.isEmpty(accountBean.location) ? "" : accountBean.location);
        } else {
            accountBean = new AccountBean();
        }
    }

    private void initTitleView() {
        if (mTitle != null) {
            mTitle.setText("设置");
        }
        if (mBackLay != null) {
            mBackLay.setVisibility(View.GONE);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.sdv_pet_head_photo, R.id.stv_name, R.id.stv_location, R.id.stv_age, R.id.stv_type, R.id.stv_gender})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sdv_pet_head_photo:
                showAddPicDialog();
                break;
            case R.id.stv_name:
                String rightString = mStvName.getRightString();
                CommonBottomDialog.showEditTextDialog(mContext, TYPE_NAME, TextUtils.isEmpty(rightString) ? "修改名字" : rightString, "修改名字", this);
                break;
            case R.id.stv_location:
                rightString = mStvLocation.getRightString();
                CommonBottomDialog.showEditTextDialog(mContext, TYPE_LOCATION, TextUtils.isEmpty(rightString) ? "修改地址" : rightString, "修改地址", this);
                break;
            case R.id.stv_age:
                LocationUtils.register(100, 100, new LocationUtils.OnLocationChangeListener() {
                    @Override
                    public void getLastKnownLocation(Location location) {

                    }

                    @Override
                    public void onLocationChanged(Location location) {
                        mLocationAddr=LocationUtils.getLocality(location.getLatitude(),location.getLongitude() );
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }
                });
                rightString = mStvAge.getRightString();
                rightString=mLocationAddr;
                CommonBottomDialog.showEditTextDialog(mContext, TYPE_AGE, TextUtils.isEmpty(rightString) ? "修改年龄" : rightString, "修改年龄", this);
                break;
            case R.id.stv_type:
                rightString = mStvType.getRightString();
                CommonBottomDialog.showEditTextDialog(mContext, TYPE_TYPE, TextUtils.isEmpty(rightString) ? "修改品种" : rightString, "修改品种", this);
                break;
            case R.id.stv_gender:
                rightString = mStvGender.getRightString();
                CommonBottomDialog.showEditTextDialog(mContext, TYPE_GENDER, TextUtils.isEmpty(rightString) ? "修改性别" : rightString, "修改性别", this);
                break;
        }
    }

    @BindView(R.id.sdv_pet_head_photo)
    SimpleDraweeView mSdvPetHeadPhoto;

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mSdvPetHeadPhoto.setImageURI(Uri.parse("file://" + result.getImage().getCompressPath()));
    }

    @Override
    public void editComplete(String editTextString, int type) {
        switch (type) {
            case TYPE_NAME:
                mStvName.setRightString(editTextString);
                accountBean.petName = editTextString;
                break;
            case TYPE_AGE:
                mStvAge.setRightString(editTextString);
                accountBean.petAge = editTextString;
                break;
            case TYPE_LOCATION:
                mStvLocation.setRightString(editTextString);
                accountBean.location = editTextString;
                break;
            case TYPE_GENDER:
                mStvGender.setRightString(editTextString);
                accountBean.petGender = editTextString;
                break;
            case TYPE_TYPE:
                mStvType.setRightString(editTextString);
                accountBean.petType = editTextString;
                break;
        }
        PrefUtils.saveUserInfo(accountBean);
    }
}

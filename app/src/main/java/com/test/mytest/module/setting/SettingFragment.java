package com.test.mytest.module.setting;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.model.TResult;
import com.test.mytest.R;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.module.base.BaseTakePhotoActivity;
import com.test.mytest.module.base.BaseTakePhotoFragment;
import com.test.mytest.utils.CustomTakePhotoHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017-11-27.
 */

public class SettingFragment extends BaseTakePhotoFragment {
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
    }

    private void initTitleView() {
        if(mTitle!=null){
            mTitle.setText("设置");
        }
        if(mBackLay!=null){
            mBackLay.setVisibility(View.GONE);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.sdv_pet_head_photo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sdv_pet_head_photo:
                showAddPicDialog();
                break;
        }
    }

    @BindView(R.id.sdv_pet_head_photo)
    SimpleDraweeView mSdvPetHeadPhoto;

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        mSdvPetHeadPhoto.setImageURI(Uri.parse("file://"+result.getImage().getCompressPath()));
    }
}

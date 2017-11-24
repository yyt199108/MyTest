package com.test.mytest.module.photo.detail;

import android.view.View;

import com.blankj.utilcode.utils.ToastUtils;
import com.test.mytest.R;
import com.test.mytest.injector.components.DaggerPhotoDetailComponent;
import com.test.mytest.injector.module.PhotoDetailModule;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.module.base.IBasePresenter;

import butterknife.OnClick;

/**
 * Created by admin on 2017-11-23.
 */

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_photo_detail;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoDetailComponent.builder()
                .photoDetailModule(new PhotoDetailModule())
                .build();
    }

    @Override
    protected void initViews() {
        initTitleView();

    }

    private void initTitleView() {
        if (mTitle != null) {
            mTitle.setText("");
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.tv_like})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_like:
                if(mPresenter!=null) {
                    mPresenter.getData(false);
                }else{
                    ToastUtils.showLongToastSafe("null");
                }
                break;
        }
    }
}

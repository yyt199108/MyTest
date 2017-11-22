package com.test.mytest.module.photo.main;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.injector.components.DaggerPhotoListComponent;
import com.test.mytest.injector.module.PhotoListModule;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.module.base.IBasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by admin on 2017-11-22.
 */

public class PhotoListFragment extends BaseFragment<IBasePresenter> implements PhotoListContract.View {

    public static PhotoListFragment newInstance() {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    BaseQuickAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_list;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoListComponent.builder()
                .photoListModule(new PhotoListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        if(mEmptyLayout!=null){
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void loadData(List<PhotoInfoBean> data) {

    }

    @Override
    public void loadMoreData(List<PhotoInfoBean> data) {

    }

    @Override
    public void loadNoData() {

    }
}

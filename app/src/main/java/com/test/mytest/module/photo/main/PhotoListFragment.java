package com.test.mytest.module.photo.main;

import android.os.Bundle;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.R;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.module.base.IBasePresenter;

import javax.inject.Inject;

/**
 * Created by admin on 2017-11-22.
 */

public class PhotoListFragment extends BaseFragment<IBasePresenter> {

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

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

}

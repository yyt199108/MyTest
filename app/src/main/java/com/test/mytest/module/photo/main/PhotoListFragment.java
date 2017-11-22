package com.test.mytest.module.photo.main;

import android.os.Bundle;

import com.test.mytest.module.base.BaseFragment;

/**
 * Created by admin on 2017-11-22.
 */

public class PhotoListFragment extends BaseFragment {

    public static PhotoListFragment newInstance() {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
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

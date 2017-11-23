package com.test.mytest.module.club;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.injector.components.DaggerClubListComponent;
import com.test.mytest.injector.module.ClubListModule;
import com.test.mytest.module.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by admin on 2017-11-23.
 */

public class ClubListFragment extends BaseFragment implements ClubListContract.View {

    public static ClubListFragment newInstance() {
        ClubListFragment fragment = new ClubListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    BaseQuickAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_club;
    }

    @Override
    protected void initInjector() {
        DaggerClubListComponent.builder().clubListModule(new ClubListModule(this)).build();
    }

    @Override
    protected void initViews() {
        mRecyView.setLayoutManager(new GridLayoutManager(mContext,3, LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void updateViews(boolean isRefresh) {

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

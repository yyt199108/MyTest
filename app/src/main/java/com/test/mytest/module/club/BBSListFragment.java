package com.test.mytest.module.club;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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

public class BBSListFragment extends BaseFragment implements BBSListContract.View {

    public static BBSListFragment newInstance() {
        BBSListFragment fragment = new BBSListFragment();
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
        DaggerClubListComponent.builder()
                .clubListModule(new ClubListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        initTitle();
        mRecyView.setLayoutManager(new GridLayoutManager(mContext, 3, LinearLayoutManager.VERTICAL, false));
        mRecyView.setAdapter(mAdapter);
    }

    private void initTitle() {
        if(mTitle!=null){
            mTitle.setText("论坛");
        }
        if(mBackLay!=null){
            mBackLay.setVisibility(View.GONE);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<PhotoInfoBean> data) {
        mAdapter.addData(data);
    }

    @Override
    public void loadMoreData(List<PhotoInfoBean> data) {

    }

    @Override
    public void loadNoData() {

    }
}

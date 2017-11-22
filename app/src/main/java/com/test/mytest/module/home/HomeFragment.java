package com.test.mytest.module.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.test.mytest.R;
import com.test.mytest.adapter.VPAdapter;
import com.test.mytest.api.bean.TabEntity;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.module.photo.main.PhotoListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主菜单
 */
public class HomeFragment extends BaseFragment {

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    List<Fragment> fragments = new ArrayList<Fragment>();

    private String[] mTitles = {"1", "2", "3", "4"};

    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};


    @BindView(R.id.menu_viewpager)
    ViewPager menuViewpager;

    @BindView(R.id.menu_tab)
    CommonTabLayout menuTab;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initHomeFragmentList();
        initTabLayout();
        initViewPager();
    }


    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initHomeFragmentList() {
        fragments.add(PhotoListFragment.newInstance());
    }

    //tablayout初始化
    void initTabLayout() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        menuTab.setTabData(mTabEntities);

        menuTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (menuViewpager != null) {
                    menuViewpager.setCurrentItem(position);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    //viewpager初始化
    private void initViewPager() {
        VPAdapter vpAdapter = new VPAdapter(getFragmentManager(), fragments);
        menuViewpager.setAdapter(vpAdapter);
        menuViewpager.setOffscreenPageLimit(vpAdapter.getCount());
        menuViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuTab != null) {
                    menuTab.setCurrentTab(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}

package com.test.mytest.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Viewpager适配器
 */
public class VPAdapter extends FragmentPagerAdapter {

    List<String> titleData = null;

    List<Fragment> fragments = null;

    public VPAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public VPAdapter(FragmentManager fm, List<String> titleData, List<Fragment> fragments) {
        super(fm);
        this.titleData = titleData;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments == null ? null : fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleData == null ? "" : titleData.get(position);
    }
}

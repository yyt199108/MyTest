package com.test.mytest.module.home;

import android.widget.TextView;

import com.test.mytest.R;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.utils.RxHelper;

import butterknife.BindView;

/**
 * Created by admin on 2017-11-21.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.sb_skip)
    TextView mTvSkip;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateView(boolean isRefresh) {
        RxHelper.countdown(5);
    }


}

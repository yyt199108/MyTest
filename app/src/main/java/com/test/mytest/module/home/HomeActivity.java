package com.test.mytest.module.home;

import com.blankj.utilcode.utils.ToastUtils;
import com.test.mytest.R;
import com.test.mytest.module.base.BaseActivity;

/**
 * Created by admin on 2017-11-21.
 */

public class HomeActivity extends BaseActivity {

    private long mExitTime = 0;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        if(findFragment(HomeFragment.class)==null){
            loadRootFragment(R.id.fl_container,HomeFragment.newInstance());
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            //提示再按一次退出
            if (System.currentTimeMillis() - mExitTime > 2000) {
                ToastUtils.showShortToastSafe("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressedSupport();
    }
}

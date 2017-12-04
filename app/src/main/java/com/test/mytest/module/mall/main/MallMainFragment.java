package com.test.mytest.module.mall.main;

import android.os.Bundle;
import android.view.View;

import com.test.mytest.R;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.widget.CommonBottomDialog;

import butterknife.OnClick;

/**
 * Created by admin on 2017-12-04.
 */

public class MallMainFragment extends BaseFragment implements CommonBottomDialog.AddCommentLisetener {

    public static MallMainFragment newInstance() {
        MallMainFragment fragment = new MallMainFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_mall_main;
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

    @OnClick({R.id.btn_test})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:
                CommonBottomDialog.showAddCommentDialog(getActivity(), this);
                break;
        }
    }

    @Override
    public void addComment(String commentContent) {

    }

}

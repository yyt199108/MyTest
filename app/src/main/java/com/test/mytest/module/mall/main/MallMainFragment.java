package com.test.mytest.module.mall.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.blankj.utilcode.utils.AppUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.test.mytest.R;
import com.test.mytest.module.base.BaseFragment;
import com.test.mytest.module.webview.AgentWebFragment;
import com.test.mytest.widget.CommonBottomDialog;

import butterknife.OnClick;

/**
 * Created by admin on 2017-12-04.
 */

public class MallMainFragment extends AgentWebFragment implements CommonBottomDialog.AddCommentLisetener {

//    public static String tbPath = "https://shop385121372.taobao.com/shop/view_shop.htm?shop_id=385121372";
    public static String tbPath = "https://shop385121372.m.taobao.com/?shop_id=385121372";

    public static MallMainFragment newInstance(Bundle bundle) {
        MallMainFragment fragment = new MallMainFragment();
//        Bundle bundle = new Bundle();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

//    @Override
//    protected int attachLayoutRes() {
//        return R.layout.fragment_mall_main;
//    }
//
//    @Override
//    protected void initInjector() {
//
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            checkInstallTaoBao();
        }
    }

    private void checkInstallTaoBao() {
        if (AppUtils.isInstallApp(mContext, "com.taobao.taobao")) {
            new MaterialDialog.Builder(mContext)
                    .title(getString(R.string.goto_taobao_title))
                    .content(R.string.goto_taobao)
                    .positiveText("是")
                    .negativeText("否")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            jumpToTaobao();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                            ToastUtils.showLongToastSafe("本页显示");
                        }
                    })
                    .show();
        } else {
//            ToastUtils.showLongToastSafe("本页显示");
        }
    }

    private void jumpToTaobao() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse(tbPath);//商品地址
        intent.setData(uri);
//        intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
        intent.setClassName("com.taobao.taobao", "com.taobao.android.shop.activity.ShopHomePageActivity");
        startActivity(intent);
    }

    @Override
    protected void initViews() {
        if(mFinishImageView!=null) {
            mFinishImageView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

//    @OnClick({R.id.btn_test})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_test:
//                CommonBottomDialog.showAddCommentDialog(getActivity(), this);
//
//                break;
//        }
//    }

    @Override
    public void addComment(String commentContent) {

    }

}

package com.test.mytest.module.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.test.mytest.R;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.injector.module.LoginModule;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.module.home.HomeActivity;
import com.test.mytest.utils.PrefUtils;

import butterknife.BindView;
import butterknife.OnClick;

import com.test.mytest.injector.components.DaggerLoginComponent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import static com.test.mytest.application.MyApp.mContext;

/**
 * Created by admin on 2017-12-25.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {
        DaggerLoginComponent.builder()
//                .applicationComponent(getApplicationComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        if (PrefUtils.getUserInfo() != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @BindView(R.id.et_login_phone)
    EditText mLoginPhone;

    @BindView(R.id.et_login_password)
    EditText mLoginPassword;

    @OnClick({R.id.login, R.id.login_wx, R.id.login_qq, R.id.login_wb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresenter.userLogin();
                break;
            case R.id.login_wx:     //微信登录
                if (!NetworkUtils.isConnected()) {
                    ToastUtils.showShortToast("未连接到网络");
                    return;
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.login_qq:     //qq登录
                if (!NetworkUtils.isConnected()) {
                    ToastUtils.showShortToast("未连接到网络");
                    return;
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.login_wb:     //微博登录
                if (!NetworkUtils.isConnected()) {
                    ToastUtils.showShortToast("未连接到网络");
                    return;
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, authListener);
                break;
        }
    }

    private int getPlatType(SHARE_MEDIA platform){
        if(platform==SHARE_MEDIA.QQ){
            return 1;
        }else if(platform==SHARE_MEDIA.WEIXIN){
            return 2;
        }else if(platform==SHARE_MEDIA.SINA){
            return 3;
        }else{
            return 0;
        }
    }
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            mPresenter.thirdLogin(data.get("iconurl"),data.get("name"),data.get("uid"),data.get("gender"),getPlatType(platform));

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getUserPhone() {
        return mLoginPhone.getText().toString();
    }

    @Override
    public String getPassword() {
        return mLoginPassword.getText().toString();
    }

    @Override
    public void loginSuccess(AccountBean data) {
        ToastUtils.showLongToastSafe("登录成功");
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void loginError(String s) {
        ToastUtils.showLongToastSafe("登录失败：" + s);
    }
}

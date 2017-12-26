package com.test.mytest.module.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.utils.ToastUtils;
import com.test.mytest.R;
import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.module.base.BaseActivity;
import com.test.mytest.module.home.HomeActivity;
import com.test.mytest.utils.PrefUtils;

import butterknife.BindView;
import butterknife.OnClick;

//import com.test.mytest.injector.components.DaggerLoginComponent;

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
//        DaggerLoginComponent.builder()
//                .applicationComponent(getApplicationComponent())
//                .loginModule(new LoginModule(this))
//                .build().inject(this);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initViews() {
        if(PrefUtils.getUserInfo()!=null){
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @BindView(R.id.et_login_phone)
    EditText mLoginPhone;

    @BindView(R.id.et_login_password)
    EditText mLoginPassword;

    @OnClick({R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresenter.userLogin();
                break;
        }
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
    }

    @Override
    public void loginError(String s) {
        ToastUtils.showLongToastSafe("登录失败：" + s);
    }
}

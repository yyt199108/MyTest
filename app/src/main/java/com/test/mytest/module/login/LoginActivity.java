package com.test.mytest.module.login;

import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.utils.ToastUtils;
import com.test.mytest.R;
import com.test.mytest.api.bean.UserBean;
import com.test.mytest.injector.components.DaggerLoginComponent;
import com.test.mytest.injector.module.LoginModule;
import com.test.mytest.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
                .applicationComponent(getApplicationComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {

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
    public void loginSuccess(UserBean data) {
        ToastUtils.showLongToastSafe("登录成功");
    }

    @Override
    public void loginError(String s) {
        ToastUtils.showLongToastSafe("登录失败：" + s);
    }
}

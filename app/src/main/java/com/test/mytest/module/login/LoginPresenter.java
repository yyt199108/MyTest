package com.test.mytest.module.login;

import android.text.TextUtils;
import android.widget.TextView;

import com.test.mytest.api.bean.UserBean;
import com.test.mytest.api.model.LoginModel;
import com.test.mytest.api.response.BaseBeanRes;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017-12-25.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginModel loginModel;
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        loginModel = new LoginModel();
    }

    @Override
    public void userLogin() {
        String phone = mView.getUserPhone();
        String password = mView.getPassword();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("", phone);
            map.put("", password);
            loginModel.userLogin(map)
                    .subscribe(new Observer<BaseBeanRes<UserBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseBeanRes<UserBean> userBeanBaseBeanRes) {
                            if (userBeanBaseBeanRes != null && userBeanBaseBeanRes.data != null) {
                                mView.loginSuccess(userBeanBaseBeanRes.data);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.loginError(e.getMessage().toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}

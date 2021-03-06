package com.test.mytest.module.login;

import android.text.TextUtils;

import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.api.exception.ApiException;
import com.test.mytest.api.model.UserModel;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.utils.PrefUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017-12-25.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private UserModel userModel;
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        this.mView = view;
        userModel = new UserModel();
    }

    @Override
    public void userLogin() {
        String phone = mView.getUserPhone();
        String password = mView.getPassword();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(password)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("phone", phone);
            map.put("password", password);
            userModel.userLogin(map)
                    .subscribe(new Observer<BaseBeanRes<AccountBean>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseBeanRes<AccountBean> userBeanBaseBeanRes) {

                            if (userBeanBaseBeanRes != null && userBeanBaseBeanRes.data != null) {
                                PrefUtils.saveUserInfo(userBeanBaseBeanRes.data);
                                mView.loginSuccess(userBeanBaseBeanRes.data);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
//                            mView.loginError(e.getMessage().toString());
                            if (e instanceof ApiException) {
                                mView.loginError(((ApiException) e).message);
                            } else {
                                mView.loginError(e.getMessage());
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void thirdLogin(String platUserAvatar, String platUserNickname, String thirdPlatUserId, String gender, int thirdPlatType) {
        HashMap<String, String> map = new HashMap<>();
        map.put("platUserAvatar", platUserAvatar);
        map.put("platUserNickname", platUserNickname);
        map.put("thirdPlatUserId", thirdPlatUserId);
        map.put("gender", gender);
        map.put("thirdPlatType", String.valueOf(thirdPlatType));
        userModel.thirdLogin(map)
                .subscribe(new Observer<BaseBeanRes<AccountBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBeanRes<AccountBean> userBeanBaseBeanRes) {
                        if (userBeanBaseBeanRes != null && userBeanBaseBeanRes.data != null) {
                            PrefUtils.saveUserInfo(userBeanBaseBeanRes.data);
                            mView.loginSuccess(userBeanBaseBeanRes.data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            mView.loginError(((ApiException) e).message);
                        } else {
                            mView.loginError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }
}

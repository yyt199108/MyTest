package com.test.mytest.module.setting;

import android.text.TextUtils;

import com.test.mytest.api.exception.ApiException;
import com.test.mytest.api.model.UserModel;
import com.test.mytest.api.response.BaseRes;
import com.test.mytest.utils.PrefUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2018-01-24.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private UserModel userModel;
    private SettingContract.View mView;

    public SettingPresenter(SettingContract.View view) {
        this.mView = view;
        userModel = new UserModel();
    }

    @Override
    public void getData(boolean isRefresh) {

    }

    @Override
    public void getMoreData() {

    }

    @Override
    public void modifyInfo(String age, String gender, String nickname) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(age)) {
            map.put("age", age);
        }
        if (!TextUtils.isEmpty(gender)) {
            map.put("gender", gender);
        }
        if (!TextUtils.isEmpty(nickname)) {
            map.put("nickname", nickname);
        }
        map.put("userId", PrefUtils.getUserId());
        map.put("token", PrefUtils.getToken());
        userModel.modifyUserInfo(map)
                .compose(mView.bindLifecycle())
                .subscribe(new Observer<BaseRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseRes o) {
                        mView.modifySuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            if (((ApiException) e).code == 213) {
                                //登录过期
                                mView.loginTokenOut();
                            } else {
                                mView.showServerError(((ApiException) e).message);
                            }
                        } else {
                            mView.showServerError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

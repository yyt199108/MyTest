package com.test.mytest.module.login;

import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.module.base.IBasePresenter;

/**
 * Created by admin on 2017-12-25.
 */

public interface LoginContract {
    interface View {
        String getUserPhone();

        String getPassword();

        void loginSuccess(AccountBean data);

        void loginError(String s);
    }

    interface Presenter extends IBasePresenter {
        //账号密码登录
        void userLogin();
        //第三方登录
        void thirdLogin(String platUserAvatar,String platUserNickname,String thirdPlatUserId,String gender,int thirdPlatType);
    }
}

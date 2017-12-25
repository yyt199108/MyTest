package com.test.mytest.module.login;

import com.test.mytest.api.bean.UserBean;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.ILoadDataView;

/**
 * Created by admin on 2017-12-25.
 */

public class LoginContract {
    public interface View {
        String getUserPhone();
        String getPassword();
        void loginSuccess(UserBean data);
        void loginError(String s);
    }

    public interface Presenter extends IBasePresenter{
        void userLogin();
    }
}

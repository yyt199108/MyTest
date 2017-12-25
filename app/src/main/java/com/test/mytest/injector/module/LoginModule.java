package com.test.mytest.injector.module;

import com.test.mytest.api.model.LoginModel;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.login.LoginContract;
import com.test.mytest.module.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-12-25.
 */

@Module
public class LoginModule {
    private LoginContract.View mView;
    public LoginModule(LoginContract.View view){
        this.mView=view;
    }
    @Provides
    public IBasePresenter providePresenter(){
        return new LoginPresenter(mView);
    }

}
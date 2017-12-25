package com.test.mytest.injector.components;

import com.test.mytest.injector.PerActivity;
import com.test.mytest.injector.module.LoginModule;
import com.test.mytest.module.login.LoginActivity;

import dagger.Component;

/**
 * Created by admin on 2017-12-25.
 */
@PerActivity
@Component(modules = LoginModule.class,dependencies = ApplicationComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}

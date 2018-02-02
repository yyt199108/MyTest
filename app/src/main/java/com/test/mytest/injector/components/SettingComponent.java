package com.test.mytest.injector.components;

import com.test.mytest.injector.PerFragment;
import com.test.mytest.injector.module.SettingModule;
import com.test.mytest.module.setting.SettingFragment;

import dagger.Component;

/**
 * Created by admin on 2018-01-24.
 */
@PerFragment
@Component(modules = SettingModule.class,dependencies = ApplicationComponent.class)
public interface SettingComponent {
    void inject(SettingFragment fragment);
}

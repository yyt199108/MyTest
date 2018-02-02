package com.test.mytest.injector.module;

import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.setting.SettingContract;
import com.test.mytest.module.setting.SettingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2018-01-24.
 */

@Module
public class SettingModule {
    private SettingContract.View mView;
    public SettingModule(SettingContract.View view){
        this.mView=view;
    }

    @Provides
    public IBasePresenter providePresenter(){
        return new SettingPresenter(mView);
    }
}

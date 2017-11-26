package com.test.mytest.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.adapter.BBSListAdapter;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.club.BBSListContract;
import com.test.mytest.module.club.BBSListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-23.
 */

@Module
public class ClubListModule {
    private BBSListContract.View mView;

    public ClubListModule(BBSListContract.View view){
        this.mView=view;
    }
    @Provides
    IBasePresenter providePresenter(){
        return new BBSListPresenter(mView);
    }
    @Provides
    BaseQuickAdapter provideAdapter(){
        return new BBSListAdapter();
    }
}

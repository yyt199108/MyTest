package com.test.mytest.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.adapter.ClubListAdapter;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.club.ClubListContract;
import com.test.mytest.module.club.ClubListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2017-11-23.
 */

@Module
public class ClubListModule {
    private ClubListContract.View mView;

    public ClubListModule(ClubListContract.View view){
        this.mView=view;
    }
    @Provides
    IBasePresenter providePresenter(){
        return new ClubListPresenter(mView);
    }
    @Provides
    BaseQuickAdapter provideAdapter(){
        return new ClubListAdapter();
    }
}

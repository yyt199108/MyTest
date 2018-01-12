package com.test.mytest.injector.module;

import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.user.main.PetInfoContract;
import com.test.mytest.module.user.main.PetInfoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 2018-01-02.
 */
@Module
public class PetInfoModule {
    private PetInfoContract.View mView;
    private String petId;

    public PetInfoModule(PetInfoContract.View mView, String petId) {
        this.mView = mView;
        this.petId = petId;
    }

    @Provides
    PetInfoPresenter providePetPresenter() {
        return new PetInfoPresenter(mView, petId);
    }
}

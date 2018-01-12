package com.test.mytest.injector.components;

import com.test.mytest.injector.module.PetInfoModule;
import com.test.mytest.module.user.main.PetMainPageActivity;

import dagger.Component;

/**
 * Created by admin on 2018-01-02.
 */
@Component(modules = PetInfoModule.class)
public interface PetInfoComponent {
    void inject(PetMainPageActivity activity);
}

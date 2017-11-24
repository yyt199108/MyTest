package com.test.mytest.injector.components;

import com.test.mytest.injector.PerFragment;
import com.test.mytest.injector.module.ClubListModule;
import com.test.mytest.module.club.ClubListFragment;

import dagger.Component;

/**
 * Created by admin on 2017-11-23.
 */
@PerFragment
@Component(modules = ClubListModule.class)
public interface ClubListComponent {
    void inject(ClubListFragment fragment);
}

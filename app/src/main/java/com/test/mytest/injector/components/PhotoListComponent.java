package com.test.mytest.injector.components;

import com.test.mytest.injector.module.PhotoListModule;
import com.test.mytest.module.photo.main.PhotoListFragment;

import dagger.Component;

/**
 * Created by admin on 2017-11-22.
 */

@Component(modules = PhotoListModule.class)
public interface PhotoListComponent {
    void inject(PhotoListFragment fragment);
}

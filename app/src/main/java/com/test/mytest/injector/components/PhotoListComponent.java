package com.test.mytest.injector.components;

import com.test.mytest.injector.PerFragment;
import com.test.mytest.injector.module.PhotoListModule;
import com.test.mytest.module.photo.main.PhotoListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 2017-11-22.
 */
@PerFragment
@Component(modules = PhotoListModule.class,dependencies = ApplicationComponent.class)
public interface PhotoListComponent {
    void inject(PhotoListFragment fragment);
}

package com.test.mytest.injector.components;

import com.test.mytest.injector.module.PhotoDetailModule;
import com.test.mytest.module.photo.detail.PhotoDetailActivity;

import dagger.Component;

/**
 * Created by admin on 2017-11-24.
 */
@Component(modules = PhotoDetailModule.class)
public interface PhotoDetailComponent {
    void inject(PhotoDetailActivity photoDetailActivity);
}

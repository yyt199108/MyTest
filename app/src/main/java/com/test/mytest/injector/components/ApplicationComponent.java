package com.test.mytest.injector.components;

import android.content.Context;

import com.test.mytest.api.model.CommentModel;
import com.test.mytest.api.model.UserModel;
import com.test.mytest.api.model.PhotoModel;
import com.test.mytest.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by admin on 2017-11-24.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
//    IPhotoApi getIPhotoApi();

    Context getContext();

    void inject(PhotoModel photoModel);

    void inject(CommentModel commentModel);

    void inject(UserModel userModel);
}

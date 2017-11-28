package com.test.mytest.injector.components;

import com.test.mytest.injector.module.CommentListModule;
import com.test.mytest.module.comment.CommentListActivity;

import dagger.Component;

/**
 * Created by yyt19 on 2017/11/28.
 */
@Component(modules = CommentListModule.class)
public interface CommentListComponent {
    void inject(CommentListActivity commentListActivity);
}

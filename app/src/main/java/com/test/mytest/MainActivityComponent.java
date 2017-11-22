package com.test.mytest;

import dagger.Component;

/**
 * Created by admin on 2017-11-20.
 */
//modules作用：告诉Component 当你需要创建对象的时候就到我这里来拿
@Component(modules = Bean2Module.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

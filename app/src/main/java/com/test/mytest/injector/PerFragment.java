package com.test.mytest.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by admin on 2017-11-24.
 * 单例标识
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}

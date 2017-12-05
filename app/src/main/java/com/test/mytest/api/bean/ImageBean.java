package com.test.mytest.api.bean;

import android.support.annotation.IdRes;

/**
 * Created by admin on 2017-12-05.
 */

public class ImageBean {
//    @IdRes
    public int imageResId;
    public String imageDescribe;
    public int id;

    public ImageBean() {
    }

    public ImageBean( int imageResId, String imageDescribe, int id) {
        this.imageResId = imageResId;
        this.imageDescribe = imageDescribe;
        this.id = id;
    }
}

package com.test.mytest.module.photo.detail;

import com.socks.library.KLog;

/**
 * Created by admin on 2017-11-24.
 */

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {

    public PhotoDetailPresenter(){

    }
    @Override
    public void getData(boolean isRefresh) {
        KLog.e("photodetail");
    }

    @Override
    public void getMoreData() {

    }
}

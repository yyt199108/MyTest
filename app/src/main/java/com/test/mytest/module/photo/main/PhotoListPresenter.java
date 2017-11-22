package com.test.mytest.module.photo.main;

import com.test.mytest.api.bean.RetrofitService;
import com.test.mytest.module.base.IBasePresenter;

/**
 * Created by admin on 2017-11-22.
 * 图集列表界面 Presenter
 */

public class PhotoListPresenter implements PhotoListContract.Presenter {

    private PhotoListContract.View mView;

    public PhotoListPresenter(PhotoListContract.View view) {
        this.mView = view;
    }

    @Override
    public void getData(boolean isRefresh) {
        RetrofitService.get
    }

    @Override
    public void getMoreData() {

    }
}

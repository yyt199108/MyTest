package com.test.mytest.module.club;

import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-23.
 */

public class BBSListPresenter implements BBSListContract.Presenter {

    private BBSListContract.View mView;

    public BBSListPresenter(BBSListContract.View view) {
        this.mView = view;
    }

    @Override
    public void getData(boolean isRefresh) {
        List<PhotoInfoBean> list = new ArrayList<>();
        PhotoInfoBean infoBean = new PhotoInfoBean();
        infoBean.infoName = "猫咪";
        infoBean.resId = R.drawable.bbs_cat;

        PhotoInfoBean infoBean2 = new PhotoInfoBean();
        infoBean2.infoName = "狗狗";
        infoBean2.resId = R.drawable.bbs_dog;

        PhotoInfoBean infoBean3 = new PhotoInfoBean();
        infoBean3.infoName = "寄养";
        infoBean3.resId = R.drawable.bbs_foster;

        PhotoInfoBean infoBean4 = new PhotoInfoBean();
        infoBean4.infoName = "医药";
        infoBean4.resId = R.drawable.bbs_medicine;

        PhotoInfoBean infoBean5 = new PhotoInfoBean();
        infoBean5.infoName = "婚配";
        infoBean5.resId = R.drawable.bbs_marry;
        list.add(infoBean);
        list.add(infoBean2);
        list.add(infoBean3);
        list.add(infoBean4);
        list.add(infoBean5);

        mView.loadData(list);

    }

    @Override
    public void getMoreData() {

    }
}

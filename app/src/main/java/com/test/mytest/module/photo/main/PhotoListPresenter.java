package com.test.mytest.module.photo.main;

import com.blankj.utilcode.utils.ThreadPoolUtils;
import com.blankj.utilcode.utils.Utils;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

import rx.Observable;

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
    public void getData(final boolean isRefresh) {
        if(!isRefresh){
            mView.showLoading();
        }
        List<PhotoInfoBean> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            PhotoInfoBean infoBean = new PhotoInfoBean();
            if(i%5==0){
                infoBean.height = 800;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet1;
            }else  if(i%5==1){
                infoBean.height = 400;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet2;
            }else  if(i%5==2){
                infoBean.height = 200;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet3;
            }else  if(i%5==3){
                infoBean.height = 500;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet4;
            }else  if(i%5==4){
                infoBean.height = 100;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet5;
            }else if(i%5==0&&i%6==0) {
                infoBean.resId=R.drawable.ic_github;
            }
            list.add(infoBean);

        }
        if(isRefresh){
            mView.finishRefresh();
        }else{
            mView.hideLoading();
        }
        mView.loadData(list);
    }

    @Override
    public void getMoreData() {
        List<PhotoInfoBean> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            PhotoInfoBean infoBean = new PhotoInfoBean();
            if(i%5==0){
                infoBean.height = 800;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet1;
            }else  if(i%5==1){
                infoBean.height = 400;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet2;
            }else  if(i%5==2){
                infoBean.height = 200;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet3;
            }else  if(i%5==3){
                infoBean.height = 500;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet4;
            }else  if(i%5==4){
                infoBean.height = 100;
                infoBean.width = 400;
                infoBean.resId=R.drawable.ic_pet5;
            }else if(i%5==0&&i%6==0) {
                infoBean.resId=R.drawable.ic_github;
            }
            list.add(infoBean);

        }
        mView.loadMoreData(list);
    }
}

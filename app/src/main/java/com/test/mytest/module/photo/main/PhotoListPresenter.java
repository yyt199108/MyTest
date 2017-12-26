package com.test.mytest.module.photo.main;

import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.api.model.PhotoModel;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.utils.PrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017-11-22.
 * 图集列表界面 Presenter
 */

public class PhotoListPresenter implements PhotoListContract.Presenter {

    private PhotoModel photoModel;
    private PhotoListContract.View mView;

    private int mPage = 0;
    private int pageSize = 20;

    public PhotoListPresenter(PhotoListContract.View view) {
        this.mView = view;
        photoModel = new PhotoModel();
    }

    @Override
    public void getData(final boolean isRefresh) {
        if (!isRefresh) {
            mView.showLoading();
        }

        photoModel.getMainPetPhotoList(mPage, pageSize, PrefUtils.getUserId(), PrefUtils.getToken(), "2")
                .compose(mView.bindLifecycle())
                .subscribe(new Observer<BaseListRes<PhotoInfoBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                    }

                    @Override
                    public void onComplete() {
                        KLog.e("onComplete");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseListRes<PhotoInfoBean> photoInfoBeanBaseListRes) {
                        mView.loadData(photoInfoBeanBaseListRes.data.dataList);
                        if (photoInfoBeanBaseListRes.data.page.hasNextPage(mPage)) {
                            mPage++;
                        } else {
                            mView.hasNoMoreData();
                        }
                    }
                });
        if (isRefresh) {
            mView.finishRefresh();
        } else {
            mView.hideLoading();
        }
    }

    @Override
    public void getMoreData() {
        photoModel.getMainPetPhotoList(mPage, pageSize, PrefUtils.getUserId(), PrefUtils.getToken(), "2")
                .compose(mView.bindLifecycle())
                .subscribe(new Observer<BaseListRes<PhotoInfoBean>>() {
                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                    }

                    @Override
                    public void onComplete() {
                        KLog.e("onComplete");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseListRes<PhotoInfoBean> photoInfoBeanBaseListRes) {
                        mView.loadMoreData(photoInfoBeanBaseListRes.data.dataList);
                        if (photoInfoBeanBaseListRes.data.page != null && photoInfoBeanBaseListRes.data.page.hasNextPage(mPage)) {
                            mPage++;
                        } else {
                            mView.hasNoMoreData();
                        }
                    }
                });
    }
}

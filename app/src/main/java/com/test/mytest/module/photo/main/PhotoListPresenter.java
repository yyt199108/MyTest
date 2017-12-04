package com.test.mytest.module.photo.main;

import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.api.model.PhotoModel;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseListRes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017-11-22.
 * 图集列表界面 Presenter
 */

public class PhotoListPresenter implements PhotoListContract.Presenter {

    private PhotoModel photoModel;
    private PhotoListContract.View mView;

    public PhotoListPresenter(PhotoListContract.View view) {
        this.mView = view;
        photoModel = new PhotoModel();
    }

    @Override
    public void getData(final boolean isRefresh) {
        if (!isRefresh) {
            mView.showLoading();
        }
        photoModel.getMainPetPhotoList()
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
                KLog.e("onNext");

                List<PhotoInfoBean> list = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    PhotoInfoBean infoBean = new PhotoInfoBean();
                    if (i % 5 == 0) {
                        infoBean.width = 1200;
                        infoBean.height = 1800;
                        infoBean.resId = R.drawable.ic_pet1;
                    } else if (i % 5 == 1) {
                        infoBean.width = 1920;
                        infoBean.height = 1200;
                        infoBean.resId = R.drawable.ic_pet2;
                    } else if (i % 5 == 2) {
                        infoBean.width = 548;
                        infoBean.height = 660;
                        infoBean.resId = R.drawable.ic_pet3;
                    } else if (i % 5 == 3) {
                        infoBean.width = 939;
                        infoBean.height = 1200;
                        infoBean.resId = R.drawable.ic_pet4;
                    } else if (i % 5 == 4) {
                        infoBean.width = 1024;
                        infoBean.height = 683;
                        infoBean.resId = R.drawable.ic_pet5;
                    } else if (i % 5 == 0 && i % 6 == 0) {
                        infoBean.resId = R.drawable.ic_github;
                    }
                    list.add(infoBean);
                    mView.loadData(list);

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
        List<PhotoInfoBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PhotoInfoBean infoBean = new PhotoInfoBean();
            if (i % 5 == 0) {
                infoBean.width = 1200;
                infoBean.height = 1800;
                infoBean.resId = R.drawable.ic_pet1;
            } else if (i % 5 == 1) {
                infoBean.width = 1920;
                infoBean.height = 1200;
                infoBean.resId = R.drawable.ic_pet2;
            } else if (i % 5 == 2) {
                infoBean.width = 548;
                infoBean.height = 660;
                infoBean.resId = R.drawable.ic_pet3;
            } else if (i % 5 == 3) {
                infoBean.width = 939;
                infoBean.height = 1200;
                infoBean.resId = R.drawable.ic_pet4;
            } else if (i % 5 == 4) {
                infoBean.width = 1024;
                infoBean.height = 683;
                infoBean.resId = R.drawable.ic_pet5;
            } else if (i % 5 == 0 && i % 6 == 0) {
                infoBean.resId = R.drawable.ic_github;
            }
            list.add(infoBean);

        }
        mView.loadMoreData(list);
    }
}

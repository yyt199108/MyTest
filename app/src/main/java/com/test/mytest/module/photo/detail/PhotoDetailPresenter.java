package com.test.mytest.module.photo.detail;

import com.blankj.utilcode.utils.DeviceUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoDetailBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.exception.ApiException;
import com.test.mytest.api.model.CommentModel;
import com.test.mytest.api.model.PhotoModel;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.api.response.BaseRes;
import com.test.mytest.utils.PrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017-11-24.
 */

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {

    private CommentModel mCommentModel;
    private PhotoModel mPhotoModel;
    private int mPhotoId;
    private PhotoDetailContract.View mView;

    public PhotoDetailPresenter(PhotoDetailContract.View view, int photoId) {
        this.mView = view;
        this.mPhotoId = photoId;
        mPhotoModel = new PhotoModel();
        mCommentModel = new CommentModel();
    }

    @Override
    public void getData(boolean isRefresh) {
        if (!isRefresh) {
            mView.showLoading();
        }
        mPhotoModel.getDetailPetPhoto(PrefUtils.getUserId(), PrefUtils.getToken(), mPhotoId)
                .subscribe(new Observer<BaseBeanRes<PhotoInfoBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseBeanRes<PhotoInfoBean> photoInfoBeanBaseBeanRes) {
                        if (photoInfoBeanBaseBeanRes.data != null) {
                            mView.loadPhotoDetailView(photoInfoBeanBaseBeanRes.data);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            mView.showServerError(((ApiException) e).message);
                        } else {
                            mView.showServerError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        List<CommentBean> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            CommentBean commentBean = new CommentBean();
//            commentBean.createTime = (i + 1) + "分钟前";
//            commentBean.commentLocation = "北京/海淀";
//            commentBean.nickName = "卡卡";
//            commentBean.commentContent = "拍照技术真不错";
//            commentBean.commentFavorCount = 22;
//            list.add(commentBean);
//        }
//        if (list.size() > 0) {
//            list.get(0).showCommentTypeTag = true;
//            mView.showLookMoreComment();
//        }
//        mView.loadCommentListView(list);

        if (isRefresh) {
            mView.finishRefresh();
        } else {
            mView.hideLoading();
        }
    }

    @Override
    public void getMoreData() {

    }

    @Override
    public void getCommentList() {
        mCommentModel.getPhotoDetailCommentList(mPhotoId, PrefUtils.getUserId(), PrefUtils.getToken(), 1, 3)
                .subscribe(new Observer<BaseListRes<CommentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseListRes<CommentBean> commentBeanBaseListRes) {
                        if (commentBeanBaseListRes != null) {
                            if (commentBeanBaseListRes.data != null && commentBeanBaseListRes.data.dataList != null) {
                                List<CommentBean> dataList = commentBeanBaseListRes.data.dataList;
                                if (dataList.size() > 0) {
                                    dataList.get(0).showCommentTypeTag = true;
                                    mView.showLookMoreComment();
                                }
                                mView.loadCommentListView(dataList);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            mView.showServerError(((ApiException) e).message);
                        } else {
                            mView.showServerError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void like(String jokesId, String jokeCreaterId) {
        Map<String, String> map = new HashMap<>();
        map.put("jokesId", jokesId);
        map.put("jokeCreaterId", jokeCreaterId);
        map.put("category", "2");
        map.put("ip", NetworkUtils.getIPAddress(true));
        mPhotoModel.like(map)
                .subscribe(new Observer<BaseRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseRes baseRes) {
                        mView.likeSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            mView.showServerError(((ApiException) e).message);
                        } else {
                            mView.showServerError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

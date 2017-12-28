package com.test.mytest.module.comment;

import android.text.TextUtils;

import com.socks.library.KLog;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.model.CommentModel;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.api.response.BaseRes;
import com.test.mytest.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListPresenter implements CommentListContract.Presenter {
    private int mPhotoId;
    private CommentModel mModel;
    private CommentListContract.View mView;


    public CommentListPresenter(CommentListContract.View view,int photoId) {
        this.mView = view;
        this.mPhotoId=photoId;
        this.mModel = new CommentModel();
    }

    int pageNum=1;
    int pageSize=20;

    @Override
    public void getData(final boolean isRefresh) {
        if(!isRefresh){
            mView.showLoading();
        }
        mModel.getPhotoDetailCommentList(mPhotoId, PrefUtils.getUserId(), PrefUtils.getToken(), pageNum, pageSize)
                .compose(mView.bindLifecycle())
                .subscribe(new Observer<BaseListRes<CommentBean>> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        KLog.e("dispose");
                    }

                    @Override
                    public void onNext(BaseListRes<CommentBean> commentBeanBaseListRes) {
                        KLog.e("onNext");

                        if (isRefresh) {
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                        if(commentBeanBaseListRes.data.page.hasNextPage(pageNum)){
                            pageNum++;
                        }else{
                            mView.hasNoMoreData();
                        }

                        mView.loadData(commentBeanBaseListRes.data.dataList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e("error "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        KLog.e("complete");
                    }
                });
    }

    @Override
    public void getMoreData() {
//        mView.hideLoading();
        mView.hasNoMoreData();
    }

    @Override
    public void addComment(String photoId, String commentContent, String defendantId) {
        if(TextUtils.isEmpty(defendantId)){
            defendantId=null;
        }
        mModel.addComment(photoId,commentContent,defendantId,PrefUtils.getUserId(),PrefUtils.getToken())
                .subscribe(new Observer<BaseRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseRes baseRes) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

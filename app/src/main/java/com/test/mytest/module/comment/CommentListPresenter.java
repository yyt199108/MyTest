package com.test.mytest.module.comment;

import com.socks.library.KLog;
import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.model.CommentModel;
import com.test.mytest.api.response.BaseListRes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by yyt19 on 2017/11/28.
 */

public class CommentListPresenter implements CommentListContract.Presenter {
    private CommentModel mModel;
    private CommentListContract.View mView;

    public CommentListPresenter(CommentListContract.View view) {
        this.mView = view;
        this.mModel = new CommentModel();
    }

    @Override
    public void getData(final boolean isRefresh) {
        if(!isRefresh){
            mView.showLoading();
        }
        mModel.getPhotoDetailCommentList()
                .compose(mView.bindLifecycle())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseListRes<CommentBean>, List<CommentBean>>() {
                    @Override
                    public List<CommentBean> apply(BaseListRes<CommentBean> commentBeanBaseListRes) throws Exception {
//                        return commentBeanBaseListRes.data.dataList;
                        return new ArrayList<CommentBean>();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CommentBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        KLog.e("dispose");
                    }

                    @Override
                    public void onNext(List<CommentBean> commentBeanList) {
                        KLog.e("onNext");

                        if (isRefresh) {
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                        List<CommentBean> list = new ArrayList<>();
                        for(int i=0;i<11;i++) {
                            CommentBean commentBean=new CommentBean();
                            commentBean.createTime = (i+1)+"分钟前";
                            commentBean.commentLocation = "北京/海淀";
                            commentBean.nickName = "卡卡"+i;
                            commentBean.commentContent = "拍照技术真不错"+i;
                            commentBean.commentFavorCount = 22;
                            if(i==0){
                                commentBean.showCommentTypeTag=true;
                                commentBean.commentTypeName = "热门评论";
                            }if(i==3){
                                commentBean.showCommentTypeTag=true;
                                commentBean.commentTypeName = "最新评论";
                            }
                            list.add(commentBean);
                        }
                        mView.loadData(list);
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
}

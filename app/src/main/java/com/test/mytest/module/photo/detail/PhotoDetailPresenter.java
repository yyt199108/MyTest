package com.test.mytest.module.photo.detail;

import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoDetailBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.model.PhotoModel;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2017-11-24.
 */

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {

    private PhotoModel mPhotoModel;
    private int mPhotoId;
    private PhotoDetailContract.View mView;

    public PhotoDetailPresenter(PhotoDetailContract.View view,int photoId){
        this.mView=view;
        this.mPhotoId=photoId;
        mPhotoModel=new PhotoModel();
    }
    @Override
    public void getData(boolean isRefresh) {
        if(!isRefresh){
            mView.showLoading();
        }
        mPhotoModel.getDetailPetPhoto(PrefUtils.getUserId(), PrefUtils.getToken(),mPhotoId)
        .subscribe(new Observer<BaseBeanRes<PhotoInfoBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBeanRes<PhotoInfoBean> photoInfoBeanBaseBeanRes) {
                if(photoInfoBeanBaseBeanRes.data!=null){
                    mView.loadPhotoDetailView(photoInfoBeanBaseBeanRes.data);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
//        PhotoDetailBean photoDetailBean=new PhotoDetailBean();
//        photoDetailBean.doFavor = "1";
//        photoDetailBean.attentionCount = "6";
//        mView.loadPhotoDetailView(photoDetailBean);

        List<CommentBean> list = new ArrayList<>();
        for(int i=0;i<3;i++) {
            CommentBean commentBean=new CommentBean();
            commentBean.createTime = (i+1)+"分钟前";
            commentBean.commentLocation = "北京/海淀";
            commentBean.nickName = "卡卡";
            commentBean.commentContent = "拍照技术真不错";
            commentBean.commentFavorCount = 22;
            list.add(commentBean);
        }
        if(list.size()>0){
            list.get(0).showCommentTypeTag =true;
            mView.showLookMoreComment();
        }
        mView.loadCommentListView(list);
        if(isRefresh){
            mView.finishRefresh();
        }else{
            mView.hideLoading();
        }
    }

    @Override
    public void getMoreData() {

    }
}

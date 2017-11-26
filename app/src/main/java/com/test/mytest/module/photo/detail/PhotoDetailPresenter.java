package com.test.mytest.module.photo.detail;

import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-24.
 */

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {

    private PhotoDetailContract.View mView;

    public PhotoDetailPresenter(PhotoDetailContract.View view){
        this.mView=view;
    }
    @Override
    public void getData(boolean isRefresh) {
        if(!isRefresh){
            mView.showLoading();
        }
        PhotoDetailBean photoDetailBean=new PhotoDetailBean();
        photoDetailBean.doFavor = "1";
        photoDetailBean.attentionCount = "6";
        mView.loadPhotoDetailView(photoDetailBean);

        List<CommentBean> list = new ArrayList<>();
        for(int i=0;i<3;i++) {
            CommentBean commentBean=new CommentBean();
            commentBean.createTime = (i+1)+"分钟前";
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

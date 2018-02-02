package com.test.mytest.module.photo.detail;

import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.IBaseView;

import java.util.List;

/**
 * Created by admin on 2017-11-23.
 */

public interface PhotoDetailContract {
    interface View extends IBaseView {
        /**
         * 显示详情
         * @param photoDetailBean
         */
        void loadPhotoDetailView(PhotoInfoBean photoDetailBean);

        /**
         * 显示评论列表
         * @param commentBeanList
         */
        void loadCommentListView(List<CommentBean> commentBeanList);

        /**
         * 显示查看更多评论
         */
        void showLookMoreComment();

        /**
         * 点赞
         */
        void likeSuccess();
    }

    interface Presenter extends IBasePresenter {
        void getCommentList();

        void like(String jokesId,String jokeCreaterId);
    }
}

package com.test.mytest.module.comment;

import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.ILoadDataView;

import java.util.List;

/**
 * Created by yyt19 on 2017/11/28.
 */

public interface CommentListContract {
    interface View extends ILoadDataView<List<CommentBean>>{
        void clearDefendantInfo();
        void setDefendantInfo(String defendantId,String defendantNickName);
    }
    interface Presenter extends IBasePresenter{
        void addComment(String photoId,String commentContent,String defendantId);
    }
}

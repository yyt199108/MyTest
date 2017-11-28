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

    }
    interface Presenter extends IBasePresenter{

    }
}

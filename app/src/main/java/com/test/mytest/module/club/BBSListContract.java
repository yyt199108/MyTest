package com.test.mytest.module.club;

import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.ILoadDataView;

import java.util.List;

/**
 * Created by admin on 2017-11-23.
 */

public interface BBSListContract {

    interface View extends ILoadDataView<List<PhotoInfoBean>> {
    }

    interface Presenter extends IBasePresenter {

    }
}

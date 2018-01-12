package com.test.mytest.module.user.main;

import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.IBaseView;

/**
 * Created by admin on 2018-01-02.
 */

public interface PetInfoContract {
    interface View extends IBaseView{

        void showPetInfo(AccountBean data);
    }

    interface Presenter extends IBasePresenter{

    }
}

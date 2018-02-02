package com.test.mytest.module.setting;

import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.base.IBaseView;

/**
 * Created by admin on 2018-01-24.
 */

public interface SettingContract {
    interface View extends IBaseView{

        void modifySuccess();
    }

    interface Presenter extends IBasePresenter{
        void modifyInfo(String age,String gender,String nickname);
    }
}

package com.test.mytest.module.user.main;

import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.api.exception.ApiException;
import com.test.mytest.api.model.UserModel;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.api.response.BaseRes;
import com.test.mytest.utils.PrefUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2018-01-02.
 */

public class PetInfoPresenter implements PetInfoContract.Presenter {

    private UserModel userModel;
    private PetInfoContract.View mView;
    private String petId;

    public PetInfoPresenter(PetInfoContract.View view, String petId){
        this.mView=view;
        this.petId =petId;

        userModel=new UserModel();
    }

    @Override
    public void getData(final boolean isRefresh) {
        if(!isRefresh){
            mView.showLoading();
        }
        userModel.petInfo(PrefUtils.getUserId(), PrefUtils.getToken(), petId)
        .subscribe(new Observer<BaseBeanRes<AccountBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBeanRes<AccountBean> accountBeanBaseBeanRes) {
                if (isRefresh) {
                    mView.finishRefresh();
                } else {
                    mView.hideLoading();
                }
                if(accountBeanBaseBeanRes!=null&&accountBeanBaseBeanRes.data!=null) {
                    mView.showPetInfo(accountBeanBaseBeanRes.data);
                }else{

                }
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof ApiException) {
                    if(((ApiException) e).code==213){
                        //登录过期
                        mView.loginTokenOut();
                    }else {
                        mView.showServerError(((ApiException) e).message);
                    }
                } else {
                    mView.showServerError(e.getMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getMoreData() {

    }
}

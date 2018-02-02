package com.test.mytest.module.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by admin on 2017-11-21.
 * 基础BaseView接口
 */

public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();
    /**
     * 隐藏加载
     */
    void hideLoading();
    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    void showNetError();

    /**
     * 服务器返回异常
     * @param message
     */
    void showServerError(String message);

    /**
     * 登录过期
     */
    void loginTokenOut();
    /**
     * 完成刷新, 新增控制刷新
     */
    void finishRefresh();

    /**
     * 绑定生命周期
     */
    LifecycleTransformer bindLifecycle();
}

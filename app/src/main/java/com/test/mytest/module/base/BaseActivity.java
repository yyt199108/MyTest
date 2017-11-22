package com.test.mytest.module.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.test.mytest.R;
import com.test.mytest.utils.SwipeRefreshHelper;
import com.test.mytest.widget.EmptyLayout;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基类Activity
 * Created by admin on 2017-11-21.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity {
    /**
     * @Nullalbe 表明view可以为null
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    /**
     * 把Presenter提取到基类需要配合基类的initInjector()进行注入。
     * 如果继承这个基类则必定要提供一个Presenter注入方法
     * 该APP所有 Presenter 都是在 Module 提供注入实现，
     * 也可以选择提供另外不带 Presenter 的基类
     */
    @Inject
    protected T mPresenter;

    /**
     * 刷新控件，注意，资源的ID一定要一样
     */
    @Nullable
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initViews();
        initSwipeRefresh();
        updateView(false);
    }

    private void initSwipeRefresh() {
        if(mSwipeRefresh!=null){
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateView(true);
                }
            });
        }
    }
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();
    /**
     * Dagger注入
     */
    protected abstract void initInjector();
    /**
     * 初始化视图控件
     */
    protected abstract void initViews();
    /**
     * 更新视图控件
     * @param isRefresh
     */
    protected abstract void updateView(boolean isRefresh);






}

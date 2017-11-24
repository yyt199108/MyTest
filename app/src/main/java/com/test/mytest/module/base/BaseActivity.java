package com.test.mytest.module.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.mytest.R;
import com.test.mytest.utils.SwipeRefreshHelper;
import com.test.mytest.widget.EmptyLayout;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * 基类Activity
 * Created by admin on 2017-11-21.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends SupportActivity implements IBaseView, LifecycleProvider<ActivityEvent>,EmptyLayout.OnRetryListener {
    /**
     * @Nullalbe 表明view可以为null
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    @Nullable
    @BindView(R.id.title)
    protected TextView mTitle;

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

    @Nullable
    @BindView(R.id.recy_view)
    protected RecyclerView mRecyView;

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initViews();
        initSwipeRefresh();
        updateViews(false);
    }

    private void initSwipeRefresh() {
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.init(mSwipeRefresh, new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    updateViews(true);
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
     *
     * @param isRefresh
     */
    protected abstract void updateViews(boolean isRefresh);

    /**
     * 显示加载动画
     */
    @Override
    public void showLoading(){
        if(mEmptyLayout!=null){
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh,false);
        }
    }
    /**
     * 隐藏加载
     */
    @Override
    public void hideLoading(){
        if(mEmptyLayout!=null){
            mEmptyLayout.hide();
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh,true);
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh,false);
        }
    }
    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    @Override
    public void showNetError(){
        if(mEmptyLayout!=null){
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }
    /**
     * 完成刷新, 新增控制刷新
     */
    @Override
    public void finishRefresh(){
        if(mSwipeRefresh!=null){
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    @CallSuper
    protected void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    @CallSuper
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }


}

package com.test.mytest.module.base;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.utils.DeviceUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.application.MyApp;
import com.test.mytest.injector.components.ApplicationComponent;
import com.test.mytest.module.login.LoginActivity;
import com.test.mytest.utils.PrefUtils;
import com.test.mytest.utils.SwipeRefreshHelper;
import com.test.mytest.widget.EmptyLayout;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 基类Activity
 * Created by admin on 2017-11-21.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends SupportActivity implements IBaseView, LifecycleProvider<ActivityEvent>, EmptyLayout.OnRetryListener {
    /**
     * @Nullalbe 表明view可以为null
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;

    @Nullable
    @BindView(R.id.title)
    protected TextView mTitle;

    @Nullable
    @BindView(R.id.back_img_lay)
    protected View mBackLay;

    @Nullable
    @BindView(R.id.right_tv)
    protected TextView mRightTv;

    @Nullable
    @BindView(R.id.right_lay)
    protected View mRightLay;


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

    protected int page = 0;

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

    public boolean isSoftShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        KLog.e("height:" + (screenHeight - rect.bottom - getSoftBottomBarHeight()));
        return screenHeight - rect.bottom - getSoftBottomBarHeight() != 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftBottomBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
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
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);
        }
    }

    /**
     * 隐藏加载
     */
    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);
        }
    }

    /**
     * 显示网络错误，modify 对网络异常在 BaseActivity 和 BaseFragment 统一处理
     */
    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public void showServerError(String message) {
        if (!TextUtils.isEmpty(message)) {
            ToastUtils.showShortToast(message);
        }
    }

    @Override
    public void loginTokenOut() {
        new AlertDialog.Builder(this)
                .setTitle("登录过期")
                .setMessage("用户登录过期，请重新登录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PrefUtils.clearAll();
                        MyApp.clearAct();
                        startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 完成刷新, 新增控制刷新
     */
    @Override
    public void finishRefresh() {
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    @Override
    public void onRetry() {
        updateViews(false);
    }

    protected ApplicationComponent getApplicationComponent() {
        return MyApp.getApplicationComponent();
    }


    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initViews();
        initSwipeRefresh();
        updateViews(false);

        MyApp.add(this);
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
        MyApp.remove(this);
    }

    private ArrayList<MyTouchListener> myTouchListenerList = new ArrayList<>();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //将触摸事件传递给回调函数 fragment触摸事件分发，将触摸事件分发给每个能够响应的fragment
        for (MyTouchListener listener : myTouchListenerList) {
            if (listener != null) {
                listener.onTouch(ev);
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    public interface MyTouchListener {
        void onTouch(MotionEvent ev);
    }

    //注册回调事件
    public void registerMyTouchListener(MyTouchListener myTouchListener) {
        this.myTouchListenerList.add(myTouchListener);
    }

    public void unregisterMyTouchListener(MyTouchListener myTouchListener) {
        this.myTouchListenerList.remove(myTouchListener);
    }
}

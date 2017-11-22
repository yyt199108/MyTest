package com.test.mytest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.test.mytest.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 加载、空视图
 * Created by admin on 2017-11-21.
 */

public class EmptyLayout extends FrameLayout {

    private static final int STATUS_HIDE = 1001;
    private static final int STATUS_LOADING = 1;
    private static final int STATUS_NO_NET = 2;
    private static final int STATUS_NO_DATA = 3;
    private Context mContext;
    private int mBgColor;
    private OnRetryListener mOnRetryListener;
    private int mEmptyStatus = STATUS_LOADING;

    @BindView(R.id.tv_net_error)
    TextView mTvEmptyMessage;
    @BindView(R.id.rl_empty_container)
    View mRlEmptyContainer;
    @BindView(R.id.empty_loading)
    SpinKitView mEmptyLoading;
    @BindView(R.id.empty_layout)
    FrameLayout mEmptyLayout;

    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    //初始化
    private void init(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        try {
            mBgColor = a.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        } finally {
            a.recycle();
        }
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        ButterKnife.bind(this);
        mEmptyLayout.setBackgroundColor(mBgColor);
        _switchEmptyView();
    }

    /**
     * 切换视图
     */
    private void _switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(GONE);
                mEmptyLoading.setVisibility(VISIBLE);
                break;
            case STATUS_NO_DATA:
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
                mEmptyLoading.setVisibility(GONE);
                mRlEmptyContainer.setVisibility(VISIBLE);
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                break;
        }
    }

    /**
     * 隐藏视图
     */
    public void hide() {
        mEmptyStatus = STATUS_HIDE;
        _switchEmptyView();
    }

    /**
     * 设置状态
     *
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus) {
        mEmptyStatus = emptyStatus;
        _switchEmptyView();
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public int getEmptyStatus() {
        return mEmptyStatus;
    }

    /**
     * 设置异常消息
     *
     * @param msg 显示消息
     */
    public void setEmptyMessage(String msg) {
        mTvEmptyMessage.setText(msg);
    }

    public void hideErrorIcon() {
        mTvEmptyMessage.setCompoundDrawables(null, null, null, null);
    }

    public void setLoadingIcon(Sprite d) {
        mEmptyLoading.setIndeterminateDrawable(d);
    }

    public void setRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }

    public interface OnRetryListener {
        void onRetry();
    }

    @OnClick({R.id.tv_net_error})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_net_error:
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry();
                }
                break;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA})
    public @interface EmptyStatus {
    }
}

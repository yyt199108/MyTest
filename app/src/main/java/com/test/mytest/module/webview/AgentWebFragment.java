package com.test.mytest.module.webview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.LogUtils;
import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DownLoadResultListener;
import com.just.library.FragmentKeyDown;
import com.just.library.PermissionInterceptor;
import com.just.library.WebDefaultSettingsManager;
import com.test.mytest.R;
import com.test.mytest.module.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2017-12-07.
 */

public class AgentWebFragment extends BaseFragment implements FragmentKeyDown {

    public static final String URL_KEY = "url_key";
    private AgentWeb mAgentWeb;
    private PopupMenu mPopupMenu;

    public static AgentWebFragment getInstance(Bundle bundle) {
        AgentWebFragment fragment = new AgentWebFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @BindView(R.id.toolbar_title)
    TextView mTitleTextView;
    @BindView(R.id.iv_back)
    ImageView mBackImageView;
    @BindView(R.id.iv_finish)
    protected ImageView mFinishImageView;
    @BindView(R.id.iv_more)
    ImageView mMoreImageView;

    @BindView(R.id.view_line)
    View mLineView;

    @OnClick({R.id.iv_back,R.id.iv_finish,R.id.iv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                mAgentWeb.back();
                break;
            case R.id.iv_finish:
                AgentWebFragment.this.getActivity().finish();
                break;
            case R.id.iv_more:
                showPoPup(view);
                break;
        }
    }


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_agentweb;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((ViewGroup) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//传入AgentWeb的父控件
                .setIndicatorColorWithHeight(-1, 2) //设置进度条颜色与高度，-1为默认值，2单位dp
                .setAgentWebWebSettings(getSettings()) ////设置 AgentWebSettings
                .setWebViewClient(mWebViewClient) //WebViewClient 与webview一样
                .setWebChromeClient(mWebChromeClient)
                .setPermissionInterceptor(mPermissionInterceptor) //权限拦截
                .setReceivedTitleCallback(mCallback) //标题回调
                .setSecurityType(AgentWeb.SecurityType.strict) //严格模式
                .addDownLoadResultListener(mDownLoadResultListener) //下载回调
                .openParallelDownload()//打开并行下载 , 默认串行下载
                .setNotifyIcon(R.mipmap.download)
                .createAgentWeb()//创建AgentWeb
                .ready()//设置 WebSettings
                .go(getUrl()); //WebView载入该url地址的页面并显示。
    }

    protected DownLoadResultListener mDownLoadResultListener = new DownLoadResultListener() {
        @Override
        public void success(String path) {
            //do you work
        }

        @Override
        public void error(String path, String resUrl, String cause, Throwable e) {
            //do you work
        }
    };

    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mTitleTextView != null && !TextUtils.isEmpty(title)) {
                if (title.length() > 10) {
                    title = title.substring(0, 10).concat("...");
                }
                mTitleTextView.setText(title);
            }
        }
    };


    private AgentWebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    protected WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (url.equals(getUrl())) {
                pageNavigator(View.GONE);
            } else {
                pageNavigator(View.VISIBLE);
            }
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }
    };

    protected PermissionInterceptor mPermissionInterceptor = new PermissionInterceptor() {

        //AgentWeb 在触发某些敏感的 Action 时候会回调该方法， 比如定位触发 。
        //例如 https//:www.baidu.com 该 Url 需要定位权限， 返回false ，如果版本大于等于23 ， agentWeb 会动态申请权限 ，true 该Url对应页面请求定位失败。
        //该方法是每次都会优先触发的 ， 开发者可以做一些敏感权限拦截 。
        @Override
        public boolean intercept(String url, String[] permissions, String action) {
            LogUtils.i("url:" + url + "  permission:" + permissions + " action:" + action);
            return false;
        }
    };

    private void pageNavigator(int tag) {
        mBackImageView.setVisibility(tag);
        mLineView.setVisibility(tag);
    }

    private String getUrl() {
        String target = "";
        if (!TextUtils.isEmpty(target = this.getArguments().getString(URL_KEY))) {
            target = getArguments().getString(URL_KEY);
        }
        return target;
    }


    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    private void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(this.getContext(), targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri url = Uri.parse(targetUrl);
        intent.setData(url);
        startActivity(intent);

    }

    /**
     * 显示更多菜单
     *
     * @param view 菜单依附在该View下面
     */
    private void showPoPup(View view) {
        if (mPopupMenu == null) {
            mPopupMenu = new PopupMenu(this.getActivity(), view);
            mPopupMenu.inflate(R.menu.toolbar_menu);
            mPopupMenu.setOnMenuItemClickListener(mOnMenuItemClickListener);
        }
        mPopupMenu.show();
    }

    //菜单事件
    private PopupMenu.OnMenuItemClickListener mOnMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {

                case R.id.refresh:
                    if (mAgentWeb != null) {
                        mAgentWeb.getLoader().reload();
                    }
                    return true;

                case R.id.copy:
                    if (mAgentWeb != null) {
                        toCopy(AgentWebFragment.this.getContext(), mAgentWeb.getWebCreator().get().getUrl());
                    }
                    return true;
                case R.id.default_browser:
                    if (mAgentWeb != null) {
                        openBrowser(mAgentWeb.getWebCreator().get().getUrl());
                    }
                    return true;
                case R.id.default_clean:
                    toCleanWebCache();
                    return true;
                default:
                    return false;
            }

        }
    };

    //清除 WebView 缓存
    private void toCleanWebCache() {

        if (this.mAgentWeb != null) {

            //清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
            this.mAgentWeb.clearWebCache();
            Toast.makeText(getActivity(), "已清理缓存", Toast.LENGTH_SHORT).show();
            //清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
//            AgentWebConfig.clearDiskCache(this.getContext());
        }

    }


    //复制字符串
    private void toCopy(Context context, String text) {

        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));

    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onDestroyView() {
//        toCleanWebCache();
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }
}

package com.llw.goodtrash.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.widget.Toolbar;
import com.llw.goodtrash.R;
import com.llw.mvplibrary.base.BaseActivity;

/**
 * 新闻详情页面
 *
 * @author i小灰
 * @date 2021/4/16 15:43
 */
public class NewsDetailsActivity extends BaseActivity {
    private Toolbar toolbar;
    private WebView webView;

    @Override
    public void initData(Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_view);
        back(toolbar, false);
        //设置状态栏
        setStatubar(this, R.color.white, true);
        if (!hasNetwork()) {
            //获取Token
            showMsg("请连接网络");
        }
        //加载WebView
        loadWebView();
    }

    /**
     * 加载webView
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebView() {
        //开始加载Url
        showLoadingDialog();

        String url = getIntent().getStringExtra("url");
        if (url == null || url.isEmpty()) {
            showMsg("无法显示新闻详情");
            return;
        }

        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //支持插件
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        //优先使用缓存
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //加载一个网页

        webView.loadUrl(url);

        //重写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //加载完成
                hideLoadingDialog();
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_details;
    }
}

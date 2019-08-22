package com.jiuhao.jhjk.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 加载web
 * title 标题
 * html 加载页面URL
 */
public class NewsWebView extends BaseActivity {

    private ImageView mIvBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    private WebView webView;

    private String html;
    private String title;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.view_web);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        webView = (WebView) findViewById(R.id.web);
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        html = intent.getStringExtra("html");
        tvTitle.setText(title);
        initData();
    }

    private void initData() {
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        //表示当前的webview支持javaScript*****************
        settings.setJavaScriptEnabled(true);
        //设置是否支持缩放
        settings.setSupportZoom(true);
        //设置是否使用webview内置的缩放组件，由悬浮在窗口上的缩放控制跟手势控制组成
        settings.setBuiltInZoomControls(true);
        //是否显示窗口的悬浮设置默认是true
        settings.setDisplayZoomControls(true);
        //屏幕自适应
        settings.setUseWideViewPort(true);//支持任意比例缩放`
        //当页面宽度超过WebView显示宽度时，缩小页面适应WebView，默认false
        settings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(html);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    protected void initEvent() {

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

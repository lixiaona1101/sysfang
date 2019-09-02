package com.jiuhao.jhjk.view;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 加载webview
 */
public class WebviewActivity extends BaseActivity {


    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 确认
     */
    private TextView tvTitleSure;
    private RelativeLayout rlTitleSure;
    private RelativeLayout rlTitle;
    private WebView webview;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_webview);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        webview = (WebView) findViewById(R.id.webview);

    }

    @Override
    protected void obtainData() {

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String html = intent.getStringExtra("html");

        tvTitle.setText(title);


        //一,获取webView的设置选项
        WebSettings settings = webview.getSettings();

        //表示当前的webview支持javaScript*****************
        settings.setJavaScriptEnabled(true);

        //三设置是否支持缩放
        settings.setSupportZoom(true);
        //设置是否使用webview内置的缩放组件，由悬浮在窗口上的缩放控制跟手势控制组成
        settings.setBuiltInZoomControls(true);
        //是否显示窗口的悬浮设置默认是true
        settings.setDisplayZoomControls(true);

        //四屏幕自适应
        settings.setUseWideViewPort(true);//支持任意比例缩放`
        //当页面宽度超过WebView显示宽度时，缩小页面适应WebView，默认false
        settings.setLoadWithOverviewMode(true);

        //五设置默认字体的大小1-72
//        settings.setDefaultFixedFontSize(60);
//        settings.setTextSize(WebSettings.TextSize.LARGER);
        settings.setTextSize(WebSettings.TextSize.NORMAL);//默认大小

        //六设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");

        //7设置缓存模式
        //LOAD_DEFAULT 默认加载方式
        //LOAD_CACHE_ELSE_NETWORK 按网络情况使用缓存
        //LOAD_NO_CACHE 不使用缓存
        //LOAD_CACHE_ONLY 只使用缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        //*************必须放在设置属性的最下面*************
        webview.loadUrl(html);

    }

    @Override
    protected void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

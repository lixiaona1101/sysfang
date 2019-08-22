package com.jiuhao.jhjk.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 科室
 */
public class AdministrativeActivity extends BaseActivity {

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
    private ImageView administrative1;
    private ImageView administrative2;
    private ImageView administrative3;
    private ImageView administrative4;
    private ImageView administrative5;
    private ImageView administrative6;
    private ImageView administrative7;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_administrative);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        administrative1 = (ImageView) findViewById(R.id.administrative1);
        administrative2 = (ImageView) findViewById(R.id.administrative2);
        administrative3 = (ImageView) findViewById(R.id.administrative3);
        administrative4 = (ImageView) findViewById(R.id.administrative4);
        administrative5 = (ImageView) findViewById(R.id.administrative5);
        administrative6 = (ImageView) findViewById(R.id.administrative6);
        administrative7 = (ImageView) findViewById(R.id.administrative7);
        tvTitle.setText("科室");
        tvTitleSure.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}

package com.jiuhao.jhjk.activity.patient;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 付费咨询设置-患者
 */
public class PaySetActivity extends BaseActivity {

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
    /**
     * 【患者名字】
     */
    private TextView personName;
    /**
     * 免费
     */
    private EditText money;
    /**
     * 保存设置
     */
    private TextView saveSet;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pay_set);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        personName = (TextView) findViewById(R.id.person_name);
        money = (EditText) findViewById(R.id.money);
        saveSet = (TextView) findViewById(R.id.save_set);
        tvTitle.setText("付费咨询设置");
    }

    @Override
    protected void obtainData() {

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

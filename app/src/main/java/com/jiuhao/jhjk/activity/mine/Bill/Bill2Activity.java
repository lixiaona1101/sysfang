package com.jiuhao.jhjk.activity.mine.Bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 新建问诊单
 */
public class Bill2Activity extends BaseActivity implements View.OnClickListener {

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
     * 中医科
     */
    private TextView billName;
    /**
     * 编辑
     */
    private TextView compile;
    /**
     * 问题库导入
     */
    private Button buttonOne;
    /**
     * 新建问题
     */
    private Button buttonTwo;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_bill2);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        billName = (TextView) findViewById(R.id.bill_name);
        compile = (TextView) findViewById(R.id.compile);
        buttonOne = (Button) findViewById(R.id.button_one);
        buttonOne.setOnClickListener(this);
        buttonTwo = (Button) findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(this);
        tvTitle.setText("问诊单");
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("保存");
    }

    @Override
    protected void obtainData() {

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        billName.setText(title);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button_one://问题库导入
                startActivity(new Intent(getContext(),ListQuestionActivity.class));
                break;
            case R.id.button_two://新建问题
                startActivity(new Intent(getContext(),QuestionActivity.class));
                break;
        }
    }
}

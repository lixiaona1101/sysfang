package com.jiuhao.jhjk.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 基本信息
 */
public class PersonalDataActivity extends BaseActivity {


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
     * 点击更换
     */
    private TextView upHead;
    private ImageView head;
    /**
     * 男
     */
    private TextView man;
    /**
     * 女
     */
    private TextView woman;
    /**
     * 职称
     */
    private TextView textView;
    private RelativeLayout administrative;
    private RelativeLayout major;
    /**
     * 点击这里编辑简介内容...
     */
    private EditText doctorSynopsis;
    /**
     * 0
     */
    private TextView nowNumber;
    /**
     * /300
     */
    private TextView san;
    private RelativeLayout occupation;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_personal_data);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        upHead = (TextView) findViewById(R.id.up_head);
        head = (ImageView) findViewById(R.id.head);
        man = (TextView) findViewById(R.id.man);
        woman = (TextView) findViewById(R.id.woman);
        textView = (TextView) findViewById(R.id.textView);
        administrative = (RelativeLayout) findViewById(R.id.administrative);
        major = (RelativeLayout) findViewById(R.id.major);
        doctorSynopsis = (EditText) findViewById(R.id.doctor_synopsis);
        nowNumber = (TextView) findViewById(R.id.now_number);
        san = (TextView) findViewById(R.id.san);
        tvTitle.setText("基本信息");
        rlTitleSure.setVisibility(View.VISIBLE);
        occupation = (RelativeLayout) findViewById(R.id.occupation);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

        //科室
        administrative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AdministrativeActivity.class));
            }
        });

        //职称
        occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OccupationActivity.class));
            }
        });
    }

}

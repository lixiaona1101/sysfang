package com.jiuhao.jhjk.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 账号管理
 */
public class AccountManagementActivity extends BaseActivity {


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
     * 137****5684
     */
    private TextView phoneNumber;
    /**
     * 更换
     */
    private TextView change;
    private RelativeLayout setPassword;
    private RelativeLayout relaCut;
    /**
     * 退出登录
     */
    private TextView secede;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_account_management);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        phoneNumber = (TextView) findViewById(R.id.phone_number);
        change = (TextView) findViewById(R.id.change);
        setPassword = (RelativeLayout) findViewById(R.id.set_password);
        relaCut = (RelativeLayout) findViewById(R.id.rela_cut);
        secede = (TextView) findViewById(R.id.secede);
        tvTitle.setText("账号管理");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        //更换绑定手机号
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),UserPhoneActivity.class));
            }
        });

        //设置登录密码
        setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),UserPassActivity.class));
            }
        });

        //切换账号
        relaCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),CutUserActivity.class));
            }
        });
    }
}

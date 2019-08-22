package com.jiuhao.jhjk.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 设置登录密码
 */
public class UserPassActivity extends BaseActivity implements View.OnClickListener {

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
     * 设置六位登录密码
     */
    private EditText etNewPhone;
    /**
     * 请输入验证码
     */
    private EditText etSetPhoneCode;
    /**
     * 获取验证码
     */
    private TextView tvSetPhoneGetCode;
    /**
     * 保存
     */
    private Button btnSavePhone;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_user_pass);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        etNewPhone = (EditText) findViewById(R.id.et_new_phone);
        etSetPhoneCode = (EditText) findViewById(R.id.et_set_phone_code);
        tvSetPhoneGetCode = (TextView) findViewById(R.id.tv_set_phone_get_code);
        btnSavePhone = (Button) findViewById(R.id.btn_save_phone);
        btnSavePhone.setOnClickListener(this);
        tvTitle.setText("设置登录密码");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO:OnCreate Method has been created, run FindViewById again to generate code
        setContentView(R.layout.activity_user_pass);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_save_phone:
                break;
        }
    }
}

package com.jiuhao.jhjk.activity.mine.AccountManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.activity.welcome.RegisterActivity;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;

/**
 * 切换账号
 */
public class CutUserActivity extends BaseActivity {


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
     * 173****4562
     */
    private TextView phone;
    private ImageView yes;
    /**
     * 切换账号
     */
    private TextView cut;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_cut_user);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        phone = (TextView) findViewById(R.id.phone);
        yes = (ImageView) findViewById(R.id.yes);
        cut = (TextView) findViewById(R.id.cut);
        tvTitle.setText("切换账号");
    }

    @Override
    protected void obtainData() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outLogin();
            }
        });

        String phoneNum = SPUtils.getString(getContext(), ConfigKeys.PHONE, "11111111111");
        phone.setText(centerPhone(phoneNum));

    }

    //退出账号
    public void outLogin() {
        OkHttpUtils.get(ConfigKeys.LOGINOUT, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {

                Config.quit(getContext());
                ToastUtils.show("退出登录成功！");
                startActivity(new Intent(getContext(), RegisterActivity.class));
            }

            @Override
            public void onFailure(int code, Exception e) {
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //手机号中间隐藏
    public String centerPhone(String phoneNum) {
        String substring1 = phoneNum.substring(0, 3);
        String substring2 = phoneNum.substring(7, 11);
        String substr = substring1 + "****" + substring2;
        return substr;
    }

    @Override
    protected void initEvent() {

    }

}

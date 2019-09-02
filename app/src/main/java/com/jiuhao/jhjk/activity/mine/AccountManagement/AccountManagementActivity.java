package com.jiuhao.jhjk.activity.mine.AccountManagement;

import android.content.Intent;
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
import com.orhanobut.logger.Logger;

/**
 * 账号管理
 */
public class AccountManagementActivity extends BaseActivity implements View.OnClickListener {


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
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        String phoneNum = SPUtils.getString(getContext(), ConfigKeys.PHONE, "11111111111");
        phoneNumber.setText(centerPhone(phoneNum));

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //更换绑定手机号
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UserPhoneActivity.class));
            }
        });

        //设置登录密码
        setPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UserPassActivity.class));
            }
        });

        //切换账号
        relaCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CutUserActivity.class));
            }
        });

        //退出登录
        secede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outLogin();
            }
        });
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
                Logger.e(e.getMessage());
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
    public void onClick(View view) {

    }
}

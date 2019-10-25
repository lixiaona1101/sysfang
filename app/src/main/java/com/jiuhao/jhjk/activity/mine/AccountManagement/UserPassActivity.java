package com.jiuhao.jhjk.activity.mine.AccountManagement;

import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.fy.BaseTimerTask;
import com.jiuhao.jhjk.utils.fy.ITimerListener;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Timer;

/**
 * 设置登录密码
 */
public class UserPassActivity extends BaseActivity implements View.OnClickListener, ITimerListener {

    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 手机号
     */
    private TextView oldphone;
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

    private String phoneNum;
    private Timer mTimer;
    private int mCount = 60;
    private BaseTimerTask timerTask;

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
        oldphone = (TextView) findViewById(R.id.oldPhone);
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
        phoneNum = SPUtils.getString(getContext(), ConfigKeys.PHONE, "未绑定");
        if (phoneNum.equals("未绑定")) {
            oldphone.setText(phoneNum);
        } else {
            oldphone.setText(Config.centerPhone(phoneNum));
        }

    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //获取验证码
        tvSetPhoneGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCode();
            }
        });
    }

    //获取验证码
    public void getCode() {
        tvSetPhoneGetCode.setText("60s后重新获取");
        tvSetPhoneGetCode.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_666666));
        tvSetPhoneGetCode.setClickable(false);

        mTimer = new Timer();
        timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);

        String url = ConfigKeys.GET_CODE + "?phone=" + phoneNum;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("验证码发送成功");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e("验证码发送error：" + e);
            }
        });
    }

    @Override
    public void onTimer() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvSetPhoneGetCode != null) {
                    tvSetPhoneGetCode.setText(MessageFormat.format("{0}s后重新获取", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mCount = 60;
                            mTimer.cancel();
                            timerTask.cancel();
                            tvSetPhoneGetCode.setTextColor(ContextCompat.getColor(getContext(), R.color.green_009685));
                            tvSetPhoneGetCode.setText("获取验证码");
                            tvSetPhoneGetCode.setClickable(true);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save_phone:
                keepPass();
                break;
        }
    }

    //保存
    public void keepPass() {
        String newPassWord = etNewPhone.getText().toString();
        String passCode = etSetPhoneCode.getText().toString();
        if (newPassWord != null && !newPassWord.isEmpty()) {
            if (passCode != null && !passCode.isEmpty()) {
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("phone",phoneNum);
                linkedHashMap.put("passCode",passCode);
                linkedHashMap.put("newPassWord",newPassWord);
                Logger.e(phoneNum+"***"+passCode+"***"+newPassWord);
                OkHttpUtils.putJson(ConfigKeys.UPDATAPASSWORD, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(int code, String response) {
                        ToastUtils.show("设置成功！");
                        finish();
                    }

                    @Override
                    public void onFailure(int code, Exception e) {
                        Logger.e(e.getMessage());
                        ToastUtils.show(e.getMessage());
                    }
                });
            } else {
                ToastUtils.show("请输入验证码");
            }
        } else {
            ToastUtils.show("请输入登录密码");
        }

    }
}

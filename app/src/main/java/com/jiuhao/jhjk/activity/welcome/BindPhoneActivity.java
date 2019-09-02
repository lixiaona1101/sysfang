package com.jiuhao.jhjk.activity.welcome;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.MainActivity;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.LoginBean2;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.fy.BaseTimerTask;
import com.jiuhao.jhjk.utils.fy.ITimerListener;
import com.jiuhao.jhjk.utils.fy.RegexUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.WebviewActivity;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Timer;

import cn.jpush.android.api.JPushInterface;

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener, ITimerListener {

    private ImageView mIvBack;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 确认
     */
    private TextView mTvTitleSure;
    private RelativeLayout mRlTitleSure;
    private RelativeLayout mRlTitle;
    /**
     * 请输入手机号
     */
    private EditText mEtBindPhone;
    /**
     * 短信验证码
     */
    private EditText mEtBindCode;
    /**
     * 获取验证码
     */
    private TextView mTvGetBindCode;
    private LinearLayout mLlBingUseRead;
    /**
     * 绑定并登陆
     */
    private Button mBtnBindPhone;

    private Timer mTimer;
    private int mCount = 60;
    private BaseTimerTask timerTask;

    private String unionId;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_bind_phone);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        unionId = (String) getIntent().getExtras().get("unionId");
        Logger.e(unionId);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        mRlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        mRlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        mEtBindPhone = (EditText) findViewById(R.id.et_bind_phone);
        mEtBindCode = (EditText) findViewById(R.id.et_bind_code);
        mTvGetBindCode = (TextView) findViewById(R.id.tv_get_bind_code);
        mLlBingUseRead = (LinearLayout) findViewById(R.id.ll_bing_use_read);
        mBtnBindPhone = (Button) findViewById(R.id.bind_phone);
        mBtnBindPhone.setOnClickListener(this);
        mTvGetBindCode.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mLlBingUseRead.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        mTvTitle.setText("绑定手机号");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_get_bind_code://获取验证码
                getCode();
                break;
            case R.id.ll_bing_use_read://用户协议
                getUserRead();
                break;
            case R.id.bind_phone://绑定并登录
                bingPhone();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvGetBindCode != null) {
                    mTvGetBindCode.setText(MessageFormat.format("{0}s后重新获取", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mCount = 60;
                            mTimer.cancel();
                            timerTask.cancel();
                            mTvGetBindCode.setTextColor(ContextCompat.getColor(getContext(), R.color.green_009685));
                            mTvGetBindCode.setText("获取验证码");
                            mTvGetBindCode.setClickable(true);
                        }
                    }
                }
            }
        });
    }

    //获取验证码
    public void getCode() {
        String phone = mEtBindPhone.getText().toString().trim();
        if (!RegexUtils.isMobileSimple(phone)) {
            mEtBindPhone.setError("请输入正确的手机号");
            mEtBindPhone.requestFocus();
            return;
        }
        mEtBindCode.requestFocus();
//        KeyboardUtils.hideSoftInput(_mActivity);
        mTvGetBindCode.setText("60s后重新获取");
        mTvGetBindCode.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_666666));
        mTvGetBindCode.setClickable(false);

        mTimer = new Timer();
        timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);


        String url = ConfigKeys.GET_CODE + "?phone=" + phone;
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

//        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put("phone", phone);
//        OkHttpUtils.post(true, ConfigKeys.GET_CODE, linkedHashMap, new OkHttpUtils.ResultCallback() {
//            @Override
//            public void onSuccess(int code, String response) {
//                ToastUtils.show("验证码发送成功");
//            }
//
//            @Override
//            public void onFailure(int code, Exception e) {
//                Logger.e("验证码发送error：" + e);
//            }
//        });
    }

    //用户协议
    public void getUserRead() {
        Intent intent = new Intent(this, WebviewActivity.class);
        intent.putExtra("title", "用户协议");
        intent.putExtra("html", ConfigKeys.USER_INSTRUCTIONS);
        startActivity(intent);
    }

    //绑定并登录
    public void bingPhone() {

        String phone = mEtBindPhone.getText().toString().trim();
        String code = mEtBindCode.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || phone.length() < 11) {
            mEtBindPhone.setError("请输入正确的手机号");
            mEtBindPhone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            mEtBindCode.setError("请输入短信验证码");
            mEtBindCode.requestFocus();
            return;
        }

        Logger.e(phone);
        Logger.e(code);
        Logger.e(unionId);
        String registrationId = JPushInterface.getRegistrationID(getApplicationContext());//极光设备号
        if (!registrationId.isEmpty()) {
            Logger.e(registrationId);
        } else {
            Logger.e("加入极光失败！");
        }
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("phone", phone);
        linkedHashMap.put("passCode", code);
        //加入极光设备号
        linkedHashMap.put("registrationId", registrationId);
        linkedHashMap.put("unionId", unionId);
        linkedHashMap.put("state", "3");

        OkHttpUtils.postJson(ConfigKeys.LOGIN, linkedHashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.d(response);
                Gson gson = new Gson();
                LoginBean2 loginBean = gson.fromJson(response, LoginBean2.class);
                Config.userId = loginBean.getId();
                Config.userToken = loginBean.getToken();
                Logger.d(loginBean.toString());
                ToastUtils.show("绑定手机号成功");
                check(loginBean);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e("微信登录error" + e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    private void check(LoginBean2 data) {
        SPUtils.putInt(getContext(), ConfigKeys.ID, data.getId());
        SPUtils.putInt(getContext(), ConfigKeys.USERID, data.getUserId());
        SPUtils.putString(getContext(), ConfigKeys.HOSPITAL, data.getHospital());
        SPUtils.putInt(getContext(), ConfigKeys.DEPARTMENTID, data.getDepartmentId());
        SPUtils.putString(getContext(), ConfigKeys.TITLES, data.getTitles());
        SPUtils.putString(getContext(), ConfigKeys.LABEL, data.getLabel());
        SPUtils.putString(getContext(), ConfigKeys.AVATAR, data.getAvatar());
        SPUtils.putInt(getContext(), ConfigKeys.SEX, data.getSex());
        SPUtils.putString(getContext(), ConfigKeys.BIRTHDAY, data.getBirthday());
        SPUtils.putInt(getContext(), ConfigKeys.AUTHSTAT, data.getAuthStat());
        SPUtils.putInt(getContext(), ConfigKeys.FEES, data.getFees());
        SPUtils.putInt(getContext(), ConfigKeys.FACTORYID, data.getFactoryId());
        SPUtils.putString(getContext(), ConfigKeys.BUSINESSCARD, data.getBusinessCard());
        SPUtils.putString(getContext(), ConfigKeys.INVITECODE, data.getInviteCode());
        SPUtils.putString(getContext(), ConfigKeys.CLINICTIME, data.getClinicTime());
        SPUtils.putInt(getContext(), ConfigKeys.AREAID, data.getAreaId());
        SPUtils.putLong(getContext(), ConfigKeys.CREATETIME, data.getCreateTime());
        SPUtils.putLong(getContext(), ConfigKeys.UPDATETIME, data.getUpdateTime());
        SPUtils.putString(getContext(), ConfigKeys.TOKEN, data.getToken());
        SPUtils.putString(getContext(), ConfigKeys.RESUME, data.getResume());
        SPUtils.putString(getContext(), ConfigKeys.PHONE, data.getPhone());
        SPUtils.putString(getContext(), ConfigKeys.PASSWORD, data.getPassword());
        SPUtils.putString(getContext(), ConfigKeys.UNIONID, data.getUnionId());
        SPUtils.putString(getContext(), ConfigKeys.DEPARTMENTNAME, data.getDepartmentName());
        SPUtils.putString(getContext(), ConfigKeys.NAME, data.getName());

        //登录状态
        SPUtils.putBoolean(getContext(), ConfigKeys.LOGIN_STATE, true);

        startActivity(new Intent(this, MainActivity.class));
    }
}

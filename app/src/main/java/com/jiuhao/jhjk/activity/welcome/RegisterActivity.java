package com.jiuhao.jhjk.activity.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.MainActivity;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.LoginBean;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.fy.BaseTimerTask;
import com.jiuhao.jhjk.utils.fy.ITimerListener;
import com.jiuhao.jhjk.utils.fy.RegexUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.wechat.IWeChatSignInCallback;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Timer;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, ITimerListener {

    /**
     * 验证码登录
     */
    private TextView tvCodeLogin;
    private TextView tvLine1;
    /**
     * 密码登录
     */
    private TextView tvPassLogin;
    private TextView tvLine2;
    /**
     * 请输入手机号
     */
    private EditText etPhoneCode;
    /**
     * 短信验证码
     */
    private EditText etCode;
    /**
     * 获取验证码
     */
    private TextView tvGetCode;
    /**
     * 登录
     */
    private Button btnLoginCode;
    private LinearLayout llCode;
    /**
     * 请输入手机号
     */
    private EditText etPhonePass;
    /**
     * 请输入密码
     */
    private EditText etPassword;
    private ImageView ivPass;
    /**
     * 登录
     */
    private Button btnLoginPass;
    private LinearLayout llPass;
    private ImageView ivWxLogin;
    private LinearLayout llLogin;


    private Timer mTimer;
    private int mCount = 60;
    private BaseTimerTask timerTask;

    //密码是否可见
    private boolean isPwdVisible = false;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_register);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        tvCodeLogin = (TextView) findViewById(R.id.tv_code_login);
        tvLine1 = (TextView) findViewById(R.id.tv_line1);
        tvPassLogin = (TextView) findViewById(R.id.tv_pass_login);
        tvLine2 = (TextView) findViewById(R.id.tv_line2);
        etPhoneCode = (EditText) findViewById(R.id.et_phone_code);
        etCode = (EditText) findViewById(R.id.et_code);
        tvGetCode = (TextView) findViewById(R.id.tv_get_code);
        btnLoginCode = (Button) findViewById(R.id.btn_login_code);
        llCode = (LinearLayout) findViewById(R.id.ll_code);
        etPhonePass = (EditText) findViewById(R.id.et_phone_pass);
        etPassword = (EditText) findViewById(R.id.et_password);
        ivPass = (ImageView) findViewById(R.id.iv_pass);
        btnLoginPass = (Button) findViewById(R.id.btn_login_pass);
        llPass = (LinearLayout) findViewById(R.id.ll_pass);
        ivWxLogin = (ImageView) findViewById(R.id.iv_wx_login);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);
        tvCodeLogin.setOnClickListener(this);
        tvPassLogin.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
        btnLoginCode.setOnClickListener(this);
        ivPass.setOnClickListener(this);
        btnLoginPass.setOnClickListener(this);
        ivWxLogin.setOnClickListener(this);
        codeLogin();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_code_login://验证码登录
                codeLogin();
                break;
            case R.id.tv_pass_login://密码登录
                passLogin();
                break;
            case R.id.tv_get_code://获取验证码
                getCode();
                break;
            case R.id.iv_pass://密码是否可见
                settingPassVisible();
                break;
            case R.id.btn_login_code://验证码登录
                codePhoneLogin();
                break;
            case R.id.btn_login_pass://手机密码登录
                passPhoneLogin();
                break;
            case R.id.iv_wx_login://微信登录
                WVlogin();
                break;
            default:
                break;
        }
    }

    //验证码选中
    public void codeLogin() {
        llCode.setVisibility(View.VISIBLE);
        llPass.setVisibility(View.GONE);
        tvLine1.setVisibility(View.VISIBLE);
        tvLine2.setVisibility(View.INVISIBLE);
        tvCodeLogin.setTextColor(Color.BLACK);
        tvPassLogin.setTextColor(ContextCompat.getColor(getContext(), R.color.gary_999999));
    }

    //密码选中
    public void passLogin() {
        llCode.setVisibility(View.GONE);
        llPass.setVisibility(View.VISIBLE);
        tvLine1.setVisibility(View.INVISIBLE);
        tvLine2.setVisibility(View.VISIBLE);
        tvCodeLogin.setTextColor(ContextCompat.getColor(getContext(), R.color.gary_999999));
        tvPassLogin.setTextColor(Color.BLACK);
    }

    //获取验证码
    public void getCode() {
        String phone = etPhoneCode.getText().toString().trim();
        if (!RegexUtils.isMobileSimple(phone)) {
            etPhoneCode.setError("请输入正确的手机号");
            etPhoneCode.requestFocus();
            return;
        }
        etCode.requestFocus();
//        KeyboardUtils.hideSoftInput(getContext());
        tvGetCode.setText("60s后重新获取");
        tvGetCode.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_666666));
        tvGetCode.setClickable(false);

        mTimer = new Timer();
        timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);

//        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put("phone", phone);
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
//        OkHttpUtils.post(ConfigKeys.GET_CODE,
//                linkedHashMap, new OkHttpUtils.ResultCallback() {
//                    @Override
//                    public void onSuccess(int code, String response) {
//                        ToastUtils.show("验证码发送成功");
//                    }
//
//                    @Override
//                    public void onFailure(int code, Exception e) {
//                        Logger.e("验证码发送error：" + e);
//                    }
//                });
    }

    @Override
    public void onTimer() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvGetCode != null) {
                    tvGetCode.setText(MessageFormat.format("{0}s后重新获取", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mCount = 60;
                            mTimer.cancel();
                            timerTask.cancel();
                            tvGetCode.setTextColor(ContextCompat.getColor(getContext(), R.color.green_009685));
                            tvGetCode.setText("获取验证码");
                            tvGetCode.setClickable(true);
                        }
                    }
                }
            }
        });
    }

    //密码是否可见
    public void settingPassVisible() {
        if (isPwdVisible) {
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            ivPass.setImageResource(R.mipmap.invisible);
            isPwdVisible = false;
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            ivPass.setImageResource(R.mipmap.visible);
            isPwdVisible = true;
        }
    }

    //验证码登录
    public void codePhoneLogin() {
        String phone = etPhoneCode.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        if (!RegexUtils.isMobileSimple(phone)) {
            etPhoneCode.setError("请输入正确的手机号");
            etPhoneCode.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(code)) {
            etCode.setError("请输入短信验证码");
            etCode.requestFocus();
            return;
        }
        login(phone, code, "2");
    }

    //手机密码登录
    public void passPhoneLogin() {
        String phone = etPhonePass.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || phone.length() < 11) {
            etPhonePass.setError("请输入正确的手机号");
            etPhonePass.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("密码不能为空");
            etPassword.requestFocus();
            return;
        }
        login(phone, password, "1");
    }


    /**
     * 登录
     *
     * @param phone    手机号
     * @param passCode 密码或验证码
     * @param b        1 密码登录 2验证码登录
     */
    private void login(final String phone, final String passCode, String b) {

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("phone", phone);
        linkedHashMap.put("passCode", passCode);
        linkedHashMap.put("state", b);
        OkHttpUtils.postJson(ConfigKeys.LOGIN,
                linkedHashMap, new OkHttpUtils.ResultCallback() {
                    @Override
                    public void onSuccess(int code, String response) {
                        Logger.e(response);
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                        Logger.e(loginBean.toString());
                        ToastUtils.show("登录成功");
                        //登录成功用户的id跟token封装在OKhttp中
                        //如果id为-1 token为“” 那么就是未登录状态
                        Config.userId=loginBean.getId();
                        Config.userToken=loginBean.getToken();
                        check(loginBean);
                    }

                    @Override
                    public void onFailure(int code, Exception e) {
                        Logger.e(e.getMessage());
                        ToastUtils.show(e.getMessage());
                    }
                });
    }


    private void check(LoginBean data) {
        SPUtils.putInt(getContext(), ConfigKeys.ID, data.getId());
        SPUtils.putInt(getContext(), ConfigKeys.USERID, data.getUserId());
//        SPUtils.putString(getContext(), ConfigKeys.HOSPITAL, data.getHospital());
//        SPUtils.putInt(getContext(), ConfigKeys.DEPARTMENTID, data.getDepartmentId());
//        SPUtils.putString(getContext(), ConfigKeys.TITLES, data.getTitles());
//        SPUtils.putString(getContext(), ConfigKeys.LABEL, data.getLabel());
//        SPUtils.putString(getContext(), ConfigKeys.AVATAR, data.getAvatar());
//        SPUtils.putString(getContext(), ConfigKeys.SEX, data.getSex());
//        SPUtils.putString(getContext(), ConfigKeys.BIRTHDAY, data.getBirthday());
//        SPUtils.putString(getContext(), ConfigKeys.AUTHSTAT, data.getAuthStat());
//        SPUtils.putInt(getContext(), ConfigKeys.FEES, data.getFees());
//        SPUtils.putInt(getContext(), ConfigKeys.FACTORYID, data.getFactoryId());
//        SPUtils.putString(getContext(), ConfigKeys.BUSINESSCARD, data.getBusinessCard());
//        SPUtils.putString(getContext(), ConfigKeys.INVITECODE, data.getInviteCode());
//        SPUtils.putString(getContext(), ConfigKeys.CLINICTIME, data.getClinicTime());
//        SPUtils.putInt(getContext(), ConfigKeys.AREAID, data.getAreaId());
//        SPUtils.putInt(getContext(), ConfigKeys.CREATETIME, data.getCreateTime());
//        SPUtils.putString(getContext(), ConfigKeys.UPDATETIME, data.getUpdateTime());
//        SPUtils.putString(getContext(), ConfigKeys.TOKEN, data.getToken());
//        SPUtils.putInt(getContext(), ConfigKeys.RESUME, data.getResume());
//        SPUtils.putInt(getContext(), ConfigKeys.PHONE, data.getPhone());
//        SPUtils.putString(getContext(), ConfigKeys.PASSWORD, data.getPassword());
//        SPUtils.putInt(getContext(), ConfigKeys.UNIONID, data.getUnionId());
//        SPUtils.putInt(getContext(), ConfigKeys.DEPARTMENTNAME, data.getDepartmentName());

        //登录状态
        SPUtils.putBoolean(getContext(), ConfigKeys.LOGIN_STATE, true);

//        PreferenceManager.getInstance().setCurrentUserAvatar(data.getAvatar());
//        PreferenceManager.getInstance().setCurrentUserNick(data.getName());

        /**-----------------------------环信登录服务器------------------------------**/
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                EMClient.getInstance().login(String.valueOf(data.getId()), data.getPassword(), new EMCallBack() {//回调
//                    @Override
//                    public void onSuccess() {
//                        JHJKLoading.stopLoading();
//                        EMClient.getInstance().groupManager().loadAllGroups();
//                        EMClient.getInstance().chatManager().loadAllConversations();
//                        Log.d("main", "登录聊天服务器成功！");
//                    }
//
//                    @Override
//                    public void onProgress(int progress, String status) {
//                        JHJKLoading.showLoading();
//                    }
//
//                    @Override
//                    public void onError(int code, String message) {
//                        Log.d("main", "登录聊天服务器失败！");
//                        ToastUtils.showShort(message);
//                    }
//                });
//            }
//        }.start();

        getContext().startActivity(new Intent(getContext(), MainActivity.class));
    }

    //微信登录
    public void WVlogin() {
        JHJKWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                Logger.e(userInfo);
                JSONObject object = JSONObject.parseObject(userInfo);
                String unionid = object.getString("unionid");
                Logger.e(unionid);
                loginWx(unionid);
            }
        }).signIn();
    }


    private void loginWx(final String unionId) {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
        hashMap.put("unionId", unionId);
        hashMap.put("state", "3");
        OkHttpUtils.postJson(ConfigKeys.LOGIN, hashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                if (code == 0) {
                    Gson gson = new Gson();
                    LoginBean loginBean = gson.fromJson(response, LoginBean.class);
                    Config.userId=loginBean.getId();
                    Config.userToken=loginBean.getToken();
                    check(loginBean);
                }
            }

            @Override
            public void onFailure(int code, Exception e) {
                if (code == 100) {
                    Intent intent = new Intent(getContext(), BindPhoneActivity.class);
                    intent.putExtra("unionId", unionId);
                    getContext().startActivity(intent);
                } else {
                    Logger.e(e.getMessage());
                }
            }
        });
    }
}

package com.jiuhao.jhjk.activity.mine;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.fy.BaseTimerTask;
import com.jiuhao.jhjk.utils.fy.ITimerListener;
import com.jiuhao.jhjk.utils.fy.RegexUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Timer;

/**
 * 更换手机号
 */
public class UserPhoneActivity extends BaseActivity implements View.OnClickListener, ITimerListener {


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
     * 请输入新的手机号码
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
     * 确定
     */
    private Button btnSavePhone;

    private Timer mTimer;
    private int mCount = 60;
    private BaseTimerTask timerTask;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_user_phone);
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
        tvTitle.setText("更换手机号");
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
            default:
                break;
            case R.id.tv_set_phone_get_code:
                getCode();
                break;

            case R.id.btn_save_phone:
                save();
                break;
        }
    }

    //获取验证码
    public void getCode() {
        String newPhone = etNewPhone.getText().toString().trim();
        if (!RegexUtils.isMobileSimple(newPhone)) {
            etNewPhone.setError("请输入正确的手机号");
            etNewPhone.requestFocus();
            return;
        }
        etSetPhoneCode.requestFocus();
        tvSetPhoneGetCode.setText("60s后重新获取");
        tvSetPhoneGetCode.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_666666));
        tvSetPhoneGetCode.setClickable(false);
        mTimer = new Timer();
        timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);


        String url = ConfigKeys.GET_CODE + "?phone=" + newPhone;
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
//        linkedHashMap.put("phone", newPhone);
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


    //确定更改绑定手机号
    public void save() {
        String newPhone = etNewPhone.getText().toString().trim();
        if (!RegexUtils.isMobileSimple(newPhone)) {
            etNewPhone.setError("请输入正确的手机号");
            etNewPhone.requestFocus();
            return;
        }
        String code = etNewPhone.getText().toString();
        if (TextUtils.isEmpty(code) || code.length() < 6) {
            tvSetPhoneGetCode.setError("请输入正确的短信验证码");
            tvSetPhoneGetCode.requestFocus();
            return;
        }
        //掉更换手机号接口
//        x.http().post(MyParamsCreator.getInstance().postUri(ConfigKeys.EDIT_PHONE)
//                .addParams("phone", newPhone)
//                .addParams("code", code)
//                .build(true), new MyCallBack<BaseMsgResult>() {
//            @Override
//            public void onSuccess(BaseMsgResult result) {
//                super.onSuccess(result);
//                if (result.getStatus() == 0) {
//                    ToastUtils.showShort("更换手机号成功，请用新手机号重新登录");
//                    SpUtil.clear();
//                    startWithPop(new LoginDelegate());
//                } else {
//                    RxToast.error(result.getMsg());
//                }
//            }
//        });
    }

    @Override
    public void onTimer() {
        runOnUiThread(new Runnable() {
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
}

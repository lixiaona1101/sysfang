package com.jiuhao.jhjk.activity.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.MainActivity;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.SPUtils;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.utils.Log;

/**
 * Created by lxn on 2019/8/12.
 */

public class WelcomeActivity extends BaseActivity {
    private TextView mTvTime;
    private int time = 3;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    startAc();
                    break;
                case 1:
                    mTvTime.setText("跳过" + String.valueOf(time) + "s");
                    break;
            }

        }
    };

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_wecome);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        mTvTime = (TextView) findViewById(R.id.tv_time);
    }

    private void requestPermissions() {

        //判断是否授权
        if (!PermissionUtils.isGranted(PermissionConstants.PHONE, PermissionConstants.STORAGE, PermissionConstants.CAMERA)) {
            //设置请求权限
            PermissionUtils.permission(PermissionConstants.PHONE, PermissionConstants.STORAGE, PermissionConstants.CAMERA).callback(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {
                    Log.d("WelcomeActivity", "权限已申请");
                    startTime1();
                }

                @Override
                public void onDenied() {
                    AppUtils.exitApp();
                }
            }).request();
        }else{
            startTime1();
        }


    }

    @Override
    protected void obtainData() {
        requestPermissions();
    }


    public void startTime1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time--;
                if (!isDestroy) {
                    if (time < 0) {
                        mHandler.sendEmptyMessage(0);
                    } else {
                        mHandler.sendEmptyMessage(1);
                        mHandler.postDelayed(this, 1000);
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    @Override
    protected void initEvent() {
        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAc();
            }
        });
    }

    public void startAc() {
        finish();
        boolean aBoolean = SPUtils.getBoolean(getContext(), ConfigKeys.LOGIN_STATE, false);
        Logger.e("登录状态" + aBoolean);
        if (aBoolean) {//登录状态
//            Config.userId=SPUtils.getInt(getContext(),ConfigKeys.ID,-1);
            Config.userToken = SPUtils.getString(getContext(), ConfigKeys.TOKEN, "");
            Logger.e("usertoken:"+Config.userToken);
            //跳转主页面
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        } else {//未登录状态
            //跳转引领页再到登录注册页面再到主页面
            startActivity(new Intent(WelcomeActivity.this, WelcomeSiActivity.class));
        }
    }
}

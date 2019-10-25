package com.jiuhao.jhjk.APP;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import androidx.multidex.MultiDex;

import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import cn.jpush.android.api.JPushInterface;

public class App extends Application {

    private static App mApplication;

    public static App getApplication() {
        return mApplication;
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mApplication = this;
        initRC();

        //权限初始化
        MultiDex.install(context);

//        微信初始化
        WeChat.setAppId("wx19105639b52300a5");
        WeChat.setAppSecret("dc76a66a690f04f1309292d6a068bd06");

        //logger 初始化
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)      //（可选）是否显示线程信息。 默认值为true
                .methodCount(3)               // （可选）要显示的方法行数。 默认2
                .methodOffset(0)               // （可选）设置调用堆栈的函数偏移值，0的话则从打印该Log的函数开始输出堆栈信息，默认是0
                .tag("BeeStore")                  //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        ToastUtils.init(this);
        Logger.i(sHA1(this));

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

//        极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        String registrationId = JPushInterface.getRegistrationID(getContext());//极光设备号
        Logger.e("极光设备号："+registrationId);
        SPUtils.putString(getContext(),ConfigKeys.REGISTRATIONID,registrationId);

    }

    public String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initRC() {
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}

package com.jiuhao.jhjk.APP;


import android.content.Context;

import com.jiuhao.jhjk.Receiver.Logger;
import com.jiuhao.jhjk.utils.SPUtils;

public class Config {

    //用户id
//    public static int userId = -1;
    //用户token
    public static String userToken ="";


    public static double LONGITUDE =0;
    public static double LATITUDE =0;

    public static void quit(Context context){

        SPUtils.putInt(context, ConfigKeys.ID, 0);
        SPUtils.putInt(context, ConfigKeys.USERID, 0);
        SPUtils.putString(context, ConfigKeys.HOSPITAL, "");
        SPUtils.putInt(context, ConfigKeys.DEPARTMENTID, 0);
        SPUtils.putString(context, ConfigKeys.TITLES, "");
        SPUtils.putString(context, ConfigKeys.LABEL, "");
        SPUtils.putString(context, ConfigKeys.AVATAR, "");
        SPUtils.putInt(context, ConfigKeys.SEX, 0);
        SPUtils.putString(context, ConfigKeys.BIRTHDAY, "");
        SPUtils.putInt(context, ConfigKeys.AUTHSTAT, 0);
        SPUtils.putInt(context, ConfigKeys.FEES, 0);
        SPUtils.putInt(context, ConfigKeys.FACTORYID, 0);
        SPUtils.putString(context, ConfigKeys.BUSINESSCARD, "");
        SPUtils.putString(context, ConfigKeys.INVITECODE, "");
        SPUtils.putString(context, ConfigKeys.CLINICTIME, "");
        SPUtils.putInt(context, ConfigKeys.AREAID, 0);
        SPUtils.putString(context, ConfigKeys.CREATETIME, "");
        SPUtils.putString(context, ConfigKeys.UPDATETIME, "");
        SPUtils.putString(context, ConfigKeys.TOKEN, "");
        SPUtils.putString(context, ConfigKeys.RESUME, "");
        SPUtils.putString(context, ConfigKeys.PHONE, "");
        SPUtils.putString(context, ConfigKeys.PASSWORD, "");
        SPUtils.putString(context, ConfigKeys.UNIONID, "");
        SPUtils.putString(context, ConfigKeys.DEPARTMENTNAME, "");
        SPUtils.putString(context, ConfigKeys.NAME, "");

        //登录状态
        SPUtils.putBoolean(context, ConfigKeys.LOGIN_STATE, false);
        com.orhanobut.logger.Logger.e("登录状态false");
    }
}

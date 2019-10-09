package com.jiuhao.jhjk.utils.net.xutils;

import android.app.Activity;
import android.support.v4.app.FragmentManager;


public class JHJKLoading {
    private static MyLoading myLoading;

    private JHJKLoading() {

    }

    public static void init(Activity activity) {
        myLoading = new MyLoading();
    }

    public static void showLoading(FragmentManager supportFragmentManager) {
        if (myLoading != null && !myLoading.isShowing) {
            myLoading.randomText();
            myLoading.show(supportFragmentManager);
        }
    }

    public static void stopLoading() {
        if (myLoading != null && myLoading.isShowing) {
            myLoading.dismiss();
        }
    }
}

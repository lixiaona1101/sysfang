package com.jiuhao.jhjk.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

/**
 * Created by Feiyang on 2018/8/14.
 */

public class DialogUtil {
    public static View view;

    public static AlertDialog createDialog(Context context, int layoutId) {
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        view = View.inflate(context, layoutId, null);
        ab.setView(view);
        final AlertDialog alertDialog = ab.create();
        alertDialog.setCanceledOnTouchOutside(false);
        Window window = alertDialog.getWindow();
        //这一句消除白块
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return alertDialog;
    }
}

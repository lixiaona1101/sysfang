package com.jiuhao.jhjk.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.jiuhao.jhjk.R;


/**
 * Created by Feiyang on 2018/7/24.
 */

public class BottomDialogCreator {



    public static Window getDialogWindow(AlertDialog alertDialog, int layoutId){
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setContentView(layoutId);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
        }
        return window;
    }

}

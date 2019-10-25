package com.jiuhao.jhjk.fragment.base;

import android.graphics.drawable.Drawable;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * description
 */
public interface IBaseFragment {

    /**
     * 从Resource中获取颜色
     * @param colorId colorId
     * @return 颜色值
     */
    int getResourceColor(@ColorRes int colorId);

    /**
     * 从Resource中获取字符串
     * @param stringId stringId
     * @return string
     */
    String getResourceString(@StringRes int stringId);

    /**
     * 从Resource中获取Drawable
     * @param id DrawableRes
     * @return Drawable
     */
    Drawable getResourceDrawable(@DrawableRes int id);


}

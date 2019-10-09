package com.jiuhao.jhjk.utils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jiuhao.jhjk.R;

/**
 * Glide 图片加载工具类
 */
public class GlideUtil {
    //加载失败 、占位   图片
    private static final int errorImg = R.mipmap.background;
    private static final int errorCircleImg = R.mipmap.background;

    static RequestOptions requestOptions = new RequestOptions().error(errorImg).placeholder(errorImg);
    static RequestOptions requestOptionsCircle = new RequestOptions().circleCrop().error(errorCircleImg).placeholder(errorCircleImg);

    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).applyDefaultRequestOptions(requestOptions).load(url).into(imageView);
    }

    public static void loadCircle(Context context, String url, ImageView imageView) {
        Glide.with(context).applyDefaultRequestOptions(requestOptionsCircle).load(url).into(imageView);
    }

}

package com.jiuhao.jhjk.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jiuhao.jhjk.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Glide 图片加载工具类
 */
public class GlideUtil {
    //加载失败 、占位   图片
    private static final int errorImg = R.mipmap.waiting;
    private static final int errorCircleImg = R.mipmap.head;

    public static void load(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions().centerInside().error(errorImg).placeholder(errorImg);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void loadCircle(Context context, String url, ImageView imageView) {
        RequestOptions requestOptionsCircle = new RequestOptions()
                .circleCrop().error(errorCircleImg).placeholder(errorCircleImg);
        Glide.with(context).load(url).apply(requestOptionsCircle).into(imageView);
    }

    public static void loadRound(Context context, String url, ImageView imageView, int r) {
        RequestOptions options = new RequestOptions()
                .centerInside()
                .error(errorCircleImg)
                .placeholder(errorCircleImg)
                .transforms(new RoundTransform(context, r));

        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void load(Context context, String url, ImageView WidthImageView, ImageView HeightImageView) {

        RequestOptions requestOptions = new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.logo_sysf).placeholder(R.mipmap.logo_sysf);
        Glide.with(context).asBitmap().load(url).apply(requestOptions).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                int width = resource.getWidth();
                int height = resource.getHeight();
                if (width > height) {//橫
                    WidthImageView.setVisibility(View.VISIBLE);
                    HeightImageView.setVisibility(View.GONE);
                    WidthImageView.setImageBitmap(resource);
                } else {//樹
                    WidthImageView.setVisibility(View.GONE);
                    HeightImageView.setVisibility(View.VISIBLE);
                    HeightImageView.setImageBitmap(resource);
                }
            }
        });
    }
}

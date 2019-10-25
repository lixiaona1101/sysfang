package com.jiuhao.jhjk.utils.banner;

import android.content.Context;
import androidx.appcompat.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Feiyang on 2018/5/24.
 */

public class ImageHolder implements Holder<String> {

    private int imageStyle;//1centerCrop，2fitCenter
    private final RequestOptions BANNER_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public ImageHolder(int imageStyle) {
        this.imageStyle = imageStyle;
        if (imageStyle == 1) {
            BANNER_OPTIONS.centerCrop();
        } else {
            BANNER_OPTIONS.fitCenter();
        }
    }

    private AppCompatImageView mImageView = null;


    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(data)
                .apply(BANNER_OPTIONS)
                .into(mImageView);
    }
}
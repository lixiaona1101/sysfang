package com.jiuhao.jhjk.utils;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.view.ZoomImageView;

/**
 * 点击放大图片
 */
public class BigImgActivity extends BaseActivity {


    private ZoomImageView zivImage;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_big_img);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {
        zivImage = (ZoomImageView) findViewById(R.id.ziv_image);
    }

    @Override
    protected void obtainData() {

        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra("imgUrl");


        if (!TextUtils.isEmpty(imgUrl)) {
            Glide.with(getContext())
                    .load(imgUrl)
                    .apply(ConfigKeys.REQUEST_OPTIONS)
                    .into(zivImage);
            zivImage.setOnPhotoTapListener(new PhotoTapListener());
        } else {
            finish();
        }
    }

    @Override
    protected void initEvent() {

    }

    private class PhotoTapListener implements ZoomImageView.OnPhotoTapListener {
        @Override
        public void onPhotoTap(View view, float x, float y) {
            finish();
        }
    }
}

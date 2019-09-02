package com.jiuhao.jhjk.utils.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Feiyang on 2018/5/24.
 */


public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
    private int imageStyle;//1centerCrop，2fitCenter

    public HolderCreator(int imageStyle) {
        this.imageStyle = imageStyle;
    }

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder(imageStyle);
    }
}

package com.jiuhao.jhjk.utils.banner;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jiuhao.jhjk.R;

import java.util.ArrayList;

/**
 * Created by Feiyang on 2018/5/24.
 */

public class BannerCreator {

    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener, int imageStyle) {

        convenientBanner
                .setPages(new HolderCreator(imageStyle), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new AccordionTransformer())
                .setCanLoop(true);

    }
}

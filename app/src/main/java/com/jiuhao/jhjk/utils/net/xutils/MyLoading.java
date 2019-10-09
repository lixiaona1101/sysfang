package com.jiuhao.jhjk.utils.net.xutils;

import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.dialog.base.BaseDialog;

import java.util.Random;

/**
 *  请求等待logo旋转提醒
 */
public class MyLoading extends BaseDialog {
    private ImageView ivLoading;
    private String[] loadingText = new String[]{"对的，坚持；错的，放弃！",
            "你若安好，便是晴天。", "走得太快，灵魂都跟不上了。",
            "生气是拿别人的错误惩罚自己。", "让未来到来，让过去过去。", "每一种创伤，都是一种成熟。"
            , "相信我，就不要走", "左3圈右3圈，脖子扭扭，屁股扭扭……", "不是我太慢，是时间太快了……",
            "亲，精彩马上来", "正在加载，请稍后……"};
    /**
     * 正在加载数据...
     */
    private TextView tvReminder;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading_progress_acfun_video;
    }

    @Override
    protected int getDialogGravity() {
        return Gravity.CENTER;
    }

    public void randomText() {
        Random random = new Random();
        int number = Math.abs(random.nextInt() % this.loadingText.length);
        tvReminder.setText(this.loadingText[number]);
    }

    @Override
    public void initView(View view) {

        ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        tvReminder = (TextView) view.findViewById(R.id.tv_reminder);
    }

    @Override
    public void initData() {

        ((AnimationDrawable) ivLoading.getBackground()).start();
    }

    @Override
    public void initEvent() {

    }
}

package com.jiuhao.jhjk.activity.patient;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 更多设置
 */
public class MoreSetActivity extends BaseActivity {


    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 确认
     */
    private TextView tvTitleSure;
    private RelativeLayout rlTitleSure;
    private RelativeLayout rlTitle;
    /**
     * 清空聊天记录
     */
    private TextView clearDetails;
    /**
     * 消息免打扰
     */
    private TextView noDisturb;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_more_set);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        clearDetails = (TextView) findViewById(R.id.clear_details);
        noDisturb = (TextView) findViewById(R.id.no_disturb);

        tvTitle.setText("更多设置");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //清空聊天记录
        clearDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //消息免打扰
        noDisturb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}

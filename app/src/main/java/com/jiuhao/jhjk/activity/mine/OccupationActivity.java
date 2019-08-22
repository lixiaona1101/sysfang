package com.jiuhao.jhjk.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 职称
 */
public class OccupationActivity extends BaseActivity {

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
    private ImageView occupation1;
    private ImageView occupation2;
    private ImageView occupation3;
    private ImageView occupation4;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_occupation);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        occupation1 = (ImageView) findViewById(R.id.occupation1);
        occupation2 = (ImageView) findViewById(R.id.occupation2);
        occupation3 = (ImageView) findViewById(R.id.occupation3);
        occupation4 = (ImageView) findViewById(R.id.occupation4);
        tvTitle.setText("职称");
        tvTitleSure.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}

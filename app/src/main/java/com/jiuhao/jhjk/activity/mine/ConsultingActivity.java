package com.jiuhao.jhjk.activity.mine;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 设置咨询费
 */
public class ConsultingActivity extends BaseActivity {

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
     * 设置金额
     */
    private EditText setMoney;
    private ImageView sui;
    private ImageView five;
    private ImageView fifteen;
    private ImageView twentyFive;
    private ImageView thirtyFive;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_consulting);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        setMoney = (EditText) findViewById(R.id.set_money);
        sui = (ImageView) findViewById(R.id.sui);
        five = (ImageView) findViewById(R.id.five);
        fifteen = (ImageView) findViewById(R.id.fifteen);
        twentyFive = (ImageView) findViewById(R.id.twenty_five);
        thirtyFive = (ImageView) findViewById(R.id.thirty_five);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}

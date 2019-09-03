package com.jiuhao.jhjk.activity.patient;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 邀请患者
 */
public class InviteCustomerActivity extends BaseActivity {


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
    private ImageView ivNameCard;
    /**
     * 啊啊啊
     */
    private TextView tvDocNameInvite;
    /**
     * 主任医师
     */
    private TextView tvDocPositionInvite;
    /**
     * 患者直接扫码
     */
    private TextView tvScan;
    /**
     * 分享给微信好友
     */
    private TextView tvWxInvite;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite_customer);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        ivNameCard = (ImageView) findViewById(R.id.iv_name_card);
        tvDocNameInvite = (TextView) findViewById(R.id.tv_doc_name_invite);
        tvDocPositionInvite = (TextView) findViewById(R.id.tv_doc_position_invite);
        tvScan = (TextView) findViewById(R.id.tv_scan);
        tvWxInvite = (TextView) findViewById(R.id.tv_wx_invite);
        tvTitle.setText("邀请患者");
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
    }
}

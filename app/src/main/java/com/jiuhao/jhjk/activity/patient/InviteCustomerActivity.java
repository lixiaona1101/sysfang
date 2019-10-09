package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.wechat.JHJKWeChat;

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
    private String businesscard;//微信名片

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
        businesscard = SPUtils.getString(getContext(), ConfigKeys.BUSINESSCARD, "");

        if (!TextUtils.isEmpty(businesscard)) {
            GlideUtil.load(getContext(), businesscard, ivNameCard);
        }
        tvDocNameInvite.setText(SPUtils.getString(getContext(), ConfigKeys.NAME, ""));
        tvDocPositionInvite.setText(SPUtils.getString(getContext(), ConfigKeys.TITLES, ""));
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //患者直接扫码
        tvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BigImgActivity.class);
                intent.putExtra("imgUrl", businesscard);
                startActivity(intent);
            }
        });
        //微信分享
        tvWxInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareName = SPUtils.getString(getContext(), ConfigKeys.BUSINESSCARD, "");
                if (TextUtils.isEmpty(shareName)) {
                    getShareName();
                } else {
                    JHJKWeChat.getInstance().shareImg(2, shareName);
                }
            }
        });

    }

    /**
     * 获取微信分享名片
     */
    private void getShareName() {
//        x.http().post(MyParamsCreator.getInstance().postUri(ConfigKeys.GET_SHARE_NAME)
//                .build(false), new MyCallBack<ShareNameResult>() {
//            @Override
//            public void onSuccess(ShareNameResult result) {
//                super.onSuccess(result);
//                if (result.getStatus() == 0) {
//                    String imgUrl = result.getData().get(0).getImgUrl();
//                    SpUtil.putString(ConfigKeys.SHARE_NAME, imgUrl);
//                    JHJKWeChat.getInstance().shareImg(2, imgUrl);
//
//                } else {
//                    RxToast.error("获取分享链接失败");
//                }
//            }
//        });
    }
}

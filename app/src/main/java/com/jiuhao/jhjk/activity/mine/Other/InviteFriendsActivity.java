package com.jiuhao.jhjk.activity.mine.Other;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.InviteFriendsBean;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.banner.BannerCreator;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的-邀请医生
 */
public class InviteFriendsActivity extends BaseActivity implements View.OnClickListener, OnItemClickListener {


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
     * 邀请医生
     */
    private Button btnInviteFriend;
    private RelativeLayout rlInvite;
    private ConvenientBanner cbInviteFriend;


    private ArrayList<String> imgList = new ArrayList<>();
    private String shareUrl = null;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_invite_friends);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        btnInviteFriend = (Button) findViewById(R.id.btn_invite_friend);
        btnInviteFriend.setOnClickListener(this);
        rlInvite = (RelativeLayout) findViewById(R.id.rl_invite);
        cbInviteFriend = (ConvenientBanner) findViewById(R.id.cb_invite_friend);
        tvTitle.setText("邀请医生");
    }

    @Override
    protected void obtainData() {
        if (!(imgList.size() > 0)) {

            OkHttpUtils.get(ConfigKeys.DSIMG, null, new OkHttpUtils.ResultCallback<String>() {
                @Override
                public void onSuccess(int code, String response) {
                    List<InviteFriendsBean> inviteFriendsBeans = Json.parseArr(response, InviteFriendsBean.class);
                    for (int i = 0; i < inviteFriendsBeans.size(); i++) {
                        imgList.add(inviteFriendsBeans.get(i).getImgUrl());
                    }
                    BannerCreator.setDefault(cbInviteFriend, imgList, InviteFriendsActivity.this, 2);
                }

                @Override
                public void onFailure(int code, Exception e) {
                    Logger.e(e.getMessage());
                }
            });
        }
    }

    @Override
    protected void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cbInviteFriend.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                shareUrl = imgList.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_invite_friend:
                JHJKWeChat.getInstance().shareImg(2, shareUrl);
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), BigImgActivity.class);
        intent.putExtra("imgUrl", imgList.get(position));
        startActivity(intent);
    }
}

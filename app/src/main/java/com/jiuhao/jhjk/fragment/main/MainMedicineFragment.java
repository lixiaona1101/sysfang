package com.jiuhao.jhjk.fragment.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.mine.Other.InviteFriendsActivity;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.view.MyGridView;
import com.jiuhao.jhjk.view.MyListView;

/**
 * 开方主fragment
 */
public class MainMedicineFragment extends BaseFragment {

    private View view;
    /**
     * 名片
     */
    private TextView tvNameCard;
    private ConvenientBanner cbIndex;
    private TextView tvMainTip;
    private LinearLayout llOnline;
    private LinearLayout llCamera;
    private MyGridView mgvEnv;
    private ImageView ivNoCase;
    private MyListView mlvFiveRecord;

    private int authState;
    private boolean ibnviteState;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_medicine;
    }

    @Override
    protected void initView() {

        tvNameCard = (TextView) findViewById(R.id.tv_name_card);
        cbIndex = (ConvenientBanner)findViewById(R.id.cb_index);
        tvMainTip = (TextView) findViewById(R.id.tv_main_tip);
        llOnline = (LinearLayout) findViewById(R.id.ll_online);
        llCamera = (LinearLayout) findViewById(R.id.ll_camera);
        mgvEnv = (MyGridView) findViewById(R.id.mgv_env);
        ivNoCase = (ImageView) findViewById(R.id.iv_no_case);
        mlvFiveRecord = (MyListView) findViewById(R.id.mlv_five_record);
    }

    @Override
    protected void initData() {
        authState = SPUtils.getInt(getContext(), ConfigKeys.AUTHSTAT, 0);
        ibnviteState = SPUtils.getBoolean(getContext(), ConfigKeys.INVITE_STATUS, false);
    }

    @Override
    protected void setListener() {
        //名片 邀请医生
        tvNameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InviteFriendsActivity.class));
            }
        });
        //在线开方
        llOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (authState != 3) {
                    //弹窗
//                    baseTipDialog.show();
                    return;
                }
//                startActivity(new Intent(getContext(),MainEvoDelegate.class));
            }
        });
        //拍照上传
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authState != 3) {
//                    baseTipDialog.show();
                    return;
                }
//                startActivity(new Intent(getContext(),CameraEvoDelegate.class));
            }
        });

    }

}

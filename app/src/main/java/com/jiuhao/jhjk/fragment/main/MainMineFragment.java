package com.jiuhao.jhjk.fragment.main;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.mine.Other.AboutOurActivity;
import com.jiuhao.jhjk.activity.mine.AccountManagement.AccountManagementActivity;
import com.jiuhao.jhjk.activity.mine.Bill.BillActivity;
import com.jiuhao.jhjk.activity.mine.ConsultingFee.ConsultingFeeActivity;
import com.jiuhao.jhjk.activity.mine.DoctorCertified.MessageCertifiedActivity;
import com.jiuhao.jhjk.activity.mine.Other.FillInActivity;
import com.jiuhao.jhjk.activity.mine.Other.IntegralActivity;
import com.jiuhao.jhjk.activity.mine.Other.InviteFriendsActivity;
import com.jiuhao.jhjk.activity.mine.Other.OutCallActivity;
import com.jiuhao.jhjk.activity.mine.Personage.PersonalDataActivity;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.view.WebviewActivity;

/**
 * 我的主fragment
 */
public class MainMineFragment extends BaseFragment {


    private ImageView mIvHead;
    /**
     * 医生名字
     */
    private TextView mTvDocNameUser;
    /**
     * 完善个人资料
     */
    private TextView mPerfect;
    /**
     * 邀请医生
     */
    private TextView mInvite;
    private ImageView mOkAuthentication;
    /**
     * 医生资质认证
     */
    private TextView mRen;
    /**
     * 未认证
     */
    private TextView mAuthenticationRight;
    private RelativeLayout mLin1;
    /**
     * 咨询费设置
     */
    private TextView mSeetingMoney;
    /**
     * 出诊时间
     */
    private TextView mGoOutTime;
    /**
     * 问诊单
     */
    private TextView mInterrogationOfSingle;
    /**
     * 积分统计
     */
    private TextView mNumberStatistics;
    private LinearLayout mLin2;
    /**
     * 账号管理
     */
    private TextView mManagement;
    /**
     * 智能填写
     */
    private TextView mTvIc6;
    /**
     * (什么是智能填写)
     */
    private TextView mTvIc6Question;
    private ImageView mIvZhiOffOn;
    private LinearLayout mLin3;
    /**
     * 使用帮助
     */
    private TextView mUseHelp;
    /**
     * 关于我们
     */
    private TextView mAboutOur;
    private LinearLayout mLin4;
    private boolean isWhite = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mian_mine;
    }

    @Override
    protected void initView() {

        mIvHead = (ImageView) findViewById(R.id.iv_head);
        mTvDocNameUser = (TextView) findViewById(R.id.tv_doc_name_user);
        mPerfect = (TextView) findViewById(R.id.perfect);
        mInvite = (TextView) findViewById(R.id.invite);
        mOkAuthentication = (ImageView) findViewById(R.id.ok_authentication);
        mRen = (TextView) findViewById(R.id.ren);
        mAuthenticationRight = (TextView) findViewById(R.id.authentication_right);
        mLin1 = (RelativeLayout) findViewById(R.id.lin_1);
        mSeetingMoney = (TextView) findViewById(R.id.seeting_money);
        mGoOutTime = (TextView) findViewById(R.id.go_out_time);
        mInterrogationOfSingle = (TextView) findViewById(R.id.Interrogation_of_single);
        mNumberStatistics = (TextView) findViewById(R.id.number_statistics);
        mLin2 = (LinearLayout) findViewById(R.id.lin_2);
        mManagement = (TextView) findViewById(R.id.management);
        mTvIc6 = (TextView) findViewById(R.id.tv_ic_6);
        mTvIc6Question = (TextView) findViewById(R.id.tv_ic_6_question);
        mIvZhiOffOn = (ImageView) findViewById(R.id.iv_zhi_off_on);
        mLin3 = (LinearLayout) findViewById(R.id.lin_3);
        mUseHelp = (TextView) findViewById(R.id.use_help);
        mAboutOur = (TextView) findViewById(R.id.about_our);
        mLin4 = (LinearLayout) findViewById(R.id.lin_4);
    }

    @Override
    protected void initData() {

        //医生头像
        String headUrl = SPUtils.getString(getContext(), ConfigKeys.AVATAR, "");
        GlideUtil.loadCircle(getContext(), headUrl, mIvHead);
        //医生名字
        String name = SPUtils.getString(getContext(), ConfigKeys.NAME, "医生名字");
        mTvDocNameUser.setText(name);
        //认证状态 1未认证 3已认证
        int authstat = SPUtils.getInt(getContext(), ConfigKeys.AUTHSTAT, 1);
        if (authstat == 1) {
            mOkAuthentication.setImageResource(R.mipmap.ok_1);
            mAuthenticationRight.setText("未认证  ");
        } else if (authstat == 3) {
            mOkAuthentication.setImageResource(R.mipmap.ok);
            mAuthenticationRight.setText("已认证  ");
        }

    }

    @Override
    protected void setListener() {

        //医生认证
        mAuthenticationRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MessageCertifiedActivity.class));
            }
        });

        //账号管理
        mManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AccountManagementActivity.class));
            }
        });

        //咨询费设置
        mSeetingMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ConsultingFeeActivity.class));
            }
        });

        //积分统计
        mNumberStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), IntegralActivity.class));
            }
        });

        //问诊单
        mInterrogationOfSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BillActivity.class));
            }
        });

        //关于我们
        mAboutOur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AboutOurActivity.class));
            }
        });

        //完善个人资料
        mPerfect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PersonalDataActivity.class));
            }
        });

        //出诊时间
        mGoOutTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OutCallActivity.class));
            }
        });
        //智能填写
        mIvZhiOffOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWhite) {
                    mIvZhiOffOn.setImageResource(R.mipmap.off);
                    SPUtils.putBoolean(getActivity(), ConfigKeys.ZHI_FLAG, false);
                } else if (!isWhite) {
                    mIvZhiOffOn.setImageResource(R.mipmap.on);
                    SPUtils.putBoolean(getActivity(), ConfigKeys.ZHI_FLAG, true);
                }
                isWhite = !isWhite;
            }
        });

        //什么是智能填写
        mTvIc6Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FillInActivity.class));
            }
        });

        //使用帮助
        mUseHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtra("title", "使用帮助");
                intent.putExtra("html", ConfigKeys.USER_HELP);
                startActivity(intent);
            }
        });

        //邀请医生
        mInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InviteFriendsActivity.class));
            }
        });
    }

}

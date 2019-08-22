package com.jiuhao.jhjk.fragment.main;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.mine.AboutOurActivity;
import com.jiuhao.jhjk.activity.mine.AccountManagementActivity;
import com.jiuhao.jhjk.activity.mine.BillActivity;
import com.jiuhao.jhjk.activity.mine.ConsultingFeeActivity;
import com.jiuhao.jhjk.activity.mine.DoctorCertified.MessageCertifiedActivity;
import com.jiuhao.jhjk.activity.mine.IntegralActivity;
import com.jiuhao.jhjk.activity.mine.OutCallActivity;
import com.jiuhao.jhjk.activity.mine.PersonalDataActivity;
import com.jiuhao.jhjk.fragment.base.BaseFragment;

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
    }

}

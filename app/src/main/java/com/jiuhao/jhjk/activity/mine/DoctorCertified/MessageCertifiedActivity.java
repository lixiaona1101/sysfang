package com.jiuhao.jhjk.activity.mine.DoctorCertified;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 信息认证
 */
public class MessageCertifiedActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 确认
     */
    private TextView mTvTitleSure;
    private RelativeLayout mRlTitleSure;
    private RelativeLayout mRlTitle;
    /**
     * 请输入业务员邀请码
     */
    private EditText mInvitationCode;
    /**
     * 请输入医生真实姓名
     */
    private EditText mDoctorName;
    /**
     * 点击上传医师资格证书
     */
    private TextView mUpNoOk;
    private LinearLayout mUpOk;
    /**
     * 提交认证
     */
    private Button mMessageSubmit;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_message_certified);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        mRlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        mRlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        mInvitationCode = (EditText) findViewById(R.id.invitation_code);
        mDoctorName = (EditText) findViewById(R.id.doctor_name);
        mUpNoOk = (TextView) findViewById(R.id.up_no_ok);
        mUpOk = (LinearLayout) findViewById(R.id.up_ok);
        mMessageSubmit = (Button) findViewById(R.id.message_submit);
        mMessageSubmit.setOnClickListener(this);
        mTvTitle.setText("信息认证");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

//        测试信息认证是否成功页面
        mUpNoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(),CertifiedPassActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.message_submit://提交认证
                //接口调用
                //提交成功 跳转认证结果页面
                startActivity(new Intent(getContext(),CertifiedActivity.class));
                break;
        }
    }
}

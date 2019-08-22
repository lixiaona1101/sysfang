package com.jiuhao.jhjk.activity.mine.DoctorCertified;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 认证是否通过
 */
public class CertifiedPassActivity extends BaseActivity implements View.OnClickListener {


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
     * 您的医生资质认证已通过！
     */
    private TextView textPass;
    /**
     * 您的医生资质认证未通过！
     */
    private TextView textNoPass;
    /**
     * 业务员：
     */
    private TextView salesman;
    /**
     * 赵璐 13145897536
     */
    private TextView salesmanNamePhone;
    private ImageView salesmanPhone;
    /**
     * 认证时间:
     */
    private TextView attestation;
    /**
     * 2019.8.20
     */
    private TextView attestationTime;
    /**
     * 赵璐
     */
    private TextView renName;
    private ImageView right1Ok;
    private ImageView pass;
    private RelativeLayout relaStatusOk;
    private ImageView right;
    private ImageView fail;
    private RelativeLayout relaStatusNo;
    private ImageView right2Ok;
    private ImageView pass2Ok;
    private RelativeLayout relaPracticeOk;
    private ImageView right2;
    private ImageView fail2;
    private RelativeLayout relaPracticeNo;
    /**
     * 说明:\n1、我们承诺,您的个人信息只用做医生资格审查。绝不将您的个人信息外漏。\n2、医生资质一但认证成功,不支持进行更改。包括【医生姓名】、【执业证书】、【资格证书】。如有特殊情况请联系您的业务员进行说明更改。\n3、平台推广期,个别情况不做特别限制。
     */
    private TextView reasonOk;
    /**
     * 审核失败原因:
     */
    private TextView reasonNo1;
    /**
     * 资格证与认证医生姓名不符
     */
    private TextView reasonNo2;
    /**
     * 重新认证
     */
    private Button reasonRestartButton;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_certified_pass);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        textPass = (TextView) findViewById(R.id.text_pass);
        textNoPass = (TextView) findViewById(R.id.text_no_pass);
        salesman = (TextView) findViewById(R.id.salesman);
        salesmanNamePhone = (TextView) findViewById(R.id.salesman_name_phone);
        salesmanPhone = (ImageView) findViewById(R.id.salesman_phone);
        attestation = (TextView) findViewById(R.id.attestation);
        attestationTime = (TextView) findViewById(R.id.attestation_time);
        renName = (TextView) findViewById(R.id.ren_name);
        right1Ok = (ImageView) findViewById(R.id.right1_ok);
        pass = (ImageView) findViewById(R.id.pass);
        relaStatusOk = (RelativeLayout) findViewById(R.id.rela_status_ok);
        right = (ImageView) findViewById(R.id.right);
        fail = (ImageView) findViewById(R.id.fail);
        relaStatusNo = (RelativeLayout) findViewById(R.id.rela_status_no);
        right2Ok = (ImageView) findViewById(R.id.right2_ok);
        pass2Ok = (ImageView) findViewById(R.id.pass2_ok);
        relaPracticeOk = (RelativeLayout) findViewById(R.id.rela_practice_ok);
        right2 = (ImageView) findViewById(R.id.right2);
        fail2 = (ImageView) findViewById(R.id.fail2);
        relaPracticeNo = (RelativeLayout) findViewById(R.id.rela_practice_no);
        reasonOk = (TextView) findViewById(R.id.reason_ok);
        reasonNo1 = (TextView) findViewById(R.id.reason_no1);
        reasonNo2 = (TextView) findViewById(R.id.reason_no2);
        reasonRestartButton = (Button) findViewById(R.id.reason_restart_button);
        reasonRestartButton.setOnClickListener(this);
        tvTitle.setText("信息认证");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.reason_restart_button:
                startActivity(new Intent(getContext(),MessageCertifiedActivity.class));
                break;
        }
    }
}

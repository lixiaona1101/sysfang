package com.jiuhao.jhjk.activity.mine.DoctorCertified;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.bean.DocAuthBean;

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
    private DocAuthBean docAuthBean;
    private int authStat;//认证状态
    /**
     * 您的医生资质认证正在审核中
     */
    private TextView textNowPass;
    private ImageView rightNow;
    private RelativeLayout relaStatusNow;
    private ImageView right3;
    private RelativeLayout relaPracticeNow;
    /**
     * 重新认证
     */
    private Button againRestartButton;


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
        textNowPass = (TextView) findViewById(R.id.text_now_pass);
        rightNow = (ImageView) findViewById(R.id.right_now);
        relaStatusNow = (RelativeLayout) findViewById(R.id.rela_status_now);
        right3 = (ImageView) findViewById(R.id.right3);
        relaPracticeNow = (RelativeLayout) findViewById(R.id.rela_practice_now);
        againRestartButton = (Button) findViewById(R.id.again_restart_button);
        againRestartButton.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        docAuthBean = (DocAuthBean) intent.getSerializableExtra("bean");

        String name = docAuthBean.getSalesman().getName();
        String phone = docAuthBean.getSalesman().getPhone();
        salesmanNamePhone.setText(name + "  " + phone);
        attestationTime.setText(docAuthBean.getCreateTime() + "");
        renName.setText(docAuthBean.getName());

        authStat = docAuthBean.getAuthStat();
        if (authStat == 3) {//已认证
            textPass.setVisibility(View.VISIBLE);
            relaStatusOk.setVisibility(View.VISIBLE);
            relaPracticeOk.setVisibility(View.VISIBLE);
            reasonOk.setVisibility(View.VISIBLE);
            reasonRestartButton.setVisibility(View.VISIBLE);
        } else if (authStat == 4) {//审核失败
            textNoPass.setVisibility(View.VISIBLE);
            reasonNo1.setVisibility(View.VISIBLE);
            reasonNo2.setVisibility(View.VISIBLE);
            againRestartButton.setVisibility(View.VISIBLE);
            reasonNo2.setText(docAuthBean.getUnAuthRemark());
            if (docAuthBean.isAu_PhysicianQualCert()) {//资格证通过
                relaStatusOk.setVisibility(View.VISIBLE);
            } else {
                relaStatusNo.setVisibility(View.VISIBLE);
            }
            if (docAuthBean.isAu_PhysicianCert()) {//执业证通过
                relaPracticeOk.setVisibility(View.VISIBLE);
            } else {
                relaPracticeNo.setVisibility(View.VISIBLE);
            }

        } else if (authStat == 2) {//审核中
            textNowPass.setVisibility(View.VISIBLE);
            relaStatusNow.setVisibility(View.VISIBLE);
            relaPracticeNow.setVisibility(View.VISIBLE);
            reasonOk.setVisibility(View.VISIBLE);
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
        //call
        salesmanPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + docAuthBean.getSalesman().getPhone());
                intent.setData(data);
                startActivity(intent);
            }
        });

        //成功 医师资格证查看
        relaStatusOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                look(docAuthBean.getPhysicianQualCert());
            }
        });
        //成功 医师执业证查看
        relaPracticeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                look(docAuthBean.getPhysicianCert());
            }
        });
        //失败 医师资格证查看
        relaStatusNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                look(docAuthBean.getPhysicianQualCert());
            }
        });
        //失败 执业证查看
        relaPracticeNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                look(docAuthBean.getPhysicianCert());
            }
        });
        //审核中  医师资格证查看
        relaStatusNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                look(docAuthBean.getPhysicianQualCert());
            }
        });
        //审核中 执业证查看
        relaPracticeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                look(docAuthBean.getPhysicianCert());
            }
        });

    }

    //查看图片
    public void look(String url) {
        Intent intent = new Intent(getContext(), BigImgActivity.class);
        intent.putExtra("imgUrl", url);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.reason_restart_button://完成认证
                finish();
                break;
            case R.id.again_restart_button://重新认证
                Intent intent = new Intent(getContext(), MessageCertifiedAgainActivity.class);
                intent.putExtra("docbean", docAuthBean);
                startActivity(intent);
                break;
        }
    }

}

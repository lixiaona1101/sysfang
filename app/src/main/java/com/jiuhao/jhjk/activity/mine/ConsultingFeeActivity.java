package com.jiuhao.jhjk.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 咨询费设置
 */
public class ConsultingFeeActivity extends BaseActivity {


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
     * 自定义
     */
    private TextView consultingImg;
    /**
     * 当前已选择
     */
    private TextView text2;
    /**
     * 0
     */
    private TextView number;
    private ImageView patientImg;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_consulting_fee);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        consultingImg = (TextView) findViewById(R.id.consulting_img);
        text2 = (TextView) findViewById(R.id.text2);
        number = (TextView) findViewById(R.id.number);
        patientImg = (ImageView) findViewById(R.id.patient_img);
        tvTitle.setText("咨询费设置");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

        //设置咨询费
        consultingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ConsultingActivity.class));
            }
        });

        //设置收费患者
        patientImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),FeePersonActivity.class));
            }
        });
    }
}

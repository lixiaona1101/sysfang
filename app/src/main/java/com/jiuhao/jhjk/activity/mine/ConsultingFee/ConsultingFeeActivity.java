package com.jiuhao.jhjk.activity.mine.ConsultingFee;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.ChoosedBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

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
    private ChoosedBean choosedBean;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    consultingImg.setText(choosedBean.getPrice() + "  ");
                    number.setText(choosedBean.getCustomerCount() + "");
                    break;
            }
            return false;
        }
    });

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
        getData();
    }

    public void getData() {
        OkHttpUtils.get(ConfigKeys.DOCPRICEITEMCHOOSED, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                choosedBean = Json.parseObj(response, ChoosedBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //设置咨询费
        consultingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ConsultingActivity.class);
                intent.putExtra("price", choosedBean.getPrice());
                intent.putExtra("markType", choosedBean.getMarkType());
                startActivityForResult(intent, 1001);
            }
        });

        //设置收费患者
        patientImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (consultingImg.getText().toString().equals("未选择")) {
                    ToastUtils.show("请先设置收费金额");
                }else{
                    Intent intent = new Intent(getContext(), FeePersonActivity.class);
                    intent.putExtra("title", "选择患者");
                    intent.putExtra("fee",choosedBean.getPrice());
                    startActivityForResult(intent,101);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == 1002) {
            getData();
        }else if (requestCode == 101 && resultCode == 102) {
            getData();
        }
    }
}

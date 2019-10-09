package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.activity.patient.DialogueActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.RecordDetailRecyclerAdapter;
import com.jiuhao.jhjk.bean.SelectDetailsBean;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 最近5条/开方记录 开方详情
 */
public class RecordDetailActivity extends BaseActivity {

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
     * 此药方没有患者扫取
     */
    private TextView actNoCustomer;
    private ImageView ivDetailRecordCustomerImg;
    /**
     * 患者信息
     */
    private TextView actDetailRecordCustomer;
    private LinearLayout llDetailRecordCustomer;
    /**
     * 45:12
     */
    private TextView tvRecordDetailTime;
    /**
     * 诊断
     */
    private TextView tvRecordDetailSymptom;
    /**
     * 处方
     */
    private TextView tvRecordDetailMedType;
    /**
     * (图片处方)
     */
    private TextView tvRecordDetailIsImg;
    private RecyclerView mgvRecordRecycler;
    private TextView tvRecordDetailPlural;
    private LinearLayout llPlural;
    private TextView tvRecordDetailFreq;
    private LinearLayout llFreq;
    private TextView tvRecordDetailMeth;
    private LinearLayout llMeth;
    private TextView tvRecordDetailTotalPrice;
    private TextView tvRecordDetailMedPrice;
    private TextView tvRecordDetailFees;
    private LinearLayout llFees;
    private TextView tvRecordDetailWorking;
    private LinearLayout llWorking;
    private TextView tvRecordDetailMedStatus;
    private Intent intent;
    private SelectDetailsBean selectDetailsBean;
    private boolean customerState;
    private String timeStr;
    private String formulationName;
    private int isImg;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    setDetailData();
                    break;
            }
            return false;
        }
    });
    private ImageView imRecordDetailSrc;

    public void setDetailData() {

        if (customerState) {
            llDetailRecordCustomer.setVisibility(View.VISIBLE);
            actNoCustomer.setVisibility(View.GONE);
            String avatar = selectDetailsBean.getAvatar();
            if(!avatar.isEmpty()){
                GlideUtil.loadCircle(getContext(), avatar, ivDetailRecordCustomerImg);
            }
            actDetailRecordCustomer.setText(selectDetailsBean.getName());
        } else {
            llDetailRecordCustomer.setVisibility(View.GONE);
            actNoCustomer.setVisibility(View.VISIBLE);
        }

        tvRecordDetailTime.setText(timeStr);
        tvRecordDetailSymptom.setText(selectDetailsBean.getSymptom());
        tvRecordDetailMedType.setText(formulationName);

        //处方类型 1文字处方 2 3 图片处方
        if (isImg == 1) {
            tvRecordDetailIsImg.setVisibility(View.GONE);
            imRecordDetailSrc.setVisibility(View.GONE);
        } else if (isImg == 2 || isImg == 3) {
            tvRecordDetailIsImg.setVisibility(View.VISIBLE);
            imRecordDetailSrc.setVisibility(View.VISIBLE);
            GlideUtil.loadCircle(getContext(),selectDetailsBean.getCaseImg(),imRecordDetailSrc);
        }

        List<String> med = selectDetailsBean.getMed();
        RecordDetailRecyclerAdapter recordDetailRecyclerAdapter = new RecordDetailRecyclerAdapter(getContext(), med);
        mgvRecordRecycler.setAdapter(recordDetailRecyclerAdapter);

        tvRecordDetailPlural.setText(selectDetailsBean.getPlural()+"付");
        tvRecordDetailFreq.setText(selectDetailsBean.getFreq()+"");
        tvRecordDetailMeth.setText(selectDetailsBean.getMedMethod()+"");
        tvRecordDetailTotalPrice.setText(selectDetailsBean.getCasePrice()+"");
        tvRecordDetailMedPrice.setText(selectDetailsBean.getMedPrice()+"");
        tvRecordDetailFees.setText(selectDetailsBean.getFeePrice()+"");
        tvRecordDetailMedStatus.setText(selectDetailsBean.getOrderState()+"");
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_record_detail);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        actNoCustomer = (TextView) findViewById(R.id.act_no_customer);
        ivDetailRecordCustomerImg = (ImageView) findViewById(R.id.iv_detail_record_customer_img);
        actDetailRecordCustomer = (TextView) findViewById(R.id.act_detail_record_customer);
        llDetailRecordCustomer = (LinearLayout) findViewById(R.id.ll_detail_record_customer);
        tvRecordDetailTime = (TextView) findViewById(R.id.tv_record_detail_time);
        tvRecordDetailSymptom = (TextView) findViewById(R.id.tv_record_detail_symptom);
        tvRecordDetailMedType = (TextView) findViewById(R.id.tv_record_detail_med_type);
        tvRecordDetailIsImg = (TextView) findViewById(R.id.tv_record_detail_is_img);
        mgvRecordRecycler = (RecyclerView) findViewById(R.id.mgv_record_recycler);
        mgvRecordRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        tvRecordDetailPlural = (TextView) findViewById(R.id.tv_record_detail_plural);
        llPlural = (LinearLayout) findViewById(R.id.ll_plural);
        tvRecordDetailFreq = (TextView) findViewById(R.id.tv_record_detail_freq);
        llFreq = (LinearLayout) findViewById(R.id.ll_freq);
        tvRecordDetailMeth = (TextView) findViewById(R.id.tv_record_detail_meth);
        llMeth = (LinearLayout) findViewById(R.id.ll_meth);
        tvRecordDetailTotalPrice = (TextView) findViewById(R.id.tv_record_detail_total_price);
        tvRecordDetailMedPrice = (TextView) findViewById(R.id.tv_record_detail_med_price);
        tvRecordDetailFees = (TextView) findViewById(R.id.tv_record_detail_fees);
        llFees = (LinearLayout) findViewById(R.id.ll_fees);
        tvRecordDetailWorking = (TextView) findViewById(R.id.tv_record_detail_working);
        llWorking = (LinearLayout) findViewById(R.id.ll_working);
        tvRecordDetailMedStatus = (TextView) findViewById(R.id.tv_record_detail_med_status);
        tvTitle.setText("详情");
        imRecordDetailSrc = (ImageView) findViewById(R.id.im_record_detail_src);
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        int caseId = intent.getIntExtra("caseId", 0);//处方id
        customerState = intent.getBooleanExtra("customerState", false);//是否有患者
        timeStr = intent.getStringExtra("timeStr");//处方时间
        formulationName = intent.getStringExtra("formulationName");//剂型名称
        isImg = intent.getIntExtra("isImg", 0);//处方类型 1文字处方 2 3 图片处方

        Logger.e(caseId+"");
        Logger.e(customerState+"");
        Logger.e(timeStr+"");
        Logger.e(formulationName+"");
        Logger.e(isImg+"");

        getDetailData(caseId);

    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imRecordDetailSrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BigImgActivity.class);
                intent.putExtra("imgUrl", selectDetailsBean.getImgUrl());
                startActivity(intent);
            }
        });

        //进入到跟患者聊天的页面
        llDetailRecordCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), DialogueActivity.class);
                intent1.putExtra("nickName",selectDetailsBean.getName());//患者姓名
                intent1.putExtra("id", selectDetailsBean.getCustomerId());//患者id
            }
        });
    }


    public void getDetailData(int caseId) {

        String url = ConfigKeys.SELECTDETAIL + "?id=" + caseId;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectDetailsBean = Json.parseObj(response, SelectDetailsBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

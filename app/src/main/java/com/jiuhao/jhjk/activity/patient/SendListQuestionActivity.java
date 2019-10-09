package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.SendListQuestionRecyclerAdapter;
import com.jiuhao.jhjk.bean.BillBean2;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 跟患者聊天页面中发送问诊单
 */
public class SendListQuestionActivity extends BaseActivity {


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
    private LinearLayout linGone;
    /**
     * 我的问诊单(0)
     */
    private TextView billNumber;
    private RecyclerView billRecycler;
    private LinearLayout linVisible;
    private List<BillBean2> billListBeans;
    private Intent intent;
    private int customerId;//患者id
    private int docInterrogationId;//选中要发送的问诊单id

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (billListBeans.size() == 0) {
                        linGone.setVisibility(View.VISIBLE);
                        linVisible.setVisibility(View.GONE);
                    } else {
                        linGone.setVisibility(View.GONE);
                        linVisible.setVisibility(View.VISIBLE);
                        billNumber.setText("我的问诊单(" + billListBeans.size() + ")");
                        //recycler适配
                        SendListQuestionRecyclerAdapter sendListQuestionRecyclerAdapter =
                                new SendListQuestionRecyclerAdapter(getContext(), billListBeans,
                                        new SendListQuestionRecyclerAdapter.instem() {
                                            @Override
                                            public void onclickInstem(int i) {
                                                docInterrogationId = billListBeans.get(i).getId();
                                            }
                                        });
                        billRecycler.setAdapter(sendListQuestionRecyclerAdapter);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_send_list_question);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        linGone = (LinearLayout) findViewById(R.id.lin_gone);
        billNumber = (TextView) findViewById(R.id.bill_number);
        billRecycler = (RecyclerView) findViewById(R.id.bill_recycler);
        linVisible = (LinearLayout) findViewById(R.id.lin_visible);
        tvTitle.setText("问诊单");
        rlTitleSure.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        customerId = intent.getIntExtra("id", 0);
        getBillData();
    }

    public void getBillData() {
        OkHttpUtils.get(ConfigKeys.INTERROGATION, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                billListBeans = Json.parseArr(response, BillBean2.class);
                Logger.e(billListBeans.toString());
                handler.sendEmptyMessage(0);

            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(code + "" + e.getMessage());
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
        //确定发送
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senddocInterrogationId();
            }
        });
    }

    public void senddocInterrogationId() {

        /**
         * customerId 患者id
         docInterrogationId 问诊单id
         */
        OkHttpUtils.get(ConfigKeys.SENDDOCINTERROGATION+"?customerId="+customerId+"&docInterrogationId="+docInterrogationId, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                ToastUtils.show(response);
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

package com.jiuhao.jhjk.activity.message;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.LogisticsRecyclerAdapter;
import com.jiuhao.jhjk.bean.ExpressBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 物流详情
 */
public class LogisticsActivity extends BaseActivity {


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
     * 快递公司
     */
    private TextView typename;
    /**
     * 快递单号快递单号快递单号快递单号
     */
    private TextView number;
    /**
     * 已发货
     */
    private TextView shipments;
    /**
     * 运输中
     */
    private TextView transportation;
    /**
     * 派件中
     */
    private TextView delivery;
    /**
     * 已签收
     */
    private TextView deliverystatus;
    private TextView yiCircle;
    private TextView erCircle;
    private TextView sanCircle;
    private TextView siCircle;
    private LinearLayout linCircle;
    /**
     * 杭州市
     */
    private TextView startCity;
    /**
     * 上海市
     */
    private TextView endCity;
    private RecyclerView logisticsRecycler;
    private String orderId;
    private int flag;
    private ExpressBean expressBean;
    private List<ExpressBean.ResultBean.ListBean> list;
    private LogisticsRecyclerAdapter logisticsRecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    typename.setText(expressBean.getResult().getTypename());
                    number.setText(expressBean.getResult().getNumber());
                    startCity.setText(expressBean.getResult().getStartCity());
                    endCity.setText(expressBean.getResult().getEndCity());
                    //物流状态0已发货1在途中 2派件中 3已签收/4派送失败
                    int deliverystatus = expressBean.getResult().getDeliverystatus();
                    setCirclr(deliverystatus);

                    list = expressBean.getResult().getList();
                    logisticsRecyclerAdapter = new LogisticsRecyclerAdapter(getContext(), list);
                    logisticsRecycler.setAdapter(logisticsRecyclerAdapter);
                    break;
            }
            return false;
        }
    });

    public void setCirclr(int i) {
        yiCircle.setBackgroundResource(R.drawable.radius_10_hui_circle);
        erCircle.setBackgroundResource(R.drawable.radius_10_hui_circle);
        sanCircle.setBackgroundResource(R.drawable.radius_10_hui_circle);
        siCircle.setBackgroundResource(R.drawable.radius_10_hui_circle);

        shipments.setTextColor(getResources().getColor(R.color.gary_999999));
        shipments.setBackground(getResources().getDrawable(R.drawable.radius_6_f2));
        transportation.setTextColor(getResources().getColor(R.color.gary_999999));
        transportation.setBackground(getResources().getDrawable(R.drawable.radius_6_f2));
        delivery.setTextColor(getResources().getColor(R.color.gary_999999));
        delivery.setBackground(getResources().getDrawable(R.drawable.radius_6_f2));
        deliverystatus.setTextColor(getResources().getColor(R.color.gary_999999));
        deliverystatus.setBackground(getResources().getDrawable(R.drawable.radius_6_f2));
        switch (i) {
            case 0:
                yiCircle.setBackgroundResource(R.drawable.radius_10_circle);
                shipments.setTextColor(getResources().getColor(R.color.white));
                shipments.setBackground(getResources().getDrawable(R.drawable.radius_6_origen));
                break;
            case 1:
                erCircle.setBackgroundResource(R.drawable.radius_10_circle);
                transportation.setTextColor(getResources().getColor(R.color.white));
                transportation.setBackground(getResources().getDrawable(R.drawable.radius_6_origen));
                break;
            case 2:
                sanCircle.setBackgroundResource(R.drawable.radius_10_circle);
                delivery.setTextColor(getResources().getColor(R.color.white));
                delivery.setBackground(getResources().getDrawable(R.drawable.radius_6_origen));
                break;
            case 3:
                deliverystatus.setText("已签收");
                siCircle.setBackgroundResource(R.drawable.radius_10_circle);
                deliverystatus.setTextColor(getResources().getColor(R.color.white));
                deliverystatus.setBackground(getResources().getDrawable(R.drawable.radius_6_origen));
                break;
            case 4:
                deliverystatus.setText("派送失败");
                siCircle.setBackgroundResource(R.drawable.radius_10_circle);
                deliverystatus.setTextColor(getResources().getColor(R.color.white));
                deliverystatus.setBackground(getResources().getDrawable(R.drawable.radius_6_origen));
                break;
        }

    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_logistics);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        typename = (TextView) findViewById(R.id.typename);
        number = (TextView) findViewById(R.id.number);
        shipments = (TextView) findViewById(R.id.shipments);
        transportation = (TextView) findViewById(R.id.transportation);
        delivery = (TextView) findViewById(R.id.delivery);
        deliverystatus = (TextView) findViewById(R.id.deliverystatus);
        yiCircle = (TextView) findViewById(R.id.yi_circle);
        erCircle = (TextView) findViewById(R.id.er_circle);
        sanCircle = (TextView) findViewById(R.id.san_circle);
        siCircle = (TextView) findViewById(R.id.si_circle);
        linCircle = (LinearLayout) findViewById(R.id.lin_circle);
        startCity = (TextView) findViewById(R.id.startCity);
        endCity = (TextView) findViewById(R.id.endCity);
        logisticsRecycler = (RecyclerView) findViewById(R.id.logistics_recycler);
        logisticsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        tvTitle.setText("物流详情");
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        flag = intent.getIntExtra("flag", 0);
        getLogData();

    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getLogData() {

        String url = "";
        if (flag == 1) {
            url = ConfigKeys.EXPRESS + "?orderId=" + orderId;
        } else if (flag == 2) {
            url = ConfigKeys.EXPRESS + "?docCaseId=" + orderId;
        }
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                expressBean = Json.parseObj(response, ExpressBean.class);
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

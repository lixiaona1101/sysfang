package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.RecordDetailActivity;
import com.jiuhao.jhjk.activity.HomePage.SearchPatientActivity;
import com.jiuhao.jhjk.activity.message.LogisticsActivity;
import com.jiuhao.jhjk.bean.SaveImageBean;
import com.jiuhao.jhjk.bean.SelectCaseBean;
import com.jiuhao.jhjk.utils.DialogUtil;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by lxn on 2019/9/17.
 */

public class RecordRecyclerAdapter  extends RecyclerView.Adapter<RecordRecyclerAdapter.MyHolder> {

    private Context context;
    private List<SelectCaseBean> selectCaseBeans;
    private SaveImageBean saveImageBean;//再次開方二维码
    private android.app.AlertDialog medCodeDialog;//二维码弹出框

    public Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what){
                case 0:
                    initMedCodeDialog();
                    break;
            }
            return false;
        }
    });

    //处方二维码对话框
    private void initMedCodeDialog() {
        medCodeDialog = DialogUtil.createDialog(context, R.layout.dialog_med_evo);
        medCodeDialog.show();
        View view = DialogUtil.view;
        final ImageView ivCancel = view.findViewById(R.id.iv_cancel_med_dialog);
        ImageView ivEvoCode = view.findViewById(R.id.iv_evo_code);
        Button btnSend = view.findViewById(R.id.btn_send_to_customer);
        Button btnShare = view.findViewById(R.id._btn_share_med_to_wx);
        Logger.e(saveImageBean.getUrl());
        GlideUtil.load(context,saveImageBean.getUrl(),ivEvoCode);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
            }
        });

        //发送给患者
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                Intent intent = new Intent(context, SearchPatientActivity.class);
                intent.putExtra("caseid", saveImageBean.getCaseId());
                context.startActivity(intent);
            }
        });
        //分享至微信
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                if (TextUtils.isEmpty(saveImageBean.getUrl())) {
                    ToastUtils.show("开方出了点问题，请重新开具");
                } else {
                    JHJKWeChat.getInstance().shareImg(2, saveImageBean.getUrl());
                }
            }
        });
    }
    public RecordRecyclerAdapter(Context context, List<SelectCaseBean> selectChatListBeans) {
        this.context = context;
        this.selectCaseBeans = selectChatListBeans;
    }

    @NonNull
    @Override
    public RecordRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record, viewGroup, false);
        return new RecordRecyclerAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordRecyclerAdapter.MyHolder myHolder, int i) {

        //有患者
        if(selectCaseBeans.get(i).isCustomerState()){
            GlideUtil.loadCircle(context,selectCaseBeans.get(i).getAvatar(),myHolder.iv_record_customer_img);
            myHolder.actv_record_customer_name.setText(selectCaseBeans.get(i).getName());
        }else{
            myHolder.iv_record_customer_img.setVisibility(View.GONE);
            myHolder.actv_record_customer_name.setText("暂无患者");
        }
        int caseType = selectCaseBeans.get(i).getOrderState();
        if(caseType==0){
            myHolder.actv_record_pay_state.setText("未下单");
        }else if(caseType==1){
            myHolder.actv_record_pay_state.setText("未付款");
        }else if(caseType>=2){
            myHolder.actv_record_pay_state.setText("已付款");
        }
        myHolder.actv_record_time.setText(selectCaseBeans.get(i).getCreateTime());
        myHolder.actv_record_symptom.setText(selectCaseBeans.get(i).getSymptom());

        List<String> med = selectCaseBeans.get(i).getMed();
        if(med.size()!=0){
            StringBuffer stringBuffer=new StringBuffer();
            for (int j = 0; j < med.size(); j++) {
                String s = med.get(j).toString();
                stringBuffer.append(s+",");
            }
            StringBuffer delete = stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
            myHolder.actv_record_med.setText(delete);
        }
        //无物流
        if(selectCaseBeans.get(i).getExistsExpress()==0){
            myHolder.btn_see_pres.setVisibility(View.GONE);
        }else if(selectCaseBeans.get(i).getExistsExpress()==1){
            myHolder.btn_see_pres.setVisibility(View.VISIBLE);
        }
        //物流详情
        myHolder.btn_see_pres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LogisticsActivity.class);
                intent.putExtra("orderId",selectCaseBeans.get(i).getCaseId()+"");
                intent.putExtra("flag",2);
                context.startActivity(intent);
            }
        });
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, RecordDetailActivity.class);
                intent.putExtra("caseId",selectCaseBeans.get(i).getCaseId());
                intent.putExtra("customerState",selectCaseBeans.get(i).isCustomerState());
                intent.putExtra("timeStr",selectCaseBeans.get(i).getTimeStr());
                intent.putExtra("formulationName",selectCaseBeans.get(i).getFormulationName());
                intent.putExtra("isImg",selectCaseBeans.get(i).getIsImg());
                context.startActivity(intent);
            }
        });
        //再次開方
        myHolder.btn_again_pres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               getCaseAg(selectCaseBeans.get(i).getCaseId());
            }
        });
    }

    public void getCaseAg(int caseId){
        OkHttpUtils.get(ConfigKeys.DOCCASEAG+"?caseId="+caseId, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(code+response);
                saveImageBean = Json.parseObj(response, SaveImageBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return selectCaseBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public ImageView iv_record_customer_img;
        public TextView actv_record_customer_name;
        public TextView actv_record_pay_state;
        public TextView actv_record_time;
        public TextView actv_record_symptom;
        public TextView actv_record_med;
        public Button btn_see_pres;
        public Button btn_again_pres;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            iv_record_customer_img = itemView.findViewById(R.id.iv_record_customer_img);
            actv_record_customer_name = itemView.findViewById(R.id.actv_record_customer_name);
            actv_record_pay_state = itemView.findViewById(R.id.actv_record_pay_state);
            actv_record_time = itemView.findViewById(R.id.actv_record_time);
            actv_record_symptom = itemView.findViewById(R.id.actv_record_symptom);
            actv_record_med= itemView.findViewById(R.id.actv_record_med);
            btn_see_pres=itemView.findViewById(R.id.btn_see_pres);
            btn_again_pres=itemView.findViewById(R.id.btn_again_pres);

        }
    }
}


package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.RecordDetailActivity;
import com.jiuhao.jhjk.activity.message.LogisticsActivity;
import com.jiuhao.jhjk.bean.SelectCaseBean;
import com.jiuhao.jhjk.utils.glide.GlideUtil;

import java.util.List;

/**
 * Created by lxn on 2019/9/17.
 */

public class RecordRecyclerAdapter  extends RecyclerView.Adapter<RecordRecyclerAdapter.MyHolder> {

    private Context context;
    private List<SelectCaseBean> selectCaseBeans;

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


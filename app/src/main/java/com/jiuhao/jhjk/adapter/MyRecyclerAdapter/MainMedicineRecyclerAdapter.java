package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.RecordDetailActivity;
import com.jiuhao.jhjk.bean.DocCaseBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/16.
 */

public class MainMedicineRecyclerAdapter extends RecyclerView.Adapter<MainMedicineRecyclerAdapter.MyHolder> {
    private Context context;
    private List<DocCaseBean> listBeans;

    public MainMedicineRecyclerAdapter(Context context, List<DocCaseBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public MainMedicineRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recently, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.symptom.setText(listBeans.get(i).getSymptom());
        myHolder.time_str.setText(listBeans.get(i).getTimeStr());

        boolean customerState = listBeans.get(i).isCustomerState();
        if (customerState){//有患者

            int orderState = listBeans.get(i).getOrderState();
            if (orderState<=1){//未下单
                myHolder.orderState.setText("【未下单】");
            }else if(orderState>=2){//已下单
                myHolder.orderState.setText("【已下单】");
            }
            myHolder.user_lin.setVisibility(View.VISIBLE);
            myHolder.name.setText(listBeans.get(i).getName());

        }else{//无患者
            myHolder.orderState.setText("【无患者处方】");
            myHolder.user_lin.setVisibility(View.GONE);
        }

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, RecordDetailActivity.class);
                intent.putExtra("caseId",listBeans.get(i).getCaseId());
                intent.putExtra("customerState",customerState);
                intent.putExtra("timeStr",listBeans.get(i).getTimeStr());
                intent.putExtra("formulationName",listBeans.get(i).getFormulationName());
                intent.putExtra("isImg",listBeans.get(i).getIsImg());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView orderState;
        public TextView time_str;
        public TextView symptom;
        public LinearLayout user_lin;
        public TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            orderState = itemView.findViewById(R.id.orderState);
            time_str = itemView.findViewById(R.id.time_str);
            symptom = itemView.findViewById(R.id.symptom);
            user_lin = itemView.findViewById(R.id.user_lin);
            name = itemView.findViewById(R.id.name);
        }
    }
}

package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.message.LogisticsActivity;
import com.jiuhao.jhjk.bean.SystemBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/11.
 */

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.MyHolder> {

    private Context context;
    private List<SystemBean.DataBean> data;

    public RecipeRecyclerAdapter(Context context, List<SystemBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.title.setText(data.get(i).getTitle());
        myHolder.symptom.setText(data.get(i).getSymptom());
        myHolder.customerName.setText(data.get(i).getCustomerName());
        myHolder.orderId.setText(data.get(i).getOrderId());
        myHolder.createTime.setText(data.get(i).getCreateTime());
        if(data.get(i).getExistsExpress()==1){
            myHolder.logistics.setVisibility(View.VISIBLE);
        }else if(data.get(i).getExistsExpress()==0) {
            myHolder.logistics.setVisibility(View.GONE);
        }

        //查看物流
        myHolder.logistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LogisticsActivity.class);
                intent.putExtra("orderId",data.get(i).getOrderId());
                intent.putExtra("flag",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView time_title;
        public TextView title;
        public Button logistics;//物流
        public Button service_person;//联系患者

        public TextView symptom;
        public TextView customerName;
        public TextView orderId;
        public TextView createTime;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            time_title = itemView.findViewById(R.id.time_title);
            title = itemView.findViewById(R.id.title);
            logistics = itemView.findViewById(R.id.logistics);
            service_person = itemView.findViewById(R.id.service_person);


            symptom = itemView.findViewById(R.id.symptom);
            customerName = itemView.findViewById(R.id.customerName);
            orderId = itemView.findViewById(R.id.orderId);
            createTime = itemView.findViewById(R.id.createTime);
        }
    }
}

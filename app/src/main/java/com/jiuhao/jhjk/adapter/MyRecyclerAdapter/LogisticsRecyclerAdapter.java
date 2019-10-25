package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.ExpressBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/12.
 */

public class LogisticsRecyclerAdapter extends RecyclerView.Adapter<LogisticsRecyclerAdapter.MyHolder> {

    private Context context;
    private List<ExpressBean.ResultBean.ListBean> listBeans;

    public LogisticsRecyclerAdapter(Context context, List<ExpressBean.ResultBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public LogisticsRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_logistics, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        myHolder.time.setText(listBeans.get(i).getTime());
        myHolder.status.setText(listBeans.get(i).getStatus());
        if (i == 0) {
            myHolder.line.setVisibility(View.GONE);
            myHolder.time.setTextColor(context.getResources().getColor(R.color.c_333333));
            myHolder.status.setTextColor(context.getResources().getColor(R.color.c_333333));
        }
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView line;
        public TextView time;
        public TextView status;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            line = itemView.findViewById(R.id.line);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
        }
    }
}


package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.BillBean2;

import java.util.List;

/**
 * 问诊单列表recycler
 */

public class BillRecyclerAdapter extends RecyclerView.Adapter<BillRecyclerAdapter.BillHolder> {

    private Context context;
    private List<BillBean2> billListBeans;

    public BillRecyclerAdapter(Context context, List<BillBean2> billListBeans) {
        this.context = context;
        this.billListBeans = billListBeans;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill, viewGroup, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder billHolder, int i) {

        //问诊单名字
        String namestr = billListBeans.get(i).getName();
        billHolder.name.setText(namestr);

        //问题统计
        billHolder.question_number.setText("共"+billListBeans.get(i).getCount()+"题");

        //编辑
        billHolder.redact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转问诊单编辑
            }
        });
    }

    @Override
    public int getItemCount() {
        return billListBeans.size();
    }


    class BillHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView redact;
        public TextView question_number;

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            redact = itemView.findViewById(R.id.redact);
            question_number = itemView.findViewById(R.id.question_number);
        }
    }
}

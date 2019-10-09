package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.ShopedSelectBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/23.
 */

public class ShopedSelectRecyclerAdapter extends RecyclerView.Adapter<ShopedSelectRecyclerAdapter.BillHolder> {

    private Context context;
    private List<ShopedSelectBean> shopedSelectBeans;
    private instem instem;

    public ShopedSelectRecyclerAdapter(Context context, List<ShopedSelectBean> shopedSelectBeans, instem instem) {
        this.context = context;
        this.shopedSelectBeans = shopedSelectBeans;
        this.instem = instem;
    }

    @NonNull
    @Override
    public ShopedSelectRecyclerAdapter.BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shoped_select, viewGroup, false);
        return new ShopedSelectRecyclerAdapter.BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopedSelectRecyclerAdapter.BillHolder billHolder, int i) {

        String medName = shopedSelectBeans.get(i).getMedName();//药品名称
        String medSpec = shopedSelectBeans.get(i).getMedSpec();//规格
        String cine = medName + " " + medSpec;
        billHolder.medicine_name.setText(cine);

        billHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instem.onClickInstem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopedSelectBeans.size();
    }

    public interface instem {
        void onClickInstem(int postion);
    }

    class BillHolder extends RecyclerView.ViewHolder {

        public TextView medicine_name;

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            medicine_name = itemView.findViewById(R.id.medicine_name);
        }
    }
}


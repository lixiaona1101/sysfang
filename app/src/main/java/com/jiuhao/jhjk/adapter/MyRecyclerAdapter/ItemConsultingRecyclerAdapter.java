package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/31.
 */

public class ItemConsultingRecyclerAdapter extends RecyclerView.Adapter<ItemConsultingRecyclerAdapter.MyHolder> {

    private Context context;
    private List<Integer> integers = new ArrayList<>();

    public ItemConsultingRecyclerAdapter(Context context, List<Integer> integers) {
        this.context = context;
        this.integers = integers;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cousutling_three_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.price.setText(integers.get(i)+"元/次");
    }


    @Override
    public int getItemCount() {
        return integers.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        public TextView price;
        public ImageView typetwo;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            typetwo = itemView.findViewById(R.id.type_two);
        }
    }
}

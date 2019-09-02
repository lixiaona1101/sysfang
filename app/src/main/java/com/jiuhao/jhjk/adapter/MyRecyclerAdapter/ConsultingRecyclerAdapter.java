package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.DocpricetimeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/8/31.
 * 咨询费列表
 */

public class ConsultingRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<DocpricetimeBean> docpricetimeBeans;

    private int ONE = 0;//自定义
    private int TWO = 1;//免费
    private int THREE = 2;//几元/次

    public ConsultingRecyclerAdapter(Context context, List<DocpricetimeBean> docpricetimeBeans) {
        this.context = context;
        this.docpricetimeBeans = docpricetimeBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (getItemViewType(i) == ONE) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.item_consutlting, viewGroup, false);
            return new OneHolder(view1);
        } else if (getItemViewType(i) == TWO) {
            View view2 = LayoutInflater.from(context).inflate(R.layout.item_consutling_two, viewGroup, false);
            return new TwoHolder(view2);
        } else if (getItemViewType(i) == THREE) {
            View view3 = LayoutInflater.from(context).inflate(R.layout.item_consutling_three, viewGroup, false);
            return new ThreeHolder(view3);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof OneHolder) {

        } else if (viewHolder instanceof TwoHolder) {

        } else if (viewHolder instanceof ThreeHolder) {

            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < docpricetimeBeans.size(); j++) {
                int markType = docpricetimeBeans.get(j).getMarkType();
                if (markType == 2) {
                    int price = docpricetimeBeans.get(j).getPrice();
                    integers.add(price);
                }
            }

            if (integers.size() != 0) {
                ItemConsultingRecyclerAdapter itemConsultingRecyclerAdapter = new ItemConsultingRecyclerAdapter(context, integers);
                ((ThreeHolder) viewHolder).three_recycler.setAdapter(itemConsultingRecyclerAdapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == ONE) {
            return ONE;
        } else if (position == TWO) {
            return TWO;
        } else if (position == THREE) {
            return THREE;
        } else {
            return super.getItemViewType(position);
        }
    }

    class OneHolder extends RecyclerView.ViewHolder {

        public EditText set_money;
        public ImageView type_one;

        public OneHolder(@NonNull View itemView) {
            super(itemView);
            set_money = itemView.findViewById(R.id.set_money);
            type_one = itemView.findViewById(R.id.type_one);
        }
    }

    class TwoHolder extends RecyclerView.ViewHolder {

        public ImageView type_zero;

        public TwoHolder(@NonNull View itemView) {
            super(itemView);
            type_zero = itemView.findViewById(R.id.type_zero);
        }
    }

    class ThreeHolder extends RecyclerView.ViewHolder {

        public RecyclerView three_recycler;

        public ThreeHolder(@NonNull View itemView) {
            super(itemView);
            three_recycler = itemView.findViewById(R.id.three_recycler);
            three_recycler.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}

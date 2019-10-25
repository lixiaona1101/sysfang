package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.base.RecyclerBaseAdapter;
import com.jiuhao.jhjk.adapter.base.ViewHolder;
import com.jiuhao.jhjk.bean.ShopedSelectBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/27.
 */

public class MesicneRecyclerAdapter extends RecyclerBaseAdapter<ShopedSelectBean> {


    public MesicneRecyclerAdapter(Context context, List<ShopedSelectBean> shopedSelectBeans) {
        super(context, shopedSelectBeans);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tv_med, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    protected void bindDataForView(ViewHolder holder, ShopedSelectBean shopedSelectBean, int position) {
        TextView tv_select_name = holder.getView(R.id.tv_search_med);
        tv_select_name.setText(shopedSelectBean.getMedName()+":"+shopedSelectBean.getMedNumber()+"克");
    }
}

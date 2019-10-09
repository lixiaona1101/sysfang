package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.SelectSuggestBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/24.
 * 病例联想下拉recycleradapter
 */

public class SelectSuggestRecyclerAdapter extends RecyclerView.Adapter<SelectSuggestRecyclerAdapter.MyHolder> {

    private Context context;
    private List<SelectSuggestBean> selectSuggestBeans;
    private instem instem;

    public SelectSuggestRecyclerAdapter(Context context, List<SelectSuggestBean> selectSuggestBeans,instem instem) {
        this.context = context;
        this.selectSuggestBeans = selectSuggestBeans;
        this.instem=instem;
    }

    @NonNull
    @Override
    public SelectSuggestRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_suggest, viewGroup, false);
        return new SelectSuggestRecyclerAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectSuggestRecyclerAdapter.MyHolder myHolder, int i) {
        myHolder.symptom_text.setText(selectSuggestBeans.get(i).getSymptom());
        myHolder.formulationName_text.setText("【"+selectSuggestBeans.get(i).getFormulationName()+"】");
        int templateType = selectSuggestBeans.get(i).getTemplateType();
        if(templateType==1){//模板方详情
            myHolder.templateType_text.setText("模  板");
        }else if(templateType==2){//协定方详情
            myHolder.templateType_text.setText("协定方");
        }
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instem.onClickin(i);
            }
        });
    }

    public interface instem{
        void onClickin(int postion);
    }
    @Override
    public int getItemCount() {
        return selectSuggestBeans.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        public TextView symptom_text;
        public TextView formulationName_text;
        public TextView templateType_text;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            symptom_text = itemView.findViewById(R.id.symptom_text);
            formulationName_text = itemView.findViewById(R.id.formulationName_text);
            templateType_text = itemView.findViewById(R.id.templateType_text);
        }
    }
}


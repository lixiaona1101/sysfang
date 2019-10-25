package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.CustomerBean;

import java.util.List;

/**
 * Created by Administrator on 2019/9/6.
 * 患者详情 诊单列表
 */

public class HistoryRcyclerAdapter extends RecyclerView.Adapter<HistoryRcyclerAdapter.HistoryHolder> {

    private Context context;
    private List<CustomerBean.DocCasesBean> docCases;

    public HistoryRcyclerAdapter(Context context, List<CustomerBean.DocCasesBean> docCases) {
        this.context = context;
        this.docCases = docCases;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_details_history, viewGroup, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {

        historyHolder.details_name.setText(docCases.get(i).getSymptom());
        //1中西成药 2配方颗粒 3中药饮片
        int caseType = docCases.get(i).getCaseType();
        if (caseType == 1) {
            historyHolder.details_type.setText("中西成药");
        } else if (caseType == 2) {
            historyHolder.details_type.setText("配方颗粒");
        } else if (caseType == 3) {
            historyHolder.details_type.setText("中药饮片");
        }
        historyHolder.prescription.setText(docCases.get(i).getMedStr());
        historyHolder.fu_number.setText(docCases.get(i).getPlural()+"付");
    }

    @Override
    public int getItemCount() {
        return docCases.size();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {

        public TextView details_name;
        public TextView details_type;
        public TextView prescription;
        public TextView fu_number;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            details_name = itemView.findViewById(R.id.details_name);
            details_type = itemView.findViewById(R.id.details_type);
            prescription = itemView.findViewById(R.id.prescription);
            fu_number = itemView.findViewById(R.id.fu_number);
        }
    }
}

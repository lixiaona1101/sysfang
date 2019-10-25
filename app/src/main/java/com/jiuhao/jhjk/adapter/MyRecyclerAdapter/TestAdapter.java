package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.RecordDetailActivity;
import com.jiuhao.jhjk.bean.DocCaseBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestAdapter extends BaseQuickAdapter<DocCaseBean, BaseViewHolder> {

    public TestAdapter(int layoutResId, @Nullable List<DocCaseBean> data) {
        super(layoutResId, data);
        Log.i("xxx", "TestAdapter: "+data.size());
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, DocCaseBean docCaseBean) {

        TextView orderState = holder.getView(R.id.orderState);
        TextView time_str = holder.getView(R.id.time_str);
        TextView symptom = holder.getView(R.id.symptom);
        LinearLayout user_lin = holder.getView(R.id.user_lin);
        TextView name = holder.getView(R.id.name);


        Logger.e("當前：" + holder.getAdapterPosition());

        symptom.setText(docCaseBean.getSymptom());
        time_str.setText(docCaseBean.getTimeStr());

        boolean customerState = docCaseBean.isCustomerState();
        if (customerState) {//有患者

            int order = docCaseBean.getOrderState();
            if (order <= 1) {//未下单
                orderState.setText("【未下单】");
            } else {//已下单
                orderState.setText("【已下单】");
            }
            user_lin.setVisibility(View.VISIBLE);
            name.setText(docCaseBean.getName());

        } else {//无患者
            orderState.setText("【无患者处方】");
            user_lin.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecordDetailActivity.class);
                intent.putExtra("caseId", docCaseBean.getCaseId());
                intent.putExtra("customerState", customerState);
                intent.putExtra("timeStr", docCaseBean.getTimeStr());
                intent.putExtra("formulationName", docCaseBean.getFormulationName());
                intent.putExtra("isImg", docCaseBean.getIsImg());
                mContext.startActivity(intent);
            }
        });
    }
}

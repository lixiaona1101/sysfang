package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.GridListAdapter.GvSelectedAdapter;
import com.jiuhao.jhjk.bean.CaseHistoryBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.MyGridView;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by lxn on 2019/9/19.
 */

public class TemplateRecordRecyclerAdapter extends RecyclerView.Adapter<TemplateRecordRecyclerAdapter.MyHolder> {

    private Context context;
    private List<CaseHistoryBean.DataBean> docCases;
    private instem instem;

    public TemplateRecordRecyclerAdapter(Context context, List<CaseHistoryBean.DataBean> docCases, instem instem) {
        this.context = context;
        this.docCases = docCases;
        this.instem = instem;
    }

    @NonNull
    @Override
    public TemplateRecordRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_template_record, viewGroup, false);
        return new TemplateRecordRecyclerAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateRecordRecyclerAdapter.MyHolder myHolder, int i) {

        myHolder.tv_model_name.setText(docCases.get(i).getSymptom());
        myHolder.tv_model_type.setText("【" + docCases.get(i).getFormulationName() + "】");
        myHolder.tv_model_plural.setText("【付数】 " + docCases.get(i).getPlural() + "付");
        myHolder.tv_model_freq.setText("【频次】 " + docCases.get(i).getFreq());
        myHolder.tv_model_meth.setText("【用药方法】 " + docCases.get(i).getMedMethod());

        List<String> med = docCases.get(i).getMed();
        myHolder.mgv_model_med.setAdapter(new GvSelectedAdapter(context, med));
        myHolder.mgv_model_med.setClickable(false);
        myHolder.mgv_model_med.setPressed(false);
        myHolder.mgv_model_med.setEnabled(false);

        myHolder.tv_add_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData(docCases.get(i));
            }
        });

    }

    //增加模板
    public void postData(CaseHistoryBean.DataBean dataBean) {

        OkHttpUtils.get(ConfigKeys.TEMPLATEADDBYCASE + "?caseId=" + dataBean.getId(), null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("添加成功");
                instem.onClickInstem();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public interface instem {
        void onClickInstem();
    }

    @Override
    public int getItemCount() {
        return docCases.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv_model_name;
        public TextView tv_model_type;
        public MyGridView mgv_model_med;
        public TextView tv_model_plural;
        public TextView tv_model_freq;
        public TextView tv_model_meth;
        public TextView tv_add_temp;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_model_name = itemView.findViewById(R.id.tv_model_name);
            tv_model_type = itemView.findViewById(R.id.tv_model_type);
            mgv_model_med = itemView.findViewById(R.id.mgv_model_med);
            tv_model_plural = itemView.findViewById(R.id.tv_model_plural);
            tv_model_freq = itemView.findViewById(R.id.tv_model_freq);
            tv_model_meth = itemView.findViewById(R.id.tv_model_meth);
            tv_add_temp = itemView.findViewById(R.id.tv_add_temp);

        }
    }

}

package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.GridListAdapter.GvSelectedAdapter;
import com.jiuhao.jhjk.bean.CaseHistoryBean;
import com.jiuhao.jhjk.bean.SelectByIdBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.MyGridView;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by lxn on 2019/9/17.
 */

public class ImportMedRecyclerAdapter extends RecyclerView.Adapter<ImportMedRecyclerAdapter.MyHolder> {

    private Context context;
    private List<CaseHistoryBean.DataBean> docCases;
    private instem instem;
    private SelectByIdBean selectByIdBean;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    List<SelectByIdBean.MedBean> med = selectByIdBean.getMed();
                    instem.onClickInstem(med);
                    break;
            }
            return false;
        }
    });

    public ImportMedRecyclerAdapter(Context context, List<CaseHistoryBean.DataBean> docCases, instem instem) {
        this.context = context;
        this.docCases = docCases;
        this.instem = instem;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_model, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        Logger.e(docCases.get(i).toString());
        myHolder.tv_model_name.setText(docCases.get(i).getSymptom());
        myHolder.tv_model_type.setText("【" + docCases.get(i).getFormulationName() + "】");
        myHolder.iv_template_code.setVisibility(View.GONE);
        myHolder.tv_model_plural.setText("【付数】 " + docCases.get(i).getPlural() + "付");
        myHolder.tv_model_freq.setText("【频次】 " + docCases.get(i).getFreq());
        myHolder.tv_model_meth.setText("【用药方法】 " + docCases.get(i).getMedMethod());

        List<String> med = docCases.get(i).getMed();
        myHolder.mgv_model_med.setAdapter(new GvSelectedAdapter(context, med));
        myHolder.mgv_model_med.setClickable(false);
        myHolder.mgv_model_med.setPressed(false);
        myHolder.mgv_model_med.setEnabled(false);

        //使用开方
        myHolder.tv_add_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectByIdData(docCases.get(i).getId());
            }
        });
    }

    //开方记录详情
    public void getSelectByIdData(int id) {

        OkHttpUtils.get(ConfigKeys.SELECYBYID + "?id=" + id, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectByIdBean = Json.parseObj(response, SelectByIdBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return docCases.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv_model_name;
        public TextView tv_model_type;
        public ImageView iv_template_code;
        public MyGridView mgv_model_med;
        public TextView tv_model_plural;
        public TextView tv_model_freq;
        public TextView tv_model_meth;
        public TextView tv_add_temp;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_model_name = itemView.findViewById(R.id.tv_model_name);
            tv_model_type = itemView.findViewById(R.id.tv_model_type);
            iv_template_code = itemView.findViewById(R.id.iv_template_code);
            mgv_model_med = itemView.findViewById(R.id.mgv_model_med);
            tv_model_plural = itemView.findViewById(R.id.tv_model_plural);
            tv_model_freq = itemView.findViewById(R.id.tv_model_freq);
            tv_model_meth = itemView.findViewById(R.id.tv_model_meth);
            tv_add_temp = itemView.findViewById(R.id.tv_add_temp);

        }
    }

    public interface instem {
        void onClickInstem(List<SelectByIdBean.MedBean> med);
    }
}

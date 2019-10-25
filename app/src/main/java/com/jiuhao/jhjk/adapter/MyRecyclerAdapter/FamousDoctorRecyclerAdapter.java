package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.FamousActivity;
import com.jiuhao.jhjk.activity.HomePage.SearchPatientActivity;
import com.jiuhao.jhjk.bean.FamousDoctorBean;
import com.jiuhao.jhjk.bean.SpecialFamousDoctorBean;
import com.jiuhao.jhjk.utils.DialogUtil;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lxn on 2019/9/18.
 */

public class FamousDoctorRecyclerAdapter extends RecyclerView.Adapter<FamousDoctorRecyclerAdapter.MyHolder> {

    private Context context;
    private List<FamousDoctorBean> famousDoctorBeans;
    private boolean check = false;
    private android.app.AlertDialog medCodeDialog;//二维码弹出框

    public FamousDoctorRecyclerAdapter(Context context, List<FamousDoctorBean> famousDoctorBeans) {
        this.context = context;
        this.famousDoctorBeans = famousDoctorBeans;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_famous_doctor, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        GlideUtil.loadCircle(context, famousDoctorBeans.get(i).getPicUrl(), myHolder.picUrl);
        myHolder.docName.setText(famousDoctorBeans.get(i).getDocName());
        myHolder.title.setText(famousDoctorBeans.get(i).getTitle());
        myHolder.symptom.setText(famousDoctorBeans.get(i).getSymptom());
        myHolder.efficacy.setText(famousDoctorBeans.get(i).getEfficacy());
        myHolder.indication.setText(famousDoctorBeans.get(i).getIndication());
        myHolder.formulationName.setText(famousDoctorBeans.get(i).getFormulationName());
        myHolder.price.setText("$" + famousDoctorBeans.get(i).getPrice());
        myHolder.useNum.setText(famousDoctorBeans.get(i).getUseNum() + "次");

        //秘方 0是 1否
        int secrecy = famousDoctorBeans.get(i).getSecrecy();
        if (secrecy == 0) {
            myHolder.secrecy.setVisibility(View.VISIBLE);
        } else {
            myHolder.secrecy.setVisibility(View.GONE);
        }

        //0未收藏 1已收藏
        int isAttention = famousDoctorBeans.get(i).getIsAttention();
        if (isAttention == 0) {
            check = false;
            myHolder.collect_text.setText("收藏");
            myHolder.collect_src.setImageResource(R.mipmap.collection1);
        } else if (isAttention == 1) {
            check = true;
            myHolder.collect_text.setText("已收藏");
            myHolder.collect_src.setImageResource(R.mipmap.collection);
        }

        myHolder.collect_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;
                if (check) {
                    myHolder.collect_text.setText("已收藏");
                    myHolder.collect_src.setImageResource(R.mipmap.collection);
                } else {
                    myHolder.collect_text.setText("收藏");
                    myHolder.collect_src.setImageResource(R.mipmap.collection1);
                }
                setColletData(i);
            }
        });

        myHolder.use_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUserData(i);
            }
        });

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, FamousActivity.class);
                intent.putExtra("data",famousDoctorBeans.get(i));
                context.startActivity(intent);
            }
        });
    }

    public void postUserData(int postion) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id",famousDoctorBeans.get(postion).getId());
        OkHttpUtils.postJson(ConfigKeys.SELECTCOLLECTADD , linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                SpecialFamousDoctorBean specialFamousDoctorBean = Json.parseObj(response, SpecialFamousDoctorBean.class);
                //二维码弹窗
                initMedCodeDialog(specialFamousDoctorBean);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
    /**
     * 处方二维码对话框
     */
    private void initMedCodeDialog(SpecialFamousDoctorBean specialFamousDoctorBean ) {
        medCodeDialog = DialogUtil.createDialog(context, R.layout.dialog_med_evo);
        medCodeDialog.show();
        View view = DialogUtil.view;
        final ImageView ivCancel = view.findViewById(R.id.iv_cancel_med_dialog);
        ImageView ivEvoCode = view.findViewById(R.id.iv_evo_code);
        Button btnSend = view.findViewById(R.id.btn_send_to_customer);
        Button btnShare = view.findViewById(R.id._btn_share_med_to_wx);
        GlideUtil.load(context,specialFamousDoctorBean.getUrl(),ivEvoCode);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
            }
        });

        //发送给患者
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                Intent intent = new Intent(context, SearchPatientActivity.class);
                intent.putExtra("caseid", specialFamousDoctorBean.getCaseId());
                context.startActivity(intent);
            }
        });
        //分享至微信
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                if (TextUtils.isEmpty(specialFamousDoctorBean.getUrl())) {
                    ToastUtils.show("开方出了点问题，请重新开具");
                } else {
                    JHJKWeChat.getInstance().shareImg(2, specialFamousDoctorBean.getUrl());
                }
            }
        });
    }

    public void setColletData(int postion) {

        OkHttpUtils.get(ConfigKeys.SELECTDOCTORCOLLECT + "?specialId=" + famousDoctorBeans.get(postion).getId(), null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e("收藏操作成功");
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
        return famousDoctorBeans.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        public ImageView picUrl;
        public TextView docName;
        public TextView title;
        public TextView symptom;
        public TextView secrecy;
        public TextView efficacy;
        public TextView indication;
        public TextView formulationName;
        public TextView price;
        public TextView useNum;
        public TextView collect_text;
        public ImageView collect_src;
        public Button use_button;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            picUrl = itemView.findViewById(R.id.picUrl);
            docName = itemView.findViewById(R.id.docName);
            title = itemView.findViewById(R.id.title);
            symptom = itemView.findViewById(R.id.symptom);
            secrecy = itemView.findViewById(R.id.secrecy);
            efficacy = itemView.findViewById(R.id.efficacy);
            indication = itemView.findViewById(R.id.indication);
            formulationName = itemView.findViewById(R.id.formulationName);
            price = itemView.findViewById(R.id.price);
            useNum = itemView.findViewById(R.id.useNum);
            collect_text = itemView.findViewById(R.id.collect_text);
            collect_src = itemView.findViewById(R.id.collect_src);
            use_button = itemView.findViewById(R.id.use_button);
        }
    }
}


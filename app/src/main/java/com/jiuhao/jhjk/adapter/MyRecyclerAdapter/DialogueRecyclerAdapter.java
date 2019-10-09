package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.ReadedBean;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;

import java.util.List;

/**
 * Created by lxn on 2019/9/19.
 */

public class DialogueRecyclerAdapter extends RecyclerView.Adapter<DialogueRecyclerAdapter.MyHolder> {

    private Context context;
    private ReadedBean readedBean;

    public DialogueRecyclerAdapter(Context context, ReadedBean readedBean) {
        this.context = context;
        this.readedBean = readedBean;
    }

    @NonNull
    @Override
    public DialogueRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialogue, viewGroup, false);
        return new DialogueRecyclerAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogueRecyclerAdapter.MyHolder myHolder, int i) {

        List<ReadedBean.DataBean> data = readedBean.getData();
        String contentitle = data.get(i).getContent();//文字内容
        String url = data.get(i).getUrl();//图片内容
        int msgType = data.get(i).getMsgType();//消息类型 1文字 2图片 3问诊单问题 4问诊单答案
        int sendType = data.get(i).getSendType();
        if (sendType == 2) {//2患者
            myHolder.person_rela.setVisibility(View.VISIBLE);
            myHolder.doctor_rela.setVisibility(View.GONE);
            myHolder.sen_lin.setVisibility(View.GONE);
            GlideUtil.loadCircle(context, readedBean.getPicUrl(), myHolder.person_head);
            if(msgType==1){
                myHolder.person_contemn_text.setVisibility(View.VISIBLE);
                myHolder.person_contemn_img.setVisibility(View.GONE);
                myHolder.person_contemn_text.setText(contentitle);
            }else if(msgType==2){
                myHolder.person_contemn_text.setVisibility(View.GONE);
                myHolder.person_contemn_img.setVisibility(View.VISIBLE);
                GlideUtil.load(context,url,myHolder.person_contemn_img);
            }
        } else if (sendType == 1) {//1医生
            myHolder.doctor_rela.setVisibility(View.VISIBLE);
            myHolder.person_rela.setVisibility(View.GONE);
            myHolder.sen_lin.setVisibility(View.GONE);
            String headurl = SPUtils.getString(context, ConfigKeys.AVATAR, "");
            GlideUtil.loadCircle(context,headurl,myHolder.doctor_head);
            if(msgType==1){
                myHolder.doctor_content_text.setVisibility(View.VISIBLE);
                myHolder.doctor_content_img.setVisibility(View.GONE);
                myHolder.doctor_content_text.setText(contentitle);
            }else if(msgType==2){
                myHolder.doctor_content_text.setVisibility(View.GONE);
                myHolder.doctor_content_img.setVisibility(View.VISIBLE);
                GlideUtil.load(context,url,myHolder.doctor_content_img);
            }else if(msgType==3){
                myHolder.doctor_rela.setVisibility(View.GONE);
                myHolder.person_rela.setVisibility(View.GONE);
                myHolder.sen_lin.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public int getItemCount() {
        return readedBean.getData().size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        public RelativeLayout person_rela;
        public ImageView person_head;
        public TextView person_contemn_text;
        public ImageView person_contemn_img;

        public RelativeLayout doctor_rela;
        public ImageView doctor_head;
        public TextView doctor_content_text;
        public ImageView doctor_content_img;

        public LinearLayout sen_lin;
        public TextView send_d;
        public TextView send_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            person_rela = itemView.findViewById(R.id.person_rela);
            person_head = itemView.findViewById(R.id.person_head);
            person_contemn_text = itemView.findViewById(R.id.person_contemn_text);
            person_contemn_img = itemView.findViewById(R.id.person_contemn_img);

            doctor_rela = itemView.findViewById(R.id.doctor_rela);
            doctor_content_text = itemView.findViewById(R.id.doctor_content_text);
            doctor_content_img = itemView.findViewById(R.id.doctor_content_img);
            doctor_head = itemView.findViewById(R.id.doctor_head);

            sen_lin = itemView.findViewById(R.id.sen_lin);
            send_d = itemView.findViewById(R.id.send_d);
            send_name = itemView.findViewById(R.id.send_name);
        }
    }
}


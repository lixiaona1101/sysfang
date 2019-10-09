package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.SelectChatListBean;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by lxn on 2019/9/10.
 */

public class SelectChatListRecyclerAdapter extends RecyclerView.Adapter<SelectChatListRecyclerAdapter.MyHolder> {

    private Context context;
    private List<SelectChatListBean> selectChatListBeans;

    public SelectChatListRecyclerAdapter(Context context, List<SelectChatListBean> selectChatListBeans) {
        this.context = context;
        this.selectChatListBeans = selectChatListBeans;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
       GlideUtil.loadCircle(context,selectChatListBeans.get(i).getAvatar(),myHolder.person_head);
        myHolder.person_name.setText(selectChatListBeans.get(i).getPatientName());
        myHolder.person_conment.setText(selectChatListBeans.get(i).getContent());
        myHolder.person_time.setText(selectChatListBeans.get(i).getTimeStr());
        Logger.e(selectChatListBeans.get(i).getTimeStr());
        int notReadNum = selectChatListBeans.get(i).getNotReadNum();//未读数量
        if(notReadNum==0){
            myHolder.not_read_lin.setVisibility(View.GONE);
        }else {
            myHolder.not_read_lin.setVisibility(View.VISIBLE);
            myHolder.not_read_number.setText(selectChatListBeans.get(i).getNotReadNum()+"");
        }
    }

    @Override
    public int getItemCount() {
        return selectChatListBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public ImageView person_head;
        public TextView person_name;
        public TextView person_conment;
        public TextView person_time;
        public TextView not_read_number;
        public LinearLayout not_read_lin;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            person_head = itemView.findViewById(R.id.person_head);
            person_name = itemView.findViewById(R.id.person_name);
            person_conment = itemView.findViewById(R.id.person_conment);
            person_time = itemView.findViewById(R.id.person_time);
            not_read_number = itemView.findViewById(R.id.not_read_number);
            not_read_lin= itemView.findViewById(R.id.not_read_lin);
        }
    }
}

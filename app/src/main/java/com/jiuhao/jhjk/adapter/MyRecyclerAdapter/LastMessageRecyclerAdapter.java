package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.message.RecipeNotificationActivity;
import com.jiuhao.jhjk.activity.message.SystematicNotificationActivity;
import com.jiuhao.jhjk.bean.LastMessageBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/10.
 */

public class LastMessageRecyclerAdapter extends RecyclerView.Adapter<LastMessageRecyclerAdapter.MyHolder> {

    private Context context;
    private List<LastMessageBean> lastMessageBeans;

    public LastMessageRecyclerAdapter(Context context, List<LastMessageBean> lastMessageBeans) {
        this.context = context;
        this.lastMessageBeans = lastMessageBeans;
    }

    @NonNull
    @Override
    public LastMessageRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notice, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastMessageRecyclerAdapter.MyHolder myHolder, int i) {

        if (lastMessageBeans.get(i).getType() == 1) {//系统通知
            myHolder.notice.setImageResource(R.mipmap.notice);
            myHolder.title_text.setText(lastMessageBeans.get(i).getTitle());
            myHolder.content_text.setText(lastMessageBeans.get(i).getContent());
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SystematicNotificationActivity.class);
                    context.startActivity(intent);
                }
            });
        } else if (lastMessageBeans.get(i).getType() == 2) {//处方通知
            myHolder.notice.setImageResource(R.mipmap.notice1);
            myHolder.title_text.setText(lastMessageBeans.get(i).getTitle());
            myHolder.content_text.setText(lastMessageBeans.get(i).getContent());
            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, RecipeNotificationActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return lastMessageBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public ImageView notice;
        public TextView title_text;
        public TextView content_text;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            notice = itemView.findViewById(R.id.notice);
            title_text = itemView.findViewById(R.id.title_text);
            content_text = itemView.findViewById(R.id.content_text);
        }
    }
}

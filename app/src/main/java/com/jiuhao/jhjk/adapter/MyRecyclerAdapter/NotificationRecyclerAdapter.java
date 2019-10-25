package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.SelectBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/11.
 */

public class NotificationRecyclerAdapter extends RecyclerView.Adapter<NotificationRecyclerAdapter.MyHolder> {

    private Context context;
    private List<SelectBean.DataBean> data;

    public NotificationRecyclerAdapter(Context context, List<SelectBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        String title = data.get(i).getTitle();
        String content = data.get(i).getContent();

        myHolder.notification_type.setText("【" + title + "】");
        myHolder.notification_conment.setText(content);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView time_title;
        public TextView notification_type;
        public TextView notification_conment;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            time_title = itemView.findViewById(R.id.time_title);
            notification_type = itemView.findViewById(R.id.notification_type);
            notification_conment = itemView.findViewById(R.id.notification_conment);
        }
    }
}

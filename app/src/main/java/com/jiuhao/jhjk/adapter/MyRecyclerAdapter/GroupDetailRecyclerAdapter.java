package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.DcGroupBean;

import java.util.List;

/**
 * Created by lxn on 2019/9/9.
 */

public class GroupDetailRecyclerAdapter extends RecyclerView.Adapter<GroupDetailRecyclerAdapter.MyHolder> {

    private Context context;
    private List<DcGroupBean> dcGroupBeans;
    private String groupName;
    private onListen onListen;
    private int flag = -1;

    public GroupDetailRecyclerAdapter(Context context, List<DcGroupBean> dcGroupBeans, String groupName, onListen onListen) {
        this.context = context;
        this.dcGroupBeans = dcGroupBeans;
        this.groupName = groupName;
        this.onListen = onListen;
    }

    @NonNull
    @Override
    public GroupDetailRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_detail, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupDetailRecyclerAdapter.MyHolder myHolder, int i) {

        String name = dcGroupBeans.get(i).getName();
        myHolder.type_name.setText(name);

        myHolder.check_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = i;
                onListen.onClickListen(i);
                notifyDataSetChanged();
            }
        });
        if (groupName.equals(dcGroupBeans.get(i).getName()) && flag == -1) {
            myHolder.check_img.setImageResource(R.mipmap.select);
        } else if (flag == i) {
            myHolder.check_img.setImageResource(R.mipmap.select);
        } else {
            myHolder.check_img.setImageResource(R.mipmap.select1);
        }
    }

    @Override
    public int getItemCount() {
        return dcGroupBeans.size();
    }

    public interface onListen {
        void onClickListen(int postion);
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView type_name;
        public ImageView check_img;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            type_name = itemView.findViewById(R.id.type_name);
            check_img = itemView.findViewById(R.id.check_img);
        }
    }
}

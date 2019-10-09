package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.MessageHisBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lxn on 2019/9/10.
 */

public class MessageHisRecyclerAdapter extends RecyclerView.Adapter<MessageHisRecyclerAdapter.MyHolder> {

    private Context context;
    private List<MessageHisBean> messageHisBeans;

    public MessageHisRecyclerAdapter(Context context, List<MessageHisBean> messageHisBeans) {
        this.context = context;
        this.messageHisBeans = messageHisBeans;
    }

    @NonNull
    @Override
    public MessageHisRecyclerAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHisRecyclerAdapter.MyHolder myHolder, int i) {

        String title = messageHisBeans.get(i).getTitle();
        myHolder.type_name.setText(title);

        String content = messageHisBeans.get(i).getContent();
        myHolder.details.setText(content);

        String createTime = messageHisBeans.get(i).getCreateTime();
        myHolder.time_title.setText(createTime);

        String receiveIds = messageHisBeans.get(i).getReceiveIds();
        String[] split = receiveIds.split(" ");
        List<String> stringList = new ArrayList<String>();
        for (int j = 0; j < split.length; j++) {
            stringList.add(split[j]);
        }
        TagAdapter<String> tagAdapter = new TagAdapter<String>(stringList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {

                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.flow_group,
                        myHolder.flowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        myHolder.flowLayout.setAdapter(tagAdapter);

        //消息重发
        myHolder.again_send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData(messageHisBeans.get(i).getId());
            }
        });
    }

    public void postData(int id) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", id);
        OkHttpUtils.postJson(ConfigKeys.MESSAGERESEND, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("重发成功");
            }

            @Override
            public void onFailure(int code, Exception e) {
                com.orhanobut.logger.Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageHisBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView type_name;
        public TextView time_title;
        public Button again_send_button;
        public TextView details;
        public TagFlowLayout flowLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            type_name = itemView.findViewById(R.id.type_name);
            time_title = itemView.findViewById(R.id.time_title);
            again_send_button = itemView.findViewById(R.id.again_send_button);
            details = itemView.findViewById(R.id.details);
            flowLayout = itemView.findViewById(R.id.flowLayout);
        }
    }
}

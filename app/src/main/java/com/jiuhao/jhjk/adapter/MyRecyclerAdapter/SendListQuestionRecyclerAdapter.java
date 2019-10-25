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
import com.jiuhao.jhjk.bean.BillBean2;

import java.util.List;

/**
 * Created by lxn on 2019/9/20.
 */

public class SendListQuestionRecyclerAdapter extends RecyclerView.Adapter<SendListQuestionRecyclerAdapter.BillHolder> {

    private Context context;
    private List<BillBean2> billListBeans;
    private int flag=-1;
    private instem instem;

    public SendListQuestionRecyclerAdapter(Context context, List<BillBean2> billListBeans,instem instem) {
        this.context = context;
        this.billListBeans = billListBeans;
        this.instem=instem;
    }

    @NonNull
    @Override
    public SendListQuestionRecyclerAdapter.BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_send_listquestion, viewGroup, false);
        return new SendListQuestionRecyclerAdapter.BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendListQuestionRecyclerAdapter.BillHolder billHolder, int i) {

        //问诊单名字
        String namestr = billListBeans.get(i).getName();
        billHolder.name.setText(namestr);

        //问题统计
        billHolder.question_number.setText("共" + billListBeans.get(i).getCount() + "题");

        //选中
        billHolder.redact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=i;
                instem.onclickInstem(i);
                notifyDataSetChanged();
            }
        });
        if(flag==i){
            billHolder.redact.setImageResource(R.mipmap.select);
        }else{
            billHolder.redact.setImageResource(R.mipmap.select1);
        }
    }

    @Override
    public int getItemCount() {
        return billListBeans.size();
    }

    public interface instem{
        void onclickInstem(int i);
    }

    class BillHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView redact;
        public TextView question_number;

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_name);
            redact = itemView.findViewById(R.id.redact);
            question_number = itemView.findViewById(R.id.question_number);
        }
    }
}


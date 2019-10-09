package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.DocpricetimeBean;

import java.util.List;

/**
 * Created by Administrator on 2019/8/31.
 * 咨询费列表
 */

public class ConsultingRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<DocpricetimeBean> docpricetimeBeans;
    private View view1;
    private OneHolder oneHolder;
    private int flag = -1;
    private int pri;
    private int maType;
    private onListen onListen;

    public ConsultingRecyclerAdapter(Context context, List<DocpricetimeBean> docpricetimeBeans,
                                     int price, int markType,onListen onListen) {
        this.context = context;
        this.docpricetimeBeans = docpricetimeBeans;
        this.pri = price;
        this.maType = markType;
        this.onListen = onListen;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view1 = LayoutInflater.from(context).inflate(R.layout.item_consutlting, viewGroup, false);
        return new OneHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        oneHolder = (OneHolder) viewHolder;
        int markType = docpricetimeBeans.get(i).getMarkType();
        if (markType == 1) {
            oneHolder.set_money.setVisibility(View.VISIBLE);
            oneHolder.price.setText("自定义");
        } else if (markType == 0) {
            oneHolder.set_money.setVisibility(View.GONE);
            oneHolder.price.setText("免费");
        } else if (markType == 2) {
            oneHolder.set_money.setVisibility(View.GONE);
            int price = docpricetimeBeans.get(i).getPrice();
            oneHolder.price.setText(price + "元/次");
        }

        oneHolder.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = i;
                onListen.onClick(i);
                notifyDataSetChanged();
            }
        });
        if (maType == docpricetimeBeans.get(i).getMarkType() && flag == -1) {
            if (docpricetimeBeans.get(i).getMarkType() == 1) {
                oneHolder.set_money.setText(pri + "");
            }else if (docpricetimeBeans.get(i).getMarkType() == 2) {
                if(pri==docpricetimeBeans.get(i).getPrice()){
                    oneHolder.type.setImageResource(R.mipmap.select);
                }
            }

        } else if (flag == i) {
            oneHolder.type.setImageResource(R.mipmap.select);
        } else {
            oneHolder.type.setImageResource(R.mipmap.select1);
        }

    }

    @Override
    public int getItemCount() {
        return docpricetimeBeans.size();
    }

    //自定义金额
    public String getEdit() {
        String pice = oneHolder.set_money.getText().toString();
        return pice;
    }

    public interface onListen {
        void onClick(int postion);
    }

    class OneHolder extends RecyclerView.ViewHolder {

        public EditText set_money;
        public TextView price;
        public ImageView type;

        public OneHolder(@NonNull View itemView) {
            super(itemView);

            set_money = itemView.findViewById(R.id.set_money);
            price = itemView.findViewById(R.id.price);
            type = itemView.findViewById(R.id.type);

        }
    }

}

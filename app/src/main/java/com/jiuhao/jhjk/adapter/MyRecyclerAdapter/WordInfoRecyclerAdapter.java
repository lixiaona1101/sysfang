package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.base.RecyclerBaseAdapter;
import com.jiuhao.jhjk.adapter.base.ViewHolder;
import com.jiuhao.jhjk.bean.SelectingBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lxn on 2019/9/24.
 */

public class WordInfoRecyclerAdapter extends RecyclerBaseAdapter<SelectingBean> {

    public HashMap<String, String> herbsNumMap = new HashMap<>();//单个药品的数量

    public WordInfoRecyclerAdapter(@NonNull Context context, @NonNull List<SelectingBean> selectingBeans) {
        super(context, selectingBeans);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_selectind, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, SelectingBean selectingBean, int position) {
        TextView tv_select_name = holder.getView(R.id.tv_select_name);
        EditText et_select_num = holder.getView(R.id.et_select_num);
        RelativeLayout rl_select_delete = holder.getView(R.id.rl_select_delete);

        if (herbsNumMap.containsKey(selectingBean.getName())) {
            et_select_num.setText(herbsNumMap.get(selectingBean.getName()));
        } else {
            et_select_num.setText("");
        }

        tv_select_name.setText(selectingBean.getName());
        et_select_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_select_num.getText().toString();
                if (!s.isEmpty()) {
                    herbsNumMap.put(selectingBean.getName(), s);
                }
            }
        });

        rl_select_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (herbsNumMap.containsKey(selectingBean.getName())) {
                    herbsNumMap.remove(selectingBean.getName());
                }
                removeItem(position);
            }
        });
    }
}
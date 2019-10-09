package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.base.RecyclerBaseAdapter;
import com.jiuhao.jhjk.adapter.base.ViewHolder;

import java.util.List;

/**
 * Created by lxn on 2019/9/3.
 * 添加问题答案列表 多选
 */

public class QuestionerRcyclerAdapter extends RecyclerBaseAdapter<String> {

    public QuestionerRcyclerAdapter(@NonNull Context context, @NonNull List<String> mDataList) {
        super(context, mDataList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_choice, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, String s, int position) {
        EditText answer = holder.getView(R.id.answer);
        ImageView delete = holder.getView(R.id.delete);
        answer.setText(s);
        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getDataList().set(position, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });
    }


}

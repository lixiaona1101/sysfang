package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CompileBillRecycleritemAdapter extends BaseQuickAdapter<String , BaseViewHolder> {

    public CompileBillRecycleritemAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        TextView question_c=helper.getView(R.id.question_c);
        question_c.setText(item);
    }
}

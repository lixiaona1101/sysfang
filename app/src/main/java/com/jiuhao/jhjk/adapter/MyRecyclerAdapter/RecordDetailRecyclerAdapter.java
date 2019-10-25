package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.R;

import java.util.List;

/**
 * Created by lxn on 2019/9/16.
 */

public class RecordDetailRecyclerAdapter extends RecyclerView.Adapter<RecordDetailRecyclerAdapter.MyHolder> {

    private Context context;
    private List<String> data;

    public RecordDetailRecyclerAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_med, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.tv_search_med.setText(data.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv_search_med;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_search_med = itemView.findViewById(R.id.tv_search_med);
        }
    }
}

package com.jiuhao.jhjk.adapter.GridListAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiuhao.jhjk.R;

import java.util.List;

public class GvSelectedAdapter extends BaseAdapter {

    private Context mContext;

    private  List<String> beanList;

    public GvSelectedAdapter(Context mContext, List<String> beanList) {
        this.mContext = mContext;
        this.beanList = beanList;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.tv_med, null);
            holder.textView = convertView.findViewById(R.id.tv_search_med);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(beanList.get(position).toString());

        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}

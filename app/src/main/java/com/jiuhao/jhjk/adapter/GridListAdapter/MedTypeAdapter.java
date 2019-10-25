package com.jiuhao.jhjk.adapter.GridListAdapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.SelectFromulationBean;

import java.util.List;

public class MedTypeAdapter extends BaseAdapter {
    private List<SelectFromulationBean> dataBeanList;

    private Context mContext;

    private int selectPosition = 0;

    public MedTypeAdapter(List<SelectFromulationBean> dataBeanList, Context mContext, int selectPosition) {
        this.dataBeanList = dataBeanList;
        this.mContext = mContext;
        this.selectPosition = selectPosition;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.tv_med_type, null);
            viewHolder.textView = convertView.findViewById(R.id.tv_med_type);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(dataBeanList.get(position).getName());
        if (position == selectPosition) {
            viewHolder.textView.setBackgroundResource(R.drawable.radius_16_green_gray_line);
            viewHolder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        } else {
            viewHolder.textView.setBackgroundResource(R.drawable.radius_16_whit_gray_line);
            viewHolder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.gary_999999));
        }

        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}

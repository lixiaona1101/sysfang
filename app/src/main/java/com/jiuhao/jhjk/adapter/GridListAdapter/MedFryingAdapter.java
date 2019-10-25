package com.jiuhao.jhjk.adapter.GridListAdapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.MedFryingBean;

/**
 * Created by lxn on 2019/9/23.
 */

public class MedFryingAdapter  extends BaseAdapter {

    private MedFryingBean medFryingBean;
    private Context mContext;


    public MedFryingAdapter(MedFryingBean medFryingBean, Context mContext) {
        this.medFryingBean = medFryingBean;
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return medFryingBean.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return medFryingBean.getData().get(position);
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
        viewHolder.textView.setText(medFryingBean.getData().get(position).toString());
        viewHolder.textView.setBackgroundResource(R.drawable.radius_16_whit_gray_line);
        viewHolder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.gary_999999));
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}


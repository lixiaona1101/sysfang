package com.jiuhao.jhjk.adapter.GridListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiuhao.jhjk.R;

import java.util.List;
import java.util.Map;

public class CheckMedTipAdapter extends BaseAdapter {

    private Context mContext;

    private List<Map<String, String>> mapList;

    public CheckMedTipAdapter(Context mContext, List<Map<String, String>> mapList) {
        this.mContext = mContext;
        this.mapList = mapList;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_check_tip, null);
        TextView med1 = convertView.findViewById(R.id.actv_check_med1);
        TextView med2 = convertView.findViewById(R.id.actv_check_med2);
        Map<String, String> map = mapList.get(position);
        for (String key : map.keySet()) {
            med1.setText(key);
            med2.setText(map.get(key));
        }
        return convertView;
    }
}

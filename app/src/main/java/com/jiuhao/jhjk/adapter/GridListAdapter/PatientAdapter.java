package com.jiuhao.jhjk.adapter.GridListAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.FindByNameBean;

import java.util.List;

public class PatientAdapter extends BaseAdapter {

    private Context mContext;

    private List<FindByNameBean> dataBeanList;

    public PatientAdapter(Context mContext, List<FindByNameBean> dataBeanList) {
        this.mContext = mContext;
        this.dataBeanList = dataBeanList;
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
            convertView = View.inflate(mContext, R.layout.item_patient, null);
            viewHolder.imageView = convertView.findViewById(R.id.iv_item_head);
            viewHolder.tvName = convertView.findViewById(R.id.tv_item_user_name);
            viewHolder.tvAge = convertView.findViewById(R.id.tv_item_user_age);
            viewHolder.ivSex = convertView.findViewById(R.id.iv_item_user_sex);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_item_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FindByNameBean findByNameBean = dataBeanList.get(position);
        Glide.with(mContext).load(findByNameBean.getAvatar()).into(viewHolder.imageView);
        viewHolder.tvName.setText(findByNameBean.getNickName());
        String age = "年龄：" + findByNameBean.getAged();
        viewHolder.tvAge.setText(age);
        if (findByNameBean.getSex() == 1) {
            viewHolder.ivSex.setImageResource(R.mipmap.man);
        } else {
            viewHolder.ivSex.setImageResource(R.mipmap.women);
        }
//        viewHolder.tvTime.setText(findByNameBean.getMsgTime() + "首诊");
        return convertView;
    }

    class ViewHolder {
        ImageView imageView, ivSex;
        TextView tvName, tvAge, tvTime;
    }
}

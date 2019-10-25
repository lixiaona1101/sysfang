package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.FindByNameBean;
import com.jiuhao.jhjk.utils.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PatientRecyclerAdapter extends BaseQuickAdapter<FindByNameBean, BaseViewHolder> {

    private instem instem;

    public PatientRecyclerAdapter(int layoutResId, @Nullable List<FindByNameBean> data,instem instem) {
        super(layoutResId, data);
        this.instem=instem;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FindByNameBean item) {
        ImageView imageView = helper.getView(R.id.iv_item_head);
        TextView tvName = helper.getView(R.id.tv_item_user_name);
        TextView tvAge = helper.getView(R.id.tv_item_user_age);
        ImageView ivSex = helper.getView(R.id.iv_item_user_sex);
        TextView tvTime = helper.getView(R.id.tv_item_time);


        GlideUtil.loadCircle(mContext,item.getAvatar(),imageView);
        tvName.setText(item.getUserName());
        String age = "年龄：" + item.getAged();
        tvAge.setText(age);
        if (item.getSex() == 1) {
            ivSex.setImageResource(R.mipmap.man);
        } else {
            ivSex.setImageResource(R.mipmap.women);
        }
//        viewHolder.tvTime.setText(findByNameBean.getMsgTime() + "首诊");

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instem.onClick(item);
            }
        });
    }

    public interface instem{
        void onClick(FindByNameBean findByNameBean);
    }
}

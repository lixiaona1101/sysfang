package com.jiuhao.jhjk.adapter.welcome;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.DepartmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiaona on 2019/8/23.
 */

public class AdministrativeRecycler extends RecyclerView.Adapter<AdministrativeRecycler.AdministrativeViewHolder> {

    private Context context;
    private List<DepartmentBean> list = new ArrayList<>();
    private String departmentnamestr;
    private im_onlisten monlisten;

    private int flag = -1;


    public AdministrativeRecycler(Context context, List<DepartmentBean> mDataList, String departmentnamestr,im_onlisten monlisten) {
        this.context = context;
        this.list = mDataList;
        this.departmentnamestr = departmentnamestr;
        this.monlisten=monlisten;
    }

    @NonNull
    @Override
    public AdministrativeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_administrative, viewGroup, false);
        return new AdministrativeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdministrativeViewHolder administrativeViewHolder, int i) {
        DepartmentBean departmentBean = list.get(i);
        String departmentName = departmentBean.getDepartmentName();
        administrativeViewHolder.subject.setText(departmentName);
        administrativeViewHolder.administrative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = i;
                monlisten.imOnlisten(i);
                notifyDataSetChanged();
            }
        });
        if (departmentnamestr.equals(departmentName) && flag == -1) {
            administrativeViewHolder.administrative.setImageResource(R.mipmap.select);
        } else if (flag == i) {
            administrativeViewHolder.administrative.setImageResource(R.mipmap.select);
        } else {
            administrativeViewHolder.administrative.setImageResource(R.mipmap.select1);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface im_onlisten {
         void imOnlisten(int postion);
    }

    class AdministrativeViewHolder extends RecyclerView.ViewHolder {

        public TextView subject;
        public ImageView administrative;

        @SuppressLint("WrongViewCast")
        public AdministrativeViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.subject);
            administrative = itemView.findViewById(R.id.administrative);
        }
    }
}


package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.GroupIdxBean;
import com.jiuhao.jhjk.dialog.MyDialog;
import com.jiuhao.jhjk.dialog.RemindDialog;
import com.jiuhao.jhjk.utils.CollectionUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lxn on 2019/9/9.
 */

public class MyManagementecyclerAdapter extends RecyclerView.Adapter<MyManagementecyclerAdapter.MyHolder> {

    private FragmentManager supportFragmentManager;
    private Context context;
    private List<GroupIdxBean> groupIdxBeans;

    public MyManagementecyclerAdapter(FragmentManager supportFragmentManager, Context context, List<GroupIdxBean> groupIdxBeans) {
        this.supportFragmentManager = supportFragmentManager;
        this.context = context;
        this.groupIdxBeans = groupIdxBeans;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_management, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        int groupType = groupIdxBeans.get(i).getGroupType();

        if (groupType == 1) {//默认
            myHolder.circle_te.setVisibility(View.GONE);
            myHolder.mo_name.setVisibility(View.VISIBLE);
            myHolder.set_name.setVisibility(View.VISIBLE);
            myHolder.line.setVisibility(View.GONE);
            myHolder.delete_name.setVisibility(View.GONE);
        } else if (groupType == 0) {//其他自己增加的分组
            myHolder.circle_te.setVisibility(View.VISIBLE);
            myHolder.mo_name.setVisibility(View.VISIBLE);
            myHolder.set_name.setVisibility(View.VISIBLE);
            myHolder.line.setVisibility(View.VISIBLE);
            myHolder.delete_name.setVisibility(View.VISIBLE);
        }

        String name = groupIdxBeans.get(i).getName();
        myHolder.mo_name.setText(name);

        //编辑分组名称
        myHolder.set_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog myDialog = new MyDialog("请输入分组名称");
                myDialog.show(supportFragmentManager);
                myDialog.setOnLeftClick(new MyDialog.OnLeftClick() {
                    @Override
                    public void onLeft() {
                        ToastUtils.show("取消");
                    }
                });
                myDialog.setOnRightClick(new MyDialog.OnRightClick() {
                    @Override
                    public void onRight() {
                        String edit = myDialog.getEdit();//问诊单名
                        if (!edit.isEmpty()) {
                            myDialog.dismiss();
                            putData(edit, i, myHolder.mo_name);
                        } else {
                            ToastUtils.show("请输入分组名称！");
                        }
                    }
                });
            }
        });
        //删除分组
        myHolder.delete_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemindDialog remindDialog = new RemindDialog();
                remindDialog.show(supportFragmentManager);
                remindDialog.setOnLeftClick(new RemindDialog.OnLeftClick() {
                    @Override
                    public void onLeft() {
                        ToastUtils.show("取消");
                    }
                });
                remindDialog.setOnRightClick(new RemindDialog.OnRightClick() {
                    @Override
                    public void onRight() {
                        daleteData(i,remindDialog);
                    }
                });
            }
        });
    }

    public void daleteData(int i,RemindDialog remindDialog) {
        //修改分组
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", groupIdxBeans.get(i).getId());
        OkHttpUtils.dele(ConfigKeys.DCGROUP, linkedHashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("删除成功");
                remindDialog.dismiss();
                removeItem(i);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //移除某个位置的数据
    public void removeItem(@IntRange(from = 0) int position) {
        if (CollectionUtils.isEmpty(groupIdxBeans) || position <= -1) {
            return;
        }
        notifyItemRemoved(position);
        groupIdxBeans.remove(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    public void putData(String edit, int i, TextView moName) {
        //修改分组
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", groupIdxBeans.get(i).getId());
        linkedHashMap.put("name", edit);
        OkHttpUtils.putJson(ConfigKeys.DCGROUP, linkedHashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                ToastUtils.show("修改成功");
                moName.setText(edit);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupIdxBeans.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView circle_te;
        public TextView mo_name;
        public TextView set_name;
        public TextView line;
        public TextView delete_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            circle_te = itemView.findViewById(R.id.circle_te);
            mo_name = itemView.findViewById(R.id.mo_name);
            set_name = itemView.findViewById(R.id.set_name);
            line = itemView.findViewById(R.id.line);
            delete_name = itemView.findViewById(R.id.delete_name);

        }
    }
}

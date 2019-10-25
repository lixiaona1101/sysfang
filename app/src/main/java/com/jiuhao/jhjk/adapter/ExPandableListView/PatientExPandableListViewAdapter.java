package com.jiuhao.jhjk.adapter.ExPandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.patient.FathData;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;

/**
 * Created by lxn on 2019/9/2.
 * 患者 分组用户的详情adapter
 */
public class PatientExPandableListViewAdapter extends BaseExpandableListAdapter {

    // 定义一个Context
    private Context context;
    // 定义一个LayoutInflater
    private LayoutInflater mInflater;
    // 定义一个List来保存列表数据
    private ArrayList<FathData> data_list = new ArrayList<>();
    private FragmentManager fragmentManager;

    // 定义一个构造方法
    public PatientExPandableListViewAdapter(Context context, ArrayList<FathData> datas,
                                            FragmentManager fragmentManager) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data_list = datas;
        this.fragmentManager=fragmentManager;
    }

    // 刷新数据
    public void flashData(ArrayList<FathData> datas) {
        this.data_list = datas;
        this.notifyDataSetChanged();
    }

    // 获取二级列表的内容
    @Override
    public Object getChild(int arg0, int arg1) {
        return data_list.get(arg0).getList().get(arg1);
    }

    // 获取二级列表的ID
    @Override
    public long getChildId(int arg0, int arg1) {
        return arg1;
    }

    // 定义二级列表中的数据
    @Override
    public View getChildView(int arg0, int arg1, boolean arg2, View arg3, ViewGroup arg4) {
        // 定义一个二级列表的视图类
        HolderView childrenView;
        if (arg3 == null) {
            childrenView = new HolderView();
            // 获取子视图的布局文件
            arg3 = mInflater.inflate(R.layout.expandablelistview_patient_in, arg4, false);
            childrenView.rela_item=(RelativeLayout)arg3.findViewById(R.id.rela_item);
            childrenView.user_head = (ImageView) arg3.findViewById(R.id.user_head);
            childrenView.user_name = (TextView) arg3.findViewById(R.id.user_name);
            childrenView.user_age = (TextView) arg3.findViewById(R.id.user_age);
            childrenView.user_sex = (TextView) arg3.findViewById(R.id.user_sex);
            childrenView.user_customerMsg = (TextView) arg3.findViewById(R.id.user_customerMsg);
            childrenView.user_customerMsgTime = (TextView) arg3.findViewById(R.id.user_customerMsgTime);
            // 这个函数是用来将holderview设置标签,相当于缓存在view当中
            arg3.setTag(childrenView);
        } else {
            childrenView = (HolderView) arg3.getTag();
        }

        /**
         * 设置相应控件的内容
         */
        // 设置标题上的文本信息
        GlideUtil.loadCircle(context, data_list.get(arg0).getList().get(arg1).getAvatar(), childrenView.user_head);
        childrenView.user_name.setText(data_list.get(arg0).getList().get(arg1).getUserName());
        Logger.e(data_list.get(arg0).getList().get(arg1).getUserName()
                +"的年龄为："+data_list.get(arg0).getList().get(arg1).getAged());
        childrenView.user_age.setText(data_list.get(arg0).getList().get(arg1).getAged());
        childrenView.user_sex.setText(data_list.get(arg0).getList().get(arg1).getSex());
        childrenView.user_customerMsg.setText(data_list.get(arg0).getList().get(arg1).getCustomerMsg());
        childrenView.user_customerMsgTime.setText(data_list.get(arg0).getList().get(arg1).getCustomerMsgTime());

        return arg3;
    }

    // 保存二级列表的视图类
    private class HolderView {
        RelativeLayout rela_item;
        ImageView user_head;
        TextView user_name;
        TextView user_age;
        TextView user_sex;
        TextView user_customerMsg;
        TextView user_customerMsgTime;
    }

    // 获取二级列表的数量
    @Override
    public int getChildrenCount(int arg0) {
        return data_list.get(arg0).getList().size();
    }

    // 获取一级列表的数据
    @Override
    public Object getGroup(int arg0) {
        return data_list.get(arg0);
    }

    // 获取一级列表的个数
    @Override
    public int getGroupCount() {
        return data_list.size();
    }

    // 获取一级列表的ID
    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    // 设置一级列表的view
    @Override
    public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3) {
        HodlerViewFather hodlerViewFather;
        if (arg2 == null) {
            hodlerViewFather = new HodlerViewFather();
            arg2 = mInflater.inflate(R.layout.expandablelistview_patient_out, arg3, false);
            hodlerViewFather.group_name = (TextView) arg2.findViewById(R.id.group_name);
            // 新建一个TextView对象，用来显示一级标签上的大体描述的信息
            hodlerViewFather.group_number = (TextView) arg2.findViewById(R.id.group_number);
            hodlerViewFather.img_jian = (ImageView) arg2.findViewById(R.id.img_jian);
            arg2.setTag(hodlerViewFather);
        } else {
            hodlerViewFather = (HodlerViewFather) arg2.getTag();
        }
        // 一级列表右侧判断箭头显示方向
        if (arg1) {
            hodlerViewFather.img_jian.setImageResource(R.mipmap.open);
        } else {
            hodlerViewFather.img_jian.setImageResource(R.mipmap.close);
        }
        /**
         * 设置相应控件的内容
         */
        // 设置标题上的文本信息
        hodlerViewFather.group_name.setText(data_list.get(arg0).getName());
        hodlerViewFather.group_number.setText("" + data_list.get(arg0).getList().size());

        // 返回一个布局对象
        return arg2;
    }

    // 定义一个 一级列表的view类
    private class HodlerViewFather {
        ImageView img_jian;
        TextView group_name;
        TextView group_number;
    }

    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 当选择子节点的时候，调用该方法(点击二级列表)
     */
    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }
}

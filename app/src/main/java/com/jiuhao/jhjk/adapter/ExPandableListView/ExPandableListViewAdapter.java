package com.jiuhao.jhjk.adapter.ExPandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.mine.ConsultingFee.ChildrenData;
import com.jiuhao.jhjk.activity.mine.ConsultingFee.FatherData;
import com.jiuhao.jhjk.utils.glide.GlideUtil;

import java.util.ArrayList;

/**
 * Created by lxn on 2019/9/2.
 * 我的 设置收费患者adapter
 */
public class ExPandableListViewAdapter extends BaseExpandableListAdapter {

    // 定义一个Context
    private Context context;
    // 定义一个LayoutInflater
    private LayoutInflater mInflater;
    // 定义一个List来保存列表数据
    private ArrayList<FatherData> data_list = new ArrayList<>();
    private erListen erListen;

    // 定义一个构造方法
    public ExPandableListViewAdapter(Context context, ArrayList<FatherData> datas,erListen erListen) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data_list = datas;
        this.erListen=erListen;
    }

    // 刷新数据
    public void flashData(ArrayList<FatherData> datas) {
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
            arg3 = mInflater.inflate(R.layout.expandablelistview_in, arg4, false);
            childrenView.user_head = (ImageView) arg3.findViewById(R.id.user_head);
            childrenView.user_name = (TextView) arg3.findViewById(R.id.user_name);
            childrenView.user_age = (TextView) arg3.findViewById(R.id.user_age);
            childrenView.user_sex = (TextView) arg3.findViewById(R.id.user_sex);
            childrenView.user_check = (ImageView) arg3.findViewById(R.id.user_check);
            // 这个函数是用来将holderview设置标签,相当于缓存在view当中
            arg3.setTag(childrenView);
        } else {
            childrenView = (HolderView) arg3.getTag();
        }
        FatherData fatherData = data_list.get(arg0);
        ArrayList<ChildrenData> childrenDatas = fatherData.getList();
        ChildrenData childrenData = childrenDatas.get(arg1);

        childrenView.user_check.setImageResource(childrenData.isCheck() ? R.mipmap.select : R.mipmap.select1);

        /**
         * 设置相应控件的内容
         */
        // 设置标题上的文本信息
        GlideUtil.loadCircle(context, childrenData.getAvatar(), childrenView.user_head);
        childrenView.user_name.setText(childrenData.getUserName());
        childrenView.user_age.setText(childrenData.getAged());
        childrenView.user_sex.setText(childrenData.getSex());
        childrenView.user_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = !childrenData.isCheck();


                childrenData.setCheck(check);
                childrenView.user_check.setImageResource(check ? R.mipmap.select : R.mipmap.select1);

                //设置选中的个数  选中+1   为选中-1
                int checkCount = fatherData.getCheckCount();
                checkCount = check ? checkCount + 1 : checkCount - 1;
                fatherData.setCheckCount(checkCount);
                notifyDataSetChanged();

                erListen.onerClick(data_list);


                boolean check1 = fatherData.isCheck();
                //如果二级选中的个数 == 二级的总个数  并且   一级为选中状态
                if (checkCount == childrenDatas.size() && !check1) {
                    fatherData.setCheck(true);
                    notifyDataSetChanged();
                }
                //如果二级选中的个数 != 二级的总个数  并且   一级为未选中状态
                else if (checkCount != childrenDatas.size() && check1) {
                    fatherData.setCheck(false);
                    notifyDataSetChanged();
                }
            }
        });
        return arg3;
    }

    // 保存二级列表的视图类
    private class HolderView {
        ImageView user_head;
        TextView user_name;
        TextView user_age;
        TextView user_sex;
        ImageView user_check;
    }

    // 获取二级列表的数量
    @Override
    public int getChildrenCount(int arg0) {
        return data_list.get(arg0).getList().size();
    }

    public interface erListen{
        void onerClick(ArrayList<FatherData> data_list);
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
            arg2 = mInflater.inflate(R.layout.expandablelistview_out, arg3, false);
            hodlerViewFather.group_name = (TextView) arg2.findViewById(R.id.group_name);
            // 新建一个TextView对象，用来显示一级标签上的大体描述的信息
            hodlerViewFather.group_now_number = (TextView) arg2.findViewById(R.id.group_now_number);
            hodlerViewFather.group_number = (TextView) arg2.findViewById(R.id.group_number);
            hodlerViewFather.group_img = (ImageView) arg2.findViewById(R.id.group_img);
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
        FatherData fatherData = data_list.get(arg0);
        ArrayList<ChildrenData> childrenDatas = fatherData.getList();

        hodlerViewFather.group_img.setImageResource(fatherData.isCheck() ? R.mipmap.select : R.mipmap.select1);

        //选中实时个数
        hodlerViewFather.group_now_number.setText(fatherData.getCheckCount() + "");
        // 设置标题上的文本信息
        hodlerViewFather.group_name.setText(fatherData.getName());
        hodlerViewFather.group_number.setText("/" + childrenDatas.size() + "");
        hodlerViewFather.group_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = !fatherData.isCheck();
                //设置一级选中状态
                fatherData.setCheck(check);
                hodlerViewFather.group_img.setImageResource(check ? R.mipmap.select : R.mipmap.select1);

                //更改二级选中状态
                for (ChildrenData childrenData : childrenDatas)
                    childrenData.setCheck(check);

                //设置选中的个数
                fatherData.setCheckCount(check ? childrenDatas.size() : 0);

                erListen.onerClick(data_list);
                //刷新适配器
                notifyDataSetChanged();
            }
        });
        // 返回一个布局对象
        return arg2;
    }

    // 定义一个 一级列表的view类
    private class HodlerViewFather {
        ImageView img_jian;
        TextView group_name;
        TextView group_now_number;
        TextView group_number;
        ImageView group_img;
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

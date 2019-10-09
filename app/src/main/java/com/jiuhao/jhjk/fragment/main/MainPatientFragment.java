package com.jiuhao.jhjk.fragment.main;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.patient.ChildData;
import com.jiuhao.jhjk.activity.patient.FathData;
import com.jiuhao.jhjk.activity.patient.InviteCustomerActivity;
import com.jiuhao.jhjk.activity.patient.ManagementActivity;
import com.jiuhao.jhjk.activity.patient.MassTextingOneActivity;
import com.jiuhao.jhjk.activity.patient.PersonDetailsActivity;
import com.jiuhao.jhjk.adapter.ExPandableListView.PatientExPandableListViewAdapter;
import com.jiuhao.jhjk.bean.DcGroupBean;
import com.jiuhao.jhjk.bean.IndexBean;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者主fragment
 */
public class MainPatientFragment extends BaseFragment {


    /**
     * 输入姓名搜索患者
     */
    private EditText editPatient;
    /**
     * 邀请患者
     */
    private TextView welcomeOne;
    /**
     * 群发消息
     */
    private TextView massTextingTwo;
    /**
     * 分组管理
     */
    private TextView groupingThree;
    private ExpandableListView alarmClockExpandablelist;
    private ArrayList<FathData> datas;
    private PatientExPandableListViewAdapter adapter;
    private List<DcGroupBean> dcGroupBeans;
    private List<List<IndexBean>> lists = new ArrayList<>();
    private int f = 0;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    for (int i = 0; i < dcGroupBeans.size(); i++) {
                        getInData(dcGroupBeans.get(i).getId(), dcGroupBeans.get(i).getCount(), dcGroupBeans.get(i).getName());
                    }

                    break;
                case 1:
                    setData();
                    setAdapter();
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_patient;
    }

    @Override
    protected void initView() {

        editPatient = (EditText) findViewById(R.id.edit_patient);
        welcomeOne = (TextView) findViewById(R.id.welcome_one);
        massTextingTwo = (TextView) findViewById(R.id.mass_texting_two);
        groupingThree = (TextView) findViewById(R.id.grouping_three);
        alarmClockExpandablelist = (ExpandableListView) findViewById(R.id.alarm_clock_expandablelist);
    }

    @Override
    protected void initData() {
        getOutData();
    }

    @Override
    protected void setListener() {
        //邀请患者
        welcomeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InviteCustomerActivity.class));
            }
        });
        //群发消息
        massTextingTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MassTextingOneActivity.class);
                startActivity(intent);
            }
        });
        // 设置ExpandableListView的监听事件
        // 设置一级item点击的监听器
        alarmClockExpandablelist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                ToastUtils.show(datas.get(i).getName());
                return false;
            }
        });

        // 设置二级item点击的监听器，同时在Adapter中设置isChildSelectable返回值true，同时二级列表布局中控件不能设置点击效果
        alarmClockExpandablelist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String groupName = datas.get(i).getName();//分组名称
                IndexBean indexBean = lists.get(i).get(i1);
                Intent intent = new Intent(getContext(), PersonDetailsActivity.class);
                intent.putExtra("GroupName", groupName);//分组名称
                intent.putExtra("indexBean", indexBean);//用户详情bean
                startActivity(intent);
                return false;
            }
        });

        //分组管理
        groupingThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), ManagementActivity.class);
                startActivity(intent1);
            }
        });
    }

    // 定义数据
    private void setData() {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        // 一级列表中的数据
        for (int i = 0; i < dcGroupBeans.size(); i++) {
            FathData fathData = new FathData();
            fathData.setName(dcGroupBeans.get(i).getName());
            fathData.setCount(dcGroupBeans.get(i).getCount());
            // 二级列表中的数据
            ArrayList<ChildData> itemList = new ArrayList<>();
            for (int j = 0; j < lists.get(i).size(); j++) {
                ChildData childData = new ChildData();
                childData.setAvatar(lists.get(i).get(j).getAvatar());
                childData.setUserName(lists.get(i).get(j).getNickName());
                childData.setCustomerMsg(lists.get(i).get(j).getCustomerMsg());
                childData.setCustomerMsgTime(lists.get(i).get(j).getCustomerMsgTime());
                String aged = lists.get(i).get(j).getAged();
                if (!aged.contains("不详")) {
                    childData.setAged(lists.get(i).get(j).getAged() + "岁");
                } else {
                    childData.setAged(lists.get(i).get(j).getAged());
                }
                int sex = lists.get(i).get(j).getSex();//0未知 1男 2女
                if (sex == 0) {
                    childData.setSex("");
                } else if (sex == 1) {
                    childData.setSex("男");
                } else if (sex == 2) {
                    childData.setSex("女");
                }
                itemList.add(childData);
            }
            fathData.setList(itemList);
            datas.add(fathData);
        }
    }

    /**
     * 自定义setAdapter
     */
    private void setAdapter() {
        if (adapter == null) {
            adapter = new PatientExPandableListViewAdapter(getContext(), datas);
            alarmClockExpandablelist.setAdapter(adapter);
        } else {
            adapter.flashData(datas);
        }
    }


    public void getOutData() {
        OkHttpUtils.get(ConfigKeys.DCGROUP, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                dcGroupBeans = Json.parseArr(response, DcGroupBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
            }
        });
    }

    public void getInData(int id, int count, String name) {
        String url = ConfigKeys.INDEX + "?groupId=" + id;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
//                Logger.e("分组名称---"+name+"个数"+"---"+count+"---"+response);
                List<IndexBean> indexBeans = Json.parseArr(response, IndexBean.class);
                lists.add(indexBeans);
//                for (int i = 0; i < lists.size(); i++) {
//                    List<IndexBean> indexBeans1 = lists.get(i);
//                    for (int j = 0; j < indexBeans1.size(); j++) {
//                        Logger.e("i:" + i + "---j:" + j + "---分组名称:" + name + "---个数:" + count + "---name:" + indexBeans1.get(j).getNickName());
//                    }
//                }
                f++;
                if (f == dcGroupBeans.size()) {
                    handler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
            }
        });
    }


}

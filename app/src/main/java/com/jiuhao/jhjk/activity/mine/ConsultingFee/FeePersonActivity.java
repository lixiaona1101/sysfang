package com.jiuhao.jhjk.activity.mine.ConsultingFee;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.ExPandableListViewAdapter;
import com.jiuhao.jhjk.bean.DcGroupBean;
import com.jiuhao.jhjk.bean.IndexBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置收费患者
 */
public class FeePersonActivity extends BaseActivity {

    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 确认
     */
    private TextView tvTitleSure;
    private RelativeLayout rlTitleSure;
    private RelativeLayout rlTitle;
    /**
     * 0
     */
    private TextView allNowNumber;
    /**
     * /120
     */
    private TextView allNumber;
    private ImageView allImg;
    private ExpandableListView alarmClockExpandablelist;
    private ArrayList<FatherData> datas;
    private ExPandableListViewAdapter adapter;
    private List<DcGroupBean> dcGroupBeans;
    private List<List<IndexBean>> lists = new ArrayList<>();
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    setAdapter();
                    setData();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_fee_person);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        allNowNumber = (TextView) findViewById(R.id.all_now_number);
        allNumber = (TextView) findViewById(R.id.all_number);
        allImg = (ImageView) findViewById(R.id.all_img);
        alarmClockExpandablelist = (ExpandableListView) findViewById(R.id.alarm_clock_expandablelist);
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitle.setText("选择患者");
    }

    @Override
    protected void obtainData() {
        getOutData();
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                ToastUtils.show(datas.get(i).getList().get(i1).getUserName());
                return false;
            }
        });
    }

    /**
     * 自定义setAdapter
     */
    private void setAdapter() {
        if (adapter == null) {
            adapter = new ExPandableListViewAdapter(this, datas);
            alarmClockExpandablelist.setAdapter(adapter);
        } else {
            adapter.flashData(datas);
        }
    }

    // 定义数据
    private void setData() {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        // 一级列表中的数据
        for (int i = 0; i < dcGroupBeans.size(); i++) {
            FatherData fatherData = new FatherData();
            fatherData.setName(dcGroupBeans.get(i).getName());
            fatherData.setCount(dcGroupBeans.get(i).getCount());
            // 二级列表中的数据
            ArrayList<ChildrenData> itemList = new ArrayList<>();
            for (int j = 0; j < lists.get(i).size(); j++) {
                ChildrenData childrenData = new ChildrenData();
                childrenData.setAvatar(lists.get(i).get(j).getAvatar());
                childrenData.setUserName(lists.get(i).get(j).getUserName());
                childrenData.setAged(lists.get(i).get(j).getAged() + "岁");
                int sex = lists.get(i).get(j).getSex();//0未知 1男 2女
                if (sex == 0) {
                    childrenData.setSex("");
                } else if (sex == 1) {
                    childrenData.setSex("男");
                } else if (sex == 2) {
                    childrenData.setSex("女");
                }
                itemList.add(childrenData);
            }
            fatherData.setList(itemList);
            datas.add(fatherData);
        }
    }

    public void getOutData() {
        OkHttpUtils.get(ConfigKeys.DCGROUP, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                dcGroupBeans = Json.parseArr(response, DcGroupBean.class);
                Logger.e(dcGroupBeans.toString());
                for (int i = 0; i < dcGroupBeans.size(); i++) {
                    getInData(dcGroupBeans.get(i).getId());
                }
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
            }
        });
    }

    public void getInData(int id) {
        String url = ConfigKeys.INDEX + "?groupId=" + id;
        Logger.e(id + "");
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                List<IndexBean> indexBeans = Json.parseArr(response, IndexBean.class);
                Logger.e(indexBeans.toString());
                lists.add(indexBeans);

                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
            }
        });
    }

}

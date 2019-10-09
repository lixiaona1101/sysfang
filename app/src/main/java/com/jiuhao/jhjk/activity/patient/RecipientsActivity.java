package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
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
import com.jiuhao.jhjk.activity.mine.ConsultingFee.ChildrenData;
import com.jiuhao.jhjk.activity.mine.ConsultingFee.FatherData;
import com.jiuhao.jhjk.adapter.ExPandableListView.ExPandableListViewAdapter;
import com.jiuhao.jhjk.bean.DcGroupBean;
import com.jiuhao.jhjk.bean.IndexBean;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 群发消息-收件人
 */
public class RecipientsActivity extends BaseActivity {

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
    private int f = 0;
    private Intent intent;
    private boolean check = false;//全选状态
    private int sum = 0;//总个数
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

    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);
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

        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer stringBufferGroupId = new StringBuffer();//分组id串
                StringBuffer stringBufferGroupName = new StringBuffer();//分组name串

                StringBuffer stringBufferId = new StringBuffer();//患者id串
                StringBuffer stringBufferName = new StringBuffer();//患者name串
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).getCheckCount() == datas.get(i).getList().size()) {
                        int a = datas.get(i).getId();
                        String aa = a + ",";
                        stringBufferGroupId.append(aa);

                        String name = datas.get(i).getName();
                        String names = name + ",";
                        stringBufferGroupName.append(names);
                    } else {
                        for (int j = 0; j < datas.get(i).getList().size(); j++) {
                            if (datas.get(i).getList().get(j).isCheck()) {
                                int id = datas.get(i).getList().get(j).getId();
                                String s = id + ",";
                                stringBufferId.append(s);

                                String name = datas.get(i).getList().get(j).getUserName();
                                String names = name + ",";
                                stringBufferName.append(names);
                            }
                        }
                    }
                }
                //分组idstr
                String clinicGroupId = "";
                //分组namestr
                String clinicGroupName = "";
                //患者idstr
                String clinicId = "";
                //患者namestr
                String clinicName = "";
                if (stringBufferGroupId.length() != 0) {
                    clinicGroupId = stringBufferGroupId.delete((stringBufferGroupId.length() - 1), stringBufferGroupId.length()).toString();
                }

                if (stringBufferGroupName.length() != 0) {
                    clinicGroupName = stringBufferGroupName.delete((stringBufferGroupName.length() - 1), stringBufferGroupName.length()).toString();
                }

                if (stringBufferId.length() != 0) {
                    clinicId = stringBufferId.delete((stringBufferId.length() - 1), stringBufferId.length()).toString();
                }

                if (stringBufferName.length() != 0) {
                    clinicName = stringBufferName.delete((stringBufferName.length() - 1), stringBufferName.length()).toString();
                }

                Logger.e(clinicGroupId);
                Logger.e(clinicGroupName);
                Logger.e(clinicId);
                Logger.e(clinicName);
                intent.putExtra("clinicGroupId", clinicGroupId);
                intent.putExtra("clinicGroupName", clinicGroupName);
                intent.putExtra("clinicId", clinicId);
                intent.putExtra("clinicName", clinicName);
                setResult(234, intent);
                finish();
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
            FatherData fatherData = new FatherData();
            fatherData.setName(dcGroupBeans.get(i).getName());
            fatherData.setCount(dcGroupBeans.get(i).getCount());
            fatherData.setId(dcGroupBeans.get(i).getId());
            // 二级列表中的数据
            ArrayList<ChildrenData> itemList = new ArrayList<>();
            for (int j = 0; j < lists.get(i).size(); j++) {
                ChildrenData childrenData = new ChildrenData();
                childrenData.setAvatar(lists.get(i).get(j).getAvatar());
                childrenData.setUserName(lists.get(i).get(j).getNickName());
                childrenData.setId(lists.get(i).get(j).getId());
                String aged = lists.get(i).get(j).getAged();
                if (!aged.contains("不详")) {
                    childrenData.setAged(lists.get(i).get(j).getAged() + "岁");
                } else {
                    childrenData.setAged(lists.get(i).get(j).getAged());
                }
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

        //总个数设置
        for (int i = 0; i < lists.size(); i++) {
            int size = lists.get(i).size();
            sum += size;
        }
        allNumber.setText("/" + sum);

        //全选 未全选
        allImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;

                allNowNumber.setText(check ? sum + "" : "0");

                allImg.setImageResource(check ? R.mipmap.select : R.mipmap.select1);

                for (int i = 0; i < datas.size(); i++) {
                    datas.get(i).setCheck(check);
                    datas.get(i).setCheckCount(check ? datas.get(i).getList().size() : 0);
                    for (int j = 0; j < datas.get(i).getList().size(); j++) {
                        datas.get(i).getList().get(j).setCheck(check);
                    }
                }
                adapter.notifyDataSetChanged();

            }
        });

    }

    /**
     * 自定义setAdapter
     */

    private void setAdapter() {
        if (adapter == null) {
            adapter = new ExPandableListViewAdapter(this, datas, new ExPandableListViewAdapter.erListen() {
                @Override
                public void onerClick(ArrayList<FatherData> data_list) {
                    int s=0;
                    for (int i = 0; i < data_list.size(); i++) {
                        int checkCount = data_list.get(i).getCheckCount();
                        s += checkCount;
                    }
                    allNowNumber.setText(s + "");
                    allImg.setImageResource(s == sum ? R.mipmap.select : R.mipmap.select1);
                }
            });
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
                for (int i = 0; i < lists.size(); i++) {
                    List<IndexBean> indexBeans1 = lists.get(i);
                    for (int j = 0; j < indexBeans1.size(); j++) {
                        Logger.e("i:" + i + "---j:" + j + "---分组名称:" + name + "---个数:" + count + "---name:" + indexBeans1.get(j).getNickName());
                    }
                }
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


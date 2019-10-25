package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.GroupDetailRecyclerAdapter;
import com.jiuhao.jhjk.bean.DcGroupBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 选择分组
 */
public class GroupDetailActivity extends BaseActivity {


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
    private RecyclerView recycler;
    private Intent intent;
    private List<DcGroupBean> dcGroupBeans;
    private GroupDetailRecyclerAdapter groupDetailRecyclerAdapter;
    private String groupName;
    private int id;
    private DcGroupBean dcGroupBean;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    groupDetailRecyclerAdapter = new GroupDetailRecyclerAdapter(getContext(), dcGroupBeans, groupName, new GroupDetailRecyclerAdapter.onListen() {
                        @Override
                        public void onClickListen(int postion) {
                            dcGroupBean = dcGroupBeans.get(postion);
                        }
                    });
                    recycler.setAdapter(groupDetailRecyclerAdapter);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_group_detail);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        tvTitle.setText("选择分组");
        rlTitleSure.setVisibility(View.VISIBLE);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void obtainData() {
         intent = getIntent();
        groupName = intent.getStringExtra("groupName");
        id = intent.getIntExtra("id",0);
        getData();
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //更改分组
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putData();
            }
        });
    }

    public void getData() {
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

    public void putData() {
        /**
         *  "groupId": 3,
         "customerId": 100000048
         */
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("groupId",dcGroupBean.getId());
        linkedHashMap.put("customerId",id);
        OkHttpUtils.putJson(ConfigKeys.DCGROUPDETAIL, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("更改分组成功！");
                intent.putExtra("gname",dcGroupBean.getName());
                setResult(102);
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

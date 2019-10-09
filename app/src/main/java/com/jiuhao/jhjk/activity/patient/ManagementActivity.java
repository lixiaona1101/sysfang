package com.jiuhao.jhjk.activity.patient;

import android.os.Handler;
import android.os.Message;
import android.support.constraint.Group;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.MyManagementecyclerAdapter;
import com.jiuhao.jhjk.bean.GroupIdxBean;
import com.jiuhao.jhjk.dialog.MyDialog;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 分组管理
 */
public class ManagementActivity extends BaseActivity {

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
    private RecyclerView managementRecycler;
    private List<GroupIdxBean> groupIdxBeans;
    private MyManagementecyclerAdapter myManagementecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    myManagementecyclerAdapter = new MyManagementecyclerAdapter(getSupportFragmentManager(),getContext(), groupIdxBeans);
                    managementRecycler.setAdapter(myManagementecyclerAdapter);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_management);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        managementRecycler = (RecyclerView) findViewById(R.id.management_recycler);
        tvTitle.setText("分组管理");
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("添加分组");
        managementRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void obtainData() {
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
        //新增分组
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog myDialog = new MyDialog("请输入新的分组名称");
                myDialog.show(getSupportFragmentManager());
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
                            postData(edit);
                        } else {
                            ToastUtils.show("请输入新的分组名称！");
                        }
                    }
                });
            }
        });
    }

    public void postData(String name) {

        /**
         * "name": "不韵不育",
         "remark": "妙手回春"
         */
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name",name);
        linkedHashMap.put("remark","");
        OkHttpUtils.postJson(ConfigKeys.DCGROUP, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("创建成功");
                getData();
//                GroupIdxBean groupIdxBean = new GroupIdxBean();
//                groupIdxBean.setName(name);
//                myManagementecyclerAdapter.insertItem(groupIdxBean);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void getData() {

        OkHttpUtils.get(ConfigKeys.DCGROUPIDX, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                groupIdxBeans = Json.parseArr(response, GroupIdxBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

}

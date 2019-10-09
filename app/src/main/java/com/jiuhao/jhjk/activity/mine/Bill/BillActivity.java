package com.jiuhao.jhjk.activity.mine.Bill;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.BillRecyclerAdapter;
import com.jiuhao.jhjk.bean.BillBean2;
import com.jiuhao.jhjk.dialog.MyDialog;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 问诊单
 */
public class BillActivity extends BaseActivity {

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
    private AlertDialog.Builder builder;
    private LinearLayout linGone;
    /**
     * 我的问诊单(1)
     */
    private TextView billNumber;
    private RecyclerView billRecycler;
    private LinearLayout linVisible;

    private List<BillBean2> billListBeans;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (billListBeans.size() == 0) {
                        linGone.setVisibility(View.VISIBLE);
                        linVisible.setVisibility(View.GONE);
                    } else {
                        linGone.setVisibility(View.GONE);
                        linVisible.setVisibility(View.VISIBLE);
                        billNumber.setText("我的问诊单(" + billListBeans.size() + ")");
                        //recycler适配
                        BillRecyclerAdapter billRecycleradpterAdapter = new BillRecyclerAdapter(getContext(), billListBeans);
                        billRecycler.setAdapter(billRecycleradpterAdapter);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_bill);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        linGone = (LinearLayout) findViewById(R.id.lin_gone);
        linVisible = (LinearLayout) findViewById(R.id.lin_visible);
        billNumber = (TextView) findViewById(R.id.bill_number);
        billRecycler = (RecyclerView) findViewById(R.id.bill_recycler);
        billRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        tvTitle.setText("问诊单");
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("新建");
    }

    @Override
    protected void obtainData() {
        getBillData();
    }

    public void getBillData() {
        OkHttpUtils.get(ConfigKeys.INTERROGATION, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(code + "" + response);
                billListBeans = Json.parseArr(response, BillBean2.class);
                Logger.e(billListBeans.toString());
                handler.sendEmptyMessage(0);

            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(code + "" + e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //新建问题
        rlTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog myDialog = new MyDialog("请输入问诊单名称");

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
                            //新建问诊单
                            newBillData(edit);

                        } else {
                            ToastUtils.show("请输入问诊单名称！");
                        }
                    }
                });
            }
        });
    }

    public void newBillData(String edit) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name", edit);
        OkHttpUtils.postJson(ConfigKeys.INTERROGATION, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("新建成功");
                getBillData();
                int id = billListBeans.get(billListBeans.size() - 1).getId();
                Intent intent = new Intent(getContext(), Bill2Activity.class);
                intent.putExtra("title", edit);
                intent.putExtra("id", id);
                startActivity(intent);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }


}

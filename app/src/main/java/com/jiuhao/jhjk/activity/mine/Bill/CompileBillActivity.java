package com.jiuhao.jhjk.activity.mine.Bill;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.CompileBillRecyclerAdapter;
import com.jiuhao.jhjk.bean.IgQuestionBean;
import com.jiuhao.jhjk.dialog.MyDialog;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 编辑问诊单
 */
public class CompileBillActivity extends BaseActivity implements View.OnClickListener {


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
     * 中医科
     */
    private TextView billName;
    /**
     * 编辑
     */
    private TextView compile;
    private RecyclerView recycler;
    /**
     * 问题库导入
     */
    private Button buttonOne;
    /**
     * 新建问题
     */
    private Button buttonTwo;
    private int id;
    private List<IgQuestionBean> igQuestionBeans;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (igQuestionBeans.size() != 0) {
                        CompileBillRecyclerAdapter compileBillRecyclerAdapter =
                                new CompileBillRecyclerAdapter(getContext(), igQuestionBeans,
                                        new CompileBillRecyclerAdapter.deleListen() {
                                            @Override
                                            public void onDele(int i) {
                                                deleData(igQuestionBeans.get(i).getId());
                                            }
                                        });
                        recycler.setAdapter(compileBillRecyclerAdapter);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_compile_bill);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        billName = (TextView) findViewById(R.id.bill_name);
        compile = (TextView) findViewById(R.id.compile);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        buttonOne = (Button) findViewById(R.id.button_one);
        buttonOne.setOnClickListener(this);
        buttonTwo = (Button) findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(this);
        tvTitle.setText("问诊单");
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("保存");
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);//问诊单id
        String title = intent.getStringExtra("title");//问诊单标题
        billName.setText(title);
        getData();
    }

    public void getData() {
        String url = ConfigKeys.IGQUESTION + "?igId=" + id;

        //按照问诊单id查询问诊问题
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                igQuestionBeans = Json.parseArr(response, IgQuestionBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {
        //编辑问诊单名字
        compile.setOnClickListener(new View.OnClickListener() {
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
                            billName.setText(edit);

                        } else {
                            ToastUtils.show("请输入问诊单名称！");
                        }
                    }
                });
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //保存问诊单
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putData();
            }
        });
    }

    public void putData() {
        //更新顺序

        //igId 问诊单ID
        String url = ConfigKeys.ORDERBY + "?igId=" + id;
        //igIds 问题ID
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < igQuestionBeans.size(); i++) {
            int id = igQuestionBeans.get(i).getId();
            stringBuffer.append(id + ",");
        }
        String igIds = stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length()).toString();
        Logger.e(igIds);

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("igIds", igIds);
        OkHttpUtils.putJson(url, linkedHashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("保存成功");
                //回传更新
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //问题删除
    public void deleData(int i){

        String url = ConfigKeys.IGQUESTION + "?id=" + i;
        OkHttpUtils.dele(url, null, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("删除成功");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button_one://问题库导入
                Intent intent1 = new Intent(getContext(), ListQuestionActivity.class);
                intent1.putExtra("id", id);
                startActivityForResult(intent1, 11);
                break;
            case R.id.button_two://新建问题
                Intent intent = new Intent(getContext(), QuestionActivity.class);
                intent.putExtra("id", id);
                startActivityForResult(intent, 101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102) {
            getData();
        } else if (requestCode == 11 && resultCode == 22) {
            getData();
        }
    }
}

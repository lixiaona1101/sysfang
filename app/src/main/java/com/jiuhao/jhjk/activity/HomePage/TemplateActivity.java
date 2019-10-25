package com.jiuhao.jhjk.activity.HomePage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.TemplateRecyclerAdapter;
import com.jiuhao.jhjk.bean.TemplateBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.EditTextWithDel;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 处方模板
 */
public class TemplateActivity extends BaseActivity implements View.OnClickListener, OnLoadMoreListener, OnRefreshListener {


    private ImageView ivSearchBack;
    /**
     * 输入关键字搜索处方
     */
    private EditTextWithDel etSearchTitle;
    private RecyclerView rvTemp;
    private ImageView ivNoTemp;
    private SmartRefreshLayout srlTemp;
    /**
     * 添加模板
     */
    private Button addButLin;
    /**
     * 记录添加
     */
    private TextView tvAddCaseRecord;
    /**
     * 新建
     */
    private TextView tvAddNewModel;
    private LinearLayout la_lin;
    private ImageView upImg;
    private boolean check = false;
    private int page1 = 0;// 全部查询
    private int page2 = 0;// 关键字查询
    private String keyword = "";//关键字
    private int flag = 1;// 1全部查询 2 关键字查询
    private List<TemplateBean> templateBeans;
    private TemplateRecyclerAdapter templateRecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (templateBeans.size() != 0 && templateBeans != null) {
                        rvTemp.setVisibility(View.VISIBLE);
                        ivNoTemp.setVisibility(View.GONE);
                        templateRecyclerAdapter = new TemplateRecyclerAdapter(getSupportFragmentManager(), getContext(), templateBeans);
                        rvTemp.setAdapter(templateRecyclerAdapter);
                    } else {
                        rvTemp.setVisibility(View.GONE);
                        ivNoTemp.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_template);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {
        ivSearchBack = (ImageView) findViewById(R.id.iv_search_back);
        etSearchTitle = (EditTextWithDel) findViewById(R.id.et_search_title);
        rvTemp = (RecyclerView) findViewById(R.id.rv_temp);
        rvTemp.setLayoutManager(new LinearLayoutManager(getContext()));
        ivNoTemp = (ImageView) findViewById(R.id.iv_no_temp);
        srlTemp = (SmartRefreshLayout) findViewById(R.id.srl_temp);
        addButLin = (Button) findViewById(R.id.add_but_lin);
        la_lin = (LinearLayout) findViewById(R.id.la_lin);
        addButLin.setOnClickListener(this);
        tvAddCaseRecord = (TextView) findViewById(R.id.tv_add_case_record);
        tvAddNewModel = (TextView) findViewById(R.id.tv_add_new_model);
        upImg = (ImageView) findViewById(R.id.up_img);
        srlTemp.setOnLoadMoreListener(this);
        srlTemp.setOnRefreshListener(this);
    }

    @Override
    protected void obtainData() {
        page1 = 0;
        flag = 1;
        getTemplateData(page1, flag, "");
    }

    @Override
    protected void initEvent() {
        //返回
        ivSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //关键字搜索
        etSearchTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = s.toString();
                Logger.e(keyword);
                page2 = 0;
                flag = 2;
                getTemplateData(page2, flag, keyword);
            }
        });
        etSearchTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        //添加模板
        addButLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;
                if (check) {
                    la_lin.setVisibility(View.VISIBLE);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(la_lin, "alpha", 0, 1)
                    );
                    animatorSet.setDuration(300).start();
                } else {
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(
                            ObjectAnimator.ofFloat(la_lin, "alpha", 1, 0)
                    );
                    animatorSet.setDuration(300).start();

                }
            }
        });
        //上点
        upImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = false;
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(
                        ObjectAnimator.ofFloat(la_lin, "alpha", 1, 0)
                );
                animatorSet.setDuration(300).start();
            }
        });
        //记录添加
        tvAddCaseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),TemplateRecordActivity.class);
                startActivityForResult(intent,100);
            }
        });
        //新建模板
        tvAddNewModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),MainEvoActivity.class);
                intent.putExtra("orinType",1);
                startActivityForResult(intent,1010);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==1000){
            getTemplateData(page1,1,"");
        }else if(requestCode==1010 &&  resultCode==2020) {
            getTemplateData(page1,1,"");
        }
    }

    public void getTemplateData(int page, int flag, String keyword) {

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        Logger.e(flag + "********" + keyword);
        linkedHashMap.put("page", page);
        linkedHashMap.put("limit", 5);
        linkedHashMap.put("symptom", keyword);

        OkHttpUtils.postJson(ConfigKeys.CASETEMPLATE, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                templateBeans = Json.parseArr(response, TemplateBean.class);
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
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.add_but_lin:
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (flag == 1) {
            page1++;
            getTemplateData(page1, 1, "");
        } else if (flag == 2) {
            page2++;
            getTemplateData(page2, 2, keyword);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (flag == 1) {
            page1 = 0;
            getTemplateData(page1, 1, "");
        } else if (flag == 2) {
            page2 = 0;
            getTemplateData(page2, 2, keyword);
        }
    }
}

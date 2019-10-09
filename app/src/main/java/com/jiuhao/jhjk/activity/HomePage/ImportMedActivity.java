package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.ImportMedRecyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.PromiseCaseRecyclerAdapter;
import com.jiuhao.jhjk.bean.CaseHistoryBean;
import com.jiuhao.jhjk.bean.SelectByIdBean;
import com.jiuhao.jhjk.bean.SelectPromiseCaseBean;
import com.jiuhao.jhjk.bean.SelectTempBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.view.EditTextWithDel;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.jiuhao.jhjk.APP.Config.userToken;

/**
 * 立即开方-导入药方-处方记录/协定方
 */
public class ImportMedActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {

    private ImageView ivSearchBack2;
    /**
     * 输入关键字搜索处方
     */
    private EditTextWithDel etSearchTitle2;
    /**
     * 处方记录
     */
    private TextView tvCaseRecord;
    /**
     * 协定方
     */
    private TextView tvModel;
    private RecyclerView rvModel;
    private ImageView ivNoTempRecord;
    private SmartRefreshLayout srlModel;

    private int flag = -1;//标记是0处方记录还是1协定方
    private int page0 = 0;//处方记录
    private int page1 = 0;//协定方
    private CaseHistoryBean caseHistoryBean;//处方记录数据源
    private SelectPromiseCaseBean selectPromiseCaseBean;//协定方数据源
    private ImportMedRecyclerAdapter importMedRecyclerAdapter;//处方记录adapter
    private PromiseCaseRecyclerAdapter promiseCaseRecyclerAdapter;//协定方adapter
    private int symptomNum = -1;//标记0处方记录还是1搜索处方记录
    private String symptom = "";//搜索内容
    private Intent intent;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (caseHistoryBean.getData().size() != 0 && caseHistoryBean != null) {
                        rvModel.setVisibility(View.VISIBLE);
                        ivNoTempRecord.setVisibility(View.GONE);
                        importMedRecyclerAdapter = new ImportMedRecyclerAdapter(getContext(), caseHistoryBean.getData(),
                                new ImportMedRecyclerAdapter.instem() {
                                    @Override
                                    public void onClickInstem(List<SelectByIdBean.MedBean> med) {
                                        //处方记录选中要导入的数据源
                                        intent.putExtra("med", (Serializable) med);
                                        setResult(102,intent);
                                        finish();
                                    }
                                });
                        rvModel.setAdapter(importMedRecyclerAdapter);
                    } else {
                        rvModel.setVisibility(View.GONE);
                        ivNoTempRecord.setVisibility(View.VISIBLE);
                    }
                    break;
                case 1:
                    if (selectPromiseCaseBean.getData().size() != 0 && selectPromiseCaseBean != null) {
                        rvModel.setVisibility(View.VISIBLE);
                        ivNoTempRecord.setVisibility(View.GONE);
                        promiseCaseRecyclerAdapter = new PromiseCaseRecyclerAdapter(getContext(), selectPromiseCaseBean.getData(),
                                new PromiseCaseRecyclerAdapter.instem() {
                                    @Override
                                    public void OnClickInstem(List<SelectTempBean.MedBean> med ) {
                                        //协定方选中要导入的数据源
                                        intent.putExtra("med", (Serializable) med);
                                        setResult(103,intent);
                                        finish();
                                    }
                                });
                        rvModel.setAdapter(promiseCaseRecyclerAdapter);
                    } else {
                        rvModel.setVisibility(View.GONE);
                        ivNoTempRecord.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_import_med);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivSearchBack2 = (ImageView) findViewById(R.id.iv_search_back2);
        etSearchTitle2 = (EditTextWithDel) findViewById(R.id.et_search_title2);
        tvCaseRecord = (TextView) findViewById(R.id.tv_case_record);
        tvModel = (TextView) findViewById(R.id.tv_model);
        rvModel = (RecyclerView) findViewById(R.id.rv_model);
        rvModel.setLayoutManager(new LinearLayoutManager(getContext()));
        ivNoTempRecord = (ImageView) findViewById(R.id.iv_no_temp_record);
        srlModel = (SmartRefreshLayout) findViewById(R.id.srl_model);
        srlModel.setOnRefreshListener(this);
        srlModel.setOnLoadMoreListener(this);
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        symptomNum = 0;
        getselectCaseHistroyData(page0);
    }

    @Override
    protected void initEvent() {
        etSearchTitle2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                symptomNum = 1;
                symptom = s.toString();
                Logger.e(symptom);
                getselectCaseHistroyData(page0);
            }
        });
        etSearchTitle2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        ivSearchBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //处方记录
        tvCaseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                tvCaseRecord.setTextColor(getResources().getColor(R.color.yellow_d49e6a));
                tvCaseRecord.setBackgroundResource(R.drawable.radius_16_f2e2);
                tvModel.setBackgroundResource(R.drawable.radius_16_whit_gray_line);
                tvModel.setTextColor(getResources().getColor(R.color.text_black));
                symptomNum = 0;
                getselectCaseHistroyData(page0);
            }
        });
        //协定方
        tvModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                tvCaseRecord.setTextColor(getResources().getColor(R.color.text_black));
                tvCaseRecord.setBackgroundResource(R.drawable.radius_16_whit_gray_line);
                tvModel.setBackgroundResource(R.drawable.radius_16_f2e2);
                tvModel.setTextColor(getResources().getColor(R.color.yellow_d49e6a));
                getselectPromiseCaseData(page1);
            }
        });
    }

    //协定方
    public void getselectPromiseCaseData(int page) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("token", userToken)
                .url(ConfigKeys.SELECTPROMISECASE + "?page=" + page + "&limit=5")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    ResponseBody body = response.body();
                    byte[] bytes = body.bytes();
                    String result = new String(bytes);

                    Logger.e(result);
                    selectPromiseCaseBean = Json.parseObj(result, SelectPromiseCaseBean.class);
                    Logger.e(selectPromiseCaseBean.toString());
                    handler.sendEmptyMessage(1);
                }
            }
        });

    }

    //处方记录
    public void getselectCaseHistroyData(int page0) {
        String url = "";
        if (symptomNum == 0) {//处方记录
            url = ConfigKeys.SELECTCASEHISTROY + "?isTemplate=0" + "&page=" + page0 + "&limit=5";
        } else if (symptomNum == 1) {//搜索处方记录
            url = ConfigKeys.SELECTCASEHISTROY + "?isTemplate=0" + "&symptom=" + symptom + "&page=" + page0 + "&limit=5";
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("token", userToken)
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    //获取响应体
                    ResponseBody body = response.body();
                    byte[] bytes = body.bytes();
                    //获取到了字符串
                    String result = new String(bytes);

                    Logger.e(result);
                    caseHistoryBean = Json.parseObj(result, CaseHistoryBean.class);
                    Logger.e(caseHistoryBean.toString());
                    handler.sendEmptyMessage(0);
                }
            }
        });
    }

//    public void

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        if (flag == 0) {
            page0++;
            getselectCaseHistroyData(page0);
        } else if (flag == 1) {
            page1++;
            getselectPromiseCaseData(page1);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (flag == 0) {
            page0 = 0;
            getselectCaseHistroyData(page0);
        } else if (flag == 1) {
            page1 = 0;
            getselectPromiseCaseData(page1);
        }
    }
}

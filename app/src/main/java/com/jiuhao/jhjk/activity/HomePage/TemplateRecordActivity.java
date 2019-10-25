package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.TemplateRecordRecyclerAdapter;
import com.jiuhao.jhjk.bean.CaseHistoryBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.view.EditTextWithDel;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.jiuhao.jhjk.APP.Config.userToken;

/**
 * 处方模板记录添加
 */

public class TemplateRecordActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {

    private ImageView ivSearchBack;
    /**
     * 输入关键字搜索处方
     */
    private EditTextWithDel etSearchTitle;
    private RecyclerView templateRecordRecycler;
    private ImageView ivNoTempRecord;
    private SmartRefreshLayout srlModel;
    private Intent intent;
    private int flag = 0;//标记是1全部还是2关键字
    private int page0 = 0;//全部
    private int page1 = 0;//关键字
    private String symptom;//关键字
    private CaseHistoryBean caseHistoryBean;//数据源
    private TemplateRecordRecyclerAdapter templateRecordRecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (caseHistoryBean.getData().size() != 0 && caseHistoryBean != null) {
                        templateRecordRecycler.setVisibility(View.VISIBLE);
                        ivNoTempRecord.setVisibility(View.GONE);
                        templateRecordRecyclerAdapter = new TemplateRecordRecyclerAdapter(getContext(), caseHistoryBean.getData(), new TemplateRecordRecyclerAdapter.instem() {
                            @Override
                            public void onClickInstem() {
                                setResult(1000,intent);
                                finish();
                            }
                        });
                        templateRecordRecycler.setAdapter(templateRecordRecyclerAdapter);
                    } else {
                        templateRecordRecycler.setVisibility(View.GONE);
                        ivNoTempRecord.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_template_record);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        ivSearchBack = (ImageView) findViewById(R.id.iv_search_back);
        etSearchTitle = (EditTextWithDel) findViewById(R.id.et_search_title);
        templateRecordRecycler = (RecyclerView) findViewById(R.id.template_record_recycler);
        ivNoTempRecord = (ImageView) findViewById(R.id.iv_no_temp_record);
        srlModel = (SmartRefreshLayout) findViewById(R.id.srl_model);
        srlModel.setOnRefreshListener(this);
        srlModel.setOnLoadMoreListener(this);
        templateRecordRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void obtainData() {
         intent = getIntent();
        flag=1;
        page0=0;
        getselectCaseHistroyData(page0,flag,"");

    }

    @Override
    protected void initEvent() {
        ivSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        etSearchTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                symptom = s.toString();
                Logger.e(symptom);
                page1 = 0;
                flag = 2;
                getselectCaseHistroyData(page1, flag, symptom);
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


    }

    public void getselectCaseHistroyData(int page0, int flag, String symptom) {
        String url = "";
        if (flag == 1) {//全部
            url = ConfigKeys.SELECTCASEHISTROY + "?isTemplate=1" + "&page=" + page0 + "&limit=5";
        } else if (flag == 2) {//关键字
            url = ConfigKeys.SELECTCASEHISTROY + "?isTemplate=1" + "&symptom=" + symptom + "&page=" + page0 + "&limit=5";
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


    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (flag == 1) {
            page0++;
            getselectCaseHistroyData(page0, 1, "");
        } else if (flag == 2) {
            page1++;
            getselectCaseHistroyData(page1, 2, symptom);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (flag == 1) {
            page0 = 0;
            getselectCaseHistroyData(page0, 1, "");
        } else if (flag == 2) {
            page1 = 0;
            getselectCaseHistroyData(page1, 2, symptom);
        }
    }
}

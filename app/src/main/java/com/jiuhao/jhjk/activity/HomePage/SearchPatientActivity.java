package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.GridListAdapter.PatientAdapter;
import com.jiuhao.jhjk.bean.FindByNameBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.EditTextWithDel;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 开方成功 发送给患者
 */
public class SearchPatientActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    private ImageView ivSearchBack;
    /**
     * 输入关键字搜索处方
     */
    private EditTextWithDel etSearchTitle;
    /**
     * 我的患者
     */
    private TextView tvMyPatient;
    private ListView mlvPatient;
    private ImageView ivNoPatient;
    private SmartRefreshLayout smrlPatient;

    private Intent intent;
    private int caseid;
    private String name = "";
    private int page = 0;
    private int limit = 5;
    private PatientAdapter patientAdapter;
    private List<FindByNameBean> findByNameBeans;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    int size = findByNameBeans.size();
                    if (findByNameBeans == null && size == 0) {
                        tvMyPatient.setText("我的患者(" + size + ")");
                        mlvPatient.setVisibility(View.GONE);
                        ivNoPatient.setVisibility(View.VISIBLE);
                    } else {
                        tvMyPatient.setText("我的患者(" + size + ")");
                        mlvPatient.setVisibility(View.VISIBLE);
                        ivNoPatient.setVisibility(View.GONE);
                        patientAdapter.notifyDataSetChanged();
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_search_patient);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivSearchBack = (ImageView) findViewById(R.id.iv_search_back);
        etSearchTitle = (EditTextWithDel) findViewById(R.id.et_search_title);
        tvMyPatient = (TextView) findViewById(R.id.tv_my_patient);
        mlvPatient = (ListView) findViewById(R.id.mlv_patient);
        ivNoPatient = (ImageView) findViewById(R.id.iv_no_patient);
        smrlPatient = (SmartRefreshLayout) findViewById(R.id.smrl_patient);
        etSearchTitle.setHint("输入姓名搜索患者");
        smrlPatient.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        smrlPatient.setOnRefreshListener(this);
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        caseid = intent.getIntExtra("caseid", 0);

        findByNameBeans = new ArrayList<>();
        patientAdapter = new PatientAdapter(getContext(), findByNameBeans);
        mlvPatient.setAdapter(patientAdapter);
        page = 0;
        getByNameData();
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
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                name = etSearchTitle.getText().toString();
                getByNameData();
            }
        });
        mlvPatient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSendId(findByNameBeans.get(position).getDocId());
            }
        });
    }

    //发送处方给患者
    public void getSendId(int docid) {
        OkHttpUtils.get(ConfigKeys.FINDBYNAME + "?caseId=" + caseid + "&customerId=" + docid, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                ToastUtils.show("已经将处方发送给患者");
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //获取患者列表
    public void getByNameData() {

        OkHttpUtils.get(ConfigKeys.FINDBYNAME + "?name=" + name + "&page=" + page + "&limit=" + limit, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                com.orhanobut.logger.Logger.e(response);
                findByNameBeans = Json.parseArr(response, FindByNameBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                com.orhanobut.logger.Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0;
        getByNameData();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        getByNameData();
    }
}

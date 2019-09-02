package com.jiuhao.jhjk.activity.mine.Personage;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.welcome.AdministrativeRecycler;
import com.jiuhao.jhjk.bean.DepartmentBean;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 科室
 */
public class AdministrativeActivity extends BaseActivity {

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
    private RecyclerView recyclerAdministrative;
    private String departmentnamestr;
    //data
    private List<DepartmentBean> list = new ArrayList();
    private String departmentyes;
    private Intent intent;
    private int departmentId;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_administrative);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        tvTitle.setText("科室");
        rlTitleSure.setVisibility(View.VISIBLE);
        recyclerAdministrative = (RecyclerView) findViewById(R.id.recycler_administrative);
        recyclerAdministrative.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        departmentnamestr = intent.getStringExtra("departmentnamestr");
        getdata();
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rlTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("departmentyes", departmentyes);
                intent.putExtra("departmentId", departmentId);
                setResult(1002, intent);
                finish();
            }
        });
    }

    //获取所有科室
    public void getdata() {

        OkHttpUtils.get(ConfigKeys.DEPARTMENT, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                list = Json.parseArr(response, DepartmentBean.class);
                AdministrativeRecycler administrativeRecycler = new AdministrativeRecycler(getContext(), list, departmentnamestr,
                        new AdministrativeRecycler.im_onlisten() {
                            @Override
                            public void imOnlisten(int postion) {
                                departmentyes = list.get(postion).getDepartmentName();
                                departmentId = list.get(postion).getId();
                            }
                        });
                recyclerAdministrative.setAdapter(administrativeRecycler);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
            }
        });
    }
}

package com.jiuhao.jhjk.fragment.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.FamousDoctorRecyclerAdapter;
import com.jiuhao.jhjk.bean.FamousDoctorBean;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 名医专方科室fragment
 */
@SuppressLint("ValidFragment")
public class FamousDoctorsFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {

    private boolean yescheck = false;
    private Context context;
    private int id;
    private int page1 = 0;
    private int page2 = 0;
    private int page3 = 0;
    private int flag = 1;//1全部查询2关键字查询3我的收藏
    private String keyword;//关键字
    private RecyclerView famousRecycler;
    private LinearLayout no_lin;
    private List<FamousDoctorBean> famousDoctorBeans;
    private FamousDoctorRecyclerAdapter famousDoctorRecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (famousDoctorBeans.size() != 0 && !famousDoctorBeans.isEmpty()) {
                        famousRecycler.setVisibility(View.VISIBLE);
                        no_lin.setVisibility(View.GONE);
                        famousDoctorRecyclerAdapter = new FamousDoctorRecyclerAdapter(getContext(), famousDoctorBeans);
                        famousRecycler.setAdapter(famousDoctorRecyclerAdapter);
                    } else {
                        famousRecycler.setVisibility(View.GONE);
                        no_lin.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return false;
        }
    });

    public FamousDoctorsFragment(Context context, int id, int flag) {
        super();
        this.context = context;
        this.id = id;
        this.flag = flag;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_famous_doctors;
    }

    @Override
    protected void initView() {
        famousRecycler = (RecyclerView) findViewById(R.id.famous_recycler);
        no_lin = (LinearLayout) findViewById(R.id.no_lin);
        famousRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        yescheck = true;
    }

    @Override
    protected void initData() {
        page1 = 0;
        getDoctorsData(page1);
    }

    @Override
    protected void setListener() {

    }

    /**
     * keyword 病症名称或者医生名称
     * departmentId 科室id
     * page
     * limit
     */
    //收藏查询
    public void getDoctorsCollectData(int page, int cflag) {
        if (yescheck) {
            flag = cflag;
            String url = ConfigKeys.SELECTCOLLECT + "?departmentId=" + id + "&page=" + page + "&limit=" + 5;
            getDocData(url);
        }
    }

    //关键字查询
    public void getDoctorsKeywordData(int page, int cflag, String key) {
        if (yescheck) {
            flag = cflag;
            keyword = key;
            String url = ConfigKeys.SELECTDOCTOR + "?keyword=" + key + "&departmentId=" + id + "&page=" + page + "&limit=" + 5;
            getDocData(url);
        }
    }

    //全部查询
    public void getDoctorsData(int page) {
        if (yescheck) {
            String url = ConfigKeys.SELECTDOCTOR + "?departmentId=" + id + "&page=" + page + "&limit=" + 5;
            getDocData(url);
        }
    }

    public void getDocData(String url) {
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                famousDoctorBeans = Json.parseArr(response, FamousDoctorBean.class);
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
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (flag == 1) {
            page1++;
            getDoctorsData(page1);
        } else if (flag == 2) {
            page2++;
            getDoctorsKeywordData(page2, 2, keyword);
        } else if (flag == 3) {
            page3++;
            getDoctorsCollectData(page3, 3);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        if (flag == 1) {
            page1 = 0;
            getDoctorsData(page1);
        } else if (flag == 2) {
            page2 = 0;
            getDoctorsKeywordData(page2, 2, keyword);
        } else if (flag == 3) {
            page3 = 0;
            getDoctorsCollectData(page3, 3);
        }
    }

}

package com.jiuhao.jhjk.fragment.patient;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.RecordRecyclerAdapter;
import com.jiuhao.jhjk.bean.SelectCaseBean;
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
 * 开方记录fragment
 */
@SuppressLint("ValidFragment")
public class EvolutionRecordFragment extends BaseFragment implements OnLoadMoreListener, OnRefreshListener {


    private RecyclerView evolutionRecycler;
    private int postion;//0全部fragment 1代付款 2已付款
    private int page = 0;
    private RelativeLayout rlNoRecord;
    private List<SelectCaseBean> selectCaseBeans;
    private RecordRecyclerAdapter recordRecyclerAdapter;

    public EvolutionRecordFragment(int i) {
        super();
        this.postion = i;
    }

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (selectCaseBeans.size() != 0 && selectCaseBeans != null) {
                        evolutionRecycler.setVisibility(View.VISIBLE);
                        rlNoRecord.setVisibility(View.GONE);
                        recordRecyclerAdapter = new RecordRecyclerAdapter(getContext(), selectCaseBeans);
                        evolutionRecycler.setAdapter(recordRecyclerAdapter);
                    } else {
                        evolutionRecycler.setVisibility(View.GONE);
                        rlNoRecord.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_evolution_record;
    }

    @Override
    protected void initView() {
        evolutionRecycler = (RecyclerView) findViewById(R.id.evolution_recycler);
        rlNoRecord = (RelativeLayout) findViewById(R.id.rl_no_record);
        evolutionRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        page = 0;
        getRecordData(page);
    }

    @Override
    protected void setListener() {

    }

    public void getRecordData(int page) {

        /**
         orderState 订单状态 1 :未支付 2: 已支付 0: 全部
         page 当前页
         limit 每页多少条
         */
        String url = "";
        if (postion == 0) {
            url = ConfigKeys.SELECTCASE + "?orderState=" + 0 + "&page=" + page + "&limit=" + 5;
        } else if (postion == 1) {
            url = ConfigKeys.SELECTCASE + "?orderState=" + 1 + "&page=" + page + "&limit=" + 5;
        } else if (postion == 2) {
            url = ConfigKeys.SELECTCASE + "?orderState=" + 2 + "&page=" + page + "&limit=" + 5;
        }

        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectCaseBeans = Json.parseArr(response, SelectCaseBean.class);
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
        page++;
        getRecordData(page);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0;
        getRecordData(page);
    }
}

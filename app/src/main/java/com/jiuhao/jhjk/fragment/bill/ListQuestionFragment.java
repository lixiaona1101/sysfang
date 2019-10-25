package com.jiuhao.jhjk.fragment.bill;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.QuestionlibraryRecyclerAdapter;
import com.jiuhao.jhjk.bean.KeShiBean;
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
 * 问诊单问题库
 */
@SuppressLint("ValidFragment")
public class ListQuestionFragment extends BaseFragment implements OnLoadMoreListener,OnRefreshListener{
    /**
     * 已选问题:0
     */
    private TextView checkNumber;
    private RecyclerView listQuestionRecycler;
    /**
     * 该科室暂无问题可以选择
     */
    private TextView t;
    private RelativeLayout noQuestion;

    private List<KeShiBean> keShiBeans;

    private Context context;
    private int id;
    private int number = 0;//选中数量标记
    private int page=0;

    public ListQuestionFragment(Context context, int id) {
        super();
        this.context = context;
        this.id = id;
    }

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (keShiBeans.size() == 0 || keShiBeans.isEmpty()) {
                        noQuestion.setVisibility(View.VISIBLE);
                    } else {
                        listQuestionRecycler.setVisibility(View.VISIBLE);
                        QuestionlibraryRecyclerAdapter questionlibraryRecyclerAdapter =
                                new QuestionlibraryRecyclerAdapter(getContext(), keShiBeans,
                                new QuestionlibraryRecyclerAdapter.OnListen() {
                                    @Override
                                    public void upListen() {
                                        number++;
                                        checkNumber.setText("已选问题:" + number);
                                    }
                                },
                                new QuestionlibraryRecyclerAdapter.downListen() {
                                    @Override
                                    public void doListen() {
                                        number--;
                                        checkNumber.setText("已选问题:" + number);
                                    }
                                });
                        listQuestionRecycler.setAdapter(questionlibraryRecyclerAdapter);
                    }
                    break;

            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_question;
    }

    @Override
    protected void initView() {

        checkNumber = (TextView) findViewById(R.id.check_number);
        t = (TextView) findViewById(R.id.t);
        noQuestion = (RelativeLayout) findViewById(R.id.no_question);
        listQuestionRecycler = (RecyclerView) findViewById(R.id.list_question_recycler);
        listQuestionRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        page=0;
        getData(page);
    }

    @Override
    protected void setListener() {

    }

    public void getData(int page) {
        //departmentId 科室编号    page 页码     limit 显示条数
        String limit = "5";
        String url = ConfigKeys.IDIGQUESTION + "?departmentId=" + String.valueOf(id) + "&page=" + page + "&limit=" + limit;

        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                keShiBeans = Json.parseArr(response, KeShiBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public StringBuffer getIdStr() {
        StringBuffer stringBuffer = new StringBuffer();
        if(keShiBeans!=null  && keShiBeans.size()!=0){
            for (int i = 0; i < keShiBeans.size(); i++) {
                boolean check = keShiBeans.get(i).isCheck();
                if (check) {
                    int id = keShiBeans.get(i).getId();
                    stringBuffer.append(id + ",");
                }
            }
        }
        return stringBuffer;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        getData(page);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=0;
        getData(page);
    }
}

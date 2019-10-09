package com.jiuhao.jhjk.fragment.main;


import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.LastMessageRecyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.SelectChatListRecyclerAdapter;
import com.jiuhao.jhjk.bean.LastMessageBean;
import com.jiuhao.jhjk.bean.SelectChatListBean;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 消息主fragment
 */
public class MainMessageFragment extends BaseFragment {


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
    private RecyclerView noticeRecycler;
    /**
     * 我的消息
     */
    private TextView myMessage;
    /**
     * 一键已读
     */
    private TextView read;
    private RecyclerView messageRecycler;
    /**
     * (0)
     */
    private TextView messageNumber;

    private List<LastMessageBean> lastMessageBeans;
    private LastMessageRecyclerAdapter lastMessageRecyclerAdapter;
    private List<SelectChatListBean> selectChatListBeans;
    private SelectChatListRecyclerAdapter selectChatListRecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    lastMessageRecyclerAdapter = new LastMessageRecyclerAdapter(getContext(), lastMessageBeans);
                    noticeRecycler.setAdapter(lastMessageRecyclerAdapter);
                    break;
                case 1:
                    int sum = 0;
                    for (int i = 0; i < selectChatListBeans.size(); i++) {
                        int notReadNum = selectChatListBeans.get(i).getNotReadNum();
                        sum += notReadNum;
                    }
                    messageNumber.setText("(" + sum + ")");
                    selectChatListRecyclerAdapter = new SelectChatListRecyclerAdapter(getContext(), selectChatListBeans);
                    messageRecycler.setAdapter(selectChatListRecyclerAdapter);
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_message;
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        myMessage = (TextView) findViewById(R.id.my_message);
        read = (TextView) findViewById(R.id.read);
        messageNumber = (TextView) findViewById(R.id.message_number);
        noticeRecycler = (RecyclerView) findViewById(R.id.notice_recycler);
        messageRecycler = (RecyclerView) findViewById(R.id.message_recycler);
        ivBack.setVisibility(View.GONE);
        tvTitle.setText("消息");
        noticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        messageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        noticeRecycler.setNestedScrollingEnabled(false);
        messageRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        getXData();
        getMData();
    }

    //获取系统通知和处方通知的最新消息
    public void getXData() {
        OkHttpUtils.get(ConfigKeys.LASTMESSAGE, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {

                Logger.e(response);
                lastMessageBeans = Json.parseArr(response, LastMessageBean.class);
                handler.sendEmptyMessage(0);

            }

            @Override
            public void onFailure(int code, Exception e) {
                com.orhanobut.logger.Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });

    }

    //我的消息
    public void getMData() {
        OkHttpUtils.get(ConfigKeys.SELECTCHATLIST, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectChatListBeans = Json.parseArr(response, SelectChatListBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int code, Exception e) {
                com.orhanobut.logger.Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void setListener() {


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });
    }

    //一键已读
    public void postData() {
        OkHttpUtils.postJson(ConfigKeys.ONEKEYREADED, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("已读！");
                getMData();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

}

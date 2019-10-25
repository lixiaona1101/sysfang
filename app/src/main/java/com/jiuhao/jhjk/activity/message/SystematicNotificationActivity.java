package com.jiuhao.jhjk.activity.message;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.NotificationRecyclerAdapter;
import com.jiuhao.jhjk.bean.SelectBean;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.jiuhao.jhjk.APP.Config.userToken;

/**
 * 系统通知
 */
public class SystematicNotificationActivity extends BaseActivity implements OnLoadMoreListener, OnRefreshListener {


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
    private RecyclerView notificationRecycler;
    private int page = 1;
    private int limit = 5;
    private SelectBean selectBean;
    private NotificationRecyclerAdapter notificationRecyclerAdapter;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    List<SelectBean.DataBean> data = selectBean.getData();
                    notificationRecyclerAdapter = new NotificationRecyclerAdapter(getContext(), data);
                    notificationRecycler.setAdapter(notificationRecyclerAdapter);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_systematic_notification);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        notificationRecycler = (RecyclerView) findViewById(R.id.notification_recycler);
        notificationRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        tvTitle.setText("系统通知");
    }

    @Override
    protected void obtainData() {
        getsystemData(1);
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getsystemData(int p) {
        /**
         * type 1 系统消息 2 订单消息
         * page  1
         * limit
         */
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("token", userToken)
                .url(ConfigKeys.SELECT + "?type=" + 1 + "&page=" + p + "&limit=" + limit)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    //获取响应体
                    ResponseBody body = response.body();
                    byte[] bytes = body.bytes();
                    //获取到了字符串
                    String result = new String(bytes);

                    Gson gson = new Gson();
                    selectBean = gson.fromJson(result, SelectBean.class);
                    handler.sendEmptyMessage(0);
                }
            }
        });

    }

    //加载更多
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (selectBean.isHasNextPage()) {
            page++;
            getsystemData(page);
        }
    }

    //刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        getsystemData(page);
    }
}

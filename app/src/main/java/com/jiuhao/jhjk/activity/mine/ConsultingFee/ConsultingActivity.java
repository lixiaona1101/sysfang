package com.jiuhao.jhjk.activity.mine.ConsultingFee;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.activity.welcome.RegisterActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.ConsultingRecyclerAdapter;
import com.jiuhao.jhjk.bean.DocpricetimeBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 设置咨询费
 */
public class ConsultingActivity extends BaseActivity {


    private ImageView mIvBack;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 确认
     */
    private TextView mTvTitleSure;
    private RelativeLayout mRlTitleSure;
    private RelativeLayout mRlTitle;
    private RecyclerView mConsultingRecycler;
    private List<DocpricetimeBean> docpricetimeBeans;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if(docpricetimeBeans.size()!=0){
                        ConsultingRecyclerAdapter consultingRecyclerAdapter = new ConsultingRecyclerAdapter(getContext(), docpricetimeBeans);
                        mConsultingRecycler.setAdapter(consultingRecyclerAdapter);
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_consulting);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        mRlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        mRlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        mConsultingRecycler = (RecyclerView) findViewById(R.id.consulting_recycler);
        mConsultingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mTvTitle.setText("咨询费");
        mRlTitleSure.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {
        getData();
    }

    public void getData() {

        OkHttpUtils.get(ConfigKeys.DOCPRICEITEM, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                docpricetimeBeans = Json.parseArr(response, DocpricetimeBean.class);
                Logger.e(docpricetimeBeans.toString());
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                if(code==-1){
                    Config.quit(getContext());
                    ToastUtils.show("Token失效，请重新登录！");
                    startActivity(new Intent(getContext(), RegisterActivity.class));
                }

            }
        });
    }

    @Override
    protected void initEvent() {

    }
}

package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

/**
 * 更多设置
 */
public class MoreSetActivity extends BaseActivity {


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
    /**
     * 清空聊天记录
     */
    private TextView clearDetails;
    /**
     * 消息免打扰
     */
    private TextView noDisturb;
    private ImageView img;
    private boolean isCheck = false;
    private Intent intent;
    private int data;//聊天室id

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_more_set);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        clearDetails = (TextView) findViewById(R.id.clear_details);
        noDisturb = (TextView) findViewById(R.id.no_disturb);
        img = (ImageView) findViewById(R.id.img);

        tvTitle.setText("更多设置");
    }

    @Override
    protected void obtainData() {
         intent = getIntent();
         data = intent.getIntExtra("data", 0);

    }

    @Override
    protected void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //清空聊天记录
        clearDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //接口
                clearData();
            }
        });

        //消息免打扰
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCheck = !isCheck;
                if (isCheck) {
                    img.setImageResource(R.mipmap.on);
                    setAvoidanceData(1);
                } else {
                    img.setImageResource(R.mipmap.off);
                    setAvoidanceData(2);
                }
            }
        });
    }
    //清空数据
    public void clearData(){
        OkHttpUtils.get(ConfigKeys.DOCPATIENTMSG+"?chatId="+data, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("清楚消息记录成功");
                setResult(12);
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //删除聊天室
    public void deleteChatData(){

        OkHttpUtils.get(ConfigKeys.DELETEDOCPATIENTMAG+"?chatId="+data, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("删除成功");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void setAvoidanceData( int shielding) {

        /**
         * chatId 聊天室id
         shielding 是否消息免打扰 1 是 2 否
         */
        String url = ConfigKeys.AVOIDANCE + "?chatId=" + data + "&shielding=" + shielding;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("消息免打扰设置成功！");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });


    }
}

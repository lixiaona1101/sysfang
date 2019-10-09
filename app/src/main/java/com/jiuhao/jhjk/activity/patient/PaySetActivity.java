package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.Receiver.Logger;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;

import java.util.LinkedHashMap;

/**
 * 付费咨询设置-患者
 */
public class PaySetActivity extends BaseActivity {

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
     * 【患者名字】
     */
    private TextView personName;
    /**
     * 免费
     */
    private EditText money;
    /**
     * 保存设置
     */
    private TextView saveSet;
    private String nickName;
    private int id;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_pay_set);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        personName = (TextView) findViewById(R.id.person_name);
        money = (EditText) findViewById(R.id.money);
        saveSet = (TextView) findViewById(R.id.save_set);
        tvTitle.setText("付费咨询设置");
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        nickName = intent.getStringExtra("nickName");
         id = intent.getIntExtra("id", 0);
        personName.setText("【" + nickName + "】");
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //保存设置
        saveSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = money.getText().toString();
                if(!s.isEmpty()){
                    int fee = Integer.valueOf(s);
                    if(fee<=200){
                        postData(fee);
                    }else {
                        ToastUtils.show("金额过大,<=200！");
                    }
                }else{
                    ToastUtils.show("请输入服务费！");
                }
            }
        });
    }

    public void postData(int fee) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("customerId",id);
        linkedHashMap.put("priceType",1);
        linkedHashMap.put("price",fee);
        OkHttpUtils.putJson(ConfigKeys.PRICE, null, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("设置成功！");
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                com.orhanobut.logger.Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

}

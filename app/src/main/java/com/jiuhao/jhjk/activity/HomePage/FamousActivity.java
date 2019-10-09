package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.FamousDoctorBean;
import com.jiuhao.jhjk.bean.SpecialFamousDoctorBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;

/**
 * 名医专方-专方详情
 */
public class FamousActivity extends BaseActivity {

    private Intent intent;
    private FamousDoctorBean famousDoctorBeans;
    private ImageView ivSearchBack2;
    /**
     * 专方详情
     */
    private TextView etTitle;
    /**
     * 收藏
     */
    private TextView collectText;
    private ImageView collectSrc;
    /**
     * 专方名称
     */
    private TextView symptom;
    /**
     * 秘方
     */
    private TextView secrecyte;
    /**
     * $176
     */
    private TextView price;
    /**
     * 专效效方功效方功效
     */
    private TextView efficacy;
    /**
     * 脾胃不适
     */
    private TextView indication;
    /**
     * 颗粒
     */
    private TextView formulationName;
    private ImageView picUrl;
    /**
     * 刘医生
     */
    private TextView docName;
    /**
     * 国医大师
     */
    private TextView title;
    private Button useButton;
    private boolean check = false;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_famous);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivSearchBack2 = (ImageView) findViewById(R.id.iv_search_back2);
        etTitle = (TextView) findViewById(R.id.et_title);
        collectText = (TextView) findViewById(R.id.collect_text);
        collectSrc = (ImageView) findViewById(R.id.collect_src);
        symptom = (TextView) findViewById(R.id.symptom);
        secrecyte = (TextView) findViewById(R.id.secrecy);
        price = (TextView) findViewById(R.id.price);
        efficacy = (TextView) findViewById(R.id.efficacy);
        indication = (TextView) findViewById(R.id.indication);
        formulationName = (TextView) findViewById(R.id.formulationName);
        picUrl = (ImageView) findViewById(R.id.picUrl);
        docName = (TextView) findViewById(R.id.docName);
        title = (TextView) findViewById(R.id.title);
        useButton = findViewById(R.id.use_button);
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        famousDoctorBeans = (FamousDoctorBean) intent.getSerializableExtra("data");

        GlideUtil.loadCircle(getContext(), famousDoctorBeans.getPicUrl(), picUrl);
        docName.setText(famousDoctorBeans.getDocName());
        title.setText(famousDoctorBeans.getTitle());
        symptom.setText(famousDoctorBeans.getSymptom());
        efficacy.setText(famousDoctorBeans.getEfficacy());
        indication.setText(famousDoctorBeans.getIndication());
        formulationName.setText(famousDoctorBeans.getFormulationName());
        price.setText("$" + famousDoctorBeans.getPrice());

        //秘方 0是 1否
        int secrecy = famousDoctorBeans.getSecrecy();
        if (secrecy == 0) {
            secrecyte.setVisibility(View.VISIBLE);
        } else {
            secrecyte.setVisibility(View.GONE);
        }

        //0未收藏 1已收藏
        int isAttention = famousDoctorBeans.getIsAttention();
        if (isAttention == 0) {
            check = false;
            collectText.setText("收藏");
            collectSrc.setImageResource(R.mipmap.collection1);
        } else if (isAttention == 1) {
            check = true;
            collectText.setText("已收藏");
            collectSrc.setImageResource(R.mipmap.collection);
        }

        collectSrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;
                if (check) {
                    collectText.setText("已收藏");
                    collectSrc.setImageResource(R.mipmap.collection);
                } else {
                    collectText.setText("收藏");
                    collectSrc.setImageResource(R.mipmap.collection1);
                }
                setColletData();
            }
        });

        useButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUserData();
            }
        });


    }

    public void postUserData() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id",famousDoctorBeans.getId());
        OkHttpUtils.postJson(ConfigKeys.SELECTCOLLECTADD , linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                SpecialFamousDoctorBean specialFamousDoctorBean = Json.parseObj(response, SpecialFamousDoctorBean.class);
                //二维码弹窗
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void setColletData() {

        OkHttpUtils.get(ConfigKeys.SELECTDOCTORCOLLECT + "?specialId=" + famousDoctorBeans.getId(), null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e("收藏操作成功");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {
        ivSearchBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

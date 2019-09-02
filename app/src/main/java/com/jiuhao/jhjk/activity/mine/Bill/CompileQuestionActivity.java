package com.jiuhao.jhjk.activity.mine.Bill;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 编辑问题
 */
public class CompileQuestionActivity extends BaseActivity {


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
     * 0
     */
    private TextView nowNumber;
    /**
     * /100
     */
    private TextView bai;
    /**
     * 点击编辑问题
     */
    private EditText doctorSynopsis;
    /**
     * 单选题
     */
    private RadioButton danChoice;
    /**
     * 多选题
     */
    private RadioButton duoChoice;
    /**
     * 简答题
     */
    private RadioButton whiteChoice;
    private RecyclerView danRecycler;
    /**
     * 点击添加选项
     */
    private TextView danAddChoice;
    private LinearLayout danLin;
    private RecyclerView duoRecycler;
    /**
     * 点击添加选项
     */
    private TextView duoAddChoice;
    private LinearLayout duoLin;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_compile_question);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        nowNumber = (TextView) findViewById(R.id.now_number);
        bai = (TextView) findViewById(R.id.bai);
        doctorSynopsis = (EditText) findViewById(R.id.doctor_synopsis);
        danChoice = (RadioButton) findViewById(R.id.dan_choice);
        duoChoice = (RadioButton) findViewById(R.id.duo_choice);
        whiteChoice = (RadioButton) findViewById(R.id.white_choice);
        danRecycler = (RecyclerView) findViewById(R.id.dan_recycler);
        danAddChoice = (TextView) findViewById(R.id.dan_add_choice);
        danLin = (LinearLayout) findViewById(R.id.dan_lin);
        duoRecycler = (RecyclerView) findViewById(R.id.duo_recycler);
        duoAddChoice = (TextView) findViewById(R.id.duo_add_choice);
        duoLin = (LinearLayout) findViewById(R.id.duo_lin);
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("保存");
    }

    @Override
    protected void obtainData() {

        //调用接口
        //数据设置
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

}

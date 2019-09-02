package com.jiuhao.jhjk.activity.mine.Bill;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
 * 新建问题-问诊单
 */
public class QuestionActivity extends BaseActivity {

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
    private ViewPager viewpager;
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
        setContentView(R.layout.activity_question);
        translucentStatusBar(true);

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
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        danRecycler = (RecyclerView) findViewById(R.id.dan_recycler);
        danAddChoice = (TextView) findViewById(R.id.dan_add_choice);
        danLin = (LinearLayout) findViewById(R.id.dan_lin);
        duoRecycler = (RecyclerView) findViewById(R.id.duo_recycler);
        duoAddChoice = (TextView) findViewById(R.id.duo_add_choice);
        duoLin = (LinearLayout) findViewById(R.id.duo_lin);
        tvTitle.setText("新建问题");
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("保存");
        danChoice.setChecked(true);
    }

    @Override
    protected void obtainData() {
        //长度
        doctorSynopsis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if (length <= 100) nowNumber.setText(length + "");
            }
        });
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //单选
        danChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danChoice.setChecked(true);
                danLin.setVisibility(View.VISIBLE);
                duoLin.setVisibility(View.GONE);
            }
        });

        //多选
        duoChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duoChoice.setChecked(true);
                danLin.setVisibility(View.GONE);
                duoLin.setVisibility(View.VISIBLE);
            }
        });

        //简答
        whiteChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whiteChoice.setChecked(true);
                danLin.setVisibility(View.GONE);
                duoLin.setVisibility(View.GONE);
            }
        });

        //保存问题
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

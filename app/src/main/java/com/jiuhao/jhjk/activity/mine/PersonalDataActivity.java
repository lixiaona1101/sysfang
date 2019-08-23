package com.jiuhao.jhjk.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;

/**
 * 基本信息
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {


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
     * 点击更换
     */
    private TextView upHead;
    private ImageView head;
    /**
     * 男
     */
    private TextView man;
    /**
     * 女
     */
    private TextView woman;
    /**
     * 职称
     */
    private TextView textView;
    private RelativeLayout administrative;
    private RelativeLayout major;
    /**
     * 点击这里编辑简介内容...
     */
    private EditText doctorSynopsis;
    /**
     * 0
     */
    private TextView nowNumber;
    /**
     * /300
     */
    private TextView san;
    private RelativeLayout occupation;
    /**
     * 未选择
     */
    private TextView departmentName;
    /**
     * 未选择
     */
    private TextView titles;
    /**
     * 未选择标签
     */
    private TextView label;
    //科室
    private String departmentnamestr;
    //职称
    private String titlesstr;
    //专业标签
    private String labelstr;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_personal_data);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        upHead = (TextView) findViewById(R.id.up_head);
        head = (ImageView) findViewById(R.id.head);
        man = (TextView) findViewById(R.id.man);
        woman = (TextView) findViewById(R.id.woman);
        textView = (TextView) findViewById(R.id.textView);
        administrative = (RelativeLayout) findViewById(R.id.administrative);
        major = (RelativeLayout) findViewById(R.id.major);
        doctorSynopsis = (EditText) findViewById(R.id.doctor_synopsis);
        nowNumber = (TextView) findViewById(R.id.now_number);
        san = (TextView) findViewById(R.id.san);
        tvTitle.setText("基本信息");
        rlTitleSure.setVisibility(View.VISIBLE);
        occupation = (RelativeLayout) findViewById(R.id.occupation);
        departmentName = (TextView) findViewById(R.id.department_name);
        titles = (TextView) findViewById(R.id.titles);
        label = (TextView) findViewById(R.id.label);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {
        //医生头像
        String headUrl = SPUtils.getString(getContext(), ConfigKeys.AVATAR, "");
        GlideUtil.loadCircle(getContext(), headUrl, head);
        //性别 0未知 1男 2女
        int sex = SPUtils.getInt(getContext(), ConfigKeys.SEX, 0);
        show(sex);
        //科室
        departmentnamestr = SPUtils.getString(getContext(), ConfigKeys.DEPARTMENTNAME, "未选择");
        departmentName.setText(departmentnamestr + "  ");

        //职称
        titlesstr = SPUtils.getString(getContext(), ConfigKeys.TITLES, "未选择");
        titles.setText(titlesstr + "  ");

        //专业标签
        labelstr = SPUtils.getString(getContext(), ConfigKeys.LABEL, "未选择标签");
        label.setText(labelstr + "  ");

        //简介
        String resumestr = SPUtils.getString(getContext(), ConfigKeys.RESUME, "点击这里编辑简介内容...");
        doctorSynopsis.setText(resumestr);
        int length = resumestr.length();
        nowNumber.setText(length + "");
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
                if (length <= 300) nowNumber.setText(length + "");
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
        //科室
        administrative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdministrativeActivity.class);
                intent.putExtra("departmentnamestr", departmentnamestr);
                startActivityForResult(intent, 1001);
            }
        });

        //职称
        occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OccupationActivity.class);
                startActivityForResult(intent,2001);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.man:
                show(1);
                break;
            case R.id.woman:
                show(2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == 1002) {
            String departmentyes = data.getStringExtra("departmentyes");
            departmentName.setText(departmentyes + "  ");
        }else if(requestCode==2001 && resultCode==2002){
            String occupation = data.getStringExtra("occupation");
            titles.setText(occupation+"  ");
        }
    }

    //性别
    public void show(int sex) {
        Drawable drawableTrue = getContext().getResources().getDrawable(R.mipmap.select);
        Drawable drawableFalse = getContext().getResources().getDrawable(R.mipmap.select1);
        if (sex == 0) {
            man.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
            woman.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
        } else if (sex == 1) {
            man.setCompoundDrawablesWithIntrinsicBounds(drawableTrue, null, null, null);
            woman.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
        } else if (sex == 2) {
            man.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
            woman.setCompoundDrawablesWithIntrinsicBounds(drawableTrue, null, null, null);
        }
    }
}

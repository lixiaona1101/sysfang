package com.jiuhao.jhjk.activity.mine.Bill;

import android.content.Intent;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.QuestionRcyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.QuestionerRcyclerAdapter;
import com.jiuhao.jhjk.bean.IgQuestion2Bean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
    private Intent intent;
    private int id;
    private int flag1 = 0;
    private int flag2 = 0;
    private QuestionRcyclerAdapter questionRcyclerAdapter;
    private QuestionerRcyclerAdapter questionerRcyclerAdapter;
    private int flag;
    private IgQuestion2Bean.DqsBean dqsBean;
    private int questionId;//问题id

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
        danRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        duoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        questionRcyclerAdapter = new QuestionRcyclerAdapter(getContext(), new ArrayList<>());
        danRecycler.setAdapter(questionRcyclerAdapter);

        questionerRcyclerAdapter = new QuestionerRcyclerAdapter(getContext(), new ArrayList<>());
        duoRecycler.setAdapter(questionerRcyclerAdapter);
    }

    @Override
    protected void obtainData() {

        intent = getIntent();
         flag = intent.getIntExtra("flag", 2);
        id = intent.getIntExtra("id", 0);//问诊单id
        if(flag==1){//編輯問題
            dqsBean = (IgQuestion2Bean.DqsBean)intent.getSerializableExtra("bean");
            tvTitle.setText("编辑问题");
            tvTitleSure.setText("确定");
            String content = dqsBean.getContent();
            doctorSynopsis.setText(content);
            nowNumber.setText(content.length() + "");
             questionId = dqsBean.getId();

            //问题答案list
            List<String> stringList = new ArrayList<>();
            //答案
            String answerChoose = dqsBean.getAnswerChoose();
            if(!answerChoose.isEmpty()){
                String replace = answerChoose.replace("{", "");
                String replace1 = replace.replace("}", "");
                String replace2 = replace1.replace("\"", "");

                String[] split = replace2.split(",");
                for (int i = 0; i < split.length; i++) {
                    String strings=split[i];
                    String[] split1 = strings.split(":");
                    for (int j = 0; j < split1.length; j++) {
                        if(j%2==1){
                            String s= split1[j];
                            stringList.add(s);
                        }
                    }
                }
            }

            //问题类型
            int questionType = dqsBean.getQuestionType();//问题类型:0：单选，1：多选，2：简答题
            if(questionType==0){
                danChoice.setChecked(true);
                danLin.setVisibility(View.VISIBLE);
                duoLin.setVisibility(View.GONE);
                for (int i = 0; i < stringList.size(); i++) {
                    questionRcyclerAdapter.insertItem(stringList.get(i));
                }
            }else if(questionType==1){
                duoChoice.setChecked(true);
                danLin.setVisibility(View.GONE);
                duoLin.setVisibility(View.VISIBLE);
                for (int i = 0; i < stringList.size(); i++) {
                    questionerRcyclerAdapter.insertItem(stringList.get(i));
                }
            }else if(questionType==2){
                whiteChoice.setChecked(true);
                danLin.setVisibility(View.GONE);
                duoLin.setVisibility(View.GONE);
            }

        }else if(flag==2){//新建問題
            tvTitle.setText("新建问题");
            tvTitleSure.setText("保存");
        }

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
        //返回
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

        //点击添加问题item 单选
        danAddChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag1++;
                if (flag1 <= 10) {
                    questionRcyclerAdapter.insertItem("");
                } else {
                    ToastUtils.show("最多可添加10个答案哟！");
                }
            }
        });

        //点击添加问题item 多选
        duoAddChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag2++;
                if (flag2 <= 10) {
                    questionerRcyclerAdapter.insertItem("");
                } else {
                    ToastUtils.show("最多可添加10个答案哟！");
                }
            }
        });

        //保存问题
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putQuestion();
            }
        });
    }

    public void putQuestion() {
        //问题内容
        String questionName = doctorSynopsis.getText().toString();

        //类型
        int questionType = -1;
        if (!questionName.isEmpty()) {
            //问题类型:0：单选，1：多选，2：简答题
            if (danChoice.isChecked()) {//单选
                questionType = 0;
            } else if (duoChoice.isChecked()) {//多选
                questionType = 1;
            } else if (whiteChoice.isChecked()) {//简答
                questionType = 2;
            }
            postData(questionType, questionName);

        } else {
            ToastUtils.show("问题标题不可以为空！");
        }
    }

    public void postData(int questionType, String questionName) {

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("interrogationId", id);//问诊单id
        linkedHashMap.put("questionType", questionType);//问题类型
        linkedHashMap.put("content", questionName);//问题标题

        //答案map
        LinkedHashMap<String, Object> stringStringLinkedHashMap = new LinkedHashMap<>();
        List<String> dataList = new ArrayList<>();
        if (danChoice.isChecked()) {
            dataList = questionRcyclerAdapter.getDataList();
        } else if (duoChoice.isChecked()) {
            dataList = questionerRcyclerAdapter.getDataList();
        }

        if (1 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"a\"", "\"" + dataList.get(0) + "\"");
        }
        if (2 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"b\"", "\"" + dataList.get(1) + "\"");
        }
        if (3 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"c\"", "\"" + dataList.get(2) + "\"");
        }
        if (4 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"d\"", "\"" + dataList.get(3) + "\"");
        }
        if (5 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"e\"", "\"" + dataList.get(4) + "\"");
        }
        if (6 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"f\"", "\"" + dataList.get(5) + "\"");
        }
        if (7 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"g\"", "\"" + dataList.get(6) + "\"");
        }
        if (8 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"h\"", "\"" + dataList.get(7) + "\"");
        }
        if (9 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"i\"", "\"" + dataList.get(8) + "\"");
        }
        if (10 <= dataList.size()) {
            stringStringLinkedHashMap.put("\"j\"", "\"" + dataList.get(9) + "\"");
        }
        if (questionType != 2) {
            linkedHashMap.put("answerChoose", stringStringLinkedHashMap.toString().replaceAll("=", ":"));//答案
        }

        if(flag==1){//编辑
            linkedHashMap.put("id", questionId);//问题id

            OkHttpUtils.putJson(ConfigKeys.IGQUESTION, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
                @Override
                public void onSuccess(int code, String response) {
                    ToastUtils.show("编辑成功");
                    setResult(102);
                    finish();
                }

                @Override
                public void onFailure(int code, Exception e) {
                    Logger.e(e.getMessage());
                    ToastUtils.show(e.getMessage());
                }
            });

        }else if(flag==2){//新建

            OkHttpUtils.postJson(ConfigKeys.IGQUESTION, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
                @Override
                public void onSuccess(int code, String response) {
                    ToastUtils.show("添加成功");
                    setResult(102);
                    finish();
                }

                @Override
                public void onFailure(int code, Exception e) {
                    Logger.e(e.getMessage());
                    ToastUtils.show(e.getMessage());
                }
            });
        }
    }
}

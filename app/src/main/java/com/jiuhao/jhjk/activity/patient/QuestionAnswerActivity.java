package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.QuestionAnswerRecyclerAdapter;
import com.jiuhao.jhjk.bean.AnswerBean;
import com.jiuhao.jhjk.bean.CustomerBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAnswerActivity extends BaseActivity {

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
     * 问诊单
     */
    private TextView billNumber;
    private RecyclerView billRecycler;
    private LinearLayout linVisible;
    private Intent intent;
    private AnswerBean answerBean;
    private QuestionAnswerRecyclerAdapter questionAnswerRecyclerAdapter;
    private String personId;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_question_answer);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        billNumber = (TextView) findViewById(R.id.bill_number);
        billRecycler = (RecyclerView) findViewById(R.id.bill_recycler);
        linVisible = (LinearLayout) findViewById(R.id.lin_visible);
        billRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        tvTitle.setText("问诊单");
        rlTitleSure.setVisibility(View.VISIBLE);
        tvTitleSure.setText("联系患者");
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
        answerBean = (AnswerBean) intent.getSerializableExtra("answerBean");
        personId = intent.getStringExtra("personId");
        billNumber.setText(answerBean.getName());
    }

    @Override
    protected void initEvent() {

        List<AnswerBean.DqsBean> dqs = answerBean.getDqs();

        questionAnswerRecyclerAdapter = new QuestionAnswerRecyclerAdapter(R.layout.item_question_answer, dqs);
        billRecycler.setAdapter(questionAnswerRecyclerAdapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    public void getData() {
        String url = ConfigKeys.CUSTOMER + "?id=" + personId;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                CustomerBean customerBean = Json.parseObj(response, CustomerBean.class);

                Intent intent = new Intent(getContext(), DialogueActivity.class);
                intent.putExtra("nickName", customerBean.getUserName());//患者姓名
                intent.putExtra("id", customerBean.getId());//患者id
                intent.putExtra("img", customerBean.getAvatar());//患者头像
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(intent);

            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

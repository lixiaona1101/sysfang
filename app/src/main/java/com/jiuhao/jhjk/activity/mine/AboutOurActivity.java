package com.jiuhao.jhjk.activity.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 关于我们
 */
public class AboutOurActivity extends BaseActivity implements View.OnClickListener {

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
     * 法律声明
     */
    private TextView statement;
    /**
     * 当前已是最新版本2.0.1
     */
    private TextView newVersions;
    /**
     * 0571-86168750
     */
    private TextView servicePhone;
    /**
     * 在这里写下您的意见或反馈，非常感谢！
     */
    private EditText feelEdit;
    /**
     * 0
     */
    private TextView nowNumber;
    /**
     * /300
     */
    private TextView san;
    /**
     * 提交
     */
    private Button submit;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_about_our);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        statement = (TextView) findViewById(R.id.statement);
        newVersions = (TextView) findViewById(R.id.new_versions);
        servicePhone = (TextView) findViewById(R.id.service_phone);
        feelEdit = (EditText) findViewById(R.id.feel_edit);
        nowNumber = (TextView) findViewById(R.id.now_number);
        san = (TextView) findViewById(R.id.san);
        submit = (Button) findViewById(R.id.submit);
        tvTitle.setText("关于我们");
        submit.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.submit:
                break;
        }
    }
}

package com.jiuhao.jhjk.activity.mine.DoctorCertified;

import android.view.View;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 认证结果
 */
public class CertifiedActivity extends BaseActivity {


    /**
     * 返回首页>>
     */
    private TextView mBackMain;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_certified);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        mBackMain = (TextView) findViewById(R.id.back_main);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        mBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

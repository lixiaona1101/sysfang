package com.example.lixiaona.study.activity;

import android.util.Log;

import com.example.lixiaona.study.R;
import com.example.lixiaona.study.activity.base.BaseActivity;

public class MainActivity extends BaseActivity  {

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
        Log.i("sss","sss");
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }
}

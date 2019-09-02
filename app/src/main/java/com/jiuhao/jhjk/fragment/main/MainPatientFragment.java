package com.jiuhao.jhjk.fragment.main;


import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.fragment.base.BaseFragment;

/**
 * 患者主fragment
 */
public class MainPatientFragment extends BaseFragment {


    private View view;
    /**
     * 输入姓名搜索患者
     */
    private EditText editPatient;
    /**
     * 邀请患者
     */
    private TextView welcomeOne;
    /**
     * 群发消息
     */
    private TextView massTextingTwo;
    /**
     * 分组管理
     */
    private TextView groupingThree;
    private ListView listList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_patient;
    }

    @Override
    protected void initView() {

        editPatient = (EditText) findViewById(R.id.edit_patient);
        welcomeOne = (TextView) findViewById(R.id.welcome_one);
        massTextingTwo = (TextView)findViewById(R.id.mass_texting_two);
        groupingThree = (TextView) findViewById(R.id.grouping_three);
        listList = (ListView) findViewById(R.id.list_list);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

}

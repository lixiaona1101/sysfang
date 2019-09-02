package com.jiuhao.jhjk.fragment.bill;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.ListQuestionBean;
import com.jiuhao.jhjk.fragment.base.BaseFragment;

/**
 * 问诊单问题库
 */
@SuppressLint("ValidFragment")
public class ListQuestionFragment extends BaseFragment {


    /**
     * 已选问题:0
     */
    private TextView checkNumber;
    /**
     * 全选
     */
    private TextView allCheck;
    private RecyclerView listQuestionRecycler;

    private Context context;
    private ListQuestionBean listQuestionBean;

    public ListQuestionFragment(Context context, ListQuestionBean listQuestionBean) {
        super();
        this.context = context;
        this.listQuestionBean = listQuestionBean;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_question;
    }

    @Override
    protected void initView() {

        checkNumber = (TextView) findViewById(R.id.check_number);
        allCheck = (TextView) findViewById(R.id.all_check);
        listQuestionRecycler = (RecyclerView) findViewById(R.id.list_question_recycler);
    }

    @Override
    protected void initData() {
        if (listQuestionBean.getData().size() == 0 || listQuestionBean.getData().isEmpty()) {
            //空页面
        } else {

        }
    }

    @Override
    protected void setListener() {

    }

}

package com.jiuhao.jhjk.activity.mine.Bill;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.ListQuestionBean;
import com.jiuhao.jhjk.fragment.bill.ListQuestionFragment;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 问题库导入
 */
public class ListQuestionActivity extends BaseActivity {

    private ImageView mIvBack;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 确认
     */
    private TextView mTvTitleSure;
    private RelativeLayout mRlTitleSure;
    private RelativeLayout mRlTitle;
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private List<ListQuestionBean> listQuestionBeans;
    private int id;

    List<String> tablist = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:

                    //tablist
                    for (int i = 0; i < listQuestionBeans.size(); i++) {

                        String departmentName = listQuestionBeans.get(i).getDepartmentName();//科室名
                        int count = listQuestionBeans.get(i).getCount();//数量
                        String tabStr = departmentName + "(" + count + ")";

                        Logger.e(tabStr);

                        tablist.add(tabStr);
                        fragmentList.add(new ListQuestionFragment(getContext(), listQuestionBeans.get(i).getId()));

                    }

                    //设置适配
                    MyFragmentAdapter myAdapter = new MyFragmentAdapter(getSupportFragmentManager());
                    mViewpager.setAdapter(myAdapter);

                    //***************把viewPager跟tab绑定在一块**************
                    mTablayout.setupWithViewPager(mViewpager);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_list_question);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        mRlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        mRlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mTvTitle.setText("问诊单");
        mRlTitleSure.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        getListQuestion();//获取问题库数据
    }

    public void getListQuestion() {
        OkHttpUtils.get(ConfigKeys.IGQUESTIONSTORE, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {

                Logger.e(response);
                listQuestionBeans = Json.parseArr(response, ListQuestionBean.class);
                Logger.e(listQuestionBeans.size() + "");
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return tablist.get(position);
        }


    }

    @Override
    protected void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //确定
        mTvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });
    }

    //上传问题库问题
    public void postData() {

        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < fragmentList.size(); i++) {
            ListQuestionFragment fragment = (ListQuestionFragment)fragmentList.get(i);
            StringBuffer idStr = fragment.getIdStr();
            stringBuffer.append(idStr);
        }
        String ids = stringBuffer.delete((stringBuffer.length() - 1), stringBuffer.length()).toString();
        Logger.e(ids);

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("interrogationId", id);//问诊单id
        linkedHashMap.put("ids",ids);//问题库问题id串
        OkHttpUtils.postJson(ConfigKeys.BATCHCREATE, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("操作成功");
                setResult(22);
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

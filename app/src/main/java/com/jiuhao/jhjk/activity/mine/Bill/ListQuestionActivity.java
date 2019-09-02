package com.jiuhao.jhjk.activity.mine.Bill;

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

    List<String> tablist = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
//                    ListQuestionFragment listQuestionFragment = new ListQuestionFragment(getContext(),listQuestionBeans);
                    //tablist
                    for (int i = 0; i < listQuestionBeans.size(); i++) {

                        String departmentName = listQuestionBeans.get(i).getDepartmentName();//科室名
                        int count = listQuestionBeans.get(i).getCount();//数量
                        String tabStr = departmentName + "(" + count + ")";

                        Logger.e(tabStr);
                        tablist.add(tabStr);
                        fragmentList.add(new ListQuestionFragment(getContext(),listQuestionBeans.get(i)));

                    }

                    Logger.e(fragmentList.size() + "");

//                //给tab设置标题
//                for (int i = 0; i < tablist.size(); i++) {
//                    //相当于每个tab的item
//                    TabLayout.Tab tabAt = mTablayout.getTabAt(i);
//                    //给每个item设置标题，同样还可以设置其他的属性
//                    tabAt.setText(tablist.get(i).toString());
//                }

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

//        @Override
//        public CharSequence getPageTitle(int position) {
//            if (tablist != null && tablist.size() != 0) {
//                return tablist.get(position);
//            }
//            return super.getPageTitle(position);
//        }

    }

    @Override
    protected void initEvent() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

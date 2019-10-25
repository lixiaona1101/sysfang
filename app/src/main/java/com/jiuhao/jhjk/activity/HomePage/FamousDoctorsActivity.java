package com.jiuhao.jhjk.activity.HomePage;

import android.os.Handler;
import android.os.Message;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.ListQuestionBean;
import com.jiuhao.jhjk.fragment.homepage.FamousDoctorsFragment;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.EditTextWithDel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 名医专方
 */
public class FamousDoctorsActivity extends BaseActivity {

    private ImageView ivSearchBack2;
    /**
     * 输入关键字搜索处方
     */
    private EditTextWithDel etSearchTitle2;
    /**
     * 我的收藏
     */
    private TextView collect;
    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<ListQuestionBean> listQuestionBeans;
    List<String> tablist = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentAdapter myAdapter;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:

                    //tablist
                    for (int i = 0; i < listQuestionBeans.size(); i++) {
                        String departmentName = listQuestionBeans.get(i).getDepartmentName();//科室名
                        tablist.add(departmentName);
                        fragmentList.add(new FamousDoctorsFragment(getContext(), listQuestionBeans.get(i).getId(), 1));
                    }

                    //设置适配
                    myAdapter = new MyFragmentAdapter(getSupportFragmentManager());
                    viewpager.setAdapter(myAdapter);

                    //***************把viewPager跟tab绑定在一块**************
                    tablayout.setupWithViewPager(viewpager);
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_famous_doctors);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivSearchBack2 = (ImageView) findViewById(R.id.iv_search_back2);
        etSearchTitle2 = (EditTextWithDel) findViewById(R.id.et_search_title2);
        collect = (TextView) findViewById(R.id.collect);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    protected void obtainData() {
        getListQuestion();
    }

    @Override
    protected void initEvent() {
        ivSearchBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //关键字搜索
        etSearchTitle2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                Logger.e(keyword);
                for (int i = 0; i < fragmentList.size(); i++) {
                    FamousDoctorsFragment fragment = (FamousDoctorsFragment) fragmentList.get(i);
                    fragment.getDoctorsKeywordData(0, 2, keyword);
                }
            }
        });
        etSearchTitle2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        //我的收藏
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < fragmentList.size(); i++) {
                    FamousDoctorsFragment fragment = (FamousDoctorsFragment) fragmentList.get(i);
                    fragment.getDoctorsCollectData(0, 3);
                }
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
}

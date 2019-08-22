package com.jiuhao.jhjk.activity.welcome;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.welcome.FragmentAdapter;
import com.jiuhao.jhjk.fragment.welcome.FourFragment;
import com.jiuhao.jhjk.fragment.welcome.OneFragment;
import com.jiuhao.jhjk.fragment.welcome.ThreeFragment;
import com.jiuhao.jhjk.fragment.welcome.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class WelcomeSiActivity extends BaseActivity {

    private ViewPager mGuidanceViewpager;
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_welcome_si);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        mGuidanceViewpager = (ViewPager) findViewById(R.id.guidance_viewpager);
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
    }

    @Override
    protected void obtainData() {
        mGuidanceViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments));
    }

    @Override
    protected void initEvent() {

    }
}

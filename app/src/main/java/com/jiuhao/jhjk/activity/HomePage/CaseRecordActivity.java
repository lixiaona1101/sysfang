package com.jiuhao.jhjk.activity.HomePage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.fragment.patient.EvolutionRecordFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 开方记录
 */
public class CaseRecordActivity extends BaseActivity {

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
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;
    private List<String> tabTitleList;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_case_record);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        tabLayout= (TabLayout) findViewById(R.id.tb_index_case);
        viewPager = (ViewPager) findViewById(R.id.vp_content_case);
        tvTitle.setText("开方记录");
    }

    @Override
    protected void obtainData() {
        tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.gray), ContextCompat.getColor(getContext(), R.color.theme_color));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.theme_color));
        tabTitleList = new ArrayList<>();
        tabTitleList.add("全部");
        tabTitleList.add("待付款");
        tabTitleList.add("已付款");
        initTab();
    }
    private void initTab() {
        ViewCompat.setElevation(tabLayout, 10);
        tabLayout.setupWithViewPager(viewPager);

        tabFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tabFragments.add(new EvolutionRecordFragment(i));
        }
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(contentAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitleList.get(position);
        }
    }
    @Override
    protected void initEvent() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

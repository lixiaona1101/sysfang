package com.jiuhao.jhjk.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.welcome.FragmentAdapter;
import com.jiuhao.jhjk.fragment.main.MainMedicineFragment;
import com.jiuhao.jhjk.fragment.main.MainMessageFragment;
import com.jiuhao.jhjk.fragment.main.MainMineFragment;
import com.jiuhao.jhjk.fragment.main.MainPatientFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主activity
 */

public class MainActivity extends BaseActivity {

    private ViewPager mainViewpager;
    /**
     * 开方
     */
    private RadioButton radioMedicine;
    /**
     * 患者
     */
    private RadioButton radioPatient;
    /**
     * 消息
     */
    private RadioButton radioMessage;
    /**
     * 我的
     */
    private RadioButton radioMine;
    private RadioGroup radioGroup;
    private List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        mainViewpager = (ViewPager) findViewById(R.id.main_viewpager);
        radioMedicine = (RadioButton) findViewById(R.id.radio_medicine);
        radioPatient = (RadioButton) findViewById(R.id.radio_patient);
        radioMessage = (RadioButton) findViewById(R.id.radio_message);
        radioMine = (RadioButton) findViewById(R.id.radio_mine);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        fragments.add(new MainMedicineFragment());//开方
        fragments.add(new MainPatientFragment());//患者
        fragments.add(new MainMessageFragment());//消息
        fragments.add(new MainMineFragment());//我的
        radioMedicine.setChecked(true);
        mainViewpager.setCurrentItem(0);
        check(0);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        mainViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),fragments));
        radioMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(0,true);
                check(0);
            }
        });
        radioPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(1,true);
                check(1);
            }
        });
        radioMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(2,true);
                check(2);
            }
        });
        radioMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(3,true);
                check(3);
            }
        });
        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0){
                    radioMedicine.setChecked(true);
                }else if(i==1){
                    radioPatient.setChecked(true);
                }else if(i==2){
                    radioMessage.setChecked(true);
                }else if(i==3){
                    radioMine.setChecked(true);
                }
                check(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void check(int i){
        radioMedicine.setTextColor(getResources().getColor(R.color.green_009685));
        radioPatient.setTextColor(getResources().getColor(R.color.green_009685));
        radioMessage.setTextColor(getResources().getColor(R.color.green_009685));
        radioMine.setTextColor(getResources().getColor(R.color.green_009685));

        if(i==0){
            radioMedicine.setTextColor(getResources().getColor(R.color.text_color_000000));
        }else if(i==1){
            radioPatient.setTextColor(getResources().getColor(R.color.text_color_000000));
        }else if(i==2){
            radioMessage.setTextColor(getResources().getColor(R.color.text_color_000000));
        }else if(i==3){
            radioMine.setTextColor(getResources().getColor(R.color.text_color_000000));
        }
    }

}

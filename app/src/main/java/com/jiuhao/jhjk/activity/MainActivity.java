package com.jiuhao.jhjk.activity;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.Receiver.ExampleUtil;
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

    //极光
    public static boolean isForeground = false;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main);
        translucentStatusBar(true);
        registerMessageReceiver();//极光
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
                radioMedicine.setChecked(true);
            }
        });
        radioPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(1,true);
                radioPatient.setChecked(true);
            }
        });
        radioMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(2,true);
                radioMessage.setChecked(true);
            }
        });
        radioMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewpager.setCurrentItem(3,true);
                radioMine.setChecked(true);
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
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



    //极光
    //用于从jpush服务器接收客户的msg
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.jiuhao.jhjk.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                }
            } catch (Exception e) {
            }
        }
    }


    @Override
    public void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    public void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

//		case R.id.stopPush:
//            JPushInterface.stopPush(getApplicationContext());
//			break;
//		case R.id.resumePush:
//            JPushInterface.resumePush(getApplicationContext());

}

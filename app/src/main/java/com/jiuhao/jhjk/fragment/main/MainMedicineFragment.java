package com.jiuhao.jhjk.fragment.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.CameraEvoActivity;
import com.jiuhao.jhjk.activity.HomePage.CaseRecordActivity;
import com.jiuhao.jhjk.activity.HomePage.FamousDoctorsActivity;
import com.jiuhao.jhjk.activity.HomePage.MainEvoActivity;
import com.jiuhao.jhjk.activity.HomePage.TemplateActivity;
import com.jiuhao.jhjk.activity.mine.Other.InviteFriendsActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.MainMedicineRecyclerAdapter;
import com.jiuhao.jhjk.bean.DocCaseBean;
import com.jiuhao.jhjk.bean.GetImagesBannerBean;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.banner.BannerCreator;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.WebviewActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 开方主fragment
 */
public class MainMedicineFragment extends BaseFragment implements OnItemClickListener {

    /**
     * 名片
     */
    private TextView tvNameCard;
    private ConvenientBanner cbIndex;
    private TextView tvMainTip;
    private LinearLayout llOnline;
    private LinearLayout llCamera;
    private LinearLayout prescriptionTemplateLin1;
    private LinearLayout prescribingRecordLin2;
    private LinearLayout famousDoctorsLin3;
    private LinearLayout healthyMallLin4;
    private ImageView ivNoCase;
    private RecyclerView medicineRecycler;
    private int authState;//认证状态
    private List<DocCaseBean> docCaseBeans;
    private MainMedicineRecyclerAdapter mainMedicineRecyclerAdapter;
    private List<GetImagesBannerBean> getImagesBannerBeans;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (docCaseBeans.size() == 0) {
                        ivNoCase.setVisibility(View.VISIBLE);
                        medicineRecycler.setVisibility(View.GONE);
                    } else {
                        ivNoCase.setVisibility(View.GONE);
                        medicineRecycler.setVisibility(View.VISIBLE);
                        mainMedicineRecyclerAdapter = new MainMedicineRecyclerAdapter(getContext(), docCaseBeans);
                        medicineRecycler.setAdapter(mainMedicineRecyclerAdapter);
                    }
                    break;
                case 1://banner
                    ArrayList<String> imgList = new ArrayList<>();
                    for (int i = 0; i < getImagesBannerBeans.size(); i++) {
                        imgList.add(getImagesBannerBeans.get(i).getImgUrl());
                    }
                    cbIndex.startTurning(3000);
                    BannerCreator.setDefault(cbIndex, imgList,MainMedicineFragment.this::onItemClick, 1);
                    break;
            }
            return false;
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_medicine;
    }

    @Override
    protected void initView() {

        tvNameCard = (TextView) findViewById(R.id.tv_name_card);
        cbIndex = (ConvenientBanner) findViewById(R.id.cb_index);
        tvMainTip = (TextView) findViewById(R.id.tv_main_tip);
        llOnline = (LinearLayout) findViewById(R.id.ll_online);
        llCamera = (LinearLayout) findViewById(R.id.ll_camera);
        prescriptionTemplateLin1 = (LinearLayout) findViewById(R.id.prescription_template_lin1);
        prescribingRecordLin2 = (LinearLayout) findViewById(R.id.prescribing_record_lin2);
        famousDoctorsLin3 = (LinearLayout) findViewById(R.id.famous_doctors_lin3);
        healthyMallLin4 = (LinearLayout) findViewById(R.id.healthy_mall_lin4);
        ivNoCase = (ImageView) findViewById(R.id.iv_no_case);
        medicineRecycler = (RecyclerView) findViewById(R.id.medicine_recycler);
        medicineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        medicineRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {
        authState = SPUtils.getInt(getContext(), ConfigKeys.AUTHSTAT, 0);
        getBanner();
        getdocCaseData();
    }

    @Override
    protected void setListener() {
        //名片 邀请医生
        tvNameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InviteFriendsActivity.class));
            }
        });

        //在线开方
        llOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (authState != 3) {
                    //弹窗
//                    baseTipDialog.show();
                    return;
                }
                Intent intent = new Intent(getContext(), MainEvoActivity.class);
                intent.putExtra("orinType",0);
                startActivity(intent);
            }
        });
        //拍照上传
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authState != 3) {
//                    baseTipDialog.show();
                    return;
                }
                startActivity(new Intent(getContext(), CameraEvoActivity.class));
            }
        });
        //处方模板
        prescriptionTemplateLin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TemplateActivity.class));
            }
        });
        //开方记录
        prescribingRecordLin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CaseRecordActivity.class));
            }
        });
        //名医专方
        famousDoctorsLin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FamousDoctorsActivity.class));
            }
        });
        //健康商城
        healthyMallLin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //banner
    private void getBanner() {

        OkHttpUtils.get(ConfigKeys.GETIMAGES + "?imgType=2", null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                getImagesBannerBeans = Json.parseArr(response, GetImagesBannerBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }


    //最近五条开方记录
    public void getdocCaseData() {
        OkHttpUtils.get(ConfigKeys.SELECTCASELAST, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                docCaseBeans = Json.parseArr(response, DocCaseBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //banner点击回调
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), WebviewActivity.class);
        intent.putExtra("title", "上医尚方");
        intent.putExtra("html", getImagesBannerBeans.get(position).getLinkUrl());
        startActivity(intent);
    }
}

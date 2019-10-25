package com.jiuhao.jhjk.fragment.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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
import com.jiuhao.jhjk.activity.mine.DoctorCertified.CertifiedPassActivity;
import com.jiuhao.jhjk.activity.mine.DoctorCertified.MessageCertifiedActivity;
import com.jiuhao.jhjk.activity.mine.Other.InviteFriendsActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.TestAdapter;
import com.jiuhao.jhjk.bean.DocAuthBean;
import com.jiuhao.jhjk.bean.DocCaseBean;
import com.jiuhao.jhjk.bean.GetImagesBannerBean;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.utils.DialogUtil;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.banner.BannerCreator;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.WebviewActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 开方主fragment
 */
public class MainMedicineFragment extends BaseFragment implements OnItemClickListener {

    /**
     * 名片
     */
    private TextView tvNameCard;
    private TextView authentication;
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
    private String authTip;//认证信息
    private DocAuthBean docAuthBean;//认证信息源
    private List<DocCaseBean> docCaseBeans;
    private List<GetImagesBannerBean> getImagesBannerBeans;
    private AlertDialog baseTipDialog;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (docCaseBeans == null || docCaseBeans.size() == 0) {
                        ivNoCase.setVisibility(View.VISIBLE);
                        medicineRecycler.setVisibility(View.GONE);
                    } else {
                        ivNoCase.setVisibility(View.GONE);
                        medicineRecycler.setVisibility(View.VISIBLE);
                        Logger.e("handler" + docCaseBeans.size());
                        for (int i = 0; i < docCaseBeans.size(); i++) {
                            Logger.e(docCaseBeans.get(i).toString());
                        }
                        TestAdapter testAdapter = new TestAdapter(R.layout.item_recently, docCaseBeans);
                        medicineRecycler.setAdapter(testAdapter);
                    }
                    break;
                case 1://banner
                    ArrayList<String> imgList = new ArrayList<>();
                    for (int i = 0; i < getImagesBannerBeans.size(); i++) {
                        imgList.add(getImagesBannerBeans.get(i).getImgUrl());
                    }
                    cbIndex.startTurning(3000);
                    BannerCreator.setDefault(cbIndex, imgList, MainMedicineFragment.this::onItemClick, 1);
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

        authentication = (TextView) findViewById(R.id.authentication);
        tvNameCard = (TextView) findViewById(R.id.tv_name_card);
        cbIndex = (ConvenientBanner) findViewById(R.id.cb_index);
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
        medicineRecycler.setHasFixedSize(true);
    }

    @Override
    protected void initData() {
        authState = SPUtils.getInt(getContext(), ConfigKeys.AUTHSTAT, 0);
        checkAuth();
        getDocAuth();
        getBanner();
        getdocCaseData();
    }

    //检查认证状态
    private void checkAuth() {
        if (authState == 3) {
            authentication.setVisibility(View.GONE);
            return;
        }
        switch (authState) {
            case 0:
            case 1://未认证
                authTip = "您的身份信息未认证，点击立即认证";
                break;
            case 2://审核中
                authTip = "您的身份信息审核中，请耐心等待";
                break;
            case 4://认证失败
                authTip = "您的身份信息认证失败，点击重新认证";
                break;
        }
        authentication.setText(authTip);
        authentication.setVisibility(View.VISIBLE);
        initBaseDialog();
    }

    //初始化提示对话框
    private void initBaseDialog() {
        baseTipDialog = DialogUtil.createDialog(getContext(), R.layout.dialog_base_tip);
        View view = DialogUtil.view;
        TextView textView = view.findViewById(R.id.dtv_tip);
        TextView tvCancel = view.findViewById(R.id.dtv_cancel);
        TextView tvSure = view.findViewById(R.id.dtv_sure);
        textView.setText(authTip);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseTipDialog.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseTipDialog.dismiss();
                if (docAuthBean != null) toAuth();
            }
        });
    }

    //查看认证
    public void getDocAuth() {
        OkHttpUtils.get(ConfigKeys.DOCAUTH, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                docAuthBean = Json.parseObj(response, DocAuthBean.class);
                Logger.e(docAuthBean.getAuthStat() + "");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void toAuth() {
        if (authState == 1 || authState == 0) {//未认证
            Intent intent = new Intent(getContext(), MessageCertifiedActivity.class);
            startActivityForResult(intent, 102);
        } else if (authState == 3 || authState == 2 || authState == 4) {//已认证//审核中//审核失败
            Intent intent = new Intent(getContext(), CertifiedPassActivity.class);
            intent.putExtra("bean", docAuthBean);
            startActivity(intent);
        }
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

        //认证
        authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAuth();
            }
        });

        //在线开方
        llOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authState != 3) {
                    //弹窗
                    baseTipDialog.show();
                    return;
                }
                Intent intent = new Intent(getContext(), MainEvoActivity.class);
                intent.putExtra("orinType", 0);
                startActivity(intent);
            }
        });
        //拍照上传
        llCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (authState != 3) {
                    baseTipDialog.show();
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
                Logger.e("bean" + docCaseBeans.size());
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102 && resultCode == 13) {
            getDocAuth();
        }
    }

}

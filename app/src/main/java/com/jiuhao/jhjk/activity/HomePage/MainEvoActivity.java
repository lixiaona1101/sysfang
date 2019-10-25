package com.jiuhao.jhjk.activity.HomePage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.KeyboardUtils;
import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.view.net.RestClient;
import com.jiuhao.jhjk.view.net.callback.IError;
import com.jiuhao.jhjk.view.net.callback.IFailure;
import com.jiuhao.jhjk.view.net.callback.ISuccess;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.GridListAdapter.CheckMedTipAdapter;
import com.jiuhao.jhjk.adapter.GridListAdapter.MedTypeAdapter;
import com.jiuhao.jhjk.adapter.GridListAdapter.WorkingAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.MesicneRecyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.SelectSuggestRecyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.WordInfoRecyclerAdapter;
import com.jiuhao.jhjk.bean.CaseTemplateBean;
import com.jiuhao.jhjk.bean.SavecaseBean;
import com.jiuhao.jhjk.bean.SelectByIdBean;
import com.jiuhao.jhjk.bean.SelectFromulationBean;
import com.jiuhao.jhjk.bean.SelectSuggestBean;
import com.jiuhao.jhjk.bean.SelectTempBean;
import com.jiuhao.jhjk.bean.SelectingBean;
import com.jiuhao.jhjk.bean.SelectingredientsBean;
import com.jiuhao.jhjk.bean.ShopedSelectBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.dialog.HintTitleDialog;
import com.jiuhao.jhjk.utils.BottomDialogCreator;
import com.jiuhao.jhjk.utils.DialogUtil;
import com.jiuhao.jhjk.utils.MedUtil;
import com.jiuhao.jhjk.utils.NumberUtils;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.AmountView;
import com.jiuhao.jhjk.view.MyGridView;
import com.jiuhao.jhjk.view.MyListView;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 在线开方
 */
public class MainEvoActivity extends BaseActivity implements View.OnClickListener {

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
    /**
     * 请输入病症描述
     */
    private EditText etSymptom;
    private RecyclerView recyclerZhiFlag;
    /**
     * 选择处方剂型
     */
    private TextView tvSelectMedType;
    private TextView tvMedDesc;
    /**
     * 添加药品
     */
    private TextView tvSelectMed;
    private RecyclerView mgvSelectedMed;
    /**
     * 请选择
     */
    private TextView tvSelectWorking;
    private TextView tvWorkName;
    private EditText etWorkNum;
    private LinearLayout llWorking;
    private LinearLayout zhiFlagline;
    private AmountView avNum;
    private EditText tvFreq;
    private EditText tvMeth;
    private ImageView ivFeeOffOn;
    /**
     * 免费
     */
    private TextView tvFees;
    private AppCompatEditText etFees;
    private ImageView ivSecrecyOffOn;
    /**
     * 立即开方
     */
    private Button btnEvo;
    private RecyclerView wordInfoRecycler;
    /**
     * 0
     */
    private TextView medicineTypeNumber;
    /**
     * 0
     */
    private TextView medicineWeight;
    /**
     * 0.00
     */
    private TextView weightPrice;
    /**
     * 每付30元
     */
    private TextView serviceCharge;
    /**
     * 每付0元
     */
    private TextView serviceChargeCheck;

    private ImageView ImgThree;
    private ImageView ImgTwo;
    private ImageView ImgOne;
    private ImageView ImgZero;

    private int docId;//医生id
    private int size = 0;//味数
    private int weight = 0;//总克数
    private double sumMescine = 0;//总价
    private int fees = 0;//诊金
    private String freq;//用药频次
    private String meth;//用药方法
    private int plural = 7;//付数
    private String symptom;//病症内容
    private int serviceFee = 0;//服务费
    private String symptomText;//病症名称
    private int metType = -1;//处方药剂类型
    private int formulationId = -1;//剂型id
    private boolean feesFlag = false;//诊金
    private String ingredients = "";//辅料
//    private String medStr = "";//药品字符串
    private int selectPosition = 0;//标记选中
    private int workingType = -1;//加工方式 1加工辅助显示
    private int secpecy = 0;//保密处方 0关闭 1打开  0公开 1不公开
    private int isImg = 1;//开方类型 1文字方 2 图片方 3核价完成后的图片处方

    private Intent intent2;//添加药品
    private Intent intent;//MainEvoActivity
    private Button btnSure;//确定开方
    private MyListView mlvCheckMed;//！listview
    private SelectTempBean selectTempBean;//病例详情数据源
    private android.app.AlertDialog medCodeDialog;//二维码弹出框
    private android.app.AlertDialog checkMedDialog;//毒品检测框
    private List<SelectSuggestBean> selectSuggestBeans;//病例联想下拉
    private List<SelectFromulationBean> selectFromulationBeans;//剂型数据源
    private MesicneRecyclerAdapter mesicneRecyclerAdapter;//药品列表adapter
    private List<SelectingredientsBean> selectingredientsBeans;//膏方数据源
    private WordInfoRecyclerAdapter wordInfoRecyclerAdapter;//膏方recycleradapter
    private List<SelectingBean> selectingBeans = new ArrayList<>();//膏方列表数据源
    private SelectSuggestRecyclerAdapter selectSuggestRecyclerAdapter;//智能开方列表adapter
    private List<ShopedSelectBean> shopedSelectBeanList = new ArrayList<>();//智能开发导入药品数据源
    //加载框变量
    private ProgressDialog progressDialog;

    private int orinType = 0;//0在线开方 1新建模板 2使用模板开方 3更改用药 4新开处方
    private CaseTemplateBean caseTemplateBean;//2模板数据源
    private boolean isinit = false;//标记初始化控件
    private SelectByIdBean selectByIdBean;//更改用药数据源
    private int flag = 3;
    private JSONArray medJsonArray;
    private SavecaseBean savecaseBean;//在线开方 二维码bean
    private int customerId;//患者id
    private int itemFlag=0;//標記是點擊item還是輸入完成 0輸入完成 1點擊item

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0://剂型
                    String name = selectFromulationBeans.get(0).getName();
                    formulationId = selectFromulationBeans.get(0).getId();//剂型id
                    tvSelectMedType.setText(name);
                    metType = selectFromulationBeans.get(0).getCaseType();
                    setDefaultValue(selectFromulationBeans.get(0));
                    break;
                case 1://病症
                    if (selectSuggestBeans != null && selectSuggestBeans.size() != 0) {
                        zhiFlagline.setVisibility(View.VISIBLE);
                        selectSuggestRecyclerAdapter = new SelectSuggestRecyclerAdapter(getContext(), selectSuggestBeans,
                                new SelectSuggestRecyclerAdapter.instem() {
                                    @Override
                                    public void onClickin(int postion) {
                                        itemFlag=1;
                                        zhiFlagline.setVisibility(View.GONE);
                                        int id = selectSuggestBeans.get(postion).getId();//历史处方id
                                        int templateType = selectSuggestBeans.get(postion).getTemplateType();//处方类型 1模板详情 2协定方详情
                                        getPromiseData(id, templateType);
                                    }
                                });
                        recyclerZhiFlag.setAdapter(selectSuggestRecyclerAdapter);
                    }
                    break;
                case 2://病症详情
                    setAllData();
                    break;
                case 3://膏方
                    if (selectingredientsBeans != null && selectingredientsBeans.size() != 0) {
                        showWoringPop(selectingredientsBeans);
                    }
                    break;
                case 4://二维码弹框
                    initMedCodeDialog();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_main_evo);
        translucentStatusBar(true);
    }


    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        etSymptom = (EditText) findViewById(R.id.et_symptom);
        tvSelectMedType = (TextView) findViewById(R.id.tv_select_med_type);
        tvMedDesc = (TextView) findViewById(R.id.tv_med_desc);
        tvSelectMed = (TextView) findViewById(R.id.tv_select_med);
        mgvSelectedMed = (RecyclerView) findViewById(R.id.mgv_selected_med);
        tvSelectWorking = (TextView) findViewById(R.id.tv_select_working);
        tvWorkName = (TextView) findViewById(R.id.tv_work_name);
        etWorkNum = (EditText) findViewById(R.id.et_work_num);
        llWorking = (LinearLayout) findViewById(R.id.ll_working);
        zhiFlagline = (LinearLayout) findViewById(R.id.zhi_flag_line);
        avNum = (AmountView) findViewById(R.id.av_num);
        tvFreq = (EditText) findViewById(R.id.tv_freq);
        tvMeth = (EditText) findViewById(R.id.tv_meth);
        ivFeeOffOn = (ImageView) findViewById(R.id.iv_fee_off_on);
        tvFees = (TextView) findViewById(R.id.tv_fees);
        etFees = findViewById(R.id.et_fees);
        ivSecrecyOffOn = (ImageView) findViewById(R.id.iv_secrecy_off_on);
        btnEvo = (Button) findViewById(R.id.btn_evo);
        recyclerZhiFlag = (RecyclerView) findViewById(R.id.recycler_zhi_flag);
        wordInfoRecycler = (RecyclerView) findViewById(R.id.word_info_recycler);
        medicineTypeNumber = (TextView) findViewById(R.id.medicine_type_number);
        medicineWeight = (TextView) findViewById(R.id.medicine_weight);
        weightPrice = (TextView) findViewById(R.id.weight_price);
        serviceCharge = (TextView) findViewById(R.id.service_charge);
        serviceChargeCheck = (TextView) findViewById(R.id.service_charge_check);
        recyclerZhiFlag.setLayoutManager(new LinearLayoutManager(getContext()));
        wordInfoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mgvSelectedMed.setLayoutManager(new GridLayoutManager(getContext(), 2));
        btnEvo.setOnClickListener(this);
        tvTitle.setText("在线开方");
        isinit = true;
    }

    @Override
    protected void obtainData() {

        docId = SPUtils.getInt(getContext(), ConfigKeys.USERID, 0);//医生id
        getSelectFormulationData();//获取挤方数据
        avNum.setNum(7);
        //膏方baserecycler
        wordInfoRecyclerAdapter = new WordInfoRecyclerAdapter(getContext(), selectingBeans);
        wordInfoRecycler.setAdapter(wordInfoRecyclerAdapter);
        //药品baseRecyclerAdapter
        mesicneRecyclerAdapter = new MesicneRecyclerAdapter(getContext(), shopedSelectBeanList);
        mgvSelectedMed.setAdapter(mesicneRecyclerAdapter);
        //药品检测毒品dialog
        checkMedDialog = DialogUtil.createDialog(getContext(), R.layout.dialog_check_med_tip);
        mlvCheckMed = DialogUtil.view.findViewById(R.id.mlv_med_check_tip);
        Button btnCancel = DialogUtil.view.findViewById(R.id.btn_cancel_improve);
        btnSure = DialogUtil.view.findViewById(R.id.btn_improve_now);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMedDialog.dismiss();
            }
        });

        intent = getIntent();
        orinType = intent.getIntExtra("orinType", 0);
        if (orinType == 0) {

        } else if (orinType == 1) {//新建模板
            btnEvo.setText("新建模板");
        } else if (orinType == 2) {//使用模板
            caseTemplateBean = (CaseTemplateBean) intent.getSerializableExtra("templateBean");
            if (isinit) setTemplateData();
        } else if (orinType == 3) {//更改用药
            btnEvo.setText("更改用药");
            selectByIdBean = (SelectByIdBean) intent.getSerializableExtra("selectDetailsBean");
            if (isinit) updateTemplateData();
        } else if(orinType==4){//新开处方
            customerId=intent.getIntExtra("id",0);
        }

    }

    //更改用药
    public void updateTemplateData() {
        if (selectByIdBean != null) {
            formulationId = selectByIdBean.getFormulationId();//剂型
            symptomText = selectByIdBean.getSymptom();//病症名称
            customerId= selectByIdBean.getCustomerId();//患者id
            etSymptom.setText(symptomText);
            plural = selectByIdBean.getPlural();//付数
            avNum.setNum(plural);
            Logger.e(selectByIdBean.getFormulationName());
            updateDefaultValueTemplate(selectByIdBean);//设置剂型，方法 ，频次
            fees = selectByIdBean.getFees();//诊金
            setFeesData();
            //药品data
            List<SelectByIdBean.MedBean> med = selectByIdBean.getMed();
            size = med.size();//味数
            for (int i = 0; i < med.size(); i++) {
                sumMescine += med.get(i).getMedPrice();//总价
                Logger.e(sumMescine + "");
                //克数
                if ("袋".equals(med.get(i).getMedUnit())) {//单位
                    String medSpec = med.get(i).getMedSpec().replace("g/袋", "");
                    int k = NumberUtils.toInt(medSpec, 1);
                    weight += k;
                } else {
                    weight += med.get(i).getMedNumber();
                }
            }

            List<ShopedSelectBean> dataList = mesicneRecyclerAdapter.getDataList();
            if (dataList != null && dataList.size() != 0) {
                mesicneRecyclerAdapter.removeAll();
            }
            //传入加入药品的数据
            for (int i = 0; i < med.size(); i++) {
                ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                shopedSelectBean.setMedNumber(med.get(i).getMedNumber());
                shopedSelectBean.setMedPrice(med.get(i).getMedPrice());
                shopedSelectBean.setMedUnit(med.get(i).getMedUnit());
                shopedSelectBean.setMedSpec(med.get(i).getMedSpec());
                shopedSelectBean.setMedName(med.get(i).getMedName());
                shopedSelectBean.setMedType(med.get(i).getMedType());
                shopedSelectBean.setMedId(med.get(i).getMedId());
                addShopDatalist(shopedSelectBean);
            }

            //药方中味克金额
            setMescineData();
            //服务费
            setServiceCharge(sumMescine);
        }
    }

    //诊金
    public void setFeesData() {
        if (fees == 0) {
            tvFees.setText("免费");
            etFees.setVisibility(View.GONE);
            KeyboardUtils.hideSoftInput(etFees);
            feesFlag = false;
            ivFeeOffOn.setImageResource(R.mipmap.off);
        } else {
            tvFees.setText("诊金：");
            feesFlag = true;
            etFees.setVisibility(View.VISIBLE);
            KeyboardUtils.showSoftInput(etFees);
            etFees.setText(fees + "");
            ivFeeOffOn.setImageResource(R.mipmap.on);
        }
    }

    //设置诊金
    public void feeOffOn() {
        if (feesFlag) {
            tvFees.setText("免费");
            etFees.setVisibility(View.GONE);
            KeyboardUtils.hideSoftInput(etFees);
            feesFlag = false;
            ivFeeOffOn.setImageResource(R.mipmap.off);
        } else {
            tvFees.setText("诊金：");
            feesFlag = true;
            etFees.setVisibility(View.VISIBLE);
            KeyboardUtils.showSoftInput(etFees);
            ivFeeOffOn.setImageResource(R.mipmap.on);
        }
    }

    //使用模板开方
    public void setTemplateData() {
        if (caseTemplateBean != null) {
            formulationId = caseTemplateBean.getFormulationId();//剂型
            symptomText = caseTemplateBean.getSymptom();//病症名称
            etSymptom.setText(symptomText);
            plural = caseTemplateBean.getPlural();//付数
            avNum.setNum(plural);
            Logger.e(caseTemplateBean.getFormulationName());
            setDefaultValueTemplate(caseTemplateBean);//设置剂型，方法 ，频次
            fees = caseTemplateBean.getFees();//诊金
            setFeesData();
            //药品data
            List<CaseTemplateBean.MedBean> med = caseTemplateBean.getMed();
            size = med.size();//味数
            for (int i = 0; i < med.size(); i++) {
                sumMescine += med.get(i).getMedPrice();//总价
                Logger.e(sumMescine + "");
                //克数
                if ("袋".equals(med.get(i).getMedUnit())) {//单位
                    String medSpec = med.get(i).getMedSpec().replace("g/袋", "");
                    int k = NumberUtils.toInt(medSpec, 1);
                    weight += k;
                } else {
                    weight += med.get(i).getMedNumber();
                }
            }

            List<ShopedSelectBean> dataList = mesicneRecyclerAdapter.getDataList();
            if (dataList != null && dataList.size() != 0) {
                mesicneRecyclerAdapter.removeAll();
            }
            //传入加入药品的数据
            for (int i = 0; i < med.size(); i++) {
                ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                shopedSelectBean.setMedNumber(med.get(i).getMedNumber());
                shopedSelectBean.setMedPrice(med.get(i).getMedPrice());
                shopedSelectBean.setMedUnit(med.get(i).getMedUnit());
                shopedSelectBean.setMedSpec(med.get(i).getMedSpec());
                shopedSelectBean.setMedName(med.get(i).getMedName());
                shopedSelectBean.setMedType(med.get(i).getMedType());
                shopedSelectBean.setMedId(med.get(i).getMedId());
                addShopDatalist(shopedSelectBean);
            }

            //药方中味克金额
            setMescineData();
            //服务费
            setServiceCharge(sumMescine);
        }
    }


    //处方二维码对话框
    private void initMedCodeDialog() {
        medCodeDialog = DialogUtil.createDialog(getContext(), R.layout.dialog_med_evo);
        medCodeDialog.show();
        View view = DialogUtil.view;
        final ImageView ivCancel = view.findViewById(R.id.iv_cancel_med_dialog);
        ImageView ivEvoCode = view.findViewById(R.id.iv_evo_code);
        Button btnSend = view.findViewById(R.id.btn_send_to_customer);
        Button btnShare = view.findViewById(R.id._btn_share_med_to_wx);
        GlideUtil.load(getContext(), savecaseBean.getData().getUrl(), ivEvoCode);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
            }
        });

        //发送给患者
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                Intent intent = new Intent(getContext(), SearchPatientActivity.class);
                intent.putExtra("caseid", savecaseBean.getData().getCaseId());
                startActivity(intent);
            }
        });
        //分享至微信
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                if (savecaseBean.getData().getUrl().isEmpty()) {
                    ToastUtils.show("开方出了点问题，请重新开具");
                } else {
                    JHJKWeChat.getInstance().shareImg(2, savecaseBean.getData().getUrl());
                }
            }
        });
    }

    //智能填写打开填入数据设置
    public void setAllData() {
        //selectTempBean为病例的详情 然后设置整个页面的数据为这个
        if (selectTempBean != null) {
            formulationId = selectTempBean.getFormulationId();//剂型
            symptomText = selectTempBean.getSymptom();//病症名称
            etSymptom.setText(symptomText);
            plural = selectTempBean.getPlural();//付数
            avNum.setNum(plural);
            Logger.e(selectTempBean.getFormulationName());
            setDefaultValueTemp(selectTempBean);//设置剂型，方法 ，频次
            fees = selectTempBean.getFees();//诊金
            setFeesData();
            //药品data
            List<SelectTempBean.MedBean> med = selectTempBean.getMed();
            size = med.size();//味数
            for (int i = 0; i < med.size(); i++) {
                sumMescine += NumberUtils.toDouble(med.get(i).getMedPrice(), 0);//总价
                Logger.e(sumMescine + "");
                //克数
                if ("袋".equals(med.get(i).getMedUnit())) {//单位
                    String medSpec = med.get(i).getMedSpec().replace("g/袋", "");
                    int k = NumberUtils.toInt(medSpec, 1);
                    weight += k;
                } else {
                    weight += med.get(i).getMedNumber();
                }
            }

            List<ShopedSelectBean> dataList = mesicneRecyclerAdapter.getDataList();
            if (dataList != null && dataList.size() != 0) {
                mesicneRecyclerAdapter.removeAll();
            }
            //传入加入药品的数据
            for (int i = 0; i < med.size(); i++) {
                ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                shopedSelectBean.setMedNumber(med.get(i).getMedNumber());
                shopedSelectBean.setMedPrice(NumberUtils.toDouble(med.get(i).getMedPrice(), 0));
                shopedSelectBean.setMedUnit(med.get(i).getMedUnit());
                shopedSelectBean.setMedSpec(med.get(i).getMedSpec());
                shopedSelectBean.setMedName(med.get(i).getMedName());
                shopedSelectBean.setMedType(med.get(i).getMedType());
                shopedSelectBean.setMedId(med.get(i).getMedId());
                addShopDatalist(shopedSelectBean);
            }

            //药方中味克金额
            setMescineData();
            //服务费
            setServiceCharge(sumMescine);
        }
    }


    //药品adapter数据去重加入数据源
    public void addShopDatalist(ShopedSelectBean shopedSelectBean) {
        List<ShopedSelectBean> dataList = mesicneRecyclerAdapter.getDataList();
        if (dataList != null && dataList.size() != 0) {
            //是否存在药方
            int s = 0;
            for (int i = 0; i < dataList.size(); i++) {
                if (dataList.get(i).getMedName().equals(shopedSelectBean.getMedName())) {
                    s++;
                }
            }
            if (s == 0) {
                mesicneRecyclerAdapter.insertItem(shopedSelectBean);
            }
        } else {
            mesicneRecyclerAdapter.insertItem(shopedSelectBean);
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

        avNum.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                plural = amount;
                setMescineData();
            }
        });

        etSymptom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                symptom = etSymptom.getText().toString();
                //智能填写
                boolean aBoolean = SPUtils.getBoolean(getContext(), ConfigKeys.ZHI_FLAG, false);

                if (symptom.isEmpty()) {
                    zhiFlagline.setVisibility(View.GONE);
                } else if (aBoolean) {
                    if(itemFlag==0){
                        setUserData();
                    }else if(itemFlag==1){
                        itemFlag=0;
                    }
                }
            }
        });

        //选择剂型
        tvSelectMedType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果已经添加药品了 再更改剂型会有提示框
                List<ShopedSelectBean> dataList = mesicneRecyclerAdapter.getDataList();
                if (dataList != null && dataList.size() != 0) {
                    HintTitleDialog myDialog = new HintTitleDialog("确定要更改剂型原有药材删除？");
                    myDialog.show(getSupportFragmentManager());
                    myDialog.setOnLeftClick(new HintDialog.OnLeftClick() {
                        @Override
                        public void onLeft() {
                            ToastUtils.show("取消");
                        }
                    });
                    myDialog.setOnRightClick(new HintDialog.OnRightClick() {
                        @Override
                        public void onRight() {
                            myDialog.dismiss();
                            size = 0;
                            weight = 0;
                            sumMescine = 0;
                            setMescineData();
                            serviceChargeCheck.setVisibility(View.GONE);
                            serviceCharge.setVisibility(View.VISIBLE);
                            serviceCharge.setText("请先添加药品  ");
                            mesicneRecyclerAdapter.removeAll();
                        }
                    });
                } else {
                    selectMedTypeDialog();
                }
            }
        });

        //添加药品
        tvSelectMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (metType == -1) {
                    ToastUtils.show("请先选择药品剂型");
                    return;
                }
                Logger.e(metType + "");
                intent2 = new Intent(getContext(), SelectMedActivity.class);
                intent2.putExtra("med_type", metType);//药剂类型
                List<ShopedSelectBean> dataList = mesicneRecyclerAdapter.getDataList();
                intent2.putParcelableArrayListExtra("med_data", (ArrayList<? extends Parcelable>) dataList);//回调导入药品数据
                startActivityForResult(intent2, 111);
            }
        });

        //膏方辅助
        tvSelectWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectingredientsData();
            }
        });

        //诊金
        ivFeeOffOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feeOffOn();
            }
        });

        //保密处方
        ivSecrecyOffOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secrecyValue();
            }
        });

    }

    //添加药品回调设置
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == 222) {
            size = data.getIntExtra("size", 0);//味
            weight = data.getIntExtra("weight", 0);//克数
            sumMescine = data.getFloatExtra("sumMescine", 0);
            Logger.e(size + "");
            Logger.e(weight + "");
            Logger.e(sumMescine + "");
            List<ShopedSelectBean> dataList = data.getParcelableArrayListExtra("shoped");
            Logger.e(dataList.toString());

            List<ShopedSelectBean> dataList1 = mesicneRecyclerAdapter.getDataList();
            Logger.e(dataList1.toString());
            if (dataList1 != null && dataList1.size() != 0) {
                mesicneRecyclerAdapter.removeAll();
                if (dataList != null && dataList.size() != 0) {
                    //传入加入药品的数据
                    for (int i = 0; i < dataList.size(); i++) {
                        ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                        shopedSelectBean.setMedNumber(dataList.get(i).getMedNumber());
                        shopedSelectBean.setMedPrice(dataList.get(i).getMedPrice());
                        shopedSelectBean.setMedUnit(dataList.get(i).getMedUnit());
                        shopedSelectBean.setMedSpec(dataList.get(i).getMedSpec());
                        shopedSelectBean.setMedName(dataList.get(i).getMedName());
                        shopedSelectBean.setMedType(dataList.get(i).getMedType());
                        shopedSelectBean.setMedId(dataList.get(i).getMedId());
                        addShopDatalist(shopedSelectBean);
                    }
                }
            } else {
                if (dataList != null && dataList.size() != 0) {
                    //传入加入药品的数据
                    for (int i = 0; i < dataList.size(); i++) {
                        ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                        shopedSelectBean.setMedNumber(dataList.get(i).getMedNumber());
                        shopedSelectBean.setMedPrice(dataList.get(i).getMedPrice());
                        shopedSelectBean.setMedUnit(dataList.get(i).getMedUnit());
                        shopedSelectBean.setMedSpec(dataList.get(i).getMedSpec());
                        shopedSelectBean.setMedName(dataList.get(i).getMedName());
                        shopedSelectBean.setMedType(dataList.get(i).getMedType());
                        shopedSelectBean.setMedId(dataList.get(i).getMedId());
                        shopedSelectBean.setId(dataList.get(i).getId());
                        addShopDatalist(shopedSelectBean);
                    }
                }
            }

            setMescineData();
            setServiceCharge(sumMescine);
        }
    }

    //更新设置味克金额
    public void setMescineData() {
        medicineTypeNumber.setText(size + "");
        medicineWeight.setText(weight * plural + "");
//        weightPrice.setText(NumberUtils.formatPoint(sumMescine) * plural + "");
        weightPrice.setText(String.format("%.2f", sumMescine*plural));
    }


    //设置服务费
    public void setServiceCharge(double sum) {
        serviceChargeCheck.setVisibility(View.VISIBLE);
        serviceCharge.setVisibility(View.GONE);

        int costThree = (int) Math.round(sum * 0.3);
        int costTwo = (int) Math.round(sum * 0.2);
        int costOne = (int) Math.round(sum * 0.1);

        serviceFee = costThree;

        serviceChargeCheck.setText("每付" + costThree + "元  ");
        serviceChargeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                down(costThree, costTwo, costOne);
            }
        });
    }

    //服务费底部弹出框
    public void down(int costThree, int costTwo, int costOne) {

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.show();
        Window window = BottomDialogCreator.getDialogWindow(alertDialog, R.layout.dialog_down);
        if (window != null) {
            TextView textView = window.findViewById(R.id.iv_med_type);
            TextView textThree = window.findViewById(R.id.text_three);
            ImgThree = window.findViewById(R.id.img_three);
            TextView textTwo = window.findViewById(R.id.text_two);
            ImgTwo = window.findViewById(R.id.img_two);
            TextView textOne = window.findViewById(R.id.text_one);
            ImgOne = window.findViewById(R.id.img_one);
            ImgZero = window.findViewById(R.id.img_zero);


            textThree.setText("每付" + costThree + "元");
            textTwo.setText("每付" + costTwo + "元");
            textOne.setText("每付" + costOne + "元");

            switch (flag) {
                case 3:
                    SetCheckImg(3);
                    break;
                case 2:
                    SetCheckImg(2);
                    break;
                case 1:
                    SetCheckImg(1);
                    break;
                case 0:
                    SetCheckImg(0);
                    break;
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });


            ImgThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(3);
                    flag = 3;
                    serviceChargeCheck.setText("每付" + costThree + "元  ");
                    serviceFee = costThree;
                    alertDialog.dismiss();
                }
            });
            ImgTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(2);
                    flag = 2;
                    serviceChargeCheck.setText("每付" + costTwo + "元  ");
                    serviceFee = costTwo;
                    alertDialog.dismiss();
                }
            });
            ImgOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(1);
                    flag = 1;
                    serviceChargeCheck.setText("每付" + costOne + "元  ");
                    serviceFee = costOne;
                    alertDialog.dismiss();
                }
            });
            ImgZero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(0);
                    flag = 0;
                    serviceChargeCheck.setText("免费");
                    serviceFee = 0;
                    alertDialog.dismiss();
                }
            });

        }
    }

    //设置底部弹出服务费的选中情况
    public void SetCheckImg(int flag) {
        ImgThree.setImageResource(R.mipmap.select1);
        ImgTwo.setImageResource(R.mipmap.select1);
        ImgOne.setImageResource(R.mipmap.select1);
        ImgZero.setImageResource(R.mipmap.select1);
        switch (flag) {
            case 3:
                ImgThree.setImageResource(R.mipmap.select);
                break;
            case 2:
                ImgTwo.setImageResource(R.mipmap.select);
                break;
            case 1:
                ImgOne.setImageResource(R.mipmap.select);
                break;
            case 0:
                ImgZero.setImageResource(R.mipmap.select);
                break;
        }
    }

    //联想病例下拉单个详情数据
    public void getPromiseData(int id, int templateType) {
        /**id 1
         templateType 1 模板详情 2 协定方详情
         */
        Logger.e("id:" + id + "**templateType:" + templateType);
        OkHttpUtils.get(ConfigKeys.SELECTTEMPORPROMISE + "?id=" + id + "&templateType=" + templateType, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectTempBean = Json.parseObj(response, SelectTempBean.class);
                handler.sendEmptyMessage(2);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //病例智能填写
    public void setUserData() {

        OkHttpUtils.get(ConfigKeys.SELECTSUGGEST + "?symptom=" + symptom, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectSuggestBeans = Json.parseArr(response, SelectSuggestBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //保密处方
    public void secrecyValue() {
        if (secpecy == 0) {
            secpecy = 1;
            ivSecrecyOffOn.setImageResource(R.mipmap.on);
        } else {
            secpecy = 0;
            ivSecrecyOffOn.setImageResource(R.mipmap.off);
        }
    }


    //获取膏方data
    public void getSelectingredientsData() {

        OkHttpUtils.get(ConfigKeys.SELECTINGREDIENTS, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectingredientsBeans = Json.parseArr(response, SelectingredientsBean.class);
                handler.sendEmptyMessage(3);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }


    //选择膏方辅料
    private void showWoringPop(final List<SelectingredientsBean> selectingredientsBeans) {

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.show();
        Window window = BottomDialogCreator.getDialogWindow(alertDialog, R.layout.dialog_med_type);
        if (window != null) {
            ImageView imageView = window.findViewById(R.id.iv_med_type_cancel);
            MyGridView gridView = window.findViewById(R.id.gv_med_type);
            TextView tvPopTitle = window.findViewById(R.id.tv_pop_title);
            tvPopTitle.setText("选择膏方辅料");
            final WorkingAdapter workingAdapter = new WorkingAdapter(selectingredientsBeans, getContext());
            gridView.setAdapter(workingAdapter);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    alertDialog.dismiss();
                    wordInfoRecycler.setVisibility(View.VISIBLE);
                    String name = selectingredientsBeans.get(position).getName();
                    SelectingBean selectingBean = new SelectingBean();
                    selectingBean.setName(name);
                    setDataList(selectingBean);
                }
            });
        }
    }

    //导入数据
    public void setDataList(SelectingBean selectingBean) {
        List<SelectingBean> dataList = wordInfoRecyclerAdapter.getDataList();
        if (dataList == null && dataList.size() == 0) {
            wordInfoRecyclerAdapter.insertItem(selectingBean);
        } else {
            //是否存在药方
            boolean isHave = false;
            for (int j = 0; j < dataList.size(); j++) {
                if (dataList.get(j).getName().equals(selectingBean.getName())) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                wordInfoRecyclerAdapter.insertItem(selectingBean);
            } else {
                ToastUtils.show("存在该药方");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_evo:
                getAllData();
                break;
        }
    }


    //立即开方
    public void getAllData() {
        symptomText = etSymptom.getText().toString();//病症名称
        if (!symptomText.isEmpty()) {
            if (formulationId == -1) {
                ToastUtils.show("请选择剂型");
            } else {
                //诊金
                if (feesFlag) fees = Integer.valueOf(etFees.getText().toString());
                freq = tvFreq.getText().toString();
                if (!freq.isEmpty()) {
                    meth = tvMeth.getText().toString();
                    if (!meth.isEmpty()) {
                        //药品字符串medStr
                        List<ShopedSelectBean> shopedSelectBeanList = mesicneRecyclerAdapter.getDataList();
                        if (shopedSelectBeanList != null && shopedSelectBeanList.size() != 0) {
                            medJsonArray = new JSONArray();
                            JSONObject jsonObject;
                            for (int i = 0; i < shopedSelectBeanList.size(); i++) {
                                jsonObject = new JSONObject();
                                Logger.e(shopedSelectBeanList.get(i).getMedName());
                                jsonObject.put("id", shopedSelectBeanList.get(i).getId());
                                jsonObject.put("medId", shopedSelectBeanList.get(i).getMedId());
                                jsonObject.put("MedFrying", shopedSelectBeanList.get(i).getMedFry());
                                jsonObject.put("medNumber", shopedSelectBeanList.get(i).getMedNumber());
                                jsonObject.put("medPrice", shopedSelectBeanList.get(i).getMedPrice());
                                medJsonArray.add(jsonObject);
                            }


                            //辅料
                            if (workingType == 1) {
                                List<SelectingBean> dataList = wordInfoRecyclerAdapter.getDataList();
                                StringBuffer stringBuffer = new StringBuffer();
                                if (dataList != null && dataList.size() != 0) {
                                    for (int i = 0; i < dataList.size(); i++) {
                                        String name = dataList.get(i).getName();
                                        int number = dataList.get(i).getNumber();
                                        String s = "使用辅料 " + name + ":" + number + "克,";
                                        stringBuffer.append(s);
                                    }
                                    StringBuffer delete = stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
                                    ingredients = delete.toString();
                                } else {
                                    ToastUtils.show("请选择辅料！");
                                }
                            }

                            //药品检测
                            List<Map<String, String>> tempListMed = MedUtil.getTempListMed(shopedSelectBeanList);
                            if (tempListMed.size() > 0) {//有毒品
                                mlvCheckMed.setAdapter(new CheckMedTipAdapter(getContext(), tempListMed));
                                btnSure.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        checkMedDialog.dismiss();
                                        Logger.e("*病症名称：" + symptomText);
                                        Logger.e("*医生id：" + docId);
                                        Logger.e("*付数：" + plural);
                                        Logger.e("*剂型id：" + formulationId);
                                        Logger.e("*诊金：" + fees);
                                        Logger.e("*是否公开：" + secpecy);
                                        Logger.e("*频次：" + freq);
                                        Logger.e("*方法：" + meth);
                                        Logger.e("*辅料：" + ingredients);
                                        Logger.e("*开方类型：" + isImg);
                                        Logger.e("*药品字符串：" + medJsonArray);
                                        Logger.e("*服务费：" + serviceFee);

                                        evolution();
                                    }
                                });
                                checkMedDialog.show();

                            } else {//无毒品 直接开方
                                Logger.e("*病症名称：" + symptomText);
                                Logger.e("*医生id：" + docId);
                                Logger.e("*付数：" + plural);
                                Logger.e("*剂型id：" + formulationId);
                                Logger.e("*诊金：" + fees);
                                Logger.e("*是否公开：" + secpecy);
                                Logger.e("*频次：" + freq);
                                Logger.e("*方法：" + meth);
                                Logger.e("*辅料：" + ingredients);
                                Logger.e("*开方类型：" + isImg);
                                Logger.e("*药品字符串：" + medJsonArray);
                                Logger.e("*服务费：" + serviceFee);

                                evolution();
                            }
                        } else {
                            ToastUtils.show("请先添加药材！");
                        }
                    } else {
                        ToastUtils.show("请先添加用药方法");
                    }
                } else {
                    ToastUtils.show("请先添加用药频次");
                }
            }
        } else {
            ToastUtils.show("请输入病症名称！");
            etSymptom.requestFocus();
        }
    }

    //跳转过来的入口不一样进行不同的开方操作
    public void evolution() {
        //0在线开方 1新建模板 2使用模板开方 3更改用药 4新开处方
        if (orinType == 0) {
            postData();
        }else if (orinType == 3) {
            updatePostData(2);
        }else if (orinType == 4) {
            updatePostData(1);
        }else if (orinType == 2) {
            postData();
        }else if(orinType==1){
            AddLate();
        }
    }

    //新建模板
    public void AddLate(){
        Config.showProgressDialog(getContext(),"正在上传信息，请稍等...");

        JSONObject object = new JSONObject();
        object.put("symptom", symptomText);//病症名
        object.put("docId", docId);//医生id
        object.put("plural", plural);//付数
        object.put("formulationId", formulationId);//剂型id
        object.put("isImg", isImg);//开方类型
        object.put("fees", fees);//诊金
        object.put("secpecy", secpecy);//是否公开
        object.put("freq", freq);//频次
        object.put("medMethod", meth);//方法
        object.put("medStrList", medJsonArray);//药品字符串
        object.put("ingredients", ingredients);//辅料
        object.put("serviceFee", serviceFee);//服务费
        RestClient.builder().url(ConfigKeys.TEMPLATEADD).raw(object.toJSONString()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Log.i("aa", "onSuccess: " + response);
                Config.dismissProgressDialog();
                ToastUtils.show("添加成功！");
                setResult(2020);
                finish();
            }
        })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String message) {
                        Logger.e("onFailure: " + message);
                        Config.dismissProgressDialog();
                        ToastUtils.show(message);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Logger.e("onFailure: " + msg);
                        Config.dismissProgressDialog();
                        ToastUtils.show(msg);
                    }
                }).build().post();
    }

    //更改用药 option  1 新开处方 2 是更改用药
    public void updatePostData(int option) {
        Config.showProgressDialog(getContext(),"正在上传信息，请稍等...");

        JSONObject object = new JSONObject();
        object.put("symptom", symptomText);//病症名
        object.put("docId", docId);//医生id
        object.put("plural", plural);//付数
        object.put("formulationId", formulationId);//剂型id
        object.put("isImg", isImg);//开方类型
        object.put("fees", fees);//诊金
        object.put("secpecy", secpecy);//是否公开
        object.put("freq", freq);//频次
        object.put("medMethod", meth);//方法
        object.put("medStrList", medJsonArray);//药品字符串
        object.put("ingredients", ingredients);//辅料
        object.put("serviceFee", serviceFee);//服务费
        object.put("customerId",customerId);//患者id
        object.put("option",option);//更改用药 1 新开处方 2 是更改用药
        RestClient.builder().url(ConfigKeys.SAVECASETOCUSTOMER).raw(object.toJSONString()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Config.dismissProgressDialog();
                Logger.e(response);
                if(option==1){
                    setResult(3333);//回调 新开处方
                }else if(option==2){
                    setResult(2222);//更改用药
                }
                finish();
            }
        })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String message) {
                        Logger.e("onFailure: " + message);
                        Config.dismissProgressDialog();
                        ToastUtils.show(message);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Logger.e("onFailure: " + msg);
                        Config.dismissProgressDialog();
                        ToastUtils.show(msg);
                    }
                }).build().post();
    }

    //在线开方
    public void postData() {
        Config.showProgressDialog(getContext(),"正在上传信息，请稍等...");

        JSONObject object = new JSONObject();
        object.put("symptom", symptomText);//病症名
        object.put("docId", docId);//医生id
        object.put("plural", plural);//付数
        object.put("formulationId", formulationId);//剂型id
        object.put("isImg", isImg);//开方类型
        object.put("fees", fees);//诊金
        object.put("secpecy", secpecy);//是否公开
        object.put("freq", freq);//频次
        object.put("medMethod", meth);//方法
        object.put("medStrList", medJsonArray);//药品字符串
        object.put("ingredients", ingredients);//辅料
        object.put("serviceFee", serviceFee);//服务费
        RestClient.builder().url(ConfigKeys.SAVECASE).raw(object.toJSONString()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Config.dismissProgressDialog();
                Log.i("aa", "onSuccess: " + response);
                savecaseBean = Json.parseObj(response, SavecaseBean.class);
                handler.sendEmptyMessage(4);
            }
        })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String message) {
                        Logger.e("onFailure: " + message);
                        Config.dismissProgressDialog();
                        ToastUtils.show(message);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Logger.e("onFailure: " + msg);
                        Config.dismissProgressDialog();
                        ToastUtils.show(msg);
                    }
                }).build().post();
    }

    //获取剂型data
    public void getSelectFormulationData() {
        OkHttpUtils.get(ConfigKeys.SELECTFORMULATION, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectFromulationBeans = Json.parseArr(response, SelectFromulationBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }


    //选择剂型弹窗
    private void selectMedTypeDialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.show();
        Window window = BottomDialogCreator.getDialogWindow(alertDialog, R.layout.dialog_med_type);
        if (window != null) {
            ImageView imageView = window.findViewById(R.id.iv_med_type_cancel);
            MyGridView gridView = window.findViewById(R.id.gv_med_type);
            final MedTypeAdapter medTypeAdapter = new MedTypeAdapter(selectFromulationBeans, getContext(), selectPosition);
            gridView.setAdapter(medTypeAdapter);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectPosition = position;
                    SelectFromulationBean dataBean = selectFromulationBeans.get(position);
                    formulationId = dataBean.getId();//剂型id
                    //中西成药
                    if (dataBean.getCaseType() == 1) {
//                        Intent intent = new Intent(getContext(), WestEvoActivity.class);
//                        startActivity(intent);
                        ToastUtils.show("暂无！");
                    }
                    setDefaultValue(dataBean);
                    alertDialog.dismiss();
                }
            });
        }
    }

    //设置剂型，方法 ，频次
    private void setDefaultValue(SelectFromulationBean dataBean) {
        metType = dataBean.getCaseType();
        workingType = dataBean.getWorkingType();
        if (workingType == 1) {
            llWorking.setVisibility(View.VISIBLE);
        } else {
            llWorking.setVisibility(View.GONE);
        }
        tvSelectMedType.setText(dataBean.getName());
        freq = dataBean.getFreq();
        meth = dataBean.getMedMethod();
        tvFreq.setText(freq);
        tvMeth.setText(meth);
    }

    //设置剂型，方法 ，频次(协定方进来设置数据)
    private void setDefaultValueTemp(SelectTempBean dataBean) {
        metType = dataBean.getCaseType();
        workingType = dataBean.getWorkingType();
        if (workingType == 1) {
            llWorking.setVisibility(View.VISIBLE);
        } else {
            llWorking.setVisibility(View.GONE);
        }
        tvSelectMedType.setText(dataBean.getFormulationName());
        freq = dataBean.getFreq();
        meth = dataBean.getMedMethod();
        tvFreq.setText(freq);
        tvMeth.setText(meth);
    }

    //设置剂型，方法 ，频次(使用模板开方)
    private void setDefaultValueTemplate(CaseTemplateBean dataBean) {
        metType = dataBean.getCaseType();
        workingType = dataBean.getWorkingType();
        if (workingType == 1) {
            llWorking.setVisibility(View.VISIBLE);
        } else {
            llWorking.setVisibility(View.GONE);
        }
        tvSelectMedType.setText(dataBean.getFormulationName());
        freq = dataBean.getFreq();
        meth = dataBean.getMedMethod();
        tvFreq.setText(freq);
        tvMeth.setText(meth);
    }

    //设置剂型，方法 ，频次(更改用药)
    private void updateDefaultValueTemplate(SelectByIdBean selectByIdBean) {
        metType = selectByIdBean.getCaseType();
        workingType = selectByIdBean.getWorkingType();
        if (workingType == 1) {
            llWorking.setVisibility(View.VISIBLE);
        } else {
            llWorking.setVisibility(View.GONE);
        }
        tvSelectMedType.setText(selectByIdBean.getFormulationName());
        freq = selectByIdBean.getFreq();
        meth = selectByIdBean.getMedMethod();
        tvFreq.setText(freq);
        tvMeth.setText(meth);
    }
}

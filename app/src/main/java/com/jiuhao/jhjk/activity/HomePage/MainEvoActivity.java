package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.GridListAdapter.CheckMedTipAdapter;
import com.jiuhao.jhjk.adapter.GridListAdapter.MedTypeAdapter;
import com.jiuhao.jhjk.adapter.GridListAdapter.WorkingAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.MesicneRecyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.SelectSuggestRecyclerAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.WordInfoRecyclerAdapter;
import com.jiuhao.jhjk.bean.*;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.dialog.HintTitleDialog;
import com.jiuhao.jhjk.utils.*;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.AmountView;
import com.jiuhao.jhjk.view.MyGridView;
import com.jiuhao.jhjk.view.MyListView;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private ScrollView scrollView;
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
    private AmountView avNum;
    private EditText tvFreq;
    private EditText tvMeth;
    private ImageView ivFeeOffOn;
    /**
     * 免费
     */
    private TextView tvFees;
    private EditText etFees;
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

    private int docId;//医生id
    private int size = 0;//味数
    private int fees = 0;//诊金
    private String freq;//用药频次
    private String meth;//用药方法
    private int plural = 7;//付数
    private int weight = 0;//总克数
    private String symptom;//病症内容
    private int serviceFee = 0;//服务费
    private double sumMescine = 0;//总价
    private String symptomText;//病症名称
    private int metType = -1;//处方药剂类型
    private int formulationId = -1;//剂型id
    private boolean feesFlag = false;//诊金
    private String ingredients = "";//辅料
    private String medStr = "";//药品字符串
    private int selectPosition = 0;//标记选中
    private int workingType = -1;//加工方式 1加工辅助显示
    private int secpecy = 0;//保密处方 0关闭 1打开  0公开 1不公开
    private int isImg = 1;//开方类型 1文字方 2 图片方 3核价完成后的图片处方

    private Intent intent2;//添加药品
    private Intent intent;//MainEvoActivity
    private Button btnSure;//确定开方
    private MyListView mlvCheckMed;//！listview
    private SelectTempBean selectTempBean;//病例详情数据源
    private SaveImageBean saveImageBean;//在线开方 二维码bean
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

    private int orinType = 0;//0在线开方(新开处方) 1新建模板 2使用模板开方 3更改用药
    private CaseTemplateBean caseTemplateBean;//2模板数据源
    private boolean isinit = false;//标记初始化控件
    private SelectByIdBean selectByIdBean;//更改用药数据源

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
                        scrollView.setVisibility(View.VISIBLE);
                        selectSuggestRecyclerAdapter =
                                new SelectSuggestRecyclerAdapter(getContext(), selectSuggestBeans,
                                        new SelectSuggestRecyclerAdapter.instem() {
                                            @Override
                                            public void onClickin(int postion) {
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
        avNum = (AmountView) findViewById(R.id.av_num);
        tvFreq = (EditText) findViewById(R.id.tv_freq);
        tvMeth = (EditText) findViewById(R.id.tv_meth);
        ivFeeOffOn = (ImageView) findViewById(R.id.iv_fee_off_on);
        tvFees = (TextView) findViewById(R.id.tv_fees);
        etFees = (EditText) findViewById(R.id.et_fees);
        ivSecrecyOffOn = (ImageView) findViewById(R.id.iv_secrecy_off_on);
        btnEvo = (Button) findViewById(R.id.btn_evo);
        recyclerZhiFlag = (RecyclerView) findViewById(R.id.recycler_zhi_flag);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
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

        } else if (orinType == 1) {

        } else if (orinType == 2) {//使用模板
            caseTemplateBean = (CaseTemplateBean) intent.getSerializableExtra("templateBean");
            if (isinit) setTemplateData();
        } else if (orinType == 3) {//更改用药
            selectByIdBean = (SelectByIdBean) intent.getSerializableExtra("selectDetailsBean");
            if (isinit) updateTemplateData();
        }

    }

    //更改用药
    public void updateTemplateData() {
        if (selectByIdBean != null) {
            formulationId = selectByIdBean.getFormulationId();//剂型
            symptomText = selectByIdBean.getSymptom();//病症名称
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
                this.size += med.get(i).getMedNumber();//味数
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
            feesFlag = false;
            etFees.setVisibility(View.GONE);
            ivFeeOffOn.setImageResource(R.mipmap.off);
        } else {
            tvFees.setText("诊金：");
            feesFlag = true;
            etFees.setVisibility(View.VISIBLE);
            etFees.setText(fees + "");
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
                this.size += med.get(i).getMedNumber();//味数
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
        Glide.with(getContext()).load(saveImageBean.getUrl()).into(ivEvoCode);
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
                intent.putExtra("caseid", saveImageBean.getCaseId());
                startActivity(intent);
            }
        });
        //分享至微信
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medCodeDialog.dismiss();
                if (saveImageBean.getUrl().isEmpty()) {
                    ToastUtils.show("开方出了点问题，请重新开具");
                } else {
                    JHJKWeChat.getInstance().shareImg(2, saveImageBean.getUrl());
                }
            }
        });
    }

    //智能填写打开填入数据设置
    public void setAllData() {
        recyclerZhiFlag.setVisibility(View.GONE);
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
            for (int i = 0; i < med.size(); i++) {
                size += med.get(i).getMedNumber();//味数
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
                    scrollView.setVisibility(View.GONE);
                } else if (aBoolean) {
                    setUserData();
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
        weightPrice.setText(NumberUtils.formatPoint(sumMescine) * plural + "");
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
            ImageView ImgThree = window.findViewById(R.id.img_three);
            TextView textTwo = window.findViewById(R.id.text_two);
            ImageView ImgTwo = window.findViewById(R.id.img_two);
            TextView textOne = window.findViewById(R.id.text_one);
            ImageView ImgOne = window.findViewById(R.id.img_one);
            ImageView ImgZero = window.findViewById(R.id.img_zero);

            textThree.setText("每付" + costThree + "元");
            textTwo.setText("每付" + costTwo + "元");
            textOne.setText("每付" + costOne + "元");

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

            ImgThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(ImgThree, ImgTwo, ImgOne, ImgZero, 3);
                    serviceChargeCheck.setText("每付" + costThree + "元");
                    serviceFee = costThree;
                    alertDialog.dismiss();
                }
            });
            ImgTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(ImgThree, ImgTwo, ImgOne, ImgZero, 2);
                    serviceChargeCheck.setText("每付" + costTwo + "元");
                    serviceFee = costTwo;
                    alertDialog.dismiss();
                }
            });
            ImgOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(ImgThree, ImgTwo, ImgOne, ImgZero, 1);
                    serviceChargeCheck.setText("每付" + costOne + "元");
                    serviceFee = costOne;
                    alertDialog.dismiss();
                }
            });
            ImgZero.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SetCheckImg(ImgThree, ImgTwo, ImgOne, ImgZero, 0);
                    serviceChargeCheck.setText("免费");
                    serviceFee = 0;
                    alertDialog.dismiss();
                }
            });

        }
    }

    //设置底部弹出服务费的选中情况
    public void SetCheckImg(ImageView imgThree, ImageView imgTwo, ImageView imgOne, ImageView imgZero, int flag) {
        imgThree.setImageResource(R.mipmap.select1);
        imgTwo.setImageResource(R.mipmap.select1);
        imgOne.setImageResource(R.mipmap.select1);
        imgZero.setImageResource(R.mipmap.select1);
        switch (flag) {
            case 3:
                imgThree.setImageResource(R.mipmap.select);
                break;
            case 2:
                imgTwo.setImageResource(R.mipmap.select);
                break;
            case 1:
                imgOne.setImageResource(R.mipmap.select);
                break;
            case 0:
                imgZero.setImageResource(R.mipmap.select);
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

    //设置诊金
    public void feeOffOn() {
        if (feesFlag) {
            tvFees.setText("免费");
            getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            etFees.setVisibility(View.GONE);
            feesFlag = false;
            ivFeeOffOn.setImageResource(R.mipmap.off);
        } else {
            tvFees.setText("诊金：");
            feesFlag = true;
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(etFees, InputMethodManager.SHOW_IMPLICIT);
            etFees.setVisibility(View.VISIBLE);
            etFees.requestFocus();
            ivFeeOffOn.setImageResource(R.mipmap.on);
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
                            StringBuffer stringBufferJson = new StringBuffer();
                            stringBufferJson.append("[");
                            for (int i = 0; i < shopedSelectBeanList.size(); i++) {
                                String signString = JSON.toJSONString(shopedSelectBeanList.get(i));
                                stringBufferJson.append(signString + ",");
                            }
                            stringBufferJson.append("]");
                            medStr = stringBufferJson.delete(stringBufferJson.length() - 2, stringBufferJson.length() - 1).toString();

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
                                        postData();
                                    }
                                });
                                checkMedDialog.show();

                            } else {//无毒品 直接开方
                                postData();
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

    //在线开方
    public void postData() {

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
        Logger.e("*药品字符串：" + medStr);
        Logger.e("*服务费：" + serviceFee);

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
        linkedHashMap.put("symptom", symptomText);//病症名
        linkedHashMap.put("docId", docId + "");//医生id
        linkedHashMap.put("plural", plural + "");//付数
        linkedHashMap.put("formulationId", formulationId + "");//剂型id
        linkedHashMap.put("isImg", isImg + "");//开方类型
        linkedHashMap.put("fees", fees + "");//诊金
        linkedHashMap.put("secpecy", secpecy + "");//是否公开
        linkedHashMap.put("freq", freq);//频次
        linkedHashMap.put("medMethod", meth);//方法
        linkedHashMap.put("medStr", medStr);//药品字符串
        linkedHashMap.put("ingredients", ingredients);//辅料
        linkedHashMap.put("serviceFee", serviceFee);//服务费

        OkHttpUtils.postJson(ConfigKeys.SAVECASE, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                saveImageBean = Json.parseObj(response, SaveImageBean.class);
                handler.sendEmptyMessage(4);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
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

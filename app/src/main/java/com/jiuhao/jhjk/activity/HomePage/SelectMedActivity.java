package com.jiuhao.jhjk.activity.HomePage;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.SelectMedRecyclerAdapter2;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.ShopedSelectRecyclerAdapter;
import com.jiuhao.jhjk.bean.SelectByIdBean;
import com.jiuhao.jhjk.bean.SelectTempBean;
import com.jiuhao.jhjk.bean.ShopedSelectBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.dialog.HintTitleDialog;
import com.jiuhao.jhjk.utils.NumberUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 添加药品
 */
public class SelectMedActivity extends BaseActivity {

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
     * 导入药方
     */
    private TextView tvImport;
    /**
     * 清空药方
     */
    private TextView tvClearMed;
    private LinearLayout llMethMed;
    /**
     * 已选药品价格：
     */
    private TextView tvLlllll;
    /**
     * $0
     */
    private TextView moneyMedicine;
    private RecyclerView medicineRecycler;
    private RecyclerView listRecycler;
    /**
     * 请输入药名首字母或药名添加
     */
    private LinearLayout addLin;
    private ScrollView scSelectMed;
    private Intent intent;
    private float sumMescine = 0;//总价格
    private int metType;//药剂类型
    private String keyword;//药品首字母或者全拼 关键字
    private List<ShopedSelectBean> shopedSelectBeans;//查询药品数据源
    private List<ShopedSelectBean> shopedSelectBeanList = new ArrayList<>();//所有药品列表数据
    private ShopedSelectRecyclerAdapter shopedSelectRecyclerAdapter;
    private SelectMedRecyclerAdapter2 selectMedRecyclerAdapter2;
    private View footer;
    private EditText etMedName;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (shopedSelectBeans.size() != 0 && !shopedSelectBeans.isEmpty()) {
                        listRecycler.setVisibility(View.VISIBLE);
                        shopedSelectRecyclerAdapter = new ShopedSelectRecyclerAdapter(getContext(), shopedSelectBeans, new ShopedSelectRecyclerAdapter.instem() {
                            @Override
                            public void onClickInstem(int postion) {
                                etMedName.setText("");
                                ShopedSelectBean shopedSelectBean = shopedSelectBeans.get(postion);
                                setDataList(shopedSelectBean);
                            }
                        });
                        listRecycler.setAdapter(shopedSelectRecyclerAdapter);
                        scSelectMed.post(new Runnable() {
                            public void run() {
                                scSelectMed.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                    } else {
                        ToastUtils.show("没有找到和“" + keyword + "”相关的内容");
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_select_med);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        tvImport = (TextView) findViewById(R.id.tv_import);
        tvClearMed = (TextView) findViewById(R.id.tv_clear_med);
        llMethMed = (LinearLayout) findViewById(R.id.ll_meth_med);
        tvLlllll = (TextView) findViewById(R.id.tv_llllll);
        moneyMedicine = (TextView) findViewById(R.id.money_medicine);
        medicineRecycler = (RecyclerView) findViewById(R.id.medicine_recycler);
        listRecycler = (RecyclerView) findViewById(R.id.list_recycler);
        scSelectMed = (ScrollView) findViewById(R.id.sc_select_med);
        tvTitle.setText("添加药材");
        rlTitleSure.setVisibility(View.VISIBLE);
        medicineRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        listRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void obtainData() {

        footer = LayoutInflater.from(this).inflate(R.layout.et_med_name_layout, null, false);
        etMedName = footer.findViewById(R.id.et_med_name);

        intent = getIntent();
        metType = intent.getIntExtra("med_type", -1);
        List<ShopedSelectBean> shopedSelectBeans = intent.getParcelableArrayListExtra("med_data");

        selectMedRecyclerAdapter2 = new SelectMedRecyclerAdapter2(R.layout.item_select_med, shopedSelectBeanList,
                new SelectMedRecyclerAdapter2.instem() {
                    @Override
                    public void onClickInst(int postion) {
                        selectMedRecyclerAdapter2.remove(postion);
                    }
                },
                new SelectMedRecyclerAdapter2.insnum() {
                    @Override
                    public void onClinkInsNum(HashMap<String, Float> herbsPriceMap) {
                        sumMescine = 0;
                        for (Float v : herbsPriceMap.values()) {
                            sumMescine += v;
                        }
                        moneyMedicine.setText("￥" + NumberUtils.formatPoint(sumMescine));
                    }
                });
        medicineRecycler.setAdapter(selectMedRecyclerAdapter2);
        selectMedRecyclerAdapter2.addFooterView(footer);

        if (shopedSelectBeans != null && shopedSelectBeans.size() != 0) {
            for (int i = 0; i < shopedSelectBeans.size(); i++) {
                setDataList(shopedSelectBeans.get(i));
            }
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

        //导入药方
        tvImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ImportMedActivity.class);
                startActivityForResult(intent, 101);
            }
        });
        //清空药方
        tvClearMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectMedRecyclerAdapter2.getData().size() != 0 && !selectMedRecyclerAdapter2.getData().isEmpty()) {
                    HintTitleDialog myDialog = new HintTitleDialog("确定要清空药方吗？");
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
                            sumMescine = 0;
                            moneyMedicine.setText("￥0.00");
                            selectMedRecyclerAdapter2.getData().clear();
                            selectMedRecyclerAdapter2.notifyDataSetChanged();
                        }
                    });
                } else {
                    ToastUtils.show("无药方可清除！");
                }
            }
        });

        //添加药方 增加recycler长度
        etMedName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listRecycler.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                keyword = etMedName.getText().toString();//输入首字母或者药品的全拼
                if (!keyword.isEmpty()) {
                    examineData();
                }
            }
        });

        etMedName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    return true;
                }
                return false;
            }
        });

        //确定
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShopedSelectBean> shopedSelectBeans = selectMedRecyclerAdapter2.getData();
                Logger.e(shopedSelectBeans.toString());
                if (shopedSelectBeans != null && shopedSelectBeans.size() != 0) {
                    int size = shopedSelectBeans.size();//几位药
                    int weight = 0;//克数
                    for (int i = 0; i < shopedSelectBeans.size(); i++) {
                        if ("袋".equals(shopedSelectBeans.get(i).getMedUnit())) {
                            String medSpec = shopedSelectBeans.get(i).getMedSpec().replace("g/袋", "");
                            int k = NumberUtils.toInt(medSpec, 1);
                            weight += k;
                        } else {
                            weight += shopedSelectBeans.get(i).getMedNumber();
                        }
                    }
                    Logger.e("*" + size + "");
                    Logger.e("*" + weight + "");
                    Logger.e("*" + sumMescine + "");
                    intent.putExtra("size", size);
                    intent.putExtra("weight", weight);
                    intent.putExtra("sumMescine", sumMescine);//总价格
                    intent.putParcelableArrayListExtra("shoped", (ArrayList<? extends Parcelable>) shopedSelectBeans);
                    setResult(222, intent);
                    finish();
                } else {
                    intent.putExtra("size", 0);
                    intent.putExtra("weight", 0);
                    intent.putExtra("sumMescine", 0.00);//总价格
                    intent.putParcelableArrayListExtra("shoped", (ArrayList<? extends Parcelable>) shopedSelectBeans);
                    setResult(222, intent);
                    finish();
                }
            }
        });
    }

    //药品根据首字母查询
    public void examineData() {
        /**
         * keyword 输入关键字
         medType 药品类型
         */
        Logger.e(keyword + "***" + metType);
        OkHttpUtils.get(ConfigKeys.SHOPMEDSELECT + "?keyword=" + keyword + "&medType=" + metType, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                shopedSelectBeans = Json.parseArr(response, ShopedSelectBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102) {//导入药方-处方记录（插入数据 adapter刷新）
            List<SelectByIdBean.MedBean> med = (List<SelectByIdBean.MedBean>) data.getSerializableExtra("med");
            Logger.e(med.toString());
            for (int i = 0; i < med.size(); i++) {
                ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                shopedSelectBean.setMedId(med.get(i).getMedId());
                shopedSelectBean.setMedName(med.get(i).getMedName());
                shopedSelectBean.setMedType(med.get(i).getMedType());
                shopedSelectBean.setMedSpec(med.get(i).getMedSpec());
                shopedSelectBean.setMedUnit(med.get(i).getMedUnit());
                shopedSelectBean.setMedNumber(med.get(i).getMedNumber());
                shopedSelectBean.setMedPrice(med.get(i).getMedPrice());
                setDataList(shopedSelectBean);
            }
        } else if (requestCode == 101 && resultCode == 103) {//导入药方-协定方（插入数据 adapter刷新）
            List<SelectTempBean.MedBean> beanList = (List<SelectTempBean.MedBean>) data.getSerializableExtra("med");
            Logger.e(beanList.toString());
            for (int i = 0; i < beanList.size(); i++) {
                ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                shopedSelectBean.setMedId(beanList.get(i).getMedId());
                shopedSelectBean.setMedName(beanList.get(i).getMedName());
                shopedSelectBean.setMedType(beanList.get(i).getMedType());
                shopedSelectBean.setMedSpec(beanList.get(i).getMedSpec());
                shopedSelectBean.setMedUnit(beanList.get(i).getMedUnit());
                shopedSelectBean.setMedNumber(beanList.get(i).getMedNumber());
                shopedSelectBean.setMedPrice(NumberUtils.toDouble(beanList.get(i).getMedPrice(), 0));
                setDataList(shopedSelectBean);
            }
        }
    }

    //导入数据
    public void setDataList(ShopedSelectBean shopedSelectBean) {
        List<ShopedSelectBean> dataList = selectMedRecyclerAdapter2.getData();
        if (dataList == null && dataList.size() == 0) {
            selectMedRecyclerAdapter2.addData(shopedSelectBean);
        } else {
            //是否存在药方
            int s = 0;
            for (int j = 0; j < dataList.size(); j++) {
                if (dataList.get(j).getMedName().equals(shopedSelectBean.getMedName())) {
                    s++;
                }
            }
            if (s == 0) {
                int medNumber = shopedSelectBean.getMedNumber();//克数/袋数
                double medPrice = shopedSelectBean.getMedPrice();//单价
                double v = medNumber * medPrice;
                float itemNum = new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();//单个药品总价

                if (medNumber != 0) {
                    selectMedRecyclerAdapter2.herbsNumMap.put(shopedSelectBean.getMedName(), medNumber + "");
                }
                selectMedRecyclerAdapter2.herbsPriceMap.put(shopedSelectBean.getMedName(), itemNum);

                sumMescine = 0;
                for (Float w : selectMedRecyclerAdapter2.herbsPriceMap.values()) {
                    sumMescine += w;
                }
                moneyMedicine.setText("￥" + NumberUtils.formatPoint(sumMescine));

                selectMedRecyclerAdapter2.addData(shopedSelectBean);
            } else {
                ToastUtils.show("存在该药方");
            }
        }
    }

    //    高效率
    private void importData(List<SelectTempBean.MedBean> beanList) {
        for (SelectTempBean.MedBean medBean : beanList) {
            List<ShopedSelectBean> dataList = selectMedRecyclerAdapter2.getData();

            //是否存在药方
            boolean isHave = false;
            for (ShopedSelectBean shopedSelectBean : dataList) {
                if (shopedSelectBean.getMedName().equals(medBean.getMedName())) {
                    isHave = true;
                    break;
                }
            }

            if (!isHave) {
                ShopedSelectBean shopedSelectBean = new ShopedSelectBean();
                shopedSelectBean.setMedId(medBean.getMedId());
                shopedSelectBean.setMedName(medBean.getMedName());
                shopedSelectBean.setMedType(medBean.getMedType());
                shopedSelectBean.setMedSpec(medBean.getMedSpec());
                shopedSelectBean.setMedUnit(medBean.getMedUnit());
                shopedSelectBean.setMedPrice(NumberUtils.toDouble(medBean.getMedPrice(), 0));

                int medNumber = medBean.getMedNumber();
                if (!"袋".equals(medBean.getMedUnit())) {
                    String medSpec = medBean.getMedSpec().replace("g/袋", "");
                    int i = NumberUtils.toInt(medSpec, 1);
                    medNumber = medNumber * i;
                }
                double medPrice = shopedSelectBean.getMedPrice();//单价
                double v = medNumber * medPrice;
                float itemNum = new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();//单个药品总价

                selectMedRecyclerAdapter2.herbsNumMap.put(shopedSelectBean.getMedName(), medNumber + "");
                selectMedRecyclerAdapter2.herbsPriceMap.put(shopedSelectBean.getMedName(), itemNum);
                selectMedRecyclerAdapter2.addData(shopedSelectBean);
            }

        }
    }
}

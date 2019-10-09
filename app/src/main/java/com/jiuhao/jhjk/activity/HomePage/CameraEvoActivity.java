package com.jiuhao.jhjk.activity.HomePage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.GridListAdapter.MedTypeAdapter;
import com.jiuhao.jhjk.adapter.GridListAdapter.WorkingAdapter;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.WordInfoRecyclerAdapter;
import com.jiuhao.jhjk.bean.SaveImageBean;
import com.jiuhao.jhjk.bean.SelectFromulationBean;
import com.jiuhao.jhjk.bean.SelectingBean;
import com.jiuhao.jhjk.bean.SelectingredientsBean;
import com.jiuhao.jhjk.dialog.PictureDialog;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.BottomDialogCreator;
import com.jiuhao.jhjk.utils.DialogUtil;
import com.jiuhao.jhjk.utils.PictureUtils;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideEngine;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.utils.net.ProgressListener;
import com.jiuhao.jhjk.view.AmountView;
import com.jiuhao.jhjk.view.MyGridView;
import com.jiuhao.jhjk.view.RegionNumberEditText;
import com.jiuhao.jhjk.view.RoundProgressBar;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 拍方上传
 */
public class CameraEvoActivity extends BaseActivity implements View.OnClickListener {

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
    private EditText etSymptom1;
    /**
     * 选择处方剂型
     */
    private TextView tvSelectMedType1;
    private ImageView ivAddMed;
    private ImageView ivDeleteMed;
    private RoundProgressBar tasksCamera;
    /**
     * 请选择
     */
    private LinearLayout llWorking1;
    private AmountView avNum1;
    private EditText tvFreq1;
    private EditText tvMeth1;
    private ImageView ivFeeOffOn1;
    /**
     * 免费
     */
    private TextView tvFees1;
    private RegionNumberEditText etFees1;
    /**
     * 立即开方
     */
    private Button btnEvo1;
    private RelativeLayout imgPhotoNo;
    private ImageView imgPhoto;
    private RelativeLayout re;
    private ImageView ivSecrecyOffOn;
    /**
     * 请选择
     */
    private TextView tvSelectWorking;
    private RecyclerView wordInfoRecycler;


    private Uri imageuri;
    private String headUri = "";//图片url
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//写权限
            Manifest.permission.CAMERA};//照相权限

    private File file;//图片文件
    private String symptomText;//病症名称
    private String ingredients = "";//辅料
    private String freq;//用药频次
    private String meth;//用药方法
    private int docId;//医生id
    private int fees = 0;//诊金
    private int plural = 7;//付数
    private int formulationId = -1;//剂型id
    private boolean feesFlag = false;//诊金
    private int secpecy = 0;//保密处方 0关闭 1打开  0公开 1不公开
    private int selectPosition = 0;//标记选中
    private int metType = -1;//处方药剂类型
    private int workingType = -1;//加工方式 1加工辅助显示
    private List<SelectFromulationBean> selectFromulationBeans;//剂型数据源
    private List<SelectingredientsBean> selectingredientsBeans;//膏方数据源
    private List<SelectingBean> selectingBeans = new ArrayList<>();//膏方列表数据源
    private WordInfoRecyclerAdapter wordInfoRecyclerAdapter;//膏方recycleradapter
    private SaveImageBean saveImageBean;//拍方上传 二维码
    private android.app.AlertDialog medCodeDialog;//二维码弹出框
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0://剂型
                    if (selectFromulationBeans != null && selectFromulationBeans.size() != 0) {
                        String name = selectFromulationBeans.get(0).getName();
                        formulationId = selectFromulationBeans.get(0).getId();//剂型id
                        tvSelectMedType1.setText(name);
                        metType = selectFromulationBeans.get(0).getCaseType();
                        setDefaultValue(selectFromulationBeans.get(0));
                    }
                    break;
                case 1://二维码弹框
                    initMedCodeDialog();
                    break;
                case 3://膏方
                    if (selectingredientsBeans != null && selectingredientsBeans.size() != 0) {
                        showWoringPop(selectingredientsBeans);
                    }
                    break;
            }
            return false;
        }
    });

    /**
     * 处方二维码对话框
     */
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
                if (TextUtils.isEmpty(saveImageBean.getUrl())) {
                    ToastUtils.show("开方出了点问题，请重新开具");
                } else {
                    JHJKWeChat.getInstance().shareImg(2, saveImageBean.getUrl());
                }
            }
        });
    }

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_camera_evo);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        etSymptom1 = (EditText) findViewById(R.id.et_symptom1);
        tvSelectMedType1 = (TextView) findViewById(R.id.tv_select_med_type1);
        ivAddMed = (ImageView) findViewById(R.id.iv_add_med);
        ivDeleteMed = (ImageView) findViewById(R.id.iv_delete_med);
        tasksCamera = (RoundProgressBar) findViewById(R.id.tasks_camera);
        avNum1 = (AmountView) findViewById(R.id.av_num1);
        tvFreq1 = (EditText) findViewById(R.id.tv_freq1);
        tvMeth1 = (EditText) findViewById(R.id.tv_meth1);
        llWorking1 = (LinearLayout) findViewById(R.id.ll_working);
        ivFeeOffOn1 = (ImageView) findViewById(R.id.iv_fee_off_on1);
        tvFees1 = (TextView) findViewById(R.id.tv_fees1);
        etFees1 = (RegionNumberEditText) findViewById(R.id.et_fees1);
        btnEvo1 = (Button) findViewById(R.id.btn_evo1);
        imgPhotoNo = (RelativeLayout) findViewById(R.id.img_photo_no);
        imgPhoto = (ImageView) findViewById(R.id.img_photo);
        ivSecrecyOffOn = (ImageView) findViewById(R.id.iv_secrecy_off_on);
        re = (RelativeLayout) findViewById(R.id.re);
        tvSelectWorking = (TextView) findViewById(R.id.tv_select_working);
        wordInfoRecycler = (RecyclerView) findViewById(R.id.word_info_recycler);
        wordInfoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        btnEvo1.setOnClickListener(this);
        tvTitle.setText("拍方上传");
    }

    @Override
    protected void obtainData() {
        getSelectFormulationData();//获取挤方数据
        avNum1.setNum(7);
        docId = SPUtils.getInt(getContext(), ConfigKeys.USERID, 0);//医生id
        //膏方baserecycler
        wordInfoRecyclerAdapter = new WordInfoRecyclerAdapter(getContext(), selectingBeans);
        wordInfoRecycler.setAdapter(wordInfoRecyclerAdapter);
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        avNum1.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                plural = amount;
            }
        });
        //选择剂型
        tvSelectMedType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果已经添加药品了 再更改剂型会有提示框
                if (true) {
                    selectMedTypeDialog();
                }
            }
        });
        //图片
        imgPhotoNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getphoto();
            }
        });
        //放大
        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BigImgActivity.class);
                intent.putExtra("imgUrl", headUri);
                startActivity(intent);
            }
        });
        //删除
        ivDeleteMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                headUri = "";
                ivDeleteMed.setVisibility(View.GONE);
                imgPhotoNo.setVisibility(View.VISIBLE);
                imgPhoto.setVisibility(View.GONE);
            }
        });
        //诊金
        ivFeeOffOn1.setOnClickListener(new View.OnClickListener() {
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
        //膏方辅助
        tvSelectWorking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectingredientsData();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_evo1:
                getAllData();
                break;
        }
    }

    //立即开方
    public void getAllData() {
        symptomText = etSymptom1.getText().toString();//病症名称
        if (!symptomText.isEmpty()) {
            if (formulationId == -1) {
                ToastUtils.show("请选择剂型");
            } else {
                //诊金
                if (feesFlag) fees = Integer.valueOf(etFees1.getText().toString());
                freq = tvFreq1.getText().toString();
                if (!freq.isEmpty()) {
                    meth = tvMeth1.getText().toString();
                    if (!meth.isEmpty()) {
                        if (!headUri.isEmpty()) {
                            file = new File(headUri);
                            //辅料
                            if (workingType == 1) {
                                List<SelectingBean> dataList = wordInfoRecyclerAdapter.getDataList();
                                StringBuffer stringBuffer = new StringBuffer();
                                if (dataList != null && dataList.size() != 0) {
                                    for (int i = 0; i < dataList.size(); i++) {
                                        String name = dataList.get(i).getName();
                                        int number = dataList.get(i).getNumber();
                                        String s = name + ":" + number + "克;";
                                        stringBuffer.append(s);
                                    }
                                    StringBuffer delete = stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
                                    ingredients = delete.toString();
                                    Logger.e("辅料：" + ingredients);
                                    postData();
                                } else {
                                    ToastUtils.show("请选择辅料！");
                                }
                            } else {
                                postData();
                            }
                        } else {
                            ToastUtils.show("请先上传处方图片！");
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
            etSymptom1.requestFocus();
        }
    }

    //拍方上传
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

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("symptom", symptomText);//病症名
        linkedHashMap.put("docId", docId + "");//医生id
        linkedHashMap.put("plural", plural + "");//付数
        linkedHashMap.put("formulationId", formulationId + "");//剂型id
        linkedHashMap.put("fees", fees + "");//诊金
        linkedHashMap.put("secpecy", secpecy + "");//是否公开
        linkedHashMap.put("freq", freq);//频次
        linkedHashMap.put("medMethod", meth);//方法
        linkedHashMap.put("ingredients", ingredients);//辅料

        List<String> strings = new ArrayList<>();
        strings.add("file");

        Uri uri = Uri.fromFile(file);
        String realFilePath = getRealFilePath(uri);
        Logger.e(realFilePath);
        List<String> strings1 = new ArrayList<>();
        strings1.add(realFilePath);

        OkHttpUtils.postFile(ConfigKeys.SAVEIMAGE, linkedHashMap, strings, strings1, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                saveImageBean = Json.parseObj(response, SaveImageBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        }, new ProgressListener() {
            @Override
            public void onProgress(long currentBytes, long contentLength, boolean done) {

            }
        });
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
            tvFees1.setText("免费");
            getWindow().setSoftInputMode(WindowManager.LayoutParams
                    .SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            etFees1.setVisibility(View.GONE);
            feesFlag = false;
            ivFeeOffOn1.setImageResource(R.mipmap.off);
        } else {
            tvFees1.setText("诊金：");
            feesFlag = true;
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(etFees1, InputMethodManager.SHOW_IMPLICIT);
            etFees1.setVisibility(View.VISIBLE);
            etFees1.requestFocus();
            ivFeeOffOn1.setImageResource(R.mipmap.on);
        }
    }

    //获取剂型data
    public void getSelectFormulationData() {
        Logger.e("获取药剂token："+Config.userToken);
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
                    }
                    setDefaultValue(dataBean);
                    alertDialog.dismiss();
                }
            });
        }
    }

    /**
     * 设置剂型，方法 ，频次
     *
     * @param dataBean
     */
    private void setDefaultValue(SelectFromulationBean dataBean) {
        metType = dataBean.getCaseType();
        workingType = dataBean.getWorkingType();
        if (workingType == 1) {
            llWorking1.setVisibility(View.VISIBLE);
        } else {
            llWorking1.setVisibility(View.GONE);
        }
        tvSelectMedType1.setText(dataBean.getName());
        freq = dataBean.getFreq();
        meth = dataBean.getMedMethod();
        tvFreq1.setText(freq);
        tvMeth1.setText(meth);
    }

    //相机
    public void getphoto() {
        PictureDialog pictureDialog = new PictureDialog();
        pictureDialog.setOnPersonalListener(new PictureDialog.OnPersonalListener() {
            @Override
            public void onTop() {
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
                imageuri = Uri.fromFile(file);
                PictureUtils.takePicture(CameraEvoActivity.this, imageuri, 4001);
//                //用于判断SDK版本是否大于23
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    //检查权限
//                    int i = ContextCompat.checkSelfPermission(getContext(), PERMISSIONS_STORAGE[0]);//0表示读写权限
//                    int i2 = ContextCompat.checkSelfPermission(getContext(), PERMISSIONS_STORAGE[1]);//1表示照相权限
//                    //如果权限申请失败，则重新申请权限
//                    if (i == PackageManager.PERMISSION_DENIED && i2 == PackageManager.PERMISSION_DENIED) {//-1没授权
//                        //重新申请权限函数
//                        startRequestPermission();
//                        Logger.e("权限请求成功");
//                    } else if (i == PackageManager.PERMISSION_GRANTED && i2 == PackageManager.PERMISSION_GRANTED) {//0授权
//                        PictureUtils.takePicture(CameraEvoActivity.this, imageuri, 4001);
//                    } else {
//                        HintTitleDialog myDialog = new HintTitleDialog("需要到系统中设置相关权限");
//                        myDialog.show(getSupportFragmentManager());
//                        myDialog.setOnLeftClick(new HintDialog.OnLeftClick() {
//                            @Override
//                            public void onLeft() {
//                                ToastUtils.show("取消");
//                            }
//                        });
//                        myDialog.setOnRightClick(new HintDialog.OnRightClick() {
//                            @Override
//                            public void onRight() {
//                                myDialog.dismiss();
//                                startAppDetailSetting(CameraEvoActivity.this, 101);
//                            }
//                        });
//                    }
//                } else {
//                    PictureUtils.takePicture(CameraEvoActivity.this, imageuri, 4001);
//                }
            }

            @Override
            public void onBottom() {
                Matisse.from(CameraEvoActivity.this)
                        .choose(MimeType.of(MimeType.JPEG, MimeType.PNG)) // 选择 mime 的类型
                        .theme(R.style.Matisse_Dracula)//Zhihu（亮蓝色主题） Dracula（黑色主题）
                        .countable(true)
                        .maxSelectable(1) // 图片选择的最多数量
                        .spanCount(3)//网格的规格
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)//图像选择和预览活动所需的方向。
                        .thumbnailScale(0.85f) // 缩略图的比例
                        .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                        .forResult(5001); // 设置作为标记的请求码
            }
        }).show(getSupportFragmentManager());
    }

//    /**
//     * 获取应用详情页面intent
//     */
//    public static void startAppDetailSetting(Activity context, int request) {
//        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//        intent.setData(uri);
//        context.startActivityForResult(intent, request);
//    }
//
//    //重新申请权限函数
//    private void startRequestPermission() {
//        //4001为请求码
//        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 4001);
//    }

//    //请求权限时的回调方法
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        //检查权限
//        int i = ContextCompat.checkSelfPermission(getContext(), PERMISSIONS_STORAGE[0]);
//        int i2 = ContextCompat.checkSelfPermission(getContext(), PERMISSIONS_STORAGE[1]);
//        //如果权限申请失败，则重新申请权限
//        if (i == PackageManager.PERMISSION_DENIED && i2 == PackageManager.PERMISSION_DENIED) {//-1没授权
//            //重新申请权限函数
//            startRequestPermission();
//            Logger.e("权限请求成功");
//        } else if (i == PackageManager.PERMISSION_GRANTED && i2 == PackageManager.PERMISSION_GRANTED) {//0授权
//            PictureUtils.takePicture(CameraEvoActivity.this, imageuri, 4001);
//        } else {
//            HintTitleDialog myDialog = new HintTitleDialog("需要到系统中设置相关权限");
//            myDialog.show(getSupportFragmentManager());
//            myDialog.setOnLeftClick(new HintDialog.OnLeftClick() {
//                @Override
//                public void onLeft() {
//                    ToastUtils.show("取消");
//                }
//            });
//            myDialog.setOnRightClick(new HintDialog.OnRightClick() {
//                @Override
//                public void onRight() {
//                    myDialog.dismiss();
//                    startAppDetailSetting(CameraEvoActivity.this, 101);
//                }
//            });
//        }
//    }

    //回传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4001 && resultCode == RESULT_OK) {
            imgPhotoNo.setVisibility(View.GONE);
            imgPhoto.setVisibility(View.VISIBLE);
            ivDeleteMed.setVisibility(View.VISIBLE);
            headUri = getRealFilePath(imageuri);
            GlideUtil.load(getContext(), headUri, imgPhoto);
        } else if (requestCode == 5001 && resultCode == RESULT_OK) {
            imgPhotoNo.setVisibility(View.GONE);
            imgPhoto.setVisibility(View.VISIBLE);
            ivDeleteMed.setVisibility(View.VISIBLE);
            if (Matisse.obtainPathResult(data).size() != 0 && Matisse.obtainPathResult(data) != null) {
                headUri = Matisse.obtainPathResult(data).get(0);
            }
            GlideUtil.load(getContext(), headUri, imgPhoto);
        }
    }

    //获取uri真实路径
    public static String getRealFilePath(final Uri uri) {
        String path = uri.getPath();
        String data = path.replace("external_files", Environment.getExternalStorageDirectory().getPath());
        Logger.d("获取uri真实路径: \nuri:" + path + "\npath:" + data);
        return data;
    }

}

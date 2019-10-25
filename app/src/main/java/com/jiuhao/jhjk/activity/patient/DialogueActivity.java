package com.jiuhao.jhjk.activity.patient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.MainEvoActivity;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.DialogueRcyclerViewAdapter;
import com.jiuhao.jhjk.bean.PriceBean;
import com.jiuhao.jhjk.bean.ReadedBean;
import com.jiuhao.jhjk.bean.SelectByIdBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.dialog.PictureDialog;
import com.jiuhao.jhjk.utils.PictureUtils;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideEngine;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.utils.net.ProgressListener;
import com.orhanobut.logger.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 跟患者的聊天对话框
 */
public class DialogueActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 确认
     */
    private ImageView tvTitleSure;
    private RelativeLayout rlTitleSure;
    private RelativeLayout rlTitle;
    /**
     * 当前为免费问诊点这里设置问诊费用
     */
    private TextView fee;
    private RecyclerView recyclerDialogue;
    /**
     * 继续服用
     */
    private TextView tv1;
    /**
     * 提醒复诊
     */
    private TextView tv2;
    /**
     * 更改用药
     */
    private TextView tv3;
    /**
     * 新开处方
     */
    private TextView tv4;
    private LinearLayout llBottom;
    private ImageView more;
    private EditText messEdit;
    /**
     * 发送
     */
    private Button sendBut;
    private RelativeLayout senRelative;
    /**
     * 图片
     */
    private TextView pictureText;
    /**
     * 问诊单
     */
    private TextView listText;

    /**
     * 问诊费用:0元
     */
    private TextView priceText;
    /**
     * 结束咨询
     */

    private List<ReadedBean.DataBean> readBeanList = new ArrayList<>();

    private TextView deletePrice;
    private ImageView rightPrice;
    private RelativeLayout priceRela;
    private LinearLayout moreLin;
    private String nickName;//患者name
    private int id;//患者id
    private String img;//患者头像
    private boolean ismore = false;
    private int page1 = 1;
    private int data;//聊天室id
    private ReadedBean readedBean;
    private PriceBean priceBean;
    private SelectByIdBean selectByIdBean;//处方信息
    private Uri imageuri;
    private DialogueRcyclerViewAdapter dialogueRcyclerViewAdapter;
    private Intent intentSend;
    private String docInterrogationName;

    private boolean bottomFlag = true;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    getUserData();
                    break;
                case 1:
                    if (readedBean != null && readedBean.getData().size() != 0) {
                        if (readedBean.getChating() == 0) {
                            fee.setVisibility(View.GONE);
                            priceRela.setVisibility(View.GONE);
                        } else if (readedBean.getChating() == 1) {
                            fee.setVisibility(View.GONE);
                            priceRela.setVisibility(View.VISIBLE);
                            getPrice();
                            if (readedBean.getCaseInConsultation() == 0) {
                                deletePrice.setText("请求结束咨询");
                            } else if (readedBean.getCaseInConsultation() == 1) {
                                deletePrice.setText("结束咨询");
                            }
                        }
                    } else {
                        dialogueRcyclerViewAdapter.setUpFetchEnable(false);
                    }
                    break;
                case 2:
                    double price = priceBean.getPrice();//咨询费
                    if (price == 0 || price == 0.0 || price == 0.00) {//免费
                        fee.setVisibility(View.VISIBLE);
                        priceRela.setVisibility(View.GONE);
                    } else {//收费
                        priceRela.setVisibility(View.VISIBLE);
                        fee.setVisibility(View.GONE);
                        priceText.setText("问诊费:" + price + "元");
                    }
                    break;
                case 3://更改用药
                    Intent intent = new Intent(getContext(), MainEvoActivity.class);
                    intent.putExtra("orinType", 3);
                    intent.putExtra("selectDetailsBean", selectByIdBean);
                    startActivityForResult(intent, 1111);
                    break;
            }
            return false;
        }
    });


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_dialogue);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (ImageView) findViewById(R.id.tv_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        fee = (TextView) findViewById(R.id.fee);
        recyclerDialogue = (RecyclerView) findViewById(R.id.recycler_dialogue);
        recyclerDialogue.setLayoutManager(new LinearLayoutManager(getContext()));
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        more = (ImageView) findViewById(R.id.more);
        messEdit = (EditText) findViewById(R.id.mess_edit);
        sendBut = (Button) findViewById(R.id.send_but);
        sendBut.setOnClickListener(this);
        senRelative = (RelativeLayout) findViewById(R.id.sen_relative);
        pictureText = (TextView) findViewById(R.id.picture_text);
        listText = (TextView) findViewById(R.id.list_text);
        moreLin = (LinearLayout) findViewById(R.id.more_lin);
        priceText = (TextView) findViewById(R.id.price_text);
        deletePrice = (TextView) findViewById(R.id.delete_price);
        rightPrice = (ImageView) findViewById(R.id.right_price);
        priceRela = (RelativeLayout) findViewById(R.id.price_rela);


    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        nickName = intent.getStringExtra("nickName");
        id = intent.getIntExtra("id", 0);
        img = intent.getStringExtra("img");
        tvTitle.setText(nickName);
        getCustomerId();
        initRecycleViewAdapter();
    }

    private void initRecycleViewAdapter() {
        String headurl = SPUtils.getString(getContext(), ConfigKeys.AVATAR, "");
        Logger.e("醫生頭像：" + headurl);
        dialogueRcyclerViewAdapter =
                new DialogueRcyclerViewAdapter(R.layout.item_dialogue, readBeanList, nickName, img, headurl);
        recyclerDialogue.setAdapter(dialogueRcyclerViewAdapter);
        dialogueRcyclerViewAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
            @Override
            public void onUpFetch() {
                page1++;
                getUserData();
            }
        });
        dialogueRcyclerViewAdapter.setStartUpFetchPosition(2);

    }

    //查询收费
    public void getPrice() {
        OkHttpUtils.get(ConfigKeys.PRICEC + "?customerId=" + id + "&priceType=" + 1, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                priceBean = Json.parseObj(response, PriceBean.class);
                handler.sendEmptyMessage(2);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //根据患者id拿到聊天室id
    public void getCustomerId() {

        OkHttpUtils.get(ConfigKeys.DOCPATIENTMSGBYCUSTOMER + "?customerId=" + id, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                data = Integer.valueOf(response);//聊天室id
                Logger.e(nickName + "id：" + id + "聊天室id为" + data + "");
                handler.sendEmptyMessage(0);

            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //获取跟患者的消息
    public void getUserData() {
        /**
         * chatId 聊天列表id
         * page 是
         limit
         */
        Logger.e(data + "");
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("chatId", data);
        linkedHashMap.put("page", page1);
        linkedHashMap.put("limit", 20);
        dialogueRcyclerViewAdapter.setUpFetching(true);
        OkHttpUtils.post(ConfigKeys.DOCPATIENTMSGREADED, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e("第" + page1 + ":" + response);
                dialogueRcyclerViewAdapter.setUpFetching(false);
                readedBean = Json.parseObj(response, ReadedBean.class);

                dialogueRcyclerViewAdapter.addData(0, readedBean.getData());
                if (bottomFlag) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialogueRcyclerViewAdapter.setUpFetchEnable(true);
                        }
                    }, 1000);
                    recyclerDialogue.scrollToPosition(dialogueRcyclerViewAdapter.getItemCount() - 1);
                    bottomFlag = false;
                }
                handler.sendEmptyMessage(1);

            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(code + "****" + e.getMessage());
                ToastUtils.show(e.getMessage());
                dialogueRcyclerViewAdapter.setUpFetching(false);
            }
        });
    }

    //结束咨询
    public void deletePriceData() {

        OkHttpUtils.get(ConfigKeys.ENDCONSULTATION + "?customerId=" + id + "&priceType=" + 1, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("结束咨询");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //更多设置
        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MoreSetActivity.class);
                intent.putExtra("data", data);
                startActivityForResult(intent, 11);
            }
        });
        //展示更多（图片 问诊单）
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ismore = !ismore;
                if (ismore) {
                    moreLin.setVisibility(View.VISIBLE);
                } else {
                    moreLin.setVisibility(View.GONE);
                }
            }
        });
        //咨询费设置
        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaySetActivity.class);
                intent.putExtra("nickName", nickName);//患者名字
                intent.putExtra("id", id);//患者id
                startActivityForResult(intent, 21);
            }
        });
        rightPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PaySetActivity.class);
                intent.putExtra("nickName", nickName);//患者名字
                intent.putExtra("id", id);//患者id
                startActivity(intent);
            }
        });

        //结束咨询
        deletePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePriceData();
            }
        });
        //继续服用
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastCastId = readedBean.getLastCastId();
                if (lastCastId == 0 || String.valueOf(lastCastId).isEmpty()) {
                    ToastUtils.show(nickName + "没有历史病例,无法提醒继续服用,请选择为其新开处方");
                } else {
                    HintDialog hintDialog = new HintDialog("提示", "确定提醒“" + nickName + "”继续用药？");
                    hintDialog.show(getSupportFragmentManager());
                    hintDialog.setOnLeftClick(new HintDialog.OnLeftClick() {
                        @Override
                        public void onLeft() {
                            ToastUtils.show("取消");
                        }
                    });
                    hintDialog.setOnRightClick(new HintDialog.OnRightClick() {
                        @Override
                        public void onRight() {
                            hintDialog.dismiss();
                            postSendWx(1);
                        }
                    });
                }

            }
        });
        //提醒复诊
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastCastId = readedBean.getLastCastId();
                if (lastCastId == 0 || String.valueOf(lastCastId).isEmpty()) {
                    ToastUtils.show(nickName + "没有历史病例,无法提醒复诊,请选择为其新开处方");
                } else {
                    HintDialog myDialog = new HintDialog("提示", "确定提醒“" + nickName + "”复诊？");
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
                            postSendWx(2);
                        }
                    });
                }
            }
        });
        //更改用药
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastCastId = readedBean.getLastCastId();
                if (lastCastId == 0 || String.valueOf(lastCastId).isEmpty()) {
                    ToastUtils.show(nickName + "没有历史病例,无法更改用药,请选择为其新开处方");
                } else {
                    getDetailData(lastCastId);
                }
            }
        });
        //新开处方
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainEvoActivity.class);
                intent.putExtra("orinType", 4);
                intent.putExtra("id", id);//患者id
                startActivityForResult(intent, 2222);
            }
        });
        //发送图片
        pictureText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getphoto();
            }
        });
        //发送问诊单
        listText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intentSend = new Intent(getContext(), SendListQuestionActivity.class);
                intentSend.putExtra("id", id);
                startActivityForResult(intentSend, 12);
            }
        });
    }

    //相机
    public void getphoto() {
        PictureDialog pictureDialog = new PictureDialog();
        pictureDialog.setOnPersonalListener(new PictureDialog.OnPersonalListener() {
            @Override
            public void onTop() {
                File fileUri = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
                imageuri = Uri.fromFile(fileUri);

                //***7.0不在推荐明文传输url了，需要改成uri格式
                //***为了隐藏真实的路径
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    //通过FileProvider创建一个content类型的Uri
                    imageuri = FileProvider.getUriForFile(DialogueActivity.this, getApplicationContext().getPackageName() + ".FileProvider", fileUri);

                PictureUtils.takePicture(DialogueActivity.this, imageuri, 1001);
            }

            @Override
            public void onBottom() {
                Matisse.from((Activity) getContext())
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {//发送图片相机
            String headUri = imageuri.getPath();
            Logger.e(headUri);
            postToPersonPhont(headUri);
        } else if (requestCode == 5001 && resultCode == RESULT_OK) {//发送图片相册
            String headUri = Matisse.obtainPathResult(data).get(0);
            postToPersonPhont(headUri);
        } else if (requestCode == 11 && resultCode == 12) {//清空聊天记录
            getUserData();
        } else if (requestCode == 1111 && resultCode == 2222) {//更改用药
            ToastUtils.show("已提醒患者更改用药！");
        } else if (requestCode == 2222 && resultCode == 3333) {//新开处方
            ToastUtils.show("开方成功！");
        } else if (requestCode == 12 && resultCode == 123) {//問診單回調
            docInterrogationName= intentSend.getStringExtra("docInterrogationName");
            Logger.e("发送问诊单回调问诊单name："+docInterrogationName);
            addDataBean(docInterrogationName,3);
        } else if (requestCode == 21 && resultCode == 221) {//設置患者咨詢費
            getPrice();
        }
    }


    //根据最近处方id查询处方信息
    public void getDetailData(int caseId) {
        OkHttpUtils.get(ConfigKeys.SELECYBYID + "?id=" + caseId, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                selectByIdBean = Json.parseObj(response, SelectByIdBean.class);
                handler.sendEmptyMessage(3);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void postSendWx(int option) {
        /**
         * customerId  患者id
         option 1 继续服用 2 提醒复诊
         */
        int lastCastId = readedBean.getLastCastId();
        String url = ConfigKeys.WXSEND + "?caseId=" + lastCastId + "&customerId=" + id + "&option=" + option;
        Logger.e(url);
        OkHttpUtils.postJson(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                if (option == 1) {
                    ToastUtils.show("提醒继续用药成功！");
                } else if (option == 2) {
                    ToastUtils.show("提醒复诊成功！");
                }
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.send_but:
                String edit = messEdit.getText().toString();
                Logger.e(edit);
                if (edit.isEmpty()) {
                    ToastUtils.show("发送内容不可以为空！");
                } else {
                    postToPerson(edit);
                }
                break;
        }
    }

    //给患者发送消息
    public void postToPerson(String edit) {

        /**
         * receiveId 接受消息id
         * content 消息内容
         * file 文件
         */
        Logger.e(id + "***" + edit);
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("receiveId", id);
        linkedHashMap.put("content", edit);

        OkHttpUtils.post(ConfigKeys.SENDTOPATIENT, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(code + "***" + response);
                addDataBean(edit,1);
                messEdit.setText("");
                KeyboardUtils.hideSoftInput(messEdit);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(code + "***" + e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //发送消息成功添加消息到数据源
    public void addDataBean(String con,int msgType) {

        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);

        ReadedBean.DataBean dataBean = new ReadedBean.DataBean();
        if (msgType==1 || msgType==3){
            dataBean.setContent(con);
        }else if(msgType==2){
            dataBean.setUrl(con);
        }
        dataBean.setTimeStr(time);
        dataBean.setMsgType(msgType);
        dataBean.setSendType(1);
        dialogueRcyclerViewAdapter.addData(dataBean);
        recyclerDialogue.scrollToPosition(dialogueRcyclerViewAdapter.getItemCount() - 1);

    }

    //给患者发送消息(图片)
    public void postToPersonPhont(String headurl) {

        /**
         * receiveId 接受消息id
         * content 消息内容
         * file 文件
         */
        Config.showProgressDialog(getContext(),"正在发送图片...");
        Logger.e(headurl);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("receiveId", id + "");

        List<String> strings = new ArrayList<>();
        strings.add("file");

        File file = new File(headurl);
        Uri uri = Uri.fromFile(file);
        String realFilePath = getRealFilePath(uri);
        Logger.e(realFilePath);
        List<String> strings1 = new ArrayList<>();
        strings1.add(realFilePath);

        OkHttpUtils.postFile(ConfigKeys.SENDTOPATIENT, linkedHashMap, strings, strings1, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(code + response);
                Config.dismissProgressDialog();
                messEdit.setText("");
                ismore = !ismore;
                moreLin.setVisibility(View.GONE);
                addDataBean(response,2);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(code + e.getMessage());
                Config.dismissProgressDialog();
                ToastUtils.show(code + e.getMessage());
            }
        }, new ProgressListener() {
            @Override
            public void onProgress(long currentBytes, long contentLength, boolean done) {

            }
        });
    }


    //获取uri真实路径
    public static String getRealFilePath(final Uri uri) {
        String path = uri.getPath();
        String data = path.replace("external_files", Environment.getExternalStorageDirectory().getPath());
        Logger.d("获取uri真实路径: \nuri:" + path + "\npath:" + data);
        return data;
    }

}

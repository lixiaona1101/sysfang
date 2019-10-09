package com.jiuhao.jhjk.activity.patient;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.MainEvoActivity;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.DialogueRecyclerAdapter;
import com.jiuhao.jhjk.bean.PriceBean;
import com.jiuhao.jhjk.bean.ReadedBean;
import com.jiuhao.jhjk.bean.SelectByIdBean;
import com.jiuhao.jhjk.bean.SelectDetailsBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideEngine;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.utils.net.ProgressListener;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 跟患者的聊天对话框
 */
public class DialogueActivity extends BaseActivity implements View.OnClickListener, OnLoadMoreListener, OnRefreshListener {

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
    private TextView deletePrice;
    private ImageView rightPrice;
    private RelativeLayout priceRela;
    private LinearLayout moreLin;
    private String nickName;//患者name
    private int id;//患者id
    private boolean ismore = false;
    private int page1 = 0;
    private int data;//聊天室id
    private ReadedBean readedBean;
    private DialogueRecyclerAdapter dialogueRecyclerAdapter;
    private PriceBean priceBean;
    private SelectByIdBean selectByIdBean;//处方信息
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    getUserData();
                    break;
                case 1:
                    if (readedBean.getData().size() != 0 && readedBean != null) {
                        dialogueRecyclerAdapter = new DialogueRecyclerAdapter(getContext(), readedBean);
                        recyclerDialogue.setAdapter(dialogueRecyclerAdapter);
                        recyclerDialogue.smoothScrollToPosition(readedBean.getData().size() - 1);
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
                    intent.putExtra("orinType",3);
                    intent.putExtra("selectDetailsBean",selectByIdBean);
                    startActivity(intent);
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
        tvTitle.setText(nickName);
        getCustomerId(id);
        getPrice();
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
    public void getCustomerId(int id) {

        OkHttpUtils.get(ConfigKeys.DOCPATIENTMSGBYCUSTOMER + "?customerId=" + id, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                data = Integer.valueOf(response);//聊天室id
                Logger.e(data + "");
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
        linkedHashMap.put("limit", 10);
        OkHttpUtils.post(ConfigKeys.DOCPATIENTMSGREADED, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                readedBean = Json.parseObj(response, ReadedBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(code + "****" + e.getMessage());
                ToastUtils.show(e.getMessage());
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
                startActivity(intent);
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
                intent.putExtra("orinType",0);
                startActivity(intent);
            }
        });
        //发送图片
        pictureText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        //发送问诊单
        listText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), SendListQuestionActivity.class);
                intent.putExtra("id", id);
                //会有回调
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5001) {//发送图片
            String headUri = Matisse.obtainPathResult(data).get(0);
            postToPersonPhont(headUri);
        } else if (requestCode == 11 && resultCode == 12) {//清空聊天记录
            getUserData();
        }
    }

    //根据最近处方id查询处方信息
    public void getDetailData(int caseId) {
        OkHttpUtils.get(ConfigKeys.SELECYBYID + "?id=" + id, null, new OkHttpUtils.ResultCallback<String>() {
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
        String url = ConfigKeys.WXSEND + "?caseId=" + lastCastId + "&custmerId=" + id + "&option=" + option;
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
        Logger.e(edit);
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("receiveId", id);
        linkedHashMap.put("content", edit);

        OkHttpUtils.postJson(ConfigKeys.SENDTOPATIENT, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                messEdit.setText("");
                dialogueRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //给患者发送消息(图片)
    public void postToPersonPhont(String headurl) {

        /**
         * receiveId 接受消息id
         * content 消息内容
         * file 文件
         */
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
                messEdit.setText("");
                dialogueRecyclerAdapter.notifyDataSetChanged();
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

    //获取uri真实路径
    public static String getRealFilePath(final Uri uri) {
        String path = uri.getPath();
        String data = path.replace("external_files", Environment.getExternalStorageDirectory().getPath());
        Logger.d("获取uri真实路径: \nuri:" + path + "\npath:" + data);
        return data;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page1++;
        getUserData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page1 = 0;
        getUserData();
    }
}

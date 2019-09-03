package com.jiuhao.jhjk.activity.mine.DoctorCertified;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.activity.mine.Other.BigImgActivity;
import com.jiuhao.jhjk.bean.DocAuthBean;
import com.jiuhao.jhjk.dialog.PictureDialog;
import com.jiuhao.jhjk.utils.PictureUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideEngine;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.LinkedHashMap;


public class MessageCertifiedAgainActivity extends BaseActivity implements View.OnClickListener {

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
     * 请输入业务员邀请码
     */
    private EditText invitationCode;
    /**
     * 请输入医生真实姓名
     */
    private EditText doctorName;
    /**
     * 重新提交医师资格证书
     */
    private TextView upNoOk;
    private LinearLayout upOk;
    /**
     * 重新提交医师执业证书
     */
    private TextView downNoOk;
    private LinearLayout downOk;
    /**
     * 提交认证
     */
    private Button messageSubmit;
    private DocAuthBean docbean;
    private Uri imageuri;
    private String headUri = "1";//资格证
    private String headUri2 = "1";//执业证
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//写权限
            Manifest.permission.CAMERA};//照相权限
    /**
     * 点击查看图片
     */
    private TextView seeImg;
    private LinearLayout upLook;
    /**
     * 点击查看图片
     */
    private TextView seeImgDown;
    private LinearLayout downLook;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_message_certified_again);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        invitationCode = (EditText) findViewById(R.id.invitation_code);
        doctorName = (EditText) findViewById(R.id.doctor_name);
        upNoOk = (TextView) findViewById(R.id.up_no_ok);
        upOk = (LinearLayout) findViewById(R.id.up_ok);
        downNoOk = (TextView) findViewById(R.id.down_no_ok);
        downOk = (LinearLayout) findViewById(R.id.down_ok);
        messageSubmit = (Button) findViewById(R.id.message_submit);
        messageSubmit.setOnClickListener(this);
        seeImg = (TextView) findViewById(R.id.see_img);
        upLook = (LinearLayout) findViewById(R.id.up_look);
        seeImgDown = (TextView) findViewById(R.id.see_img_down);
        downLook = (LinearLayout) findViewById(R.id.down_look);
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        docbean = (DocAuthBean) intent.getSerializableExtra("docbean");

        String inviteCode = docbean.getInviteCode();
        invitationCode.setText(inviteCode);
        String name = docbean.getName();
        doctorName.setText(name);

        //资格证通过
        if (docbean.isAu_PhysicianQualCert()) {
            upOk.setVisibility(View.VISIBLE);
        } else {
            upNoOk.setVisibility(View.VISIBLE);
        }

        //执业证通过
        if (docbean.isAu_PhysicianCert()) {
            downOk.setVisibility(View.VISIBLE);
        } else {
            downNoOk.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initEvent() {
        //资格证重新提交
        upNoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getphoto(101, 102);
            }
        });
        //执业证重新提交
        downNoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getphoto(201, 202);
            }
        });
        //资格证书查看
        seeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BigImgActivity.class);
                intent.putExtra("imgUrl", headUri);
                startActivity(intent);
            }
        });
        //执业证书查看
        seeImgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BigImgActivity.class);
                intent.putExtra("imgUrl", headUri2);
                startActivity(intent);
            }
        });
    }

    //相机
    public void getphoto(int requestcode, int requestcode2) {
        PictureDialog pictureDialog = new PictureDialog();
        pictureDialog.setOnPersonalListener(new PictureDialog.OnPersonalListener() {
            @Override
            public void onTop() {
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
                imageuri = Uri.fromFile(file);


                //用于判断SDK版本是否大于23
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //检查权限
                    int i = ContextCompat.checkSelfPermission(getContext(), PERMISSIONS_STORAGE[0]);
                    //如果权限申请失败，则重新申请权限
                    if (i != PackageManager.PERMISSION_GRANTED) {
                        //重新申请权限函数
                        startRequestPermission();
                        Logger.e("权限请求成功");
                    }
                }

                PictureUtils.takePicture((Activity) getContext(), imageuri, requestcode);
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
                        .forResult(requestcode2); // 设置作为标记的请求码
            }
        }).show(getSupportFragmentManager());
    }

    /**
     * 重新申请权限函数
     */
    private void startRequestPermission() {
        //4001为请求码
        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 4001);
    }

    //回传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            headUri = imageuri.toString();
            upNoOk.setVisibility(View.GONE);
            upLook.setVisibility(View.VISIBLE);
        } else if (requestCode == 102) {
            headUri = Matisse.obtainPathResult(data).get(0);
            upNoOk.setVisibility(View.GONE);
            upLook.setVisibility(View.VISIBLE);
        } else if (requestCode == 201) {
            headUri2 = imageuri.toString();
            downNoOk.setVisibility(View.GONE);
            downLook.setVisibility(View.VISIBLE);
        } else if (requestCode == 202) {
            headUri2 = Matisse.obtainPathResult(data).get(0);
            downNoOk.setVisibility(View.GONE);
            downLook.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.message_submit:
                updata();
                break;
        }
    }


    public void updata(){
        //邀请码
        String inviteCode = invitationCode.getText().toString();
        //医生姓名
        String name = doctorName.getText().toString();
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("inviteCode",inviteCode);
        linkedHashMap.put("name",name);
        linkedHashMap.put("physicianQualCert",headUri);
        linkedHashMap.put("physicianCert",headUri2);
        OkHttpUtils.postJson(ConfigKeys.DOCAUTH, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("操作成功！");
                startActivity(new Intent(getContext(),CertifiedActivity.class));
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

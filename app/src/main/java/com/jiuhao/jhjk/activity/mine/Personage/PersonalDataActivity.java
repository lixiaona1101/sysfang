package com.jiuhao.jhjk.activity.mine.Personage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.dialog.PictureDialog;
import com.jiuhao.jhjk.utils.PictureUtils;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideEngine;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 完善个人资料
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {


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
     * 点击更换
     */
    private TextView upHead;
    private ImageView head;
    /**
     * 男
     */
    private TextView man;
    /**
     * 女
     */
    private TextView woman;
    /**
     * 职称
     */
    private TextView textView;
    private RelativeLayout administrative;
    private RelativeLayout major;
    /**
     * 点击这里编辑简介内容...
     */
    private EditText doctorSynopsis;
    /**
     * 0
     */
    private TextView nowNumber;
    /**
     * /300
     */
    private TextView san;
    private RelativeLayout occupation;
    /**
     * 未选择
     */
    private TextView departmentName;
    /**
     * 未选择
     */
    private TextView titles;
    /**
     * 未选择标签
     */
    private TextView label;
    //科室
    private String departmentnamestr;
    //职称
    private String titlesstr;
    //专业标签
    private String labelstr;
    //科室id
    private int departmentId;
    private Uri imageuri;
    private int sexx;
    private String headUri="1";
    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//写权限
            Manifest.permission.CAMERA};//照相权限

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_personal_data);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        upHead = (TextView) findViewById(R.id.up_head);
        head = (ImageView) findViewById(R.id.head);
        man = (TextView) findViewById(R.id.man);
        woman = (TextView) findViewById(R.id.woman);
        textView = (TextView) findViewById(R.id.textView);
        administrative = (RelativeLayout) findViewById(R.id.administrative);
        major = (RelativeLayout) findViewById(R.id.major);
        doctorSynopsis = (EditText) findViewById(R.id.doctor_synopsis);
        nowNumber = (TextView) findViewById(R.id.now_number);
        san = (TextView) findViewById(R.id.san);
        tvTitle.setText("基本信息");
        rlTitleSure.setVisibility(View.VISIBLE);
        occupation = (RelativeLayout) findViewById(R.id.occupation);
        departmentName = (TextView) findViewById(R.id.department_name);
        titles = (TextView) findViewById(R.id.titles);
        label = (TextView) findViewById(R.id.label);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
        upHead.setOnClickListener(this);
        head.setOnClickListener(this);
        rlTitleSure.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {
        //医生头像
        String headUrl = SPUtils.getString(getContext(), ConfigKeys.AVATAR, "");
        GlideUtil.loadCircle(getContext(), headUrl, head);
        //性别 0未知 1男 2女
        int sex = SPUtils.getInt(getContext(), ConfigKeys.SEX, 0);
        show(sex);
        //科室
        departmentnamestr = SPUtils.getString(getContext(), ConfigKeys.DEPARTMENTNAME, "未选择");
        departmentName.setText(departmentnamestr + "  ");

        //职称
        titlesstr = SPUtils.getString(getContext(), ConfigKeys.TITLES, "未选择");
        titles.setText(titlesstr + "  ");

        //专业标签
        labelstr = SPUtils.getString(getContext(), ConfigKeys.LABEL, "未选择标签");
        label.setText(labelstr + "  ");

        //简介
        String resumestr = SPUtils.getString(getContext(), ConfigKeys.RESUME, "点击这里编辑简介内容...");
        doctorSynopsis.setText(resumestr);
        int length = resumestr.length();
        nowNumber.setText(length + "");
        //长度
        doctorSynopsis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if (length <= 300) nowNumber.setText(length + "");
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
        //科室
        administrative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AdministrativeActivity.class);
                intent.putExtra("departmentnamestr", departmentnamestr);
                startActivityForResult(intent, 1001);
            }
        });

        //职称
        occupation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OccupationActivity.class);
                startActivityForResult(intent, 2001);
            }
        });
        //专业标签
        major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String departmentName1 = departmentName.getText().toString();
                com.orhanobut.logger.Logger.e(departmentName1);
                if (departmentName1.contains("未选择")) {
                    ToastUtils.show("请先选择科室！");
                } else {
                    Intent intent = new Intent(getContext(), LabelActivity.class);
                    intent.putExtra("departmentName", departmentName1);//科室名
                    intent.putExtra("departmentId", departmentId);   //科室id
                    intent.putExtra("labelstr", labelstr);//专业标签
                    startActivityForResult(intent, 3001);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.man:
                show(1);
                break;
            case R.id.woman:
                show(2);
                break;
            case R.id.up_head:
            case R.id.head:
                getphoto();
                break;
            case R.id.rl_title_sure:
                upData();
                break;
        }
    }

    //上传更新信息
    public void upData() {

        String text = titles.getText().toString();//职称
        String lablee = label.getText().toString();//专业标签
        String resume = doctorSynopsis.getText().toString();//简介
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        if(!headUri.equals("1")){
            linkedHashMap.put("avatar", headUri);//头像
        }
        linkedHashMap.put("sex", sexx);//性别
        linkedHashMap.put("departmentId", departmentId);//科室编号
        linkedHashMap.put("titles", text);//职称
        linkedHashMap.put("label", lablee);//专业标签
        linkedHashMap.put("resume", resume);//简介
        Logger.e(headUri);
        Logger.e(sexx + "");
        Logger.e(departmentId + "");
        Logger.e(text);
        Logger.e(lablee);
        Logger.e(resume);
        OkHttpUtils.putJson(ConfigKeys.DOC, linkedHashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
//                DocBean docBean = Json.parseObj(response, DocBean.class);
//                docBean.getStatus();
                ToastUtils.show("更新成功");
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //相机
    public void getphoto() {
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

                PictureUtils.takePicture((Activity) getContext(), imageuri, 4001);
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
        if (requestCode == 1001 && resultCode == 1002) {
            String departmentyes = data.getStringExtra("departmentyes");
            departmentId = data.getIntExtra("departmentId", 1);
            departmentName.setText(departmentyes + "  ");
        } else if (requestCode == 2001 && resultCode == 2002) {
            String occupation = data.getStringExtra("occupation");
            titles.setText(occupation + "  ");
        } else if (requestCode == 3001 && resultCode == 3002) {
            ArrayList<String> strings = data.getStringArrayListExtra("strings");
            label.setText(strings.toString() + "  ");
        } else if (requestCode == 4001) {
            headUri = imageuri.toString();
            GlideUtil.loadCircle(getContext(), headUri, head);
        } else if (requestCode == 5001) {
            headUri = Matisse.obtainPathResult(data).get(0);
            GlideUtil.loadCircle(getContext(), headUri, head);
        }
    }

    //性别
    public void show(int sex) {
        sexx = sex;
        Drawable drawableTrue = getContext().getResources().getDrawable(R.mipmap.select);
        Drawable drawableFalse = getContext().getResources().getDrawable(R.mipmap.select1);
        if (sex == 0) {
            man.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
            woman.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
        } else if (sex == 1) {
            man.setCompoundDrawablesWithIntrinsicBounds(drawableTrue, null, null, null);
            woman.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
        } else if (sex == 2) {
            man.setCompoundDrawablesWithIntrinsicBounds(drawableFalse, null, null, null);
            woman.setCompoundDrawablesWithIntrinsicBounds(drawableTrue, null, null, null);
        }
    }
}

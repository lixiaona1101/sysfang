package com.jiuhao.jhjk.activity.mine.DoctorCertified;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.view.net.RestClient;
import com.jiuhao.jhjk.view.net.callback.IError;
import com.jiuhao.jhjk.view.net.callback.ISuccess;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.UpdataPhoto;
import com.jiuhao.jhjk.dialog.PictureDialog;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.PictureUtils;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideEngine;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.io.File;
import java.util.LinkedHashMap;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

/**
 * 信息认证
 */
public class MessageCertifiedActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBack;
    /**
     * 标题
     */
    private TextView mTvTitle;
    /**
     * 确认
     */
    private TextView mTvTitleSure;
    private RelativeLayout mRlTitleSure;
    private RelativeLayout mRlTitle;
    /**
     * 请输入业务员邀请码
     */
    private EditText mInvitationCode;
    /**
     * 请输入医生真实姓名
     */
    private EditText mDoctorName;
    /**
     * 点击上传医师资格证书
     */
    private TextView mUpNoOk;
    private LinearLayout mUpOk;
    /**
     * 提交认证
     */
    private Button mMessageSubmit;
    /**
     * 点击查看图片
     */
    private TextView mSeeImg;
    /**
     * 点击上传医师执业证书
     */
    private TextView mDownNoOk;
    /**
     * 点击查看图片
     */
    private TextView mSeeImgDown;
    private LinearLayout mDownOk;
    private Uri imageuri;
    private String headUri = "1";//资格证
    private String headUri2 = "1";//执业证
    private Intent intent;
    private String uriZi;
    private String uriZhi;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_message_certified);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        mRlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        mRlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        mInvitationCode = (EditText) findViewById(R.id.invitation_code);
        mDoctorName = (EditText) findViewById(R.id.doctor_name);
        mUpNoOk = (TextView) findViewById(R.id.up_no_ok);
        mUpOk = (LinearLayout) findViewById(R.id.up_ok);
        mMessageSubmit = (Button) findViewById(R.id.message_submit);
        mSeeImg = (TextView) findViewById(R.id.see_img);
        mMessageSubmit.setOnClickListener(this);
        mTvTitle.setText("信息认证");
        mDownNoOk = (TextView) findViewById(R.id.down_no_ok);
        mSeeImgDown = (TextView) findViewById(R.id.see_img_down);
        mDownOk = (LinearLayout) findViewById(R.id.down_ok);
    }

    @Override
    protected void obtainData() {
        intent = getIntent();
    }

    @Override
    protected void initEvent() {

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击上传医师资格证书
        mUpNoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getphoto(1001, 1002);
            }
        });

        //点击上传执业证书
        mDownNoOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getphoto(2001, 2002);
            }
        });

        //资格证书查看
        mSeeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BigImgActivity.class);
                intent.putExtra("imgUrl", headUri);
                startActivity(intent);
            }
        });
        //执业证书查看
        mSeeImgDown.setOnClickListener(new View.OnClickListener() {
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
                File fileUri = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
                imageuri = Uri.fromFile(fileUri);

                //***7.0不在推荐明文传输url了，需要改成uri格式
                //***为了隐藏真实的路径
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    //通过FileProvider创建一个content类型的Uri
                    imageuri = FileProvider.getUriForFile(MessageCertifiedActivity.this, getApplicationContext().getPackageName() + ".FileProvider", fileUri);

                PictureUtils.takePicture(MessageCertifiedActivity.this, imageuri, requestcode);
//                IntentUtils.getCaptureIntent(imageuri);
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

    //回传值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            headUri = imageuri.getPath();
            Logger.e(headUri);
            updataPhoto(headUri, 1);
        } else if (requestCode == 1002 && resultCode == RESULT_OK) {
            headUri = Matisse.obtainPathResult(data).get(0);
            Logger.e(headUri);
            updataPhoto(headUri, 1);
        } else if (requestCode == 2001 && resultCode == RESULT_OK) {
            headUri2 = imageuri.getPath();
            Logger.e(headUri2);
            updataPhoto(headUri2, 2);
        } else if (requestCode == 2002 && resultCode == RESULT_OK) {
            headUri2 = Matisse.obtainPathResult(data).get(0);
            Logger.e(headUri2);
            updataPhoto(headUri2, 2);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.message_submit://提交认证
                updata();
                break;
        }
    }

    public void updataPhoto(String url, int flag) {

        if (flag == 1) {
            Config.showProgressDialog(getContext(), "正在上传资格证书...");
        } else if (flag == 2) {
            Config.showProgressDialog(getContext(), "正在上传执业证书...");
        }

        RestClient.builder().url(ConfigKeys.UPLOADIMG).file(url).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Logger.e(response);
                Config.dismissProgressDialog();

                UpdataPhoto updataPhoto = Json.parseObj(response, UpdataPhoto.class);
                if (flag == 1) {//资格证书
                    uriZi = updataPhoto.getData();
                    ToastUtils.show(updataPhoto.getMsg());
                    Logger.e("111资格证：" + uriZi);
                    mUpNoOk.setVisibility(View.GONE);
                    mUpOk.setVisibility(View.VISIBLE);
                } else if (flag == 2) {//执业证书
                    uriZhi = updataPhoto.getData();
                    ToastUtils.show(updataPhoto.getMsg());
                    Logger.e("111执业证：" + uriZhi);
                    mDownNoOk.setVisibility(View.GONE);
                    mDownOk.setVisibility(View.VISIBLE);
                }
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Logger.e(code + ":" + msg);
                Config.dismissProgressDialog();
                ToastUtils.show(msg);
                if (flag == 1) {//资格证书
                    uriZi = "资格证书上传失败";
                } else if (flag == 2) {//执业证书
                    uriZhi = "执业证书上传失败";
                }
            }
        }).build().upload();
    }

    public void updata() {
        Logger.e("资格证：" + uriZi);
        Logger.e("执业证：" + uriZhi);
        //邀请码
        String inviteCode = mInvitationCode.getText().toString();
        //医生姓名
        String name = mDoctorName.getText().toString();

        if (inviteCode != null && !inviteCode.isEmpty()) {
            if (name != null && !name.isEmpty()) {
                if (!uriZi.contains("资格证书上传失败")) {
                    if (!uriZhi.contains("执业证书上传失败")) {
                        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                        linkedHashMap.put("inviteCode", inviteCode);
                        linkedHashMap.put("name", name);
                        linkedHashMap.put("physicianCert", uriZhi);//执业证
                        linkedHashMap.put("physicianQualCert", uriZi);//资格证

                        OkHttpUtils.postJson(ConfigKeys.DOCAUTH, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(int code, String response) {
                                ToastUtils.show("操作成功！");
                                SPUtils.putString(getContext(), ConfigKeys.INVITECODE, inviteCode);
                                SPUtils.putString(getContext(), ConfigKeys.NAME, name);
                                setResult(13, intent);
                                startActivity(new Intent(getContext(), CertifiedActivity.class));
                                finish();
                            }

                            @Override
                            public void onFailure(int code, Exception e) {
                                Logger.e(e.getMessage());
                                ToastUtils.show(e.getMessage());
                            }
                        });
                    } else {
                        ToastUtils.show("执业证书上传失败");
                    }
                } else {
                    ToastUtils.show("资格证书上传失败");
                }
            } else {
                ToastUtils.show("请输入医生真实姓名");
            }
        } else {
            ToastUtils.show("请输入业务员邀请码");
        }

    }
}

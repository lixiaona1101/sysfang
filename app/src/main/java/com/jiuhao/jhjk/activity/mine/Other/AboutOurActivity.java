package com.jiuhao.jhjk.activity.mine.Other;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import androidx.core.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.AboutusBean;
import com.jiuhao.jhjk.bean.VersionBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.utils.SPUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.WebviewActivity;
import com.orhanobut.logger.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * 关于我们
 */
public class AboutOurActivity extends BaseActivity implements View.OnClickListener {

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
     * 法律声明
     */
    private TextView statement;
    /**
     * 当前已是最新版本2.0.1
     */
    private TextView newVersions;
    /**
     * 0571-86168750
     */
    private TextView servicePhone;
    /**
     * 在这里写下您的意见或反馈，非常感谢！
     */
    private EditText feelEdit;
    /**
     * 0
     */
    private TextView nowNumber;
    /**
     * /300
     */
    private TextView san;
    /**
     * 提交
     */
    private Button submit;
    private String hostline;
    private int versionId;
    private String versionNo;
    private int userId;
    private String phoneSize;
    private String msg;
    private AboutusBean aboutusBean;//客服热线
    private VersionBean versionBean;//版本信息
    private String version;
    private String content;
    private String urlApk;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    hostline = aboutusBean.getHostline();
                    servicePhone.setText(hostline + "  ");
                    break;
                case 1:
                    if (versionBean != null) {
                        versionId = versionBean.getId();
                        version = versionBean.getVersion();//最新版本号
                        content = versionBean.getContent();//更新内容
                        urlApk = versionBean.getUrl();//spk
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_about_our);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        statement = (TextView) findViewById(R.id.statement);
        newVersions = (TextView) findViewById(R.id.new_versions);
        servicePhone = (TextView) findViewById(R.id.service_phone);
        feelEdit = (EditText) findViewById(R.id.feel_edit);
        nowNumber = (TextView) findViewById(R.id.now_number);
        san = (TextView) findViewById(R.id.san);
        submit = (Button) findViewById(R.id.submit);
        tvTitle.setText("关于我们");
        submit.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {
        versionNo = getLocalVersionName(getContext()) + "";
        newVersions.setText("当前版本为" + versionNo + "  ");
        getPhoneData();
        getVersionData();
        userId = SPUtils.getInt(getContext(), ConfigKeys.USERID, 0);
        phoneSize = android.os.Build.MODEL;

        //版本
        newVersions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (versionNo.equals(version)) {
                    ToastUtils.show("已是最新版本！");
                } else {
                    HintDialog hintDialog = new HintDialog("版本升级", "最新版本为" + version + "  " + content);
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
                            showDownloadProgressDialog(getContext());
                        }
                    });
                }
            }
        });

        //长度
        feelEdit.addTextChangedListener(new TextWatcher() {
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


    //显示 showDownloadProgressDialog
    private void showDownloadProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在下载...");
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false); //设置不可点击界面之外的区域让对话框小时
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); //进度条类型
        progressDialog.show();
        new DownloadAPK(progressDialog).execute(urlApk);//urlApk为apk路径
    }

    private class DownloadAPK extends AsyncTask {
        ProgressDialog progressDialog;
        File file;

        public DownloadAPK(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            URL url;
            HttpURLConnection conn;
            BufferedInputStream bis = null;
            FileOutputStream fos = null;

            try {
                url = new URL((String) objects[0]);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);

                int fileLength = conn.getContentLength();
                bis = new BufferedInputStream(conn.getInputStream());
                //这个为在本地创建文件的路径
                //创建的文件路径：路径+名字.apk
                String fileName = Environment.getExternalStorageDirectory().getPath() + "/sysf.apk";
                file = new File(fileName);
                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                byte data[] = new byte[4 * 1024];
                long total = 0;
                int count;
                while ((count = bis.read(data)) != -1) {
                    total += count;
                    publishProgress((int) (total * 100 / fileLength));
                    fos.write(data, 0, count);
                    fos.flush();
                }
                fos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (bis != null) {
                        bis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            // 这里 改变ProgressDialog的进度值
            progressDialog.setProgress((int) values[0]);
        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            openFile(file); //打开安装apk文件操作
            progressDialog.dismiss(); //关闭对话框
        }


        private void openFile(File file) {
            if (file != null) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //判读版本是否在7.0以上
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Uri apkUri = FileProvider.getUriForFile(getContext(), getPackageName() + ".FileProvider", file);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                } else {
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                }
                startActivity(intent);
            }
        }
    }


    //获取当前版本号
    public static int getLocalVersionName(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }


    public void getVersionData() {
        OkHttpUtils.get(ConfigKeys.VERSION + "?appType=" + 1, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                versionBean = Json.parseObj(response, VersionBean.class);
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void getPhoneData() {
        OkHttpUtils.get(ConfigKeys.ABOUTUS + "?appType=" + 1, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                aboutusBean = Json.parseObj(response, AboutusBean.class);
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
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //客服电话
        servicePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hostline != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + hostline);
                    intent.setData(data);
                    startActivity(intent);
                }
            }
        });


        //法律声明
        statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtra("title", "法律声明");
                intent.putExtra("html", ConfigKeys.USER_INSTRUCTIONS);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                msg = feelEdit.getText().toString();
                if (feelEdit != null && feelEdit.length() != 0) {
                    postFeeData();
                } else {
                    ToastUtils.show("请输入反馈意见再提交！");
                }
                break;
        }
    }

    public void postFeeData() {

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("versionId", versionId);
        linkedHashMap.put("versionNo", versionNo);
        linkedHashMap.put("userId", userId);
        linkedHashMap.put("phoneSize", phoneSize);
        linkedHashMap.put("phoneOs", "Android");
        linkedHashMap.put("msg", msg);

        OkHttpUtils.postJson(ConfigKeys.SYYSFEE, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("提交成功！");
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

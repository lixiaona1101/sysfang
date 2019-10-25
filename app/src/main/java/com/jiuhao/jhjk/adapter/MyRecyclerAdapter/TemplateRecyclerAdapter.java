package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.Config;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.HomePage.MainEvoActivity;
import com.jiuhao.jhjk.adapter.GridListAdapter.GvSelectedAdapter;
import com.jiuhao.jhjk.bean.CaseTemplateBean;
import com.jiuhao.jhjk.bean.SaveImageBean;
import com.jiuhao.jhjk.bean.TemplateBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.BottomDialogCreator;
import com.jiuhao.jhjk.utils.CollectionUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.jiuhao.jhjk.view.MyGridView;
import com.jiuhao.jhjk.wechat.JHJKWeChat;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by lxn on 2019/9/19.
 */

public class TemplateRecyclerAdapter extends RecyclerView.Adapter<TemplateRecyclerAdapter.MyHolder> {

    private FragmentManager supportFragmentManager;
    private Context context;
    private List<TemplateBean> templateBeans;
    private boolean check = false;//标识是否下拉
    private boolean codeCeck = false;//标识是否生成二维码
    private AlertDialog popDialog;//底部弹出框
    private CaseTemplateBean caseTemplateBeans;//单个模板详情
    private SaveImageBean  saveImageBean;
    private String codeUrl;//二維碼
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    //传递数据 使用模板开方
                    Intent intent = new Intent(context, MainEvoActivity.class);
                    intent.putExtra("orinType", 2);
                    //模板详情
                    intent.putExtra("templateBean", (Serializable) caseTemplateBeans);
                    context.startActivity(intent);
                    break;
            }
            return false;
        }
    });

    public TemplateRecyclerAdapter(FragmentManager supportFragmentManager, Context context, List<TemplateBean> templateBeans) {
        this.context = context;
        this.templateBeans = templateBeans;
        this.supportFragmentManager = supportFragmentManager;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_template, viewGroup, false);
        return new MyHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {

        int flag = templateBeans.get(i).getFlag();
        if (flag==0){
            myHolder.code.setImageDrawable(context.getDrawable(R.mipmap.erweima1));
        }else if (flag==1){
            myHolder.code.setImageDrawable(context.getDrawable(R.mipmap.erweima));
        }

        myHolder.symptom.setText(templateBeans.get(i).getSymptom());
        if (!templateBeans.get(i).getFormulationName().isEmpty()) {
            myHolder.formulationName.setText("【" + templateBeans.get(i).getFormulationName() + "】");
        }
        myHolder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = !check;
                if (check) {
                    myHolder.close.setImageResource(R.mipmap.open);
                    myHolder.line_text.setVisibility(View.GONE);
                    myHolder.show_lin.setVisibility(View.VISIBLE);
                    myHolder.freq.setText("【频次】" + templateBeans.get(i).getFreq());
                    myHolder.medMethod.setText("【用药方法】" + templateBeans.get(i).getMedMethod());
                } else {
                    myHolder.close.setImageResource(R.mipmap.close);
                    myHolder.line_text.setVisibility(View.VISIBLE);
                    myHolder.show_lin.setVisibility(View.GONE);
                }
            }
        });

        List<String> med = templateBeans.get(i).getMed();
        myHolder.mgv_model_med.setAdapter(new GvSelectedAdapter(context, med));
        myHolder.mgv_model_med.setClickable(false);
        myHolder.mgv_model_med.setPressed(false);
        myHolder.mgv_model_med.setEnabled(false);

        myHolder.code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0){
                    //先生成二维码 成功 底部弹框
                    getcreateCode(templateBeans.get(i),myHolder.code);
                }else if(flag==1){
                    //底部提醒分享框
                    showTemplate(templateBeans.get(i));
                }

            }
        });

        myHolder.delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codeCeck) {
                    //删除提示框
                    showDelete(templateBeans.get(i).getId(), templateBeans.get(i).getSymptom(), i);
                } else {
                    //直接删除
                    removeItem(i);
                }
            }
        });

        myHolder.use_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSelectorById(templateBeans.get(i).getId());
            }
        });
    }

    //二维码生成
    public void getcreateCode(TemplateBean templateBean, ImageView codeImageview){
        Config.showProgressDialog(context,"正在生成二维码！");

        OkHttpUtils.get(ConfigKeys.CREATECODE+"?id="+templateBean.getId(), null, new OkHttpUtils.ResultCallback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                codeUrl=response;//二維碼
                Config.dismissProgressDialog();
                templateBean.setFlag(1);
                codeImageview.setImageDrawable(context.getDrawable(R.mipmap.erweima));
                //底部提醒分享框
                showTemplate(templateBean);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                Config.dismissProgressDialog();
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //获取模板详情data
    public void getSelectorById(int templateId) {
        Logger.e("templateId:"+templateId);
        OkHttpUtils.get(ConfigKeys.CASETEMPLATESELECTBYID + "?templateId=" + templateId, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                caseTemplateBeans = Json.parseObj(response, CaseTemplateBean.class);
                Logger.e(caseTemplateBeans.toString());
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //永久二维码 删除提醒
    public void showDelete(int id, String name, int i) {
        HintDialog myDialog = new HintDialog("删除提醒", "当前处方已生成永久二维码，删除之后二维码将会失效，确认删除吗？");
        myDialog.show(supportFragmentManager);
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
                deleteData(id, name, i);
            }
        });
    }

    //删除模板
    public void deleteData(int id, String name, int i) {

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", id);
        OkHttpUtils.postJson(ConfigKeys.TEMPLATEDELETE, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show(name + "模板删除成功！");
                removeItem(i);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    //移除某个位置的数据
    public void removeItem(int position) {
        if (CollectionUtils.isEmpty(templateBeans) || position <= -1) {
            return;
        }
        notifyItemRemoved(position);
        templateBeans.remove(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }


    //显示分享及二维码
    private void showTemplate(TemplateBean entity) {
        TextView tvTemplateName = null, tvShareWx = null, tvSharePy = null, tvShareCode = null;

        if (popDialog == null) {
            popDialog = new AlertDialog.Builder(context).create();
        }
        popDialog.show();
        Window window = BottomDialogCreator.getDialogWindow(popDialog, R.layout.pop_show_template);
        if (window != null) {
            ImageView ivDismiss = window.findViewById(R.id.iv_template_delete);
            tvTemplateName = window.findViewById(R.id.tv_template_name);
            tvShareWx = window.findViewById(R.id.tv_template_share_wx);
            tvSharePy = window.findViewById(R.id.tv_template_share_py);
            tvShareCode = window.findViewById(R.id.tv_template_share_code);
            ivDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popDialog.dismiss();
                }
            });
        }
        String name = entity.getSymptom();
        tvTemplateName.setText(name);
        tvSharePy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDialog.dismiss();
                JHJKWeChat.getInstance().shareImg(1, codeUrl);
            }
        });

        tvShareWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDialog.dismiss();
                JHJKWeChat.getInstance().shareImg(2, codeUrl);
            }
        });

        tvShareCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDialog.dismiss();
                codeCeck = true;
                Intent intent = new Intent(context, BigImgActivity.class);
                intent.putExtra("imgUrl", codeUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return templateBeans.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView symptom;
        public TextView formulationName;
        public ImageView code;
        public ImageView close;
        public TextView line_text;
        public LinearLayout show_lin;
        public MyGridView mgv_model_med;
        public TextView freq;
        public TextView medMethod;
        public Button delete_but;
        public Button use_but;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            symptom = itemView.findViewById(R.id.symptom);
            formulationName = itemView.findViewById(R.id.formulationName);
            code = itemView.findViewById(R.id.code);
            close = itemView.findViewById(R.id.close);
            line_text = itemView.findViewById(R.id.line_text);
            show_lin = itemView.findViewById(R.id.show_lin);

            mgv_model_med = itemView.findViewById(R.id.mgv_model_med);
            freq = itemView.findViewById(R.id.freq);
            medMethod = itemView.findViewById(R.id.medMethod);
            delete_but = itemView.findViewById(R.id.delete_but);
            use_but = itemView.findViewById(R.id.use_but);
        }
    }
}

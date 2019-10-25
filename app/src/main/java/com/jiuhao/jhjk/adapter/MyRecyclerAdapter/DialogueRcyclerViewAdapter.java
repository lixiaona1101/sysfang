package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.patient.QuestionAnswerActivity;
import com.jiuhao.jhjk.bean.AnswerBean;
import com.jiuhao.jhjk.bean.ReadedBean;
import com.jiuhao.jhjk.utils.BigImgActivity;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DialogueRcyclerViewAdapter extends BaseQuickAdapter<ReadedBean.DataBean, BaseViewHolder> {

    private String NickName;
    private String pickUrl;
    private String headurl;

    public DialogueRcyclerViewAdapter(int layoutResId, @Nullable List<ReadedBean.DataBean> data,
                                      String nickName, String pickUrl, String headUrl) {
        super(layoutResId, data);
        this.NickName = nickName;
        this.pickUrl = pickUrl;
        this.headurl = headUrl;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ReadedBean.DataBean data) {
        helper.setIsRecyclable(false);

        RelativeLayout person_rela = helper.getView(R.id.person_rela);
        ImageView person_head = helper.getView(R.id.person_head);
        TextView person_contemn_text = helper.getView(R.id.person_contemn_text);
        ImageView person_contemn_img_width = helper.getView(R.id.person_contemn_img_width);
        ImageView person_contemn_img_height = helper.getView(R.id.person_contemn_img_height);

        RelativeLayout doctor_rela = helper.getView(R.id.doctor_rela);
        ImageView doctor_head = helper.getView(R.id.doctor_head);
        TextView doctor_content_text = helper.getView(R.id.doctor_content_text);
        ImageView doctor_content_img_witdth = helper.getView(R.id.doctor_content_img_width);
        ImageView doctor_content_img_height = helper.getView(R.id.doctor_content_img_height);

        LinearLayout sen_lin = helper.getView(R.id.sen_lin);
        TextView send_d = helper.getView(R.id.send_d);
        TextView send_name = helper.getView(R.id.send_name);

        TextView send_time = helper.getView(R.id.send_time);
        TextView doctor_time = helper.getView(R.id.doctor_time);
        TextView person_time = helper.getView(R.id.person_time);

        String contentitle = data.getContent();//文字内容
        String url = data.getUrl();//图片内容

        int msgType = data.getMsgType();//消息类型 1文字 2图片 3问诊单问题 4问诊单答案
        int sendType = data.getSendType();

        Logger.d("convert", "convert:" + data.getId() + ":" + data.getTimeStr() + "**" + data.getCreateTime());

        if (sendType == 2) {//2患者
            person_rela.setVisibility(View.VISIBLE);
            doctor_rela.setVisibility(View.GONE);
            sen_lin.setVisibility(View.GONE);
            GlideUtil.loadCircle(mContext, pickUrl, person_head);
            if (msgType == 1) {
                person_contemn_text.setVisibility(View.VISIBLE);
                person_contemn_img_height.setVisibility(View.GONE);
                person_contemn_img_width.setVisibility(View.GONE);
                person_contemn_text.setText(contentitle);
                person_time.setText(data.getTimeStr());
            } else if (msgType == 2) {
                person_contemn_text.setVisibility(View.GONE);
                GlideUtil.load(mContext, url, person_contemn_img_width, person_contemn_img_height);
                person_time.setText(data.getTimeStr());
            } else if (msgType == 4) {//醫生端收到患者的問診答案
                send_time.setText(data.getTimeStr());
                doctor_rela.setVisibility(View.GONE);
                person_rela.setVisibility(View.GONE);
                sen_lin.setVisibility(View.VISIBLE);
                send_d.setText("您收到了" + NickName + "的");
                send_name.setText(contentitle);
            }
        } else if (sendType == 1) {//1医生
            doctor_rela.setVisibility(View.VISIBLE);
            person_rela.setVisibility(View.GONE);
            sen_lin.setVisibility(View.GONE);
            GlideUtil.loadCircle(mContext, headurl, doctor_head);
            if (msgType == 1) {
                doctor_content_text.setVisibility(View.VISIBLE);
                doctor_content_img_witdth.setVisibility(View.GONE);
                doctor_content_img_height.setVisibility(View.GONE);
                doctor_content_text.setText(contentitle);
                doctor_time.setText(data.getTimeStr());
            } else if (msgType == 2) {
                doctor_content_text.setVisibility(View.GONE);
                GlideUtil.load(mContext, url, doctor_content_img_witdth, doctor_content_img_height);
                doctor_time.setText(data.getTimeStr());
            } else if (msgType == 3) {//醫生給患者發送問診單
                doctor_rela.setVisibility(View.GONE);
                person_rela.setVisibility(View.GONE);
                sen_lin.setVisibility(View.VISIBLE);
                send_name.setText(contentitle);
                send_time.setText(data.getTimeStr());
            }
        }

        //查看問診單
        send_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msgType == 4) {//患者
                    getQuestionData(data.getRecordId(),String.valueOf(data.getId()),mContext);//答案批次 患者id
                }
            }
        });

        person_contemn_img_width.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BigImgActivity.class);
                intent.putExtra("imgUrl", url);
                mContext.startActivity(intent);
            }
        });

        person_contemn_img_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BigImgActivity.class);
                intent.putExtra("imgUrl", url);
                mContext.startActivity(intent);
            }
        });

        doctor_content_img_witdth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BigImgActivity.class);
                intent.putExtra("imgUrl", url);
                mContext.startActivity(intent);
            }
        });

        doctor_content_img_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BigImgActivity.class);
                intent.putExtra("imgUrl", url);
                mContext.startActivity(intent);
            }
        });
    }

    private void getQuestionData(String s, String personId, Context context) {
        String url = ConfigKeys.ANSWER+"?batchNO="+s;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                com.orhanobut.logger.Logger.e(response);
                AnswerBean answerBean = Json.parseObj(response, AnswerBean.class);
                Intent intent = new Intent(context, QuestionAnswerActivity.class);
                intent.putExtra("answerBean",answerBean);//问诊单答案
                intent.putExtra("personId",personId);//患者id
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(int code, Exception e) {
                com.orhanobut.logger.Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

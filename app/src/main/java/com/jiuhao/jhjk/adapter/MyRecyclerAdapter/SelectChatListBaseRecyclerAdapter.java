package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.patient.DialogueActivity;
import com.jiuhao.jhjk.adapter.base.RecyclerBaseAdapter;
import com.jiuhao.jhjk.adapter.base.ViewHolder;
import com.jiuhao.jhjk.bean.SelectChatListBean;
import com.jiuhao.jhjk.dialog.HintDialog;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

public class SelectChatListBaseRecyclerAdapter extends RecyclerBaseAdapter<SelectChatListBean> {

    private FragmentManager fragmentManager;
    public SelectChatListBaseRecyclerAdapter(@NonNull Context context, @NonNull List<SelectChatListBean> mDataList, FragmentManager fragmentManager) {
        super(context, mDataList);
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, SelectChatListBean selectChatListBean, int position) {
         ImageView person_head= holder.getView(R.id.person_head);
         TextView person_name=holder.getView(R.id.person_name);
         TextView person_conment= holder.getView(R.id.person_conment);
         TextView person_time= holder.getView(R.id.person_time);
         TextView not_read_number= holder.getView(R.id.not_read_number);
         LinearLayout not_read_lin= holder.getView(R.id.not_read_lin);

        GlideUtil.loadCircle(getContext(),selectChatListBean.getAvatar(),person_head);
        person_name.setText(selectChatListBean.getPatientName());
        person_conment.setText(selectChatListBean.getContent());
        person_time.setText(selectChatListBean.getTimeStr());
        Logger.e(selectChatListBean.getTimeStr());
        int notReadNum = selectChatListBean.getNotReadNum();//未读数量
        if(notReadNum==0){
            not_read_lin.setVisibility(View.GONE);
        }else {
            not_read_lin.setVisibility(View.VISIBLE);
            not_read_number.setText(selectChatListBean.getNotReadNum()+"");
        }

        //跟患者聊天页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                not_read_lin.setVisibility(View.GONE);
                Intent intent = new Intent(getContext(), DialogueActivity.class);
                intent.putExtra("nickName",selectChatListBean.getPatientName());//患者姓名
                intent.putExtra("id", selectChatListBean.getPatientId());//患者id
                intent.putExtra("img", selectChatListBean.getAvatar());//患者头像
                getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                HintDialog hintDialog = new HintDialog("提示",
                        "确定刪除跟患者“" + selectChatListBean.getPatientName()  + "”的聊天？");
                hintDialog.show(fragmentManager);
                hintDialog.setOnLeftClick(new HintDialog.OnLeftClick() {
                    @Override
                    public void onLeft() {
                    }
                });
                hintDialog.setOnRightClick(new HintDialog.OnRightClick() {
                    @Override
                    public void onRight() {
                        hintDialog.dismiss();
                        deleteChatData(selectChatListBean.getId(),position);
                    }
                });
                return false;
            }
        });

    }

    //删除聊天室
    public void deleteChatData(int chatId,int postion){

        OkHttpUtils.get(ConfigKeys.DELETEDOCPATIENTMAG+"?chatId="+chatId, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("删除成功");
                removeItem(postion);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
}

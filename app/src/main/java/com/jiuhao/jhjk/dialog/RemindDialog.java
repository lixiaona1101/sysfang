package com.jiuhao.jhjk.dialog;


import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.dialog.base.BaseDialog;

/**
 * Created by lxn on 2019/9/9.
 * 提醒弹出框
 */

@SuppressLint("ValidFragment")
public class RemindDialog extends BaseDialog implements View.OnClickListener {
    private View view;

    private OnRightClick onRightClick;
    private OnLeftClick onLeftClick;
    private String remindconten;
    /**
     * 提示
     */
    private TextView dialogEditName;
    /**
     * 确定删除该分组吗？删除后组内患者将移动至“默认分组”
     */
    private TextView remindContent;
    /**
     * 取消
     */
    private Button dialogCancel;
    /**
     * 确定
     */
    private Button dialogConfirm;

    @SuppressLint("ValidFragment")
    public RemindDialog(String remindconten) {
        super();
        this.remindconten = remindconten;
    }

    public RemindDialog() {
        super();
    }

    @Override
    public int getLayoutId() {
        return R.layout.remind_dialog;
    }

    @Override
    public void initView(View view) {

        dialogEditName = (TextView) view.findViewById(R.id.dialog_EditName);
        remindContent = (TextView) view.findViewById(R.id.remind_content);
        dialogCancel = (Button) view.findViewById(R.id.dialog_cancel);
        dialogCancel.setOnClickListener(this);
        dialogConfirm = (Button) view.findViewById(R.id.dialog_confirm);
        dialogConfirm.setOnClickListener(this);
    }

    public RemindDialog setOnRightClick(OnRightClick onRightClick) {
        this.onRightClick = onRightClick;
        return this;
    }


    public RemindDialog setOnLeftClick(OnLeftClick onLeftClick) {
        this.onLeftClick = onLeftClick;
        return this;
    }


    @Override
    public void initData() {
//        remindContent.setText(remindconten);
    }

    @Override
    public void initEvent() {
        dialogConfirm.setOnClickListener(v -> {
            if (onRightClick != null) {
                onRightClick.onRight();
            }
        });
        dialogCancel.setOnClickListener(v -> {
            if (onLeftClick != null) {
                onLeftClick.onLeft();
            }
            dismiss();
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.dialog_cancel:
                break;
            case R.id.dialog_confirm:
                break;
        }
    }

    public interface OnRightClick {
        void onRight();
    }

    public interface OnLeftClick {
        void onLeft();
    }

    @Override
    protected int getDialogWight() {
        return getResources().getDimensionPixelSize(R.dimen.dp320);
    }

    @Override
    protected int getDialogHeight() {
        return getResources().getDimensionPixelSize(R.dimen.dp170);
    }

    @Override
    protected int getDialogGravity() {
        return Gravity.CENTER;
    }

}

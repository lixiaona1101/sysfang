package com.jiuhao.jhjk.dialog;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.dialog.base.BaseDialog;


/**
 * 自定义dialog
 * Created by Administrator on 2019/8/27.
 */
public class MyDialog extends BaseDialog {
    private View view;
    /**
     * 请输入问诊单名称
     */
    private EditText dialogEditName;
    /**
     * 取消
     */
    private Button dialogCancel;
    /**
     * 确定
     */
    private Button dialogConfirm;

    private OnRightClick onRightClick;
    private OnLeftClick onLeftClick;


    @Override
    public int getLayoutId() {
        return R.layout.my_dialog;
    }

    @Override
    public void initView(View view) {

        dialogEditName = (EditText) view.findViewById(R.id.dialog_EditName);
        dialogCancel = (Button) view.findViewById(R.id.dialog_cancel);
        dialogConfirm = (Button) view.findViewById(R.id.dialog_confirm);
    }

    public String getEdit() {
        return dialogEditName.getText().toString();
    }

    public MyDialog setOnRightClick(OnRightClick onRightClick) {
        this.onRightClick = onRightClick;
        return this;
    }


    public MyDialog setOnLeftClick(OnLeftClick onLeftClick) {
        this.onLeftClick = onLeftClick;
        return this;
    }


    @Override
    public void initData() {

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
        return getResources().getDimensionPixelSize(R.dimen.dp160);
    }

    @Override
    protected int getDialogGravity() {
        return Gravity.CENTER;
    }

}

package com.jiuhao.jhjk.dialog;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.dialog.base.BaseDialog;

/**
 * Created by lxn on 2019/9/24.
 * 删除提醒 单标题
 */

@SuppressLint("ValidFragment")
public class HintTitleDialog  extends BaseDialog implements View.OnClickListener {

    private HintDialog.OnRightClick onRightClick;
    private HintDialog.OnLeftClick onLeftClick;
    /**
     * 删除提醒
     */
    private TextView dialogEditName;
    /**
     * 取消
     */
    private Button dialogCancel;
    /**
     * 确定
     */
    private Button dialogConfirm;
    /**
     * 当前处方已生成永久二维码，删除之后二维码将会失效，确认删除吗？
     */
    private String title;

    public HintTitleDialog(String title) {
        super();
        this.title=title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.hint_title_dialog;
    }

    @Override
    public void initView(View view) {

        dialogEditName = (TextView) view.findViewById(R.id.dialog_EditName);
        dialogCancel = (Button) view.findViewById(R.id.dialog_cancel);
        dialogCancel.setOnClickListener(this);
        dialogConfirm = (Button) view.findViewById(R.id.dialog_confirm);
        dialogConfirm.setOnClickListener(this);
    }

    public HintTitleDialog setOnRightClick(HintDialog.OnRightClick onRightClick) {
        this.onRightClick = onRightClick;
        return this;
    }


    public HintTitleDialog setOnLeftClick(HintDialog.OnLeftClick onLeftClick) {
        this.onLeftClick = onLeftClick;
        return this;
    }


    @Override
    public void initData() {
        dialogEditName.setText(title);
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
        return getResources().getDimensionPixelSize(R.dimen.dp160);
    }

    @Override
    protected int getDialogGravity() {
        return Gravity.CENTER;
    }

}


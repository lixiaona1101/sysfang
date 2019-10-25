package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class itemQuestionAnswerRcyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public String answer;

    public itemQuestionAnswerRcyclerAdapter(int layoutResId, @Nullable List<String> data, String answer) {
        super(layoutResId, data);
        this.answer = answer;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {

        ImageView select = helper.getView(R.id.select1);
        TextView questionC = helper.getView(R.id.question_c);

        questionC.setText(item);

        int answerN = -1;
        if (answer.contains("a")) {
            answerN = 0;
        } else if (answer.contains("b")) {
            answerN = 1;
        } else if (answer.contains("c")) {
            answerN = 2;
        } else if (answer.contains("d")) {
            answerN = 3;
        } else if (answer.contains("e")) {
            answerN = 4;
        } else if (answer.contains("f")) {
            answerN = 5;
        } else if (answer.contains("g")) {
            answerN = 6;
        } else if (answer.contains("h")) {
            answerN = 7;
        } else if (answer.contains("i")) {
            answerN = 8;
        } else if (answer.contains("j")) {
            answerN = 9;
        }

        if (answerN == helper.getAdapterPosition()) {
            select.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.select));
        } else {
            select.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.select1));
        }
    }
}

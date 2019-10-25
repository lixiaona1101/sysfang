package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.AnswerBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionAnswerRecyclerAdapter extends BaseQuickAdapter<AnswerBean.DqsBean, BaseViewHolder> {

    public QuestionAnswerRecyclerAdapter(int layoutResId, @Nullable List<AnswerBean.DqsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AnswerBean.DqsBean item) {

        TextView questionName = helper.getView(R.id.question_name);
        TextView questionType = helper.getView(R.id.question_type);
        RecyclerView questionAnswerRecycler = helper.getView(R.id.question_answer_recycler);
        TextView answer = helper.getView(R.id.answer);
        questionAnswerRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        questionName.setText((helper.getAdapterPosition()+1) + "、" + item.getContent());

        //答案类型 0单选题 1多选题 2简答题
        int questionType1 = item.getQuestionType();
        if (questionType1 == 0) {
            questionAnswerRecycler.setVisibility(View.VISIBLE);
            answer.setVisibility(View.GONE);
            questionType.setText("（单选题）");
        } else if (questionType1 == 1) {
            questionAnswerRecycler.setVisibility(View.VISIBLE);
            answer.setVisibility(View.GONE);
            questionType.setText("（多选题）");

        } else if (questionType1 == 2) {
            questionAnswerRecycler.setVisibility(View.GONE);
            answer.setVisibility(View.VISIBLE);
            questionType.setText("（简答题）");
            answer.setText(item.getAnswer());
        }

//        {\"a\":\"啊\",\"b\":\"吧\",\"c\":\"吃\",\"d\":\"的\"}
        String answerChoose = item.getAnswerChoose();
        List<String> AstringArrayList = new ArrayList<>();
        List<String> DstringArrayList = new ArrayList<>();
        String replace = answerChoose.replace("{", "");
        String replace1 = replace.replace("}", "");
        String replace2 = replace1.replace("\\\"", "");
        String[] split = replace2.split(",");
        for (int i = 0; i < split.length; i++) {
            String strings = split[i];
            String[] split1 = strings.split(":");
            for (int j = 0; j < split1.length; j++) {
                String strings1 = split1[j];
                if (j % 2 == 1) {
                    DstringArrayList.add(strings1);
                }
//                else{
//                    AstringArrayList.add(strings1);
//                }
            }
        }

        if(questionType1 == 0){
            itemQuestionAnswerRcyclerAdapter itemQuestionAnswerRcyclerAdapter =
                    new itemQuestionAnswerRcyclerAdapter(R.layout.item_item_compile_question, DstringArrayList,item.getAnswer());
            questionAnswerRecycler.setAdapter(itemQuestionAnswerRcyclerAdapter);
        }

    }
}

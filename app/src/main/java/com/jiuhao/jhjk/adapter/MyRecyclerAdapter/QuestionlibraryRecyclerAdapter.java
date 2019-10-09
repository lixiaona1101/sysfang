package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.KeShiBean;

import java.util.List;

/**
 * 问诊单-问题库-根据科室id查询的问题库
 */

public class QuestionlibraryRecyclerAdapter extends RecyclerView.Adapter<QuestionlibraryRecyclerAdapter.LibraryHolder> {

    private Context context;
    private List<KeShiBean> keShiBeans;
    private OnListen onListen;
    private downListen downListen;

    public QuestionlibraryRecyclerAdapter(Context context, List<KeShiBean> keShiBeans,OnListen onListen,downListen downListen) {
        this.context = context;
        this.keShiBeans = keShiBeans;
        this.onListen=onListen;
        this.downListen=downListen;
    }

    @NonNull
    @Override
    public QuestionlibraryRecyclerAdapter.LibraryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_question, viewGroup, false);
        return new LibraryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionlibraryRecyclerAdapter.LibraryHolder libraryHolder, int i) {

        //0单选 1多选 2简单题
        int questionType = keShiBeans.get(i).getQuestionType();
        if (questionType == 0) {
            libraryHolder.questionType.setText("(单选)");
        } else if (questionType == 1) {
            libraryHolder.questionType.setText("(多选)");
        } else if (questionType == 2) {
            libraryHolder.questionType.setText("(简单题)");
        }

        //问题
        String content = keShiBeans.get(i).getContent();
        libraryHolder.question.setText((i + 1) + "." + content);

        //选项
        int items = keShiBeans.get(i).getItems();
        libraryHolder.items.setText("共" + items + "个选项");

        libraryHolder.check_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = keShiBeans.get(i).isCheck();
                keShiBeans.get(i).setCheck(!check);
                if (keShiBeans.get(i).isCheck()) {
                    libraryHolder.check_img.setImageResource(R.mipmap.select);
                    onListen.upListen();
                } else {
                    libraryHolder.check_img.setImageResource(R.mipmap.select1);
                    downListen.doListen();
                }
            }
        });
    }

    public interface OnListen{
       void upListen();
    }

    public interface downListen{
        void doListen();
    }
    @Override
    public int getItemCount() {
        return keShiBeans.size();
    }


    public class LibraryHolder extends RecyclerView.ViewHolder {

        public TextView questionType;
        public TextView question;
        public TextView items;
        public ImageView check_img;

        public LibraryHolder(@NonNull View itemView) {
            super(itemView);
            questionType = itemView.findViewById(R.id.questionType);
            question = itemView.findViewById(R.id.question);
            items = itemView.findViewById(R.id.items);
            check_img = itemView.findViewById(R.id.check_img);
        }
    }
}

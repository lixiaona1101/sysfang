package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.bean.IgQuestionBean;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by lxn on 2019/9/4.
 * 问诊单问题列表适配
 */

public class CompileBillRecyclerAdapter extends RecyclerView.Adapter<CompileBillRecyclerAdapter.BillHolder> {
    private Context context;
    private List<IgQuestionBean> igQuestionBeans;
    private deleListen deleListen;

    public CompileBillRecyclerAdapter(Context context, List<IgQuestionBean> igQuestionBeans,deleListen deleListen) {
        this.context = context;
        this.igQuestionBeans = igQuestionBeans;
        this.deleListen=deleListen;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_compile_question, viewGroup, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder billHolder, int i) {

        billHolder.question_name.setText((i + 1) + "." + igQuestionBeans.get(i).getContent());
        if (i == 0) {
            billHolder.button_up.setVisibility(View.GONE);
            billHolder.button_last.setVisibility(View.GONE);
            billHolder.button_next.setVisibility(View.VISIBLE);
            billHolder.button_down.setVisibility(View.VISIBLE);
        } else if (i == igQuestionBeans.size() - 1) {
            billHolder.button_next.setVisibility(View.GONE);
            billHolder.button_down.setVisibility(View.GONE);
            billHolder.button_up.setVisibility(View.VISIBLE);
            billHolder.button_last.setVisibility(View.VISIBLE);
        } else {
            billHolder.button_next.setVisibility(View.VISIBLE);
            billHolder.button_down.setVisibility(View.VISIBLE);
            billHolder.button_up.setVisibility(View.VISIBLE);
            billHolder.button_last.setVisibility(View.VISIBLE);
        }

        //删除
        billHolder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(i);
                deleListen.onDele(i);
            }
        });

        //答案recyclerview适配
        String answerChoose = igQuestionBeans.get(i).getAnswerChoose();
//        Logger.e(answerChoose);



        //上移
        billHolder.button_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(i - 1, igQuestionBeans.get(i));
                deleteItem(i + 1);
            }
        });

        //下移
        billHolder.button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(i + 2, igQuestionBeans.get(i));
                deleteItem(i);
            }
        });

        //置顶
        billHolder.button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(0, igQuestionBeans.get(i));
                deleteItem(i + 1);
            }
        });

        //置底
        billHolder.button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(igQuestionBeans.size(), igQuestionBeans.get(i));
                deleteItem(i);
            }
        });

    }

    //从某个位置开始，插入一个数据
    public void insertItem(int position, IgQuestionBean igQuestionBean) {
        if (igQuestionBeans == null) {
            return;
        }
        notifyItemInserted(position);
        igQuestionBeans.add(position, igQuestionBean);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    //从某个位置开始，删除一个数据
    public void deleteItem(int position) {
        if (this.igQuestionBeans.isEmpty() || position <= -1) {
            return;
        }
        notifyItemRemoved(position);
        this.igQuestionBeans.remove(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }


    @Override
    public int getItemCount() {
        return igQuestionBeans.size();
    }

    public interface deleListen{
       void onDele(int i);
    }
    public class BillHolder extends RecyclerView.ViewHolder {

        public TextView question_name;//标题
        public RecyclerView item_recycler;//答案
        public Button button_up;//置顶
        public Button button_last;//上移
        public Button button_next;//下移
        public Button button_down;//置底
        public TextView compile;//编辑
        public ImageView trash;//删除

        public BillHolder(@NonNull View itemView) {
            super(itemView);
            question_name = itemView.findViewById(R.id.question_name);
            item_recycler = itemView.findViewById(R.id.item_recycler);
            button_up = itemView.findViewById(R.id.button_up);
            button_last = itemView.findViewById(R.id.button_last);
            button_next = itemView.findViewById(R.id.button_next);
            button_down = itemView.findViewById(R.id.button_down);
            compile = itemView.findViewById(R.id.compile);
            trash = itemView.findViewById(R.id.trash);
        }
    }
}

package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.mine.Bill.QuestionActivity;
import com.jiuhao.jhjk.bean.IgQuestion2Bean;
import com.jiuhao.jhjk.bean.IgQuestionBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxn on 2019/9/4.
 * 问诊单问题列表适配
 */

public class CompileBillRecyclerAdapter extends RecyclerView.Adapter<CompileBillRecyclerAdapter.BillHolder> {
    private Context context;
    private  List<IgQuestion2Bean.DqsBean> dqsBeanList;
    private deleListen deleListen;
    private upListen upListen;

    public CompileBillRecyclerAdapter(Context context,
                                      List<IgQuestion2Bean.DqsBean> dqsBeanList,
                                      deleListen deleListen,upListen upListen) {
        this.context = context;
        this.dqsBeanList = dqsBeanList;
        this.deleListen=deleListen;
        this.upListen=upListen;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_compile_question, viewGroup, false);
        return new BillHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder billHolder, int i) {

        billHolder.question_name.setText((i + 1) + "." + dqsBeanList.get(i).getContent());
        if (i == 0) {
            billHolder.button_up.setVisibility(View.GONE);
            billHolder.button_last.setVisibility(View.GONE);
            billHolder.button_next.setVisibility(View.VISIBLE);
            billHolder.button_down.setVisibility(View.VISIBLE);
        } else if (i == dqsBeanList.size() - 1) {
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
                deleListen.onDele(i);
                deleteItem(i);
            }
        });

        //答案recyclerview适配
        String answerChoose = dqsBeanList.get(i).getAnswerChoose();
        Logger.e(answerChoose);
        if(!answerChoose.isEmpty()){
            String replace = answerChoose.replace("{", "");
            String replace1 = replace.replace("}", "");
            String replace2 = replace1.replace("\"", "");

            String[] strings = replace2.split(",");
            List<String> stringData = new ArrayList<>();
            for (int j = 0; j < strings.length; j++) {
                stringData.add(strings[j]);
            }

            CompileBillRecycleritemAdapter compileBillRecycleritemAdapter =
                    new CompileBillRecycleritemAdapter(R.layout.item_item_compile_question, stringData);
            billHolder.item_recycler.setAdapter(compileBillRecycleritemAdapter);
        }

        //答案编辑
        billHolder.compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upListen.onUp(i,dqsBeanList.get(i));
            }
        });

        //上移
        billHolder.button_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(i - 1, dqsBeanList.get(i));
                deleteItem(i + 1);
            }
        });

        //下移
        billHolder.button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(i + 2, dqsBeanList.get(i));
                deleteItem(i);
            }
        });

        //置顶
        billHolder.button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(0, dqsBeanList.get(i));
                deleteItem(i + 1);
            }
        });

        //置底
        billHolder.button_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertItem(dqsBeanList.size(), dqsBeanList.get(i));
                deleteItem(i);
            }
        });

    }

    //从某个位置开始，插入一个数据
    public void insertItem(int position, IgQuestion2Bean.DqsBean dqsBean) {
        if (dqsBeanList == null) {
            return;
        }
        notifyItemInserted(position);
        dqsBeanList.add(position, dqsBean);
        notifyItemRangeChanged(position, getItemCount() - position);
    }

    //从某个位置开始，删除一个数据
    public void deleteItem(int position) {
        if (this.dqsBeanList.isEmpty() || position <= -1) {
            return;
        }
        notifyItemRemoved(position);
        this.dqsBeanList.remove(position);
        notifyItemRangeChanged(position, getItemCount() - position);
    }


    @Override
    public int getItemCount() {
        return dqsBeanList.size();
    }

    public interface deleListen{
       void onDele(int i);
    }

    public interface upListen{
        void onUp(int i,IgQuestion2Bean.DqsBean dqsBean);
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
            item_recycler.setLayoutManager(new LinearLayoutManager(context));
            button_up = itemView.findViewById(R.id.button_up);
            button_last = itemView.findViewById(R.id.button_last);
            button_next = itemView.findViewById(R.id.button_next);
            button_down = itemView.findViewById(R.id.button_down);
            compile = itemView.findViewById(R.id.compile);
            trash = itemView.findViewById(R.id.trash);
        }
    }
}

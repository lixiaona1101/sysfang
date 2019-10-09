package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.GridListAdapter.MedFryingAdapter;
import com.jiuhao.jhjk.adapter.base.RecyclerBaseAdapter;
import com.jiuhao.jhjk.adapter.base.ViewHolder;
import com.jiuhao.jhjk.bean.MedFryingBean;
import com.jiuhao.jhjk.bean.ShopedSelectBean;
import com.jiuhao.jhjk.utils.BottomDialogCreator;
import com.jiuhao.jhjk.utils.CollectionUtils;
import com.jiuhao.jhjk.utils.NumberUtils;
import com.jiuhao.jhjk.utils.ThreadUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.view.MyGridView;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.jiuhao.jhjk.APP.Config.userToken;

/**
 * Created by lxn on 2019/9/23.
 */

public class SelectMedRecyclerAdapter extends RecyclerBaseAdapter<ShopedSelectBean> {
    private instem instem;
    private insnum insnum;
    public HashMap<String, Float> herbsPriceMap = new HashMap<>();//单个药品的价格
    public HashMap<String, String> herbsNumMap = new HashMap<>();//单个药品的数量
    private MedFryingBean medFryingBean;

    public SelectMedRecyclerAdapter(@NonNull Context context, @NonNull List<ShopedSelectBean> mDataList, instem instem, insnum insnum) {
        super(context, mDataList);
        this.instem = instem;
        this.insnum = insnum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_select_med, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        //判断标记集合是否等于空
        if (!CollectionUtils.isEmpty(payloads)) {
            //拦截不向下传递   可以调用多个，然后判断标记，进行不同逻辑
            String tag = payloads.get(0).toString();
            if (tag.equals("设置煎法")) { //设置煎法
                TextView med_fry = holder.getView(R.id.med_fry);
                ShopedSelectBean shopedSelectBean = getItem(position);

                //这里判断（recycler会把超出屏幕的item删除掉，再次显示时会调用此方法，重新设置）
                if (!TextUtils.isEmpty(shopedSelectBean.getMedFry())) {
                    med_fry.setVisibility(View.VISIBLE);
                    med_fry.setText(shopedSelectBean.getMedFry());
                } else {
                    med_fry.setVisibility(View.GONE);
                }
            }
        } else {
            //向下传递  最终会调用  bindDataForView  这个方法
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    protected void bindDataForView(ViewHolder holder, ShopedSelectBean shopedSelectBean, int position) {
        TextView tv_select_name = holder.getView(R.id.tv_select_name);
        TextView med_fry = holder.getView(R.id.med_fry);
        EditText et_select_num = holder.getView(R.id.et_select_num);
        TextView tv_select_unit = holder.getView(R.id.tv_select_unit);
        TextView tv_select_price = holder.getView(R.id.tv_select_price);
        RelativeLayout rl_select_delete = holder.getView(R.id.rl_select_delete);

        //这里判断（recycler会把超出屏幕的item删除掉，再次显示时会调用此方法，重新设置）
        if (!TextUtils.isEmpty(shopedSelectBean.getMedFry())) {
            med_fry.setVisibility(View.VISIBLE);
            med_fry.setText(shopedSelectBean.getMedFry());
        } else {
            med_fry.setVisibility(View.GONE);
        }

        tv_select_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTryData(position);
            }
        });

        if (herbsNumMap.containsKey(shopedSelectBean.getMedName())) {
            et_select_num.setText(herbsNumMap.get(shopedSelectBean.getMedName()));
        } else {
            et_select_num.setText("");
        }
        if (herbsPriceMap.containsKey(shopedSelectBean.getMedName())) {
            tv_select_price.setText(herbsPriceMap.get(shopedSelectBean.getMedName()) + "元");
        } else {
            tv_select_price.setText("0.00元");
        }

        String medName = shopedSelectBean.getMedName();
        tv_select_name.setText(medName);
        tv_select_unit.setText(shopedSelectBean.getMedUnit());
        rl_select_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instem.onClickInst(position);
                herbsPriceMap.remove(shopedSelectBean.getMedName());
                herbsNumMap.remove(shopedSelectBean.getMedName());
                insnum.onClinkInsNum(herbsPriceMap);
            }
        });
        et_select_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = et_select_num.getText().toString();
                double medPrice = shopedSelectBean.getMedPrice();//单价
                int num = 0;
                if (!s.isEmpty()) {
                    num = Integer.valueOf(s);//克数
                }
                double v = num * medPrice;
                shopedSelectBean.setMedNumber(num);
                float itemNum = NumberUtils.formatPoint(v);
//                float itemNum = new BigDecimal(v).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();//单个药品总价
                herbsPriceMap.put(shopedSelectBean.getMedName(), itemNum);
                herbsNumMap.put(shopedSelectBean.getMedName(), s);
                tv_select_price.setText(itemNum + "元");
                insnum.onClinkInsNum(herbsPriceMap);
            }
        });
    }

    public void getTryData(int index) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .addHeader("token", userToken)
                .url(ConfigKeys.MEDFRYING)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {//回调的方法执行在子线程。
                    //获取响应体
                    ResponseBody body = response.body();
                    byte[] bytes = body.bytes();
                    //获取到了字符串
                    String result = new String(bytes);

                    Logger.e(result);
                    medFryingBean = Json.parseObj(result, MedFryingBean.class);
                    Logger.e(medFryingBean.toString());

                    Logger.e("position: " + index);


                    ThreadUtils.executeMainThread(() -> {
                        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                        alertDialog.show();
                        Window window = BottomDialogCreator.getDialogWindow(alertDialog, R.layout.dialog_med_type);
                        if (window != null) {
                            ImageView imageView = window.findViewById(R.id.iv_med_type_cancel);
                            MyGridView gridView = window.findViewById(R.id.gv_med_type);
                            TextView tvPopTitle = window.findViewById(R.id.tv_pop_title);
                            tvPopTitle.setText("选择煎法");
                            final MedFryingAdapter medFryingAdapter = new MedFryingAdapter(medFryingBean, getContext());
                            gridView.setAdapter(medFryingAdapter);
                            imageView.setOnClickListener(v -> alertDialog.dismiss());

                            gridView.setOnItemClickListener((parent, view, position, id) -> {
                                alertDialog.dismiss();

                                String name = medFryingBean.getData().get(position);
                                //设置指定下标bean里的数据
                                getItem(index).setMedFry(name);
                                //通知指定下标，加上标记
                                notifyItemChanged(index, "设置煎法");
                            });
                        }
                    });

                }
            }
        });
    }


    public interface insnum {
        void onClinkInsNum(HashMap<String, Float> herbsPriceMap);
    }

    public interface instem {
        void onClickInst(int postion);
    }
}
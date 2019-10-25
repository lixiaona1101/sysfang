package com.jiuhao.jhjk.adapter.MyRecyclerAdapter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.adapter.GridListAdapter.MedFryingAdapter;
import com.jiuhao.jhjk.bean.MedFryingBean;
import com.jiuhao.jhjk.bean.ShopedSelectBean;
import com.jiuhao.jhjk.utils.BottomDialogCreator;
import com.jiuhao.jhjk.utils.NumberUtils;
import com.jiuhao.jhjk.utils.ThreadUtils;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.view.MyGridView;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.jiuhao.jhjk.APP.Config.userToken;

public class SelectMedRecyclerAdapter2 extends BaseQuickAdapter<ShopedSelectBean, BaseViewHolder> {

    private SelectMedRecyclerAdapter2.instem instem;
    private SelectMedRecyclerAdapter2.insnum insnum;
    public HashMap<String, Float> herbsPriceMap = new HashMap<>();//单个药品的价格
    public HashMap<String, String> herbsNumMap = new HashMap<>();//单个药品的数量
    private MedFryingBean medFryingBean;

    public SelectMedRecyclerAdapter2(int layoutResId, @Nullable List<ShopedSelectBean> data, instem instem, insnum insnum) {
        super(layoutResId, data);
        this.instem = instem;
        this.insnum = insnum;
    }


    @Override
    protected void convert(@NonNull BaseViewHolder holder, ShopedSelectBean shopedSelectBean) {

        TextView tv_select_name = holder.getView(R.id.tv_select_name);
        TextView med_fry = holder.getView(R.id.med_fry);
        EditText et_select_num = holder.getView(R.id.et_select_num);
        TextView tv_select_unit = holder.getView(R.id.tv_select_unit);
        TextView tv_select_price = holder.getView(R.id.tv_select_price);
        RelativeLayout rl_select_delete = holder.getView(R.id.rl_select_delete);

        KeyboardUtils.showSoftInput(et_select_num);

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
                getTryData(holder.getAdapterPosition(),shopedSelectBean);
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
                instem.onClickInst(holder.getAdapterPosition());
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
                herbsPriceMap.put(shopedSelectBean.getMedName(), itemNum);
                herbsNumMap.put(shopedSelectBean.getMedName(), s);
                tv_select_price.setText(itemNum + "元");
                insnum.onClinkInsNum(herbsPriceMap);
            }
        });
    }

    public void getTryData(int index, ShopedSelectBean shopedSelectBean) {
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

                    medFryingBean = Json.parseObj(result, MedFryingBean.class);

                    ThreadUtils.executeMainThread(() -> {
                        final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                        alertDialog.show();
                        Window window = BottomDialogCreator.getDialogWindow(alertDialog, R.layout.dialog_med_type);
                        if (window != null) {
                            ImageView imageView = window.findViewById(R.id.iv_med_type_cancel);
                            MyGridView gridView = window.findViewById(R.id.gv_med_type);
                            TextView tvPopTitle = window.findViewById(R.id.tv_pop_title);
                            tvPopTitle.setText("选择煎法");
                            final MedFryingAdapter medFryingAdapter = new MedFryingAdapter(medFryingBean, mContext);
                            gridView.setAdapter(medFryingAdapter);
                            imageView.setOnClickListener(v -> alertDialog.dismiss());

                            gridView.setOnItemClickListener((parent, view, position, id) -> {
                                alertDialog.dismiss();

                                String name = medFryingBean.getData().get(position);
                                shopedSelectBean.setMedFry(name);//设置指定bean煎法
                                notifyItemChanged(index);//通知指定下标刷新数据
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

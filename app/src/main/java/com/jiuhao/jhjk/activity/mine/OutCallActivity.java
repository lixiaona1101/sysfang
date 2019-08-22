package com.jiuhao.jhjk.activity.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 出诊时间
 */
public class OutCallActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * 确认
     */
    private TextView tvTitleSure;
    private RelativeLayout rlTitleSure;
    private RelativeLayout rlTitle;
    /**
     * 出诊
     */
    private TextView out11;
    /**
     * 出诊
     */
    private TextView out21;
    /**
     * 出诊
     */
    private TextView out31;
    /**
     * 出诊
     */
    private TextView out41;
    /**
     * 出诊
     */
    private TextView out51;
    /**
     * 出诊
     */
    private TextView out61;
    /**
     * 出诊
     */
    private TextView out71;
    /**
     * 出诊
     */
    private TextView out12;
    /**
     * 出诊
     */
    private TextView out22;
    /**
     * 出诊
     */
    private TextView out32;
    /**
     * 出诊
     */
    private TextView out42;
    /**
     * 出诊
     */
    private TextView out52;
    /**
     * 出诊
     */
    private TextView out62;
    /**
     * 出诊
     */
    private TextView out72;
    /**
     * 出诊
     */
    private TextView out13;
    /**
     * 出诊
     */
    private TextView out23;
    /**
     * 出诊
     */
    private TextView out33;
    /**
     * 出诊
     */
    private TextView out43;
    /**
     * 出诊
     */
    private TextView out53;
    /**
     * 出诊
     */
    private TextView out63;
    /**
     * 出诊
     */
    private TextView out73;

    //单数表示出诊
    //双数表示不出诊，默认不出诊
    //上午
    private int call11 = 0;
    private int call21 = 0;
    private int call31 = 0;
    private int call41 = 0;
    private int call51 = 0;
    private int call61 = 0;
    private int call71 = 0;

    //下午
    private int call12 = 0;
    private int call22 = 0;
    private int call32 = 0;
    private int call42 = 0;
    private int call52 = 0;
    private int call62 = 0;
    private int call72 = 0;

    //晚上
    private int call13 = 0;
    private int call23 = 0;
    private int call33 = 0;
    private int call43 = 0;
    private int call53 = 0;
    private int call63 = 0;
    private int call73 = 0;

    //a表示周几 b表示上午下午晚上
    //周1集合
    private List<TextView> Mondaylist = new ArrayList();
    //周2集合
    private List<TextView> Tuesdaylist = new ArrayList();
    //周3集合
    private List<TextView> wednesdaylist = new ArrayList();
    //周4集合
    private List<TextView> thursdaylist = new ArrayList();
    //周5集合
    private List<TextView> fridaylist = new ArrayList();
    //周6集合
    private List<TextView> satudaylist = new ArrayList();
    //周7集合
    private List<TextView> weekday = new ArrayList();

    private List[] lists = new List[7];

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_out_call);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        out11 = (TextView) findViewById(R.id.out11);
        out21 = (TextView) findViewById(R.id.out21);
        out31 = (TextView) findViewById(R.id.out31);
        out41 = (TextView) findViewById(R.id.out41);
        out51 = (TextView) findViewById(R.id.out51);
        out61 = (TextView) findViewById(R.id.out61);
        out71 = (TextView) findViewById(R.id.out71);
        out12 = (TextView) findViewById(R.id.out12);
        out22 = (TextView) findViewById(R.id.out22);
        out32 = (TextView) findViewById(R.id.out32);
        out42 = (TextView) findViewById(R.id.out42);
        out52 = (TextView) findViewById(R.id.out52);
        out62 = (TextView) findViewById(R.id.out62);
        out72 = (TextView) findViewById(R.id.out72);
        out13 = (TextView) findViewById(R.id.out13);
        out23 = (TextView) findViewById(R.id.out23);
        out33 = (TextView) findViewById(R.id.out33);
        out43 = (TextView) findViewById(R.id.out43);
        out53 = (TextView) findViewById(R.id.out53);
        out63 = (TextView) findViewById(R.id.out63);
        out73 = (TextView) findViewById(R.id.out73);
        tvTitle.setText("出诊时间");
        tvTitleSure.setVisibility(View.VISIBLE);

        out11.setOnClickListener(this);
        out21.setOnClickListener(this);
        out31.setOnClickListener(this);
        out41.setOnClickListener(this);
        out51.setOnClickListener(this);
        out61.setOnClickListener(this);
        out71.setOnClickListener(this);

        out12.setOnClickListener(this);
        out22.setOnClickListener(this);
        out32.setOnClickListener(this);
        out42.setOnClickListener(this);
        out52.setOnClickListener(this);
        out62.setOnClickListener(this);
        out72.setOnClickListener(this);

        out13.setOnClickListener(this);
        out23.setOnClickListener(this);
        out33.setOnClickListener(this);
        out43.setOnClickListener(this);
        out53.setOnClickListener(this);
        out63.setOnClickListener(this);
        out73.setOnClickListener(this);

        Mondaylist.add(out11);
        Mondaylist.add(out12);
        Mondaylist.add(out13);

        Tuesdaylist.add(out21);
        Tuesdaylist.add(out22);
        Tuesdaylist.add(out23);

        wednesdaylist.add(out31);
        wednesdaylist.add(out32);
        wednesdaylist.add(out33);

        thursdaylist.add(out41);
        thursdaylist.add(out42);
        thursdaylist.add(out43);

        fridaylist.add(out51);
        fridaylist.add(out52);
        fridaylist.add(out53);

        satudaylist.add(out61);
        satudaylist.add(out62);
        satudaylist.add(out63);

        weekday.add(out71);
        weekday.add(out72);
        weekday.add(out73);

        lists[0] = Mondaylist;
        lists[1] = Tuesdaylist;
        lists[2] = wednesdaylist;
        lists[3] = thursdaylist;
        lists[4] = fridaylist;
        lists[5] = satudaylist;
        lists[6] = weekday;
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //上午
            case R.id.out11:
                call11++;
                show(call11, out11, 1, 1);
                break;
            case R.id.out21:
                call21++;
                show(call21, out21, 2, 1);
                break;
            case R.id.out31:
                call31++;
                show(call31, out31, 3, 1);
                break;
            case R.id.out41:
                call41++;
                show(call41, out41, 4, 1);
                break;
            case R.id.out51:
                call51++;
                show(call51, out51, 5, 1);
                break;
            case R.id.out61:
                call61++;
                show(call61, out61, 6, 1);
                break;
            case R.id.out71:
                call71++;
                show(call71, out71, 7, 1);
                break;

            //下午
            case R.id.out12:
                call12++;
                show(call12, out12, 1, 2);
                break;
            case R.id.out22:
                call22++;
                show(call22, out22, 2, 2);
                break;
            case R.id.out32:
                call32++;
                show(call32, out32, 3, 2);
                break;
            case R.id.out42:
                call42++;
                show(call42, out42, 4, 2);
                break;
            case R.id.out52:
                call52++;
                show(call52, out52, 5, 2);
                break;
            case R.id.out62:
                call62++;
                show(call62, out62, 6, 2);
                break;
            case R.id.out72:
                call72++;
                show(call72, out72, 7, 2);
                break;

            //晚上
            case R.id.out13:
                call13++;
                show(call13, out13, 1, 3);
                break;
            case R.id.out23:
                call23++;
                show(call23, out23, 2, 3);
                break;
            case R.id.out33:
                call33++;
                show(call33, out33, 3, 3);
                break;
            case R.id.out43:
                call43++;
                show(call43, out43, 4, 3);
                break;
            case R.id.out53:
                call53++;
                show(call53, out53, 5, 3);
                break;
            case R.id.out63:
                call63++;
                show(call63, out63, 6, 3);
                break;
            case R.id.out73:
                call73++;
                show(call73, out73, 7, 3);
                break;
            //确定
            case R.id.tv_title_sure:
                call();
                break;
        }
    }

    public void show(int call, TextView out, int a, int b) {
        String s = a + "." + b + ",";

        if (call % 2 == 0) {//不出诊
            if (stringBuffer.indexOf(s) != -1) {
                int i = stringBuffer.indexOf(s);
                stringBuffer.delete(i, i + 4);
            }
            out.setTextColor(getResources().getColor(R.color.white));
        } else {//出诊
            stringBuffer.append(s);
            out.setTextColor(getResources().getColor(R.color.orange));
        }
        Logger.e(stringBuffer.toString());
    }


    //提交出诊时间
    public void call() {
        String clinic = stringBuffer.delete((stringBuffer.length() - 1), stringBuffer.length()).toString();

    }

    //初始化出诊情况 调取接口字段分割后调用该方法
    public void sda(int a, int b) {
        int aa = a - 1;
        TextView textView = (TextView) lists[aa].get(b);
        textView.setTextColor(getResources().getColor(R.color.orange));
    }

}
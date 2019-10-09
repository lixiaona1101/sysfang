package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.DcMessageBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 群发消息
 */
public class MassTextingOneActivity extends BaseActivity implements View.OnClickListener {


    private ImageView ivBack;
    /**
     * 历史记录
     */
    private TextView historyTitle;
    /**
     * 消息类型
     */
    private TextView messageType;
    /**
     * 医嘱提醒
     */
    private Spinner spinner;
    /**
     * 8月24日
     */
    private TextView dataText;
    /**
     * 请编辑要发送的内容
     */
    private EditText contenText;
    /**
     * 收件人：
     */
    private TextView sjr;
    private TagFlowLayout flowLayout;
    /**
     * 还没有收件人，先去添加吧
     */
    private TextView personText;
    private ImageView addPerson;
    /**
     * 发布消息
     */
    private Button issueButton;

    private LinearLayout contentLin;
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
    private LinearLayout timeLin;

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

    //周1集合
    private List<Integer> iMondaylist = new ArrayList<>();
    //周2集合
    private List<Integer> iTuesdaylist = new ArrayList<>();
    //周3集合
    private List<Integer> iwednesdaylist = new ArrayList<>();
    //周4集合
    private List<Integer> ithursdaylist = new ArrayList<>();
    //周5集合
    private List<Integer> ifridaylist = new ArrayList<>();
    //周6集合
    private List<Integer> isatudaylist = new ArrayList<>();
    //周7集合
    private List<Integer> iweekday = new ArrayList<>();

    private List<Integer>[] ilists = new List[7];


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


    //下拉菜单
    private List<DcMessageBean> dcMessageBeans;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private Intent intentRec;
    private String clinicGroupId = "";
    private String clinicId = "";
    //选中内容
    private String item;

    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (dcMessageBeans.size() != 0 && dcMessageBeans != null) {
                        for (int i = 0; i < dcMessageBeans.size(); i++) {
                            list.add(dcMessageBeans.get(i).getName());
                        }
                        //第二步：为下拉列表定义一个适配器
                        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                        //第三步：设置下拉列表下拉时的菜单样式
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //第四步：将适配器添加到下拉列表上
                        spinner.setAdapter(adapter);
                        //第五步：添加监听器，为下拉列表设置事件的响应
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                //将 spinnertext 显示
                                adapterView.setVisibility(View.VISIBLE);
                                //选中内容
                                item = adapter.getItem(i);
                                if (item.equals("坐诊时间")) {
                                    timeLin.setVisibility(View.VISIBLE);
                                    contentLin.setVisibility(View.GONE);
                                } else {
                                    timeLin.setVisibility(View.GONE);
                                    contentLin.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                adapterView.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_mass_texting_one);
        translucentStatusBar(true);
    }

    @Override
    protected void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        historyTitle = (TextView) findViewById(R.id.history_title);
        messageType = (TextView) findViewById(R.id.message_type);
        spinner = (Spinner) findViewById(R.id.spinner);
        dataText = (TextView) findViewById(R.id.data_text);
        contenText = (EditText) findViewById(R.id.conten_text);
        sjr = (TextView) findViewById(R.id.sjr);
        flowLayout = (TagFlowLayout) findViewById(R.id.flowLayout);
        personText = (TextView) findViewById(R.id.person_text);
        addPerson = (ImageView) findViewById(R.id.add_person);
        issueButton = (Button) findViewById(R.id.issue_button);
        issueButton.setOnClickListener(this);

        contentLin = (LinearLayout) findViewById(R.id.content_lin);
        timeLin = (LinearLayout) findViewById(R.id.time_lin);

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

        iMondaylist.add(0);
        iMondaylist.add(0);
        iMondaylist.add(0);

        iTuesdaylist.add(0);
        iTuesdaylist.add(0);
        iTuesdaylist.add(0);

        iwednesdaylist.add(0);
        iwednesdaylist.add(0);
        iwednesdaylist.add(0);

        ithursdaylist.add(0);
        ithursdaylist.add(0);
        ithursdaylist.add(0);

        ifridaylist.add(0);
        ifridaylist.add(0);
        ifridaylist.add(0);

        isatudaylist.add(0);
        isatudaylist.add(0);
        isatudaylist.add(0);

        iweekday.add(0);
        iweekday.add(0);
        iweekday.add(0);

        ilists[0] = iMondaylist;
        ilists[1] = iTuesdaylist;
        ilists[2] = iwednesdaylist;
        ilists[3] = ithursdaylist;
        ilists[4] = ifridaylist;
        ilists[5] = isatudaylist;
        ilists[6] = iweekday;
    }

    @Override
    protected void obtainData() {
        gettData();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        dataText.setText(simpleDateFormat.format(date));
    }

    //下拉菜单
    public void gettData() {
        OkHttpUtils.get(ConfigKeys.DCMESSAGE, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {

                Logger.e(response);
                dcMessageBeans = Json.parseArr(response, DcMessageBean.class);
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //历史记录
        historyTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });
        //收件人
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentRec = new Intent(getContext(), RecipientsActivity.class);
                intentRec.putExtra("title", "收件人");
                startActivityForResult(intentRec, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == 234) {

            clinicGroupId = data.getStringExtra("clinicGroupId");
            String clinicGroupName = data.getStringExtra("clinicGroupName");
            clinicId = data.getStringExtra("clinicId");
            String clinicName = data.getStringExtra("clinicName");

            Logger.e(clinicGroupId);
            Logger.e(clinicGroupName);
            Logger.e(clinicId);
            Logger.e(clinicName);

            if (!clinicGroupName.isEmpty() || !clinicName.isEmpty()) {
                personText.setVisibility(View.GONE);
                flowLayout.setVisibility(View.VISIBLE);

                String[] split = clinicGroupName.split(",");
                String[] split1 = clinicName.split(",");
                int length = split.length + split1.length;

                String[] stringList = new String[length];
                for (int i = 0; i < split.length; i++) {
                    stringList[i] = split[i];
                }
                for (int i = split.length; i < length; i++) {
                    stringList[i] = split1[i - split.length];
                }

                for (int i = 0; i < stringList.length; i++) {
                    Logger.e(stringList[i]);
                }

                TagAdapter<String> tagAdapter = new TagAdapter<String>(stringList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {

                        TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_recipients,
                                flowLayout, false);
                        tv.setText(s);
                        return tv;
                    }
                };
                flowLayout.setAdapter(tagAdapter);
            } else {
                personText.setVisibility(View.VISIBLE);
                flowLayout.setVisibility(View.GONE);
            }

        }
    }

    public void issue() {

        //消息类型
        int messagesType = 0;
        for (int i = 0; i < dcMessageBeans.size(); i++) {
            if (item.equals(dcMessageBeans.get(i).getName())) {
                messagesType = dcMessageBeans.get(i).getId();
            }
        }
        //消息内容 content
        String content="";
        if (messagesType == 2) {
            if (stringBuffer.length() != 0) {
                content = stringBuffer.delete((stringBuffer.length() - 1), stringBuffer.length()).toString();
            } else {
                ToastUtils.show("请选择就诊时间");
            }
        } else {
            content = contenText.getText().toString();
        }

        if (!content.isEmpty()) {
            if (!clinicGroupId.isEmpty() || !clinicId.isEmpty()) {
                postData(messagesType, content);
            } else {
                ToastUtils.show("请先选择收件人");
            }
        } else {
            ToastUtils.show("请编辑要发送的内容！");
        }
    }

    public void postData(int messagesType, String content) {
        /**
         *  "messagesType": 1,              消息类型ID
         "content": "[坐诊时间]周一上午"， 消息内容
         "receiveGids": "11,12",         分组id
         "receiveIds": "100000048"       患者ID，用逗号隔开
         */

        Logger.e(messagesType + "");
        Logger.e(content + "");
        Logger.e(clinicGroupId + "");
        Logger.e(clinicId + "");

        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("messagesType", messagesType);
        linkedHashMap.put("content", content);
        linkedHashMap.put("receiveGids", clinicGroupId);
        linkedHashMap.put("receiveIds", clinicId);
        OkHttpUtils.postJson(ConfigKeys.DCMESSAGE, linkedHashMap, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                ToastUtils.show("发布成功！");
                finish();
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.issue_button:
                issue();
                break;
            //上午
            case R.id.out11:
                call11 = ilists[0].get(0) + 1;
                ilists[0].set(0, call11);
                show(call11, out11, 1, 1);
                break;
            case R.id.out21:
                call21 = ilists[1].get(0) + 1;
                ilists[1].set(0, call21);
                show(call21, out21, 2, 1);
                break;
            case R.id.out31:
                call31 = ilists[2].get(0) + 1;
                ilists[2].set(0, call31);
                show(call31, out31, 3, 1);
                break;
            case R.id.out41:
                call41 = ilists[3].get(0) + 1;
                ilists[3].set(0, call41);
                show(call41, out41, 4, 1);
                break;
            case R.id.out51:
                call51 = ilists[4].get(0) + 1;
                ilists[4].set(0, call51);
                show(call51, out51, 5, 1);
                break;
            case R.id.out61:
                call61 = ilists[5].get(0) + 1;
                ilists[5].set(0, call61);
                show(call61, out61, 6, 1);
                break;
            case R.id.out71:
                call71 = ilists[6].get(0) + 1;
                ilists[6].set(0, call71);
                show(call71, out71, 7, 1);
                break;

            //下午
            case R.id.out12:
                call12 = ilists[0].get(1) + 1;
                ilists[0].set(1, call12);
                show(call12, out12, 1, 2);
                break;
            case R.id.out22:
                call22 = ilists[1].get(1) + 1;
                ilists[1].set(1, call22);
                show(call22, out22, 2, 2);
                break;
            case R.id.out32:
                call32 = ilists[2].get(1) + 1;
                ilists[2].set(1, call32);
                show(call32, out32, 3, 2);
                break;
            case R.id.out42:
                call42 = ilists[3].get(1) + 1;
                ilists[3].set(1, call42);
                show(call42, out42, 4, 2);
                break;
            case R.id.out52:
                call52 = ilists[4].get(1) + 1;
                ilists[4].set(1, call52);
                show(call52, out52, 5, 2);
                break;
            case R.id.out62:
                call62 = ilists[5].get(1) + 1;
                ilists[5].set(1, call62);
                show(call62, out62, 6, 2);
                break;
            case R.id.out72:
                call72 = ilists[6].get(1) + 1;
                ilists[6].set(1, call72);
                show(call72, out72, 7, 2);
                break;

            //晚上
            case R.id.out13:
                call13 = ilists[0].get(2) + 1;
                ilists[0].set(2, call13);
                show(call13, out13, 1, 3);
                break;
            case R.id.out23:
                call23 = ilists[1].get(2) + 1;
                ilists[1].set(2, call23);
                show(call23, out23, 2, 3);
                break;
            case R.id.out33:
                call33 = ilists[2].get(2) + 1;
                ilists[2].set(2, call33);
                show(call33, out33, 3, 3);
                break;
            case R.id.out43:
                call43 = ilists[3].get(2) + 1;
                ilists[3].set(2, call43);
                show(call43, out43, 4, 3);
                break;
            case R.id.out53:
                call53 = ilists[4].get(2) + 1;
                ilists[4].set(2, call53);
                show(call53, out53, 5, 3);
                break;
            case R.id.out63:
                call63 = ilists[5].get(2) + 1;
                ilists[5].set(2, call63);
                show(call63, out63, 6, 3);
                break;
            case R.id.out73:
                call73 = ilists[6].get(2) + 1;
                ilists[6].set(2, call73);
                show(call73, out73, 7, 3);
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
            if (stringBuffer.indexOf(s) == -1) {
                stringBuffer.append(s);
            }
            out.setTextColor(getResources().getColor(R.color.orange));
        }
        Logger.e(stringBuffer.toString());
    }
}

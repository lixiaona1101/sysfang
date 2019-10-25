package com.jiuhao.jhjk.activity.patient;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.adapter.MyRecyclerAdapter.HistoryRcyclerAdapter;
import com.jiuhao.jhjk.bean.CustomerBean;
import com.jiuhao.jhjk.bean.IndexBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.glide.GlideUtil;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 患者详情
 */
public class PersonDetailsActivity extends BaseActivity implements View.OnClickListener {


    private ImageView ivBack;
    /**
     * 特别关心
     */
    private LinearLayout historyTitle;
    private ImageView personHead;
    /**
     * 姓名
     */
    private TextView personName;
    private ImageView personSex;
    /**
     * 42岁
     */
    private TextView personAge;
    /**
     * 浙江省杭州市
     */
    private TextView userAddress;
    /**
     * 暂无
     */
    private TextView personGroupNo;
    /**
     * 默认分组
     */
    private TextView personGroup;
    /**
     * 历史病例 (3)
     */
    private TextView historyNumber;
    private RecyclerView historyRecycler;
    private String groupName;
    private IndexBean indexBean;
    /**
     * 联系患者
     */
    private Button relation;
    private ImageView historyLike;
    private CustomerBean customerBean;
    private List<CustomerBean.DocCasesBean> docCases;
    private Intent intent;
    private String nickName;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    docCases = customerBean.getDocCases();
                    if (docCases != null && docCases.size() != 0) {
                        HistoryRcyclerAdapter historyRcyclerAdapter = new HistoryRcyclerAdapter(getContext(), docCases);
                        historyRecycler.setAdapter(historyRcyclerAdapter);
                        historyNumber.setText("历史病例(" + docCases.size() + ")");
                    }
                    break;
            }
            return false;
        }
    });

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_person_details);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        personHead = (ImageView) findViewById(R.id.person_head);
        personName = (TextView) findViewById(R.id.person_name);
        personSex = (ImageView) findViewById(R.id.person_sex);
        personAge = (TextView) findViewById(R.id.person_age);
        userAddress = (TextView) findViewById(R.id.user_address);
        personGroupNo = (TextView) findViewById(R.id.person_group_no);
        personGroup = (TextView) findViewById(R.id.person_group);
        historyNumber = (TextView) findViewById(R.id.history_number);
        historyRecycler = (RecyclerView) findViewById(R.id.history_recycler);
        relation = (Button) findViewById(R.id.relation);
        historyLike = (ImageView) findViewById(R.id.history_like);
        historyTitle = (LinearLayout) findViewById(R.id.history_title);
        historyRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ivBack.setOnClickListener(this);
        relation.setOnClickListener(this);
        historyTitle.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {
        Intent intent = getIntent();
        groupName = intent.getStringExtra("GroupName");//分组名称
        indexBean = (IndexBean) intent.getSerializableExtra("indexBean");//用户详情bean
        getData();
        personGroup.setText(groupName);
        nickName = indexBean.getUserName();
        personName.setText(nickName);
        String address = indexBean.getAddress();
        userAddress.setText(address);
        String avatar = indexBean.getAvatar();
        GlideUtil.loadCircle(getContext(), avatar, personHead);
        String aged = indexBean.getAged();
        if (aged.contains("不详")) {
            personAge.setText(aged);
        } else {
            personAge.setText(aged + "岁");
        }
        int sex = indexBean.getSex();
        if (sex == 0) {

        } else if (sex == 1) {
            personSex.setImageResource(R.mipmap.man);
        } else if (sex == 2) {
            personSex.setImageResource(R.mipmap.women);
        }
        //关注状态 0关注 1取消关注
        int starState = indexBean.getStarState();
        Logger.e(starState + "");
        if (starState == 0) {
            historyLike.setImageResource(R.mipmap.focus);
        } else if (starState == 1) {
            historyLike.setImageResource(R.mipmap.focus1);
        }
    }

    @Override
    protected void initEvent() {
        //更改患者分组
        personGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), GroupDetailActivity.class);
                intent.putExtra("groupName", groupName);//分组名称
                intent.putExtra("id", indexBean.getId());//患者id
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.history_title:
                setLike();
                break;
            case R.id.relation://跟患者聊天对话
                Intent intent1 = new Intent(getContext(), DialogueActivity.class);
                intent1.putExtra("nickName",nickName);//患者姓名
                intent1.putExtra("id", indexBean.getId());//患者id
                intent1.putExtra("img", indexBean.getAvatar());//患者头像
                startActivity(intent1);
                break;
        }
    }

    public void setLike() {
        int starState = indexBean.getStarState();
        Logger.e(starState + "");
        if (starState == 0) {
            indexBean.setStarState(1);
            historyLike.setImageResource(R.mipmap.focus1);
        } else if (starState == 1) {
            indexBean.setStarState(0);
            historyLike.setImageResource(R.mipmap.focus);
        }
        putLike();
    }

    //取消关注
    public void putLike() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("id", indexBean.getId());
        OkHttpUtils.putJson(ConfigKeys.WATCH, linkedHashMap, new OkHttpUtils.ResultCallback() {
            @Override
            public void onSuccess(int code, String response) {
                setResult(222);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void getData() {
        String url = ConfigKeys.CUSTOMER + "?id=" + indexBean.getId();
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                customerBean = Json.parseObj(response, CustomerBean.class);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 102) {
            String gname = intent.getStringExtra("gname");
            personGroup.setText(gname);
        }
    }
}

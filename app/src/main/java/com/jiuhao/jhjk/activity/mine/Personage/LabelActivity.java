package com.jiuhao.jhjk.activity.mine.Personage;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;
import com.jiuhao.jhjk.bean.DocLadelBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 专业标签
 */
public class LabelActivity extends BaseActivity {

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
     * 中医科
     */
    private TextView subject;
    private TagFlowLayout tagflowlayout;

    private Intent intent;
    private String departmentName;
    private int departmentId;
    private List<DocLadelBean> docLadelBeans;
    private String[] stringList;
    private String labelstr;


    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_label);
        translucentStatusBar(true);

    }

    public void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        subject = (TextView) findViewById(R.id.subject);
        tagflowlayout = (TagFlowLayout) findViewById(R.id.tagflowlayout);
        tvTitle.setText("专业标签");
        rlTitleSure.setVisibility(View.VISIBLE);
    }

    @Override
    protected void obtainData() {

        intent = getIntent();
        departmentName = intent.getStringExtra("departmentName");//科室ming
        departmentId = intent.getIntExtra("departmentId", 1);//科室id
        labelstr = intent.getStringExtra("labelstr");//专业标签
        Logger.e(labelstr);
        subject.setText(departmentName);
        getData();
    }

    public void getData() {

        String url = ConfigKeys.DOCLABEL + "?departmentId=" + departmentId;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                docLadelBeans = Json.parseArr(response, DocLadelBean.class);

                stringList = new String[docLadelBeans.size()];
                for (int i = 0; i < docLadelBeans.size(); i++) {
                    stringList[i] = docLadelBeans.get(i).getSpecialty();
                }

                TagAdapter<String> tagAdapter = new TagAdapter<String>(stringList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {

                        TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tv_label_flowlayout,
                                tagflowlayout, false);
                        tv.setText(s);
                        return tv;
                    }
                };

                tagflowlayout.setAdapter(tagAdapter);

            }

            @Override
            public void onFailure(int code, Exception e) {
                ToastUtils.show(e.getMessage());
            }
        });
    }

    @Override
    protected void initEvent() {

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Integer> ints = new ArrayList<Integer>();
        //展示哪些标签处于选中状态,这个很重要我们设置标签可点击就是为了把用户选中状态的标签中的数据上传.
        tagflowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Iterator<Integer> iterator = selectPosSet.iterator();
                for (Iterator<Integer> it = iterator; it.hasNext(); ) {
                    int s = it.next();
                    String str = stringList[s];
                    if (!strings.contains(str)) {
                        strings.add(str);
                    }
                    if (!ints.contains(s)) {
                        ints.add(s);
                    }
                }
            }
        });

        //确定
        rlTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putStringArrayListExtra("strings", strings);
                setResult(3002, intent);
                finish();
            }
        });
    }
}

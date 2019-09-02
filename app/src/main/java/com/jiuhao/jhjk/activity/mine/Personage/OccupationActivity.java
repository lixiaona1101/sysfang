package com.jiuhao.jhjk.activity.mine.Personage;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.activity.base.BaseActivity;

/**
 * 职称
 */
public class OccupationActivity extends BaseActivity implements View.OnClickListener{

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
    private ImageView occupation1;
    private ImageView occupation2;
    private ImageView occupation3;
    private ImageView occupation4;
    private String occupation;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_occupation);
        translucentStatusBar(true);

    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleSure = (TextView) findViewById(R.id.tv_title_sure);
        rlTitleSure = (RelativeLayout) findViewById(R.id.rl_title_sure);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
        occupation1 = (ImageView) findViewById(R.id.occupation1);
        occupation2 = (ImageView) findViewById(R.id.occupation2);
        occupation3 = (ImageView) findViewById(R.id.occupation3);
        occupation4 = (ImageView) findViewById(R.id.occupation4);
        tvTitle.setText("职称");
        rlTitleSure.setVisibility(View.VISIBLE);
        occupation1.setOnClickListener(this);
        occupation2.setOnClickListener(this);
        occupation3.setOnClickListener(this);
        occupation4.setOnClickListener(this);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rlTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("occupation",occupation);
                setResult(2002,intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.occupation1:
                occupation="主治医生";
                show(1);
                break;
            case R.id.occupation2:
                occupation="副主任医师";
                show(2);
                break;
            case R.id.occupation3:
                occupation="主任医生";
                show(3);
                break;
            case R.id.occupation4:
                occupation="其他";
                show(4);
                break;
        }
    }
    public void show(int i) {
        occupation1.setImageResource(R.mipmap.select1);
        occupation2.setImageResource(R.mipmap.select1);
        occupation3.setImageResource(R.mipmap.select1);
        occupation4.setImageResource(R.mipmap.select1);
        if (i == 1) {
            occupation1.setImageResource(R.mipmap.select);
        } else if (i == 2) {
            occupation2.setImageResource(R.mipmap.select);
        } else if (i == 3) {
            occupation3.setImageResource(R.mipmap.select);
        } else if (i == 4) {
            occupation4.setImageResource(R.mipmap.select);
        }
    }


}

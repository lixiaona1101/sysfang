package com.jiuhao.jhjk.fragment.welcome;


import android.content.Intent;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.jiuhao.jhjk.R;
import com.jiuhao.jhjk.fragment.base.BaseFragment;
import com.jiuhao.jhjk.activity.welcome.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends BaseFragment {

    /**
     * 进入首页
     */
    private TextView mTvTime;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_four;
    }

    @Override
    protected void initView() {

        mTvTime = (TextView)findViewById(R.id.inSign);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

        mTvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进入登录页面
                startActivity(new Intent(getContext(), RegisterActivity.class));
                getActivity().finish();
            }
        });
    }

}

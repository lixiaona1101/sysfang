package com.jiuhao.jhjk.wxapi;


import com.jiuhao.jhjk.wechat.BaseWXEntryActivity;
import com.jiuhao.jhjk.wechat.JHJKWeChat;

/**
 * Created by Feiyang on 2018/10/11.
 */

public class WXEntryActivity extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
//        JHJKLogger.d("onSignInSuccess",userInfo);
        JHJKWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}

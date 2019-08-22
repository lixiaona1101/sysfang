package com.jiuhao.jhjk.wechat.templates;


import com.jiuhao.jhjk.wechat.BaseWXEntryActivity;
import com.jiuhao.jhjk.wechat.JHJKWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        JHJKWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}

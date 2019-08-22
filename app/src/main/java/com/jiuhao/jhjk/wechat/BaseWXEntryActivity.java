package com.jiuhao.jhjk.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

public class BaseWXEntryActivity extends BaseWXActivity {

    //用户登录成功后回调
    protected void onSignInSuccess(String userInfo) {
        ToastUtils.showLong(userInfo);
    }

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {
    }

    //第三方应用发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {

        final String code = ((SendAuth.Resp) baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl
                .append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(JHJKWeChat.APP_ID)
                .append("&secret=")
                .append(JHJKWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        Logger.e(authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {

        OkHttpUtils.get(authUrl, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                final JSONObject authObj = JSON.parseObject(response);
                final String accessToken = authObj.getString("access_token");
                final String openId = authObj.getString("openid");

                final StringBuilder userInfoUrl = new StringBuilder();
                userInfoUrl
                        .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                        .append(accessToken)
                        .append("&openid=")
                        .append(openId)
                        .append("&lang=")
                        .append("zh_CN");

                Logger.e( userInfoUrl.toString());
                getUserInfo(userInfoUrl.toString());
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
            }
        });
    }
    private void getUserInfo(String userInfoUrl) {
        OkHttpUtils.get(userInfoUrl, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                onSignInSuccess(response);
            }

            @Override
            public void onFailure(int code, Exception e) {

                Logger.e(e.getMessage());
            }
        });
    }
}

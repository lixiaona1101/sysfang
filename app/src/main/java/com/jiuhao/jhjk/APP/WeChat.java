package com.jiuhao.jhjk.APP;

import java.util.HashMap;

/**
 * Created by Administrator on 2019/8/15.
 */

public class WeChat {
    private static final HashMap<Object, Object> JHJK_CONFIGS = new HashMap<>();

    public static void setAppId(String appId) {
        JHJK_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
    }

    public static void setAppSecret(String appSecret) {
        JHJK_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
    }

    public String  getAppId() {
        return getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    }

    public String getAppSecret() {
        return getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    }


    final <T> T getConfiguration(Object key) {
        final Object value = JHJK_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) JHJK_CONFIGS.get(key);
    }
}

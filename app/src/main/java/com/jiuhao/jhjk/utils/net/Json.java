package com.jiuhao.jhjk.utils.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class Json {

    public static <T> List<T> parseArr(String response, Class<T> c) {
        List<T> list = new ArrayList<>();
        JSONArray array = JSON.parseArray(response);
        for (int i = 0; i < array.size(); i++) {
            //集合里的每一个对象
            String jsonObjectStr = array.getString(i);
            T bean = new Gson().fromJson(jsonObjectStr, c);
            list.add(bean);
            Logger.e(bean.toString());
        }
        return list;
    }

    public static <T> T parseObj(String response, Class<T> c) {
        return new Gson().fromJson(response, c);
    }
}

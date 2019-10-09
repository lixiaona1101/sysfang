package com.jiuhao.jhjk.utils;

import com.jiuhao.jhjk.bean.ShopedSelectBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Feiyang on 2018/9/12.
 * <p>
 * /**
 * 十八反十九畏药品检测
 */
public class MedUtil {
    private static Map<String, String[]> medLists;

    private static final String[] meds1 = {"大戟", "甘遂", "海藻", "芫花"};
    private static final String[] meds2 = {"半夏", "瓜蒌", "贝母", "白蔹", "白芨"};
    private static final String[] meds3 = {"人参", "沙参", "玄参", "丹参", "苦参", "细辛", "芍药"};
    private static final String[] meds4 = {"朴硝"};
    private static final String[] meds5 = {"砒霜"};
    private static final String[] meds6 = {"密陀僧"};
    private static final String[] meds7 = {"牵牛"};
    private static final String[] meds8 = {"郁金"};
    private static final String[] meds9 = {"川乌", "草乌"};
    private static final String[] meds10 = {"三棱"};
    private static final String[] meds11 = {"石脂"};
    private static final String[] meds12 = {"五灵脂"};

    static {
        medLists = new HashMap<>();
        medLists.put("甘草", meds1);
        medLists.put("乌头", meds2);
        medLists.put("藜芦", meds3);
        medLists.put("硫磺", meds4);
        medLists.put("水银", meds5);
        medLists.put("狼毒", meds6);
        medLists.put("巴豆", meds7);
        medLists.put("丁香", meds8);
        medLists.put("犀角", meds9);
        medLists.put("牙硝", meds10);
        medLists.put("官桂", meds11);
        medLists.put("人参", meds12);
        medLists.put("水牛角", meds9);

    }


    public static List<Map<String, String>> getTempListMed(List<ShopedSelectBean> medNames) {
        List<Map<String, String>> tempListMed = new ArrayList<>();
        for (ShopedSelectBean dataBean : medNames) {
            for (Map.Entry<String, String[]> entry : medLists.entrySet()) {
                String medName = dataBean.getMedName();
                if (medName.contains(entry.getKey())) {
                    for (ShopedSelectBean medicineDtoListBean2 : medNames) {
                        for (String s : entry.getValue()) {
                            String medName1 = medicineDtoListBean2.getMedName();
                            if (medName1.contains(s)) {
                                Map<String, String> map = new HashMap<>();
                                map.put(medName, medName1);
                                tempListMed.add(map);
                            }
                        }
                    }

                }
            }
        }

        return tempListMed;
    }


}

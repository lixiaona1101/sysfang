package com.jiuhao.jhjk.activity.patient;

import java.util.ArrayList;

/**
 * Created by lxn on 2019/9/6.
 */

public class FathData {
    private String name;//分组名称
    private String docId;//医生id
    private int count;//分组人员统计
    private ArrayList<ChildData> list;// 二级列表数据

    @Override
    public String toString() {
        return "ChildrenData{" +
                "name='" + name + '\'' +
                ", docId='" + docId + '\'' +
                ", count=" + count +
                ", list=" + list +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<ChildData> getList() {
        return list;
    }

    public void setList(ArrayList<ChildData> list) {
        this.list = list;
    }

}

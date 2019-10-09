package com.jiuhao.jhjk.activity.mine.ConsultingFee;

import java.util.ArrayList;

/**
 * Created by lxn on 2019/9/2.
 */

public class FatherData {
    private int id;//分组id
    private String name;//分组名称
    private String docId;//医生id
    private int count;//分组人员统计
    private boolean check;//选中状态
    private int checkCount = 0;
    private ArrayList<ChildrenData> list;// 二级列表数据

    @Override
    public String toString() {
        return "ChildrenData{" +
                "name='" + name + '\'' +
                ", docId='" + docId + '\'' +
                ", count=" + count +
                ", id=" + id +
                ", check=" + check +
                ", list=" + list +
                '}';
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getDocId() {
        return docId;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public int getId() {
        return id;
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

    public ArrayList<ChildrenData> getList() {
        return list;
    }

    public void setList(ArrayList<ChildrenData> list) {
        this.list = list;
    }

}

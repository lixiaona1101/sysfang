package com.jiuhao.jhjk.activity.patient;

/**
 * Created by lxn on 2019/9/6.
 * 患者-分组患者详情
 */

public class ChildData {

    private int id;//患者id
    private String avatar;//头像
    private String userName;//姓名
    private String aged;//年龄
    private String sex;//性别
    private String groupName;//分组名字
    private String customerMsg;//最近问诊信息
    private String customerMsgTime;//问诊时间

    @Override
    public String toString() {
        return "ChildData{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                ", aged='" + aged + '\'' +
                ", sex='" + sex + '\'' +
                ", groupName='" + groupName + '\'' +
                ", customerMsg='" + customerMsg + '\'' +
                ", customerMsgTime=" + customerMsgTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerMsgTime() {
        return customerMsgTime;
    }

    public void setCustomerMsgTime(String customerMsgTime) {
        this.customerMsgTime = customerMsgTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAged() {
        return aged;
    }

    public void setAged(String aged) {
        this.aged = aged;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

}

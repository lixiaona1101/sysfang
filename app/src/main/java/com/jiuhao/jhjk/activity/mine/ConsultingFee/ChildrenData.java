package com.jiuhao.jhjk.activity.mine.ConsultingFee;

/**
 * Created by lxn on 2019/9/2.
 */

public class ChildrenData {

    private String avatar;//头像
    private String userName;//姓名
    private String aged;//年龄
    private String sex;//性别
    private String groupName;//分组名字

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ChildrenData{" +
                "avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                ", aged='" + aged + '\'' +
                ", sex=" + sex +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}

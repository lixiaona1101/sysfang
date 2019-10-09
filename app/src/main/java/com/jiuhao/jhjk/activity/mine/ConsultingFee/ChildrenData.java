package com.jiuhao.jhjk.activity.mine.ConsultingFee;

/**
 * Created by lxn on 2019/9/2.
 * 我的-设置咨询费患者
 */

public class ChildrenData {

    private int id;//患者id
    private String avatar;//头像
    private String userName;//姓名
    private String aged;//年龄
    private String sex;//性别
    private String groupName;//分组名字
    private boolean check;//选中状态

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ChildrenData{" +
                "id='" + id + '\'' +
                "avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                ", aged='" + aged + '\'' +
                ", sex=" + sex +
                ", groupName='" + groupName + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}

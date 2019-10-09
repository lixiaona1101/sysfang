package com.jiuhao.jhjk.bean;

import java.util.List;

public class FindByNameBean {


    /**
     * id : 100001153
     * userId : 3359
     * userName : szj
     * sex : 1
     * aged : 27
     * idNumber : 342224199408071658
     * address : 安徽蚌埠怀远县
     * orin : 0
     * patientChatId : 0
     * nickName : 别闹????
     * <p>
     * avatar : http://jhwj.oss-cn-hangzhou.aliyuncs.com/jhjk/555.png
     * patientId : 1145
     * allergy : 酸辣粉
     * starState : 0
     * docWatch : 0
     * birthday : 1992-01-24 00:00:00
     * createTime : 2019-03-06 09:06:00
     * updateTime : 2019-07-27 01:11:02
     * groupId : 0
     * groupName :
     * customerMsg :
     * customerMsgTime :
     * docCases : []
     * docId : 0
     */

    private int id;
    private int userId;
    private String userName;
    private int sex;
    private String aged;
    private String idNumber;
    private String address;
    private int orin;
    private int patientChatId;
    private String nickName;
    private String avatar;
    private int patientId;
    private String allergy;
    private int starState;
    private int docWatch;
    private String birthday;
    private String createTime;
    private String updateTime;
    private int groupId;
    private String groupName;
    private String customerMsg;
    private String customerMsgTime;
    private int docId;
    private List<?> docCases;

    @Override
    public String toString() {
        return "FindByNameBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", aged='" + aged + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", address='" + address + '\'' +
                ", orin=" + orin +
                ", patientChatId=" + patientChatId +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", patientId=" + patientId +
                ", allergy='" + allergy + '\'' +
                ", starState=" + starState +
                ", docWatch=" + docWatch +
                ", birthday='" + birthday + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", customerMsg='" + customerMsg + '\'' +
                ", customerMsgTime='" + customerMsgTime + '\'' +
                ", docId=" + docId +
                ", docCases=" + docCases +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAged() {
        return aged;
    }

    public void setAged(String aged) {
        this.aged = aged;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrin() {
        return orin;
    }

    public void setOrin(int orin) {
        this.orin = orin;
    }

    public int getPatientChatId() {
        return patientChatId;
    }

    public void setPatientChatId(int patientChatId) {
        this.patientChatId = patientChatId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public int getStarState() {
        return starState;
    }

    public void setStarState(int starState) {
        this.starState = starState;
    }

    public int getDocWatch() {
        return docWatch;
    }

    public void setDocWatch(int docWatch) {
        this.docWatch = docWatch;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public String getCustomerMsgTime() {
        return customerMsgTime;
    }

    public void setCustomerMsgTime(String customerMsgTime) {
        this.customerMsgTime = customerMsgTime;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public List<?> getDocCases() {
        return docCases;
    }

    public void setDocCases(List<?> docCases) {
        this.docCases = docCases;
    }
}

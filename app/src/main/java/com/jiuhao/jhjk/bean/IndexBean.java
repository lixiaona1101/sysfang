package com.jiuhao.jhjk.bean;

import java.util.List;

public class IndexBean {

    /**
     * id : 100000989
     * userId : 3184
     * userName :
     * sex : 0
     * aged : 不详
     * idNumber :
     * address :
     * orin : 0
     * nickName : 兔小姐
     * avatar : http://thirdwx.qlogo.cn/mmopen/2UhfnMxyP4AnObNkPpa8IJj6YqDvbT8bAibllvCI6Y2mKiaicb6TnNpto26Unibibl0g9kbfS0ukahyLP1V2ILgwfVYkc0oI2nSz1/132
     * patientId : 960
     * allergy :
     * starState : 0
     * birthday :
     * createTime : 1564472800000
     * updateTime : 1564472836000
     * groupId : 0
     * groupName :
     * customerMsg : [最近问诊] 暂无
     * docCases : []
     */


    private int id;
    private int userId;
    private String userName;
    private int sex;
    private String aged;
    private String idNumber;
    private String address;
    private int orin;
    private String nickName;
    private String avatar;
    private int patientId;
    private String allergy;
    private int starState;
    private String birthday;
    private long createTime;
    private long updateTime;
    private int groupId;
    private String groupName;
    private String customerMsg;
    private List<?> docCases;

    @Override
    public String toString() {
        return "IndexBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", aged='" + aged + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", address='" + address + '\'' +
                ", orin=" + orin +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", patientId=" + patientId +
                ", allergy='" + allergy + '\'' +
                ", starState=" + starState +
                ", birthday='" + birthday + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", customerMsg='" + customerMsg + '\'' +
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
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

    public List<?> getDocCases() {
        return docCases;
    }

    public void setDocCases(List<?> docCases) {
        this.docCases = docCases;
    }
}


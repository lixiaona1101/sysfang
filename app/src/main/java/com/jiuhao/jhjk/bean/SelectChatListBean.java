package com.jiuhao.jhjk.bean;

public class SelectChatListBean {

    /**
     * id : 19
     * docId : 0
     * patientId : 0
     * notReadNum : 0
     * createTime : 2019-09-06 12:01:34
     * updateTime :
     * content : content 与file 必填一个
     * shielding : 0
     * patientName : suzj1
     * avatar : http://jhwj.oss-cn-hangzhou.aliyuncs.com/jhjk/555.png
     */

    private int id;
    private int docId;
    private int patientId;
    private int notReadNum;
    private String timeStr;
    private String content;
    private int shielding;
    private String patientName;
    private String avatar;

    @Override
    public String toString() {
        return "SelectChatListBean{" +
                "id=" + id +
                ", docId=" + docId +
                ", patientId=" + patientId +
                ", notReadNum=" + notReadNum +
                ", timeStr='" + timeStr + '\'' +
                ", content='" + content + '\'' +
                ", shielding=" + shielding +
                ", patientName='" + patientName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNotReadNum() {
        return notReadNum;
    }

    public void setNotReadNum(int notReadNum) {
        this.notReadNum = notReadNum;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getShielding() {
        return shielding;
    }

    public void setShielding(int shielding) {
        this.shielding = shielding;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

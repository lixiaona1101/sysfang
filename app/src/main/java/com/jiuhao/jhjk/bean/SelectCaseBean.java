package com.jiuhao.jhjk.bean;

import java.util.List;

public class SelectCaseBean {

    /**
     * med : ["矮地茶","炒白扁豆","白鲜皮","麸炒枳壳","白芷","白花蛇舌草","白茅根"]
     * symptom : 票票
     * avatar :
     * name :
     * orderState : 0
     * createTime : 2019-09-17 11:05:27
     * timeStr : 今天 11:05:27
     * caseType : 0
     * caseId : 4182
     * customerState : false
     * imgState : false
     * isImg : 1
     * formulationName : 配方颗粒
     * existsExpress : 0
     */

    private String symptom;
    private String avatar;
    private String name;
    private int orderState;
    private String createTime;
    private String timeStr;
    private int caseType;
    private int caseId;
    private boolean customerState;
    private boolean imgState;
    private int isImg;
    private String formulationName;
    private int existsExpress;
    private List<String> med;


    @Override
    public String toString() {
        return "SelectCaseBean{" +
                "symptom='" + symptom + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", orderState=" + orderState +
                ", createTime='" + createTime + '\'' +
                ", timeStr='" + timeStr + '\'' +
                ", caseType=" + caseType +
                ", caseId=" + caseId +
                ", customerState=" + customerState +
                ", imgState=" + imgState +
                ", isImg=" + isImg +
                ", formulationName='" + formulationName + '\'' +
                ", existsExpress=" + existsExpress +
                ", med=" + med +
                '}';
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getCaseType() {
        return caseType;
    }

    public void setCaseType(int caseType) {
        this.caseType = caseType;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public boolean isCustomerState() {
        return customerState;
    }

    public void setCustomerState(boolean customerState) {
        this.customerState = customerState;
    }

    public boolean isImgState() {
        return imgState;
    }

    public void setImgState(boolean imgState) {
        this.imgState = imgState;
    }

    public int getIsImg() {
        return isImg;
    }

    public void setIsImg(int isImg) {
        this.isImg = isImg;
    }

    public String getFormulationName() {
        return formulationName;
    }

    public void setFormulationName(String formulationName) {
        this.formulationName = formulationName;
    }

    public int getExistsExpress() {
        return existsExpress;
    }

    public void setExistsExpress(int existsExpress) {
        this.existsExpress = existsExpress;
    }

    public List<String> getMed() {
        return med;
    }

    public void setMed(List<String> med) {
        this.med = med;
    }
}

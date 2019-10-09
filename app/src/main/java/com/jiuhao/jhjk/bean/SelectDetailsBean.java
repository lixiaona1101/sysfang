package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

public class SelectDetailsBean implements Serializable{

    /**
     * id : 4087
     * symptom : 感冒
     * caseImg :
     * formulationId : 1
     * formulationName : 配方颗粒
     * docId : 1092
     * customerId : 0
     * patientId : 0
     * plural : 7
     * isImg : 1
     * imgUrl :
     * medMethod : 水送服
     * freq : 1tian1次
     * secrecy : 0
     * remark :
     * discountedPrice : 0
     * code : http://weixin.qq.com/q/02qZ1Vw3Pccek10000w07k
     * isFamous : 0
     * efficacy :
     * indication :
     * specialId : 0
     * med : ["﻿白芍: 1袋 打碎"]
     * casePrice : 14.28
     * medPrice : 14.28
     * orderState : 0
     * workPrice : 0
     * expressPrice :
     * generationPrice :
     * feePrice : 0
     * avatar :
     * name :
     * createTime : 2019-09-03 10:11:28
     * updateTime : 2019-09-03 10:11:28
     * customerState : false
     */

    private int id;
    private String symptom;
    private String caseImg;
    private int formulationId;
    private String formulationName;
    private int docId;
    private int customerId;
    private int patientId;
    private int plural;
    private int isImg;
    private String imgUrl;
    private String medMethod;
    private String freq;
    private int secrecy;
    private String remark;
    private int fees;
    private double discountedPrice;
    private String code;
    private int isFamous;
    private String efficacy;
    private String indication;
    private int specialId;
    private double casePrice;
    private double medPrice;
    private int orderState;
    private int workPrice;
    private String expressPrice;
    private String generationPrice;
    private int feePrice;
    private String avatar;
    private String name;
    private String createTime;
    private String updateTime;
    private boolean customerState;
    private List<String> med;


    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getCaseImg() {
        return caseImg;
    }

    public void setCaseImg(String caseImg) {
        this.caseImg = caseImg;
    }

    public int getFormulationId() {
        return formulationId;
    }

    public void setFormulationId(int formulationId) {
        this.formulationId = formulationId;
    }

    public String getFormulationName() {
        return formulationName;
    }

    public void setFormulationName(String formulationName) {
        this.formulationName = formulationName;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getPlural() {
        return plural;
    }

    public void setPlural(int plural) {
        this.plural = plural;
    }

    public int getIsImg() {
        return isImg;
    }

    public void setIsImg(int isImg) {
        this.isImg = isImg;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMedMethod() {
        return medMethod;
    }

    public void setMedMethod(String medMethod) {
        this.medMethod = medMethod;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public int getSecrecy() {
        return secrecy;
    }

    public void setSecrecy(int secrecy) {
        this.secrecy = secrecy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIsFamous() {
        return isFamous;
    }

    public void setIsFamous(int isFamous) {
        this.isFamous = isFamous;
    }

    public String getEfficacy() {
        return efficacy;
    }

    public void setEfficacy(String efficacy) {
        this.efficacy = efficacy;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public double getCasePrice() {
        return casePrice;
    }

    public void setCasePrice(double casePrice) {
        this.casePrice = casePrice;
    }

    public double getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(double medPrice) {
        this.medPrice = medPrice;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(int workPrice) {
        this.workPrice = workPrice;
    }

    public String getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(String expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getGenerationPrice() {
        return generationPrice;
    }

    public void setGenerationPrice(String generationPrice) {
        this.generationPrice = generationPrice;
    }

    public int getFeePrice() {
        return feePrice;
    }

    public void setFeePrice(int feePrice) {
        this.feePrice = feePrice;
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

    public boolean isCustomerState() {
        return customerState;
    }

    public void setCustomerState(boolean customerState) {
        this.customerState = customerState;
    }

    public List<String> getMed() {
        return med;
    }

    public void setMed(List<String> med) {
        this.med = med;
    }

    @Override
    public String toString() {
        return "SelectDetailsBean{" +
                "id=" + id +
                ", symptom='" + symptom + '\'' +
                ", caseImg='" + caseImg + '\'' +
                ", formulationId=" + formulationId +
                ", formulationName='" + formulationName + '\'' +
                ", docId=" + docId +
                ", customerId=" + customerId +
                ", patientId=" + patientId +
                ", plural=" + plural +
                ", isImg=" + isImg +
                ", imgUrl='" + imgUrl + '\'' +
                ", medMethod='" + medMethod + '\'' +
                ", freq='" + freq + '\'' +
                ", secrecy=" + secrecy +
                ", remark='" + remark + '\'' +
                ", fees='" + fees + '\'' +
                ", discountedPrice=" + discountedPrice +
                ", code='" + code + '\'' +
                ", isFamous=" + isFamous +
                ", efficacy='" + efficacy + '\'' +
                ", indication='" + indication + '\'' +
                ", specialId=" + specialId +
                ", casePrice=" + casePrice +
                ", medPrice=" + medPrice +
                ", orderState=" + orderState +
                ", workPrice=" + workPrice +
                ", expressPrice='" + expressPrice + '\'' +
                ", generationPrice='" + generationPrice + '\'' +
                ", feePrice=" + feePrice +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", customerState=" + customerState +
                ", med=" + med +
                '}';
    }
}

package com.jiuhao.jhjk.bean;

import java.io.Serializable;

public class FamousDoctorBean implements Serializable {

    /**
     * id : 3
     * symptom : 胃炎
     * efficacy : 消炎
     * indication : 消炎
     * plural : 1
     * formulationId : 1
     * remark : 张江
     * docName : 张江
     * useNum : 22
     * departmentId : 1
     * title : 张主任
     * url : http://www.baidu.com
     * picUrl : https://c-ssl.duitang.com/uploads/item/201409/08/20140908154922_Gw3h5.thumb.1800_0.jpeg
     * price : 199
     * createTime : 2019-08-27 19:37:30
     * updateTime :
     * secrecy : 0
     * isAttention : 1
     * keyword :
     * docId : 0
     * formulationName : 配方颗粒
     */

    private int id;
    private String symptom;
    private String efficacy;
    private String indication;
    private int plural;
    private String formulationId;
    private String remark;
    private String docName;
    private int useNum;
    private int departmentId;
    private String title;
    private String url;
    private String picUrl;
    private int price;
    private String createTime;
    private String updateTime;
    private int secrecy;
    private int isAttention;
    private String keyword;
    private int docId;
    private String formulationName;

    @Override
    public String toString() {
        return "FamousDoctorBean{" +
                "id=" + id +
                ", symptom='" + symptom + '\'' +
                ", efficacy='" + efficacy + '\'' +
                ", indication='" + indication + '\'' +
                ", plural=" + plural +
                ", formulationId='" + formulationId + '\'' +
                ", remark='" + remark + '\'' +
                ", docName='" + docName + '\'' +
                ", useNum=" + useNum +
                ", departmentId=" + departmentId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", price=" + price +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", secrecy=" + secrecy +
                ", isAttention=" + isAttention +
                ", keyword='" + keyword + '\'' +
                ", docId=" + docId +
                ", formulationName='" + formulationName + '\'' +
                '}';
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

    public int getPlural() {
        return plural;
    }

    public void setPlural(int plural) {
        this.plural = plural;
    }

    public String getFormulationId() {
        return formulationId;
    }

    public void setFormulationId(String formulationId) {
        this.formulationId = formulationId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int getSecrecy() {
        return secrecy;
    }

    public void setSecrecy(int secrecy) {
        this.secrecy = secrecy;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getFormulationName() {
        return formulationName;
    }

    public void setFormulationName(String formulationName) {
        this.formulationName = formulationName;
    }
}

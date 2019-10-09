package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

public class SelectByIdBean implements Serializable {

    /**
     * med : [{"medId":102171,"medName":"﻿白芍","medType":0,"medSpec":"","medUnit":"0","medPrice":2.04,"medHeat":0,"pinYin":"","medNumber":1,"medFrying":"打碎","isEnough":1}]
     * symptom : 感冒
     * createTime : 2019-09-03 10:11:28
     * caseType : 2
     * caseId : 4087
     * isImg : 1
     * formulationName : 配方颗粒
     * formulationId : 1
     * workingType : 0
     * remark :
     * id : 4087
     * caseImg :
     * docId : 1092
     * customerId : 0
     * patientId : 0
     * plural : 7
     * imgUrl :
     * medMethod : 水送服
     * freq : 1tian1次
     * secrecy : 0
     * discountedPrice : 0
     */

    private String symptom;
    private String createTime;
    private int caseType;
    private int caseId;
    private int isImg;
    private String formulationName;
    private int formulationId;
    private int workingType;
    private String remark;
    private int id;
    private String caseImg;
    private int docId;
    private int customerId;
    private int patientId;
    private int plural;
    private String imgUrl;
    private String medMethod;
    private String freq;
    private int secrecy;
    private int fees;
    private String discountedPrice;
    private List<MedBean> med;

    @Override
    public String toString() {
        return "SelectByIdBean{" +
                "symptom='" + symptom + '\'' +
                ", createTime='" + createTime + '\'' +
                ", caseType=" + caseType +
                ", caseId=" + caseId +
                ", isImg=" + isImg +
                ", fees=" + fees +
                ", formulationName='" + formulationName + '\'' +
                ", formulationId=" + formulationId +
                ", workingType=" + workingType +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", caseImg='" + caseImg + '\'' +
                ", docId=" + docId +
                ", customerId=" + customerId +
                ", patientId=" + patientId +
                ", plural=" + plural +
                ", imgUrl='" + imgUrl + '\'' +
                ", medMethod='" + medMethod + '\'' +
                ", freq='" + freq + '\'' +
                ", secrecy=" + secrecy +
                ", discountedPrice=" + discountedPrice +
                ", med=" + med +
                '}';
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
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

    public int getFormulationId() {
        return formulationId;
    }

    public void setFormulationId(int formulationId) {
        this.formulationId = formulationId;
    }

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseImg() {
        return caseImg;
    }

    public void setCaseImg(String caseImg) {
        this.caseImg = caseImg;
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

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public List<MedBean> getMed() {
        return med;
    }

    public void setMed(List<MedBean> med) {
        this.med = med;
    }

    public static class MedBean implements Serializable {

        /**
         * medId : 102171
         * medName : ﻿白芍
         * medType : 0
         * medSpec :
         * medUnit : 0
         * medPrice : 2.04
         * medHeat : 0
         * pinYin :
         * medNumber : 1
         * medFrying : 打碎
         * isEnough : 1
         */

        private int medId;
        private String medName;
        private int medType;
        private String medSpec;
        private String medUnit;
        private double medPrice;
        private int medHeat;
        private String pinYin;
        private int medNumber;
        private String medFrying;
        private int isEnough;

        @Override
        public String toString() {
            return "MedBean{" +
                    "medId=" + medId +
                    ", medName='" + medName + '\'' +
                    ", medType=" + medType +
                    ", medSpec='" + medSpec + '\'' +
                    ", medUnit='" + medUnit + '\'' +
                    ", medPrice=" + medPrice +
                    ", medHeat=" + medHeat +
                    ", pinYin='" + pinYin + '\'' +
                    ", medNumber=" + medNumber +
                    ", medFrying='" + medFrying + '\'' +
                    ", isEnough=" + isEnough +
                    '}';
        }

        public int getMedId() {
            return medId;
        }

        public void setMedId(int medId) {
            this.medId = medId;
        }

        public String getMedName() {
            return medName;
        }

        public void setMedName(String medName) {
            this.medName = medName;
        }

        public int getMedType() {
            return medType;
        }

        public void setMedType(int medType) {
            this.medType = medType;
        }

        public String getMedSpec() {
            return medSpec;
        }

        public void setMedSpec(String medSpec) {
            this.medSpec = medSpec;
        }

        public String getMedUnit() {
            return medUnit;
        }

        public void setMedUnit(String medUnit) {
            this.medUnit = medUnit;
        }

        public double getMedPrice() {
            return medPrice;
        }

        public void setMedPrice(double medPrice) {
            this.medPrice = medPrice;
        }

        public int getMedHeat() {
            return medHeat;
        }

        public void setMedHeat(int medHeat) {
            this.medHeat = medHeat;
        }

        public String getPinYin() {
            return pinYin;
        }

        public void setPinYin(String pinYin) {
            this.pinYin = pinYin;
        }

        public int getMedNumber() {
            return medNumber;
        }

        public void setMedNumber(int medNumber) {
            this.medNumber = medNumber;
        }

        public String getMedFrying() {
            return medFrying;
        }

        public void setMedFrying(String medFrying) {
            this.medFrying = medFrying;
        }

        public int getIsEnough() {
            return isEnough;
        }

        public void setIsEnough(int isEnough) {
            this.isEnough = isEnough;
        }
    }
}

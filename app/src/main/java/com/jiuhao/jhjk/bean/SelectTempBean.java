package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

//协定方详情
public class SelectTempBean implements Serializable {

    /**
     * promiseId : 1
     * symptom : 感冒
     * factoryId : 1
     * plural : 1
     * medMethod : 先煎
     * freq : 1天1次
     * formulationId : 1
     * efficacy : 感冒
     * indication : 感冒
     * remark : 1
     * createTime : 2019-08-26 13:12:54
     * updateTime :
     * med : [{"medId":100000,"medName":"丁香","medType":0,"medSpec":"","medUnit":"1","medPrice":"","medHeat":0,"pinYin":"","medNumber":1,"medFrying":"先煎","isEnough":0},{"medId":100004,"medName":"三七","medType":0,"medSpec":"","medUnit":"2","medPrice":"","medHeat":0,"pinYin":"","medNumber":2,"medFrying":"后煎","isEnough":0}]
     * formulationName : 配方颗粒
     * workingType : 0
     */

    private int promiseId;
    private String symptom;
    private int factoryId;
    private int plural;
    private int caseType;
    private String medMethod;
    private String freq;
    private int formulationId;
    private String efficacy;
    private String indication;
    private String remark;
    private String createTime;
    private String updateTime;
    private String formulationName;
    private int workingType;
    private int fees;
    private String ingredients;
    private List<MedBean> med;

    @Override
    public String toString() {
        return "SelectTempBean{" +
                "promiseId=" + promiseId +
                ", symptom='" + symptom + '\'' +
                ", factoryId=" + factoryId +
                ", fees=" + fees +
                ", ingredients=" + ingredients +
                ", caseType=" + caseType +
                ", plural=" + plural +
                ", medMethod='" + medMethod + '\'' +
                ", freq='" + freq + '\'' +
                ", formulationId=" + formulationId +
                ", efficacy='" + efficacy + '\'' +
                ", indication='" + indication + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", formulationName='" + formulationName + '\'' +
                ", workingType=" + workingType +
                ", med=" + med +
                '}';
    }
    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getCaseType() {
        return caseType;
    }

    public void setCaseType(int caseType) {
        this.caseType = caseType;
    }

    public int getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(int promiseId) {
        this.promiseId = promiseId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public int getPlural() {
        return plural;
    }

    public void setPlural(int plural) {
        this.plural = plural;
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

    public int getFormulationId() {
        return formulationId;
    }

    public void setFormulationId(int formulationId) {
        this.formulationId = formulationId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getFormulationName() {
        return formulationName;
    }

    public void setFormulationName(String formulationName) {
        this.formulationName = formulationName;
    }

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
    }

    public List<MedBean> getMed() {
        return med;
    }

    public void setMed(List<MedBean> med) {
        this.med = med;
    }

    public static class MedBean implements Serializable {
        /**
         * medId : 100000
         * medName : 丁香
         * medType : 0
         * medSpec :
         * medUnit : 1
         * medPrice :
         * medHeat : 0
         * pinYin :
         * medNumber : 1
         * medFrying : 先煎
         * isEnough : 0
         */

        private int medId;
        private String medName;
        private int medType;
        private String medSpec;
        private String medUnit;
        private String medPrice;
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
                    ", medPrice='" + medPrice + '\'' +
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

        public String getMedPrice() {
            return medPrice;
        }

        public void setMedPrice(String medPrice) {
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

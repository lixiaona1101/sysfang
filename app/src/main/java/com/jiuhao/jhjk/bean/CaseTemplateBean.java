package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

public class CaseTemplateBean implements Serializable{

    /**
     * id : 0
     * promiseId : 0
     * symptom : 感冒
     * formulationId : 1
     * formulationName :
     * caseType : 0
     * plural : 7
     * freq : 成人一天两次,早晚各一次
     * medMethod : 水冲服
     * med : [{"medId":102458,"medName":"北柴胡","medType":0,"medSpec":"1g","medUnit":"0","medPrice":0,"medHeat":0,"pinYin":"","medNumber":6,"medFrying":"","isEnough":1},{"medId":102443,"medName":"白芍","medType":0,"medSpec":"1g","medUnit":"0","medPrice":0,"medHeat":0,"pinYin":"","medNumber":10,"medFrying":"","isEnough":1},{"medId":102754,"medName":"蒲公英","medType":0,"medSpec":"1g","medUnit":"0","medPrice":0,"medHeat":0,"pinYin":"","medNumber":15,"medFrying":"","isEnough":1},{"medId":102862,"medName":"夏枯草","medType":0,"medSpec":"1g","medUnit":"0","medPrice":0,"medHeat":0,"pinYin":"","medNumber":10,"medFrying":"","isEnough":1},{"medId":103085,"medName":"海螵鞘","medType":0,"medSpec":"1g","medUnit":"0","medPrice":0,"medHeat":0,"pinYin":"","medNumber":20,"medFrying":"","isEnough":1},{"medId":102909,"medName":"赭石","medType":0,"medSpec":"1g","medUnit":"0","medPrice":0,"medHeat":0,"pinYin":"","medNumber":30,"medFrying":"","isEnough":1}]
     * code :
     * workingType : 0
     * fees : 0
     * ingredients :
     * remark :
     */

    private int id;
    private int promiseId;
    private String symptom;
    private int formulationId;
    private String formulationName;
    private int caseType;
    private int plural;
    private String freq;
    private String medMethod;
    private String code;
    private int workingType;
    private int fees;
    private String ingredients;
    private String remark;
    private List<MedBean> med;

    @Override
    public String toString() {
        return "CaseTemplateBean{" +
                "id=" + id +
                ", promiseId=" + promiseId +
                ", symptom='" + symptom + '\'' +
                ", formulationId=" + formulationId +
                ", formulationName='" + formulationName + '\'' +
                ", caseType=" + caseType +
                ", plural=" + plural +
                ", freq='" + freq + '\'' +
                ", medMethod='" + medMethod + '\'' +
                ", code='" + code + '\'' +
                ", workingType=" + workingType +
                ", fees=" + fees +
                ", ingredients='" + ingredients + '\'' +
                ", remark='" + remark + '\'' +
                ", med=" + med +
                '}';
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(int promiseId) {
        this.promiseId = promiseId;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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

    public int getCaseType() {
        return caseType;
    }

    public void setCaseType(int caseType) {
        this.caseType = caseType;
    }

    public int getPlural() {
        return plural;
    }

    public void setPlural(int plural) {
        this.plural = plural;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getMedMethod() {
        return medMethod;
    }

    public void setMedMethod(String medMethod) {
        this.medMethod = medMethod;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<MedBean> getMed() {
        return med;
    }

    public void setMed(List<MedBean> med) {
        this.med = med;
    }

    public static class MedBean implements Serializable{
        /**
         * medId : 102458
         * medName : 北柴胡
         * medType : 0
         * medSpec : 1g
         * medUnit : 0
         * medPrice : 0
         * medHeat : 0
         * pinYin :
         * medNumber : 6
         * medFrying :
         * isEnough : 1
         */

        private int medId;
        private String medName;
        private int medType;
        private String medSpec;
        private String medUnit;
        private int medPrice;
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

        public int getMedPrice() {
            return medPrice;
        }

        public void setMedPrice(int medPrice) {
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

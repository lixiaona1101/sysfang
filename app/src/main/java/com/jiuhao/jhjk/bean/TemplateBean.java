package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

public class TemplateBean implements Serializable{

    /**
     * id : 15
     * symptom : 感冒
     * formulationId : 0
     * formulationName : 配方颗粒
     * caseType : 2
     * plural : 7
     * freq : 成人一天两次,早晚各一次
     * medMethod : 水冲服
     * med : ["瘪桃干: 10袋 打碎"]
     * code :   https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFp8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyRUdWVVo4R2I4TF8xMDAwMHcwN18AAgSuvXVdAwQAAAAA
     */

    private int id;
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
    private List<String> med;
    private int flag;//標記是否生成二維碼 0未生成 1已生成

    @Override
    public String toString() {
        return "TemplateBean{" +
                "id=" + id +
                ", flag=" + flag +
                ", symptom='" + symptom + '\'' +
                ", formulationId=" + formulationId +
                ", formulationName='" + formulationName + '\'' +
                ", caseType=" + caseType +
                ", plural=" + plural +
                ", freq='" + freq + '\'' +
                ", medMethod='" + medMethod + '\'' +
                ", code='" + code + '\'' +
                ", med=" + med +
                ", workingType='" + workingType + '\'' +
                ", fees='" + fees + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", remark=" + remark +
                '}';
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

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


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

    public List<String> getMed() {
        return med;
    }

    public void setMed(List<String> med) {
        this.med = med;
    }
}

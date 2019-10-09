package com.jiuhao.jhjk.bean;

public class SelectSuggestBean {

    /**
     * symptom : 感冒
     * id : 23
     * formulationName : 配方颗粒
     * templateType : 2
     */

    private String symptom;
    private int id;
    private String formulationName;
    private int templateType;

    @Override
    public String toString() {
        return "SelectSuggestBean{" +
                "symptom='" + symptom + '\'' +
                ", id=" + id +
                ", formulationName='" + formulationName + '\'' +
                ", templateType=" + templateType +
                '}';
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormulationName() {
        return formulationName;
    }

    public void setFormulationName(String formulationName) {
        this.formulationName = formulationName;
    }

    public int getTemplateType() {
        return templateType;
    }

    public void setTemplateType(int templateType) {
        this.templateType = templateType;
    }
}

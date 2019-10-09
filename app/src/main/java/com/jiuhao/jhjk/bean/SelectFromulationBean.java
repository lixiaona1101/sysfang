package com.jiuhao.jhjk.bean;

public class SelectFromulationBean {


    /**
     * id : 1
     * name : 配方颗粒
     * caseType : 2
     * workingType : 0
     * freq : 成人一天两次,早晚各一次
     * medMethod : 水冲服
     * createTime : 2019-08-26 12:02:35
     * updateTime : 2018-12-26 08:51:19
     */

    private int id;
    private String name;
    private int caseType;
    private int workingType;
    private String freq;
    private String medMethod;
    private String createTime;
    private String updateTime;

    @Override
    public String toString() {
        return "SelectFromulationBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caseType=" + caseType +
                ", workingType=" + workingType +
                ", freq='" + freq + '\'' +
                ", medMethod='" + medMethod + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaseType() {
        return caseType;
    }

    public void setCaseType(int caseType) {
        this.caseType = caseType;
    }

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
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
}

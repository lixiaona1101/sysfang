package com.jiuhao.jhjk.bean;

import java.util.List;

/**
 * 协定方
 */
public class SelectPromiseCaseBean {

    /**
     * status : 0
     * msg :
     * data : [{"promiseId":1,"symptom":"感冒","factoryId":1,"plural":1,"medMethod":"先煎","freq":"1天1次","formulationId":1,"efficacy":"感冒","indication":"感冒","remark":"1","createTime":"2019-08-26 13:12:54","updateTime":"","med":["丁香: 1袋 先煎","三七: 2袋 后煎"],"formulationName":"配方颗粒","workingType":0},{"promiseId":2,"symptom":"反流1","factoryId":1,"plural":1,"medMethod":"后煎","freq":"1天1次","formulationId":1,"efficacy":"反流1","indication":"反流1","remark":"1","createTime":"2019-08-26 13:12:54","updateTime":"","med":[],"formulationName":"配方颗粒","workingType":0}]
     * hasNextPage : false
     * totalCount : 0
     */

    private int status;
    private String msg;
    private boolean hasNextPage;
    private int totalCount;
    private List<DataBean> data;

    @Override
    public String toString() {
        return "SelectPromiseCaseBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", hasNextPage=" + hasNextPage +
                ", totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
         * med : ["丁香: 1袋 先煎","三七: 2袋 后煎"]
         * formulationName : 配方颗粒
         * workingType : 0
         */

        private int promiseId;
        private String symptom;
        private int factoryId;
        private int plural;
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
        private List<String> med;

        @Override
        public String toString() {
            return "DataBean{" +
                    "promiseId=" + promiseId +
                    ", symptom='" + symptom + '\'' +
                    ", factoryId=" + factoryId +
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

        public List<String> getMed() {
            return med;
        }

        public void setMed(List<String> med) {
            this.med = med;
        }
    }
}

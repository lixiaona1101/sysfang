package com.jiuhao.jhjk.bean;

import java.util.List;

/**
 * 处方记录
 */
public class CaseHistoryBean {

    /**
     * status : 0
     * msg :
     * data : [{"med":["矮地茶","炒白扁豆","白鲜皮","麸炒枳壳","白芷","白花蛇舌草","白茅根"],"symptom":"票票","createTime":"2019-09-17 11:05:27","caseId":4182,"isImg":1,"formulationName":"配方颗粒","code":"","id":4182,"caseType":0,"plural":7,"freq":"成人一天两次,早晚各一次","medMethod":"水冲服"},{"med":["?白芍"],"symptom":"感冒","createTime":"2019-09-16 15:49:06","caseId":4181,"isImg":1,"formulationName":"配方颗粒","code":"","id":4181,"caseType":0,"plural":7,"freq":"1tian1次","medMethod":"水送服"},{"med":["?白芍"],"symptom":"感冒","createTime":"2019-09-16 10:21:31","caseId":4180,"isImg":1,"formulationName":"配方颗粒","code":"","id":4180,"caseType":0,"plural":7,"freq":"1tian1次","medMethod":"水送服"},{"med":["炒白扁豆","矮地茶","白鲜皮","麸炒枳壳","白芷","白花蛇舌草","白茅根"],"symptom":"Ff","createTime":"2019-09-12 15:30:07","caseId":4179,"isImg":1,"formulationName":"配方颗粒","code":"","id":4179,"caseType":0,"plural":7,"freq":"成人一天两次,早晚各一次","medMethod":"水冲服"},{"med":["矮地茶","炒白扁豆","白鲜皮","麸炒枳壳","白芷","白花蛇舌草","白茅根"],"symptom":"票票001","createTime":"2019-09-12 09:44:20","caseId":4178,"isImg":1,"formulationName":"配方颗粒","code":"","id":4178,"caseType":0,"plural":7,"freq":"成人一天两次,早晚各一次","medMethod":"水冲服"},{"med":["?白芍"],"symptom":"感冒","createTime":"2019-09-12 09:40:20","caseId":4177,"isImg":1,"formulationName":"配方颗粒","code":"","id":4177,"caseType":0,"plural":7,"freq":"1tian1次","medMethod":"水送服"},{"med":["?白芍"],"symptom":"感冒","createTime":"2019-09-12 09:40:14","caseId":4176,"isImg":1,"formulationName":"配方颗粒","code":"","id":4176,"caseType":0,"plural":7,"freq":"1tian1次","medMethod":"水送服"},{"med":["?白芍"],"symptom":"感冒","createTime":"2019-09-12 09:28:57","caseId":4174,"isImg":1,"formulationName":"配方颗粒","code":"","id":4174,"caseType":0,"plural":7,"freq":"1tian1次","medMethod":"水送服"},{"med":[],"symptom":"感冒","createTime":"2019-09-12 09:28:50","caseId":4173,"isImg":2,"formulationName":"配方颗粒","code":"","id":4173,"caseType":0,"plural":7,"freq":"1天1次","medMethod":"水送服"},{"med":["?白芍"],"symptom":"感冒","createTime":"2019-09-12 09:27:39","caseId":4170,"isImg":1,"formulationName":"配方颗粒","code":"","id":4170,"caseType":0,"plural":7,"freq":"1tian1次","medMethod":"水送服"}]
     * hasNextPage : true
     * totalCount : 0
     */

    private int status;
    private String msg;
    private boolean hasNextPage;
    private int totalCount;
    private List<DataBean> data;


    @Override
    public String toString() {
        return "CaseHistoryBean{" +
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
         * med : ["矮地茶","炒白扁豆","白鲜皮","麸炒枳壳","白芷","白花蛇舌草","白茅根"]
         * symptom : 票票
         * createTime : 2019-09-17 11:05:27
         * caseId : 4182
         * isImg : 1
         * formulationName : 配方颗粒
         * code :
         * id : 4182
         * caseType : 0
         * plural : 7
         * freq : 成人一天两次,早晚各一次
         * medMethod : 水冲服
         */

        private String symptom;
        private String createTime;
        private int caseId;
        private int isImg;
        private String formulationName;
        private String code;
        private int id;
        private int caseType;
        private int plural;
        private String freq;
        private String medMethod;
        private List<String> med;

        @Override
        public String toString() {
            return "DataBean{" +
                    "symptom='" + symptom + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", caseId=" + caseId +
                    ", isImg=" + isImg +
                    ", formulationName='" + formulationName + '\'' +
                    ", code='" + code + '\'' +
                    ", id=" + id +
                    ", caseType=" + caseType +
                    ", plural=" + plural +
                    ", freq='" + freq + '\'' +
                    ", medMethod='" + medMethod + '\'' +
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

        public int getCaseId() {
            return caseId;
        }

        public void setCaseId(int caseId) {
            this.caseId = caseId;
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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public List<String> getMed() {
            return med;
        }

        public void setMed(List<String> med) {
            this.med = med;
        }
    }
}

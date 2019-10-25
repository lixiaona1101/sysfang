package com.jiuhao.jhjk.bean;

public class SavecaseBean {

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SavecaseBean{" +
                "status='" + status + '\'' +
                ", msg=" + msg +
                ", data=" + data +
                '}';
    }

    public static class DataBean {

        private int caseId;
        private String url;

        public int getCaseId() {
            return caseId;
        }

        public void setCaseId(int caseId) {
            this.caseId = caseId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "caseId='" + caseId + '\'' +
                    ", url=" + url +
                    '}';
        }
    }
}

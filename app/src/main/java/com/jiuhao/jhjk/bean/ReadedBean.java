package com.jiuhao.jhjk.bean;

import java.util.List;

public class ReadedBean {

    /**
     * picUrl : http://jhwj.oss-cn-hangzhou.aliyuncs.com/jhjk/555.png
     * data : [{"id":213,"sendId":100001153,"receiveId":273,"title":"","content":"11","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-18 15:10:22","updateTime":"2019-09-18 15:10:22","timeStr":"昨天 15:10:22"},{"id":214,"sendId":100001153,"receiveId":273,"title":"","content":"xz1","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-18 15:10:22","updateTime":"2019-09-18 15:10:22","timeStr":"昨天 15:10:22"},{"id":215,"sendId":100001153,"receiveId":273,"title":"","content":"xz","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-18 15:10:22","updateTime":"2019-09-18 15:10:22","timeStr":"昨天 15:10:22"},{"id":218,"sendId":100001153,"receiveId":273,"title":"","content":"22","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-18 16:25:45","updateTime":"2019-09-18 16:25:45","timeStr":"昨天 16:25:45"},{"id":224,"sendId":100001153,"receiveId":273,"title":"","content":"QQ","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-18 18:49:34","updateTime":"2019-09-18 18:49:34","timeStr":"昨天 18:49:34"},{"id":246,"sendId":100001153,"receiveId":273,"title":"","content":"噢噢噢","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-19 11:27:15","updateTime":"2019-09-19 11:27:15","timeStr":"今天 11:27:15"},{"id":247,"sendId":100001153,"receiveId":273,"title":"","content":"113","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-19 11:27:15","updateTime":"2019-09-19 11:27:15","timeStr":"今天 11:27:15"},{"id":248,"sendId":100001153,"receiveId":273,"title":"","content":"33","msgType":1,"type":0,"sendType":2,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-19 11:27:15","updateTime":"2019-09-19 11:27:15","timeStr":"今天 11:27:15"},{"id":261,"sendId":273,"receiveId":100001153,"title":"","content":"content 与file 必填一个","msgType":1,"type":0,"sendType":1,"lookState":2,"url":"","listId":50,"recordId":"","createTime":"2019-09-19 17:46:10","updateTime":"2019-09-19 17:46:10","timeStr":"今天 17:46:10"}]
     * lastCastId : 4211
     * shielding : 0
     * customerName : 何大大
     */

    private String picUrl;
    private int lastCastId;
    private int shielding;
    private String customerName;
    private List<DataBean> data;
    private int caseInConsultation;
    private int chating;

    @Override
    public String toString() {
        return "ReadedBean{" +
                "picUrl='" + picUrl + '\'' +
                ", lastCastId=" + lastCastId +
                ", caseInConsultation=" + caseInConsultation +
                ", chating=" + chating +
                ", shielding=" + shielding +
                ", customerName='" + customerName + '\'' +
                ", data=" + data +
                '}';
    }
    public int getCaseInConsultation() {
        return caseInConsultation;
    }

    public void setCaseInConsultation(int caseInConsultation) {
        this.caseInConsultation = caseInConsultation;
    }

    public int getChating() {
        return chating;
    }

    public void setChating(int chating) {
        this.chating = chating;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getLastCastId() {
        return lastCastId;
    }

    public void setLastCastId(int lastCastId) {
        this.lastCastId = lastCastId;
    }

    public int getShielding() {
        return shielding;
    }

    public void setShielding(int shielding) {
        this.shielding = shielding;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 213
         * sendId : 100001153
         * receiveId : 273
         * title :
         * content : 11
         * msgType : 1
         * type : 0
         * sendType : 2
         * lookState : 2
         * url :
         * listId : 50
         * recordId :
         * createTime : 2019-09-18 15:10:22
         * updateTime : 2019-09-18 15:10:22
         * timeStr : 昨天 15:10:22
         */

        private int id;
        private int sendId;
        private int receiveId;
        private String title;
        private String content;
        private int msgType;
        private int type;
        private int sendType;
        private int lookState;
        private String url;
        private int listId;
        private String recordId;
        private String createTime;
        private String updateTime;
        private String timeStr;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", sendId=" + sendId +
                    ", receiveId=" + receiveId +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", msgType=" + msgType +
                    ", type=" + type +
                    ", sendType=" + sendType +
                    ", lookState=" + lookState +
                    ", url='" + url + '\'' +
                    ", listId=" + listId +
                    ", recordId='" + recordId + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", timeStr='" + timeStr + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSendId() {
            return sendId;
        }

        public void setSendId(int sendId) {
            this.sendId = sendId;
        }

        public int getReceiveId() {
            return receiveId;
        }

        public void setReceiveId(int receiveId) {
            this.receiveId = receiveId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSendType() {
            return sendType;
        }

        public void setSendType(int sendType) {
            this.sendType = sendType;
        }

        public int getLookState() {
            return lookState;
        }

        public void setLookState(int lookState) {
            this.lookState = lookState;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getListId() {
            return listId;
        }

        public void setListId(int listId) {
            this.listId = listId;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
            this.recordId = recordId;
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

        public String getTimeStr() {
            return timeStr;
        }

        public void setTimeStr(String timeStr) {
            this.timeStr = timeStr;
        }
    }
}

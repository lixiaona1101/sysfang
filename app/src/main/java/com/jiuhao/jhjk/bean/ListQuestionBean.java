package com.jiuhao.jhjk.bean;

import java.util.List;

public class ListQuestionBean {

    /**
     * limit : 10
     * offset : 0
     * page : 1
     * id : 1
     * departmentName : 消化内科
     * orderId : 2
     * count : 5
     * data : [{"id":3,"departmentId":1,"questionType":0,"content":"有无家族病史","answerChoose":"{\"a\":\"两边家族都有\",\"b\":\"丈夫家边有\",\"c\":\"老婆家那边有\",\"d\":\"都没有\"}","answerChoosevo":"","remark":"","createTime":"2019-08-27T07:33:18.000 0000","updateTime":"2019-08-27T07:33:18.000 0000"},{"id":4,"departmentId":1,"questionType":0,"content":"有无家族病史11256","answerChoose":"{\"a\":\"两边家族都有\",\"b\":\"丈夫家边有\",\"c\":\"老婆家那边有\",\"d\":\"都没有\"}","answerChoosevo":"","remark":"","createTime":"2019-08-27T07:33:26.000 0000","updateTime":"2019-08-27T07:33:26.000 0000"},{"id":5,"departmentId":1,"questionType":0,"content":"有无家族病史11256","answerChoose":"{\"a\":\"两边家族都有\",\"b\":\"丈夫家边有\",\"c\":\"老婆家那边有\",\"d\":\"都没有\"}","answerChoosevo":"","remark":"","createTime":"2019-08-27T07:34:12.000 0000","updateTime":"2019-08-27T07:34:12.000 0000"},{"id":6,"departmentId":1,"questionType":0,"content":"有无家族病史11256","answerChoose":"{\"a\":\"两边家族都有\",\"b\":\"丈夫家边有\",\"c\":\"老婆家那边有\",\"d\":\"都没有\"}","answerChoosevo":"","remark":"","createTime":"2019-08-27T07:34:13.000 0000","updateTime":"2019-08-27T07:34:13.000 0000"},{"id":7,"departmentId":1,"questionType":0,"content":"有无家族病史11256","answerChoose":"{\"a\":\"两边家族都有\",\"b\":\"丈夫家边有\",\"c\":\"老婆家那边有\",\"d\":\"都没有\"}","answerChoosevo":"","remark":"","createTime":"2019-08-27T07:34:14.000 0000","updateTime":"2019-08-27T07:34:14.000 0000"}]
     */

    private int limit;
    private int offset;
    private int page;
    private int id;
    private String departmentName;
    private int orderId;
    private int count;
    private List<DataBean> data;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ListQuestionBean{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", page=" + page +
                ", id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", orderId=" + orderId +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 3
         * departmentId : 1
         * questionType : 0
         * content : 有无家族病史
         * answerChoose : {"a":"两边家族都有","b":"丈夫家边有","c":"老婆家那边有","d":"都没有"}
         * answerChoosevo :
         * remark :
         * createTime : 2019-08-27T07:33:18.000 0000
         * updateTime : 2019-08-27T07:33:18.000 0000
         */

        private int id;
        private int departmentId;
        private int questionType;
        private String content;
        private String answerChoose;
        private String answerChoosevo;
        private String remark;
        private String createTime;
        private String updateTime;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", departmentId=" + departmentId +
                    ", questionType=" + questionType +
                    ", content='" + content + '\'' +
                    ", answerChoose='" + answerChoose + '\'' +
                    ", answerChoosevo='" + answerChoosevo + '\'' +
                    ", remark='" + remark + '\'' +
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

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
        }

        public int getQuestionType() {
            return questionType;
        }

        public void setQuestionType(int questionType) {
            this.questionType = questionType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAnswerChoose() {
            return answerChoose;
        }

        public void setAnswerChoose(String answerChoose) {
            this.answerChoose = answerChoose;
        }

        public String getAnswerChoosevo() {
            return answerChoosevo;
        }

        public void setAnswerChoosevo(String answerChoosevo) {
            this.answerChoosevo = answerChoosevo;
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
    }

}

package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 按照问诊单id展示问题
 */
public class IgQuestion2Bean implements Serializable {
    /**
     * dqs : [{"answerChoose":"{\"a\":\"分\",\"b\":\"好\",\"c\":\"洗\",\"d\":\"行\"}","interrogationId":56,"remark":"","id":104,"questionType":0,"content":"夏季发烧什么原因造成？"},{"answerChoose":"{\"a\":\"a\",\"b\":\"b\",\"c\":\"c\",\"d\":\"d\"}","interrogationId":56,"remark":"","id":107,"questionType":1,"content":"流鼻涕"},{"answerChoose":"{\"a\":\"是\",\"b\":\"否\"}","interrogationId":56,"remark":"","id":127,"questionType":0,"content":"是否肩膀有酸痛"}]
     * name : 发烧
     */

    private String name;
    private List<DqsBean> dqs;

        @Override
    public String toString() {
        return "IgQuestion2Bean{" +
                "name=" + name +
                ", dqs=" + dqs +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DqsBean> getDqs() {
        return dqs;
    }

    public void setDqs(List<DqsBean> dqs) {
        this.dqs = dqs;
    }

    public static class DqsBean implements Serializable{
        /**
         * answerChoose : {"a":"分","b":"好","c":"洗","d":"行"}
         * interrogationId : 56
         * remark :
         * id : 104
         * questionType : 0
         * content : 夏季发烧什么原因造成？
         */

        private String answerChoose;
        private int interrogationId;
        private String remark;
        private int id;
        private int questionType;
        private String content;

        @Override
        public String toString() {
            return "DqsBean{" +
                    "id=" + id +
                    ", interrogationId=" + interrogationId +
                    ", questionType=" + questionType +
                    ", content='" + content + '\'' +
                    ", answerChoose='" + answerChoose + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
        public String getAnswerChoose() {
            return answerChoose;
        }

        public void setAnswerChoose(String answerChoose) {
            this.answerChoose = answerChoose;
        }

        public int getInterrogationId() {
            return interrogationId;
        }

        public void setInterrogationId(int interrogationId) {
            this.interrogationId = interrogationId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}

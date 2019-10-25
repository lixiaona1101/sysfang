package com.jiuhao.jhjk.bean;

import java.io.Serializable;
import java.util.List;

public class AnswerBean implements Serializable {

    /**
     * dqs : [{"answer":"a,b,","answerChoose":"{\"a\":\"俺\",\"b\":\"不\",\"c\":\"出\",\"d\":\"多\"}","interrogationId":59,"remark":"","id":195,"questionType":1,"content":"多选题啊"},{"answer":"a","answerChoose":"{\"a\":\"是\",\"b\":\"否\"}","interrogationId":59,"remark":"","id":133,"questionType":0,"content":"是否肩膀有酸痛"},{"answer":"a","answerChoose":"{\"a\":\"是\",\"b\":\"否\"}","interrogationId":59,"remark":"","id":134,"questionType":0,"content":"近日感冒否"}]
     * name : 感冒
     */

    private String name;
    private List<DqsBean> dqs;

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

    @Override
    public String toString() {
        return "AnswerBean{" +
                "name='" + name + '\'' +
                ", dqs=" + dqs +
                '}';
    }

    public static class DqsBean implements Serializable{
        /**
         * answer : a,b,
         * answerChoose : {"a":"俺","b":"不","c":"出","d":"多"}
         * interrogationId : 59
         * remark :
         * id : 195
         * questionType : 1
         * content : 多选题啊
         */

        private String answer;
        private String answerChoose;
        private int interrogationId;
        private String remark;
        private int id;
        private int questionType;
        private String content;

        @Override
        public String toString() {
            return "DqsBean{" +
                    "answer='" + answer + '\'' +
                    ", answerChoose='" + answerChoose + '\'' +
                    ", interrogationId=" + interrogationId +
                    ", remark='" + remark + '\'' +
                    ", id=" + id +
                    ", questionType=" + questionType +
                    ", content='" + content + '\'' +
                    '}';
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
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

package com.jiuhao.jhjk.bean;

/**
 * 按照问诊单id展示问题
 */
public class IgQuestionBean {

    /**
     * id : 2
     * interrogationId : 4
     * questionType : 0
     * content : 有无家族病史
     * answerChoose : {"a":"两边家族都有","b":"丈夫家边有","c":"老婆家那边有","d":"都没有"}
     * orderBy : 1
     * items : 4
     * remark :
     */

    private int id;
    private int interrogationId;
    private int questionType;
    private String content;
    private String answerChoose;
    private int orderBy;
    private int items;
    private String remark;

    @Override
    public String toString() {
        return "IgQuestionBean{" +
                "id=" + id +
                ", interrogationId=" + interrogationId +
                ", questionType=" + questionType +
                ", content='" + content + '\'' +
                ", answerChoose='" + answerChoose + '\'' +
                ", orderBy=" + orderBy +
                ", items=" + items +
                ", remark='" + remark + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInterrogationId() {
        return interrogationId;
    }

    public void setInterrogationId(int interrogationId) {
        this.interrogationId = interrogationId;
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

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

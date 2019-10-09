package com.jiuhao.jhjk.bean;

/**
 * 按照科室id查看问题库
 */
public class KeShiBean {


    /**
     * id : 5
     * departmentId : 1
     * questionType : 0
     * content : 有无家族病史11256
     * answerChoose : {"a":"两边家族都有","b":"丈夫家边有","c":"老婆家那边有","d":"都没有"}
     * items : 4
     * remark :
     */

    private int id;

    public int getId() {
        return id;
    }

    private int departmentId;
    private int questionType;
    private String content;
    private String answerChoose;
    private int items;
    private String remark;
    private boolean check = false;


    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheck() {
        return check;
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

    @Override
    public String toString() {
        return "KeShiBean{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", questionType=" + questionType +
                ", content='" + content + '\'' +
                ", answerChoose='" + answerChoose + '\'' +
                ", items=" + items +
                ", remark='" + remark + '\'' +
                "check=" + check +
                '}';
    }
}

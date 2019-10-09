package com.jiuhao.jhjk.bean;

/**
 *历史消息
 */
public class MessageHisBean {

    /**
     * id : 14
     * title : 坐诊时间
     * content : 【坐诊时间】周一上午,周二下午,周三晚上,周五下午,周六上午
     * createTime : 2019-09-06 17:49:49
     * receiveIds : 不考虑 啦咯啦咯啦咯 羽哲hh 来来来 Mr shou
     */

    private int id;
    private String title;
    private String content;
    private String createTime;
    private String receiveIds;

    @Override
    public String toString() {
        return "MessageHisBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", receiveIds='" + receiveIds + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(String receiveIds) {
        this.receiveIds = receiveIds;
    }
}

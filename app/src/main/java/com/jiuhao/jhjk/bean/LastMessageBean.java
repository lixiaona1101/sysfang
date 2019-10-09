package com.jiuhao.jhjk.bean;

public class LastMessageBean {

    /**
     * id : 1
     * title : 系统消息
     * content : 审核通过
     * param : customerId=1&userId=2
     * docId : 273
     * type : 1
     * isRead : 1
     * status : 1
     * createTime : 2019-09-03 11:28:38
     * updateTime : 2019-09-03 11:28:40
     */

    private int id;
    private String title;
    private String content;
    private String param;
    private int docId;
    private int type;
    private int isRead;
    private int status;
    private String createTime;
    private String updateTime;

    @Override
    public String toString() {
        return "LastMessageBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", param='" + param + '\'' +
                ", docId=" + docId +
                ", type=" + type +
                ", isRead=" + isRead +
                ", status=" + status +
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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

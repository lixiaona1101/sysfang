package com.jiuhao.jhjk.bean;

/**
 * 患者分组列表
 */
public class DcGroupBean {

    /**
     * id : 10
     * name : 默认分组
     * docId : 273
     * groupType : 1
     * remark : 妙手回春
     * createTime : 1567050684000
     * updateTime : 1567050684000
     * count : 1
     */

    private int id;
    private String name;
    private int docId;
    private int groupType;
    private String remark;
    private long createTime;
    private long updateTime;
    private int count;

    @Override
    public String toString() {
        return "dcgroupbean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", docId=" + docId +
                ", groupType=" + groupType +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", count=" + count +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

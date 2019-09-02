package com.jiuhao.jhjk.bean;

public class BillBean2 {

    /**
     * id : 6
     * departmentId : 12
     * docId : 273
     * name : 感冒的问诊12qqq1
     * count : 1
     * remark :
     */

    private int id;
    private long departmentId;
    private int docId;
    private String name;
    private int count;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BillBean2{" +
                "id=" + id +
                ", departmentId=" + departmentId +
                ", docId=" + docId +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", remark='" + remark + '\'' +
                '}';
    }
}

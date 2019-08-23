package com.jiuhao.jhjk.bean;

/**
 * 获取所有科室
 */
public class DepartmentBean {

    /**
     * id : 1
     * departmentName : 消化内科
     * orderId : 2
     * createTime : 2019-01-08T05:34:53.000+0000
     * updateTime : 2019-01-08T05:34:54.000+0000
     */

    private int id;
    private String departmentName;
    private int orderId;
    private String createTime;
    private String updateTime;

    @Override
    public String toString() {
        return "DataBean{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", orderId=" + orderId +
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

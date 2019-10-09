package com.jiuhao.jhjk.bean;

public class PriceBean {

    /**
     * id : 32
     * docId : 273
     * customerId : 100001153
     * priceType : 1
     * price : 43.2
     * createTime : 2019-09-05 09:34:25
     * updateTime : 2019-09-05 09:34:25
     * customers :
     */

    private int id;
    private int docId;
    private int customerId;
    private int priceType;
    private double price;
    private String createTime;
    private String updateTime;
    private String customers;

    @Override
    public String toString() {
        return "PriceBean{" +
                "id=" + id +
                ", docId=" + docId +
                ", customerId=" + customerId +
                ", priceType=" + priceType +
                ", price=" + price +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", customers='" + customers + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }
}

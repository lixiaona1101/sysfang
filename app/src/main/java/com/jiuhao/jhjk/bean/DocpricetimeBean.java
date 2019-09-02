package com.jiuhao.jhjk.bean;

/**
 * 咨询费列表
 */
public class DocpricetimeBean {

    /**
     * id : 5
     * price : 50
     * markType : 1
     * customerCount : 0
     */

    private int id;
    private int price;
    private int markType;
    private int customerCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMarkType() {
        return markType;
    }

    public void setMarkType(int markType) {
        this.markType = markType;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    @Override
    public String toString() {
        return "DocpricetimeBean{" +
                "id=" + id +
                ", price=" + price +
                ", markType=" + markType +
                ", customerCount=" + customerCount +
                '}';
    }

}

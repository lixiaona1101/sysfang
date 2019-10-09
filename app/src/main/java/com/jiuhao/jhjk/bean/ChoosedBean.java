package com.jiuhao.jhjk.bean;

/**
 * 咨询费信息
 */
public class ChoosedBean {

    /**
     * id : 7
     * price : 5
     * markType : 2
     * customerCount : 2
     */

    private int id;
    private int price;
    private int markType;
    private int customerCount;

    @Override
    public String toString() {
        return "ChoosedBean{" +
                "id=" + id +
                ", price=" + price +
                ", markType=" + markType +
                ", customerCount=" + customerCount +
                '}';
    }

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
}

package com.jiuhao.jhjk.bean;

public class SelectingredientsBean {

    /**
     * id : 1
     * name : 蜂蜜
     * price : 0
     * workingType : 1
     */

    private int id;
    private String name;
    private int price;
    private int workingType;

    @Override
    public String toString() {
        return "SelectingredientsBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", workingType=" + workingType +
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWorkingType() {
        return workingType;
    }

    public void setWorkingType(int workingType) {
        this.workingType = workingType;
    }
}

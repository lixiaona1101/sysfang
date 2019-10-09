package com.jiuhao.jhjk.bean;

/**
 * Created by lxn on 2019/9/25.
 */

public class SelectingBean {
    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SelectingBean{" +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

}

package com.jiuhao.jhjk.bean;

public class DcMessageBean {

    /**
     * id : 1
     * name : 医嘱提醒
     */

    private int id;
    private String name;

    @Override
    public String toString() {
        return "DcMessageBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}

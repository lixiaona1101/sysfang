package com.jiuhao.jhjk.bean;

/**
 * Created by Administrator on 2019/8/26.
 */

public class DocLadelBean {

    /**
     * id : 363
     * specialty : 腹痛
     * departmentId : 1
     */

    private int id;
    private String specialty;
    private int departmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "DocLadelBean{" +
                "id=" + id +
                ", specialty='" + specialty + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }

}

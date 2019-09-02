package com.jiuhao.jhjk.bean;

/**
 * 完善医生信息
 */
public class DocBean {


    /**
     * status : 500
     * msg : java.lang.NullPointerException: null
     */


    private int status;
    private String msg;

    @Override
    public String toString() {
        return "DocBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

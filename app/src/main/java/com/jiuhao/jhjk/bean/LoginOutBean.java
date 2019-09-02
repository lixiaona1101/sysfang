package com.jiuhao.jhjk.bean;

/**
 * 退出登录
 */
public class LoginOutBean {


    /**
     * status : 0
     * msg : 操作成功
     */

    private int status;
    private String msg;

    @Override
    public String toString() {
        return "LoginOutBean{" +
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

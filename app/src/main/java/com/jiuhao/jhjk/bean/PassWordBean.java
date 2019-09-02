package com.jiuhao.jhjk.bean;

/**
 * 修改密码
 */
public class PassWordBean {

    /**
     * status : 1
     * msg : 验证码不正确
     */

    private int status;
    private String msg;

    @Override
    public String toString() {
        return "PassWordBean{" +
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

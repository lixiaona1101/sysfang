package com.jiuhao.jhjk.bean;

import java.util.List;

public class MedFryingBean {


    /**
     * status : 0
     * msg :
     * data : ["另包","打碎","兑入","先煎","后下","另煎","包煎","烊化","打粗粉","打细粉"]
     */

    private int status;
    private String msg;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

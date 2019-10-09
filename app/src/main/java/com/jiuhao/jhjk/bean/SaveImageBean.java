package com.jiuhao.jhjk.bean;

/**
 * Created by lxn on 2019/9/25.
 */

public class SaveImageBean {
    private int caseId;
    private String url;

    @Override
    public String toString() {
        return "SaveImageBean{" +
                "caseId='" + caseId + '\'' +
                ", url=" + url +
                '}';
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

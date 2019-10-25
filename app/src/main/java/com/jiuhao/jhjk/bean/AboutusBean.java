package com.jiuhao.jhjk.bean;

/**
 * Created by lxn on 2019/10/9.
 */

public class AboutusBean {

    private String hostline;

    public String getHostline() {
        return hostline;
    }

    public void setHostline(String hostline) {
        this.hostline = hostline;
    }

    @Override
    public String toString() {
        return "AboutusBean{" +
                ", hostline='" + hostline + '\'' +
                '}';
    }
}

package com.jiuhao.jhjk.bean;

/**
 * Created by lxn on 2019/10/9.
 */

public class VersionBean {
    private int id;
    private String version;
    private String content;
    private String url;
    private int appType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VersionBean{" +
                "id=" + id +
                ", version=" + version +
                ", content='" + content + '\'' +
                ", url=" + url +
                ", appType='" + appType +
                '}';
    }
}

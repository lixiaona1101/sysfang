package com.jiuhao.jhjk.bean;

public class SpecialFamousDoctorBean {

    /**
     * caseId : 4106
     * url : https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFS8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAydUdVaFlmR2I4TF8xdVQyTTF0Y0wAAgSnm3BdAwQQJwAA
     */

    private int caseId;
    private String url;

    @Override
    public String toString() {
        return "SpecialFamousDoctorBean{" +
                "caseId=" + caseId +
                ", url='" + url + '\'' +
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

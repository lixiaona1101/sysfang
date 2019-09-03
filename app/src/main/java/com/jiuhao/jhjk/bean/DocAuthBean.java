package com.jiuhao.jhjk.bean;

import java.io.Serializable;

/**
 * 查看医生认证
 */
public class DocAuthBean implements Serializable {

    /**
     * id : 0
     * docId : 273
     * name : 李贝贝
     * physicianCert : http://123
     * inviteCode :
     * salesman : {"id":0,"userId":0,"inviteCode":"","name":"张军杰","phone":"18937630677"}
     * physicianQualCert : http://123
     * au_PhysicianCert : false
     * au_PhysicianQualCert : true
     * authTime :
     * authStat : 3
     * unAuthRemark : 图片不够清楚，重新上传
     * createTime : 2019-08-28 14:05:28
     */

    private int id;
    private int docId;
    private String name;
    private String physicianCert;
    private String inviteCode;
    private SalesmanBean salesman;
    private String physicianQualCert;
    private boolean au_PhysicianCert;
    private boolean au_PhysicianQualCert;
    private String authTime;
    private int authStat;
    private String unAuthRemark;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicianCert() {
        return physicianCert;
    }

    public void setPhysicianCert(String physicianCert) {
        this.physicianCert = physicianCert;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public SalesmanBean getSalesman() {
        return salesman;
    }

    public void setSalesman(SalesmanBean salesman) {
        this.salesman = salesman;
    }

    public String getPhysicianQualCert() {
        return physicianQualCert;
    }

    public void setPhysicianQualCert(String physicianQualCert) {
        this.physicianQualCert = physicianQualCert;
    }

    public boolean isAu_PhysicianCert() {
        return au_PhysicianCert;
    }

    public void setAu_PhysicianCert(boolean au_PhysicianCert) {
        this.au_PhysicianCert = au_PhysicianCert;
    }

    public boolean isAu_PhysicianQualCert() {
        return au_PhysicianQualCert;
    }

    public void setAu_PhysicianQualCert(boolean au_PhysicianQualCert) {
        this.au_PhysicianQualCert = au_PhysicianQualCert;
    }

    public String getAuthTime() {
        return authTime;
    }

    public void setAuthTime(String authTime) {
        this.authTime = authTime;
    }

    public int getAuthStat() {
        return authStat;
    }

    public void setAuthStat(int authStat) {
        this.authStat = authStat;
    }

    public String getUnAuthRemark() {
        return unAuthRemark;
    }

    public void setUnAuthRemark(String unAuthRemark) {
        this.unAuthRemark = unAuthRemark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DocAuthBean{" +
                "id=" + id +
                ", docId=" + docId +
                ", name='" + name + '\'' +
                ", physicianCert='" + physicianCert + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", salesman=" + salesman +
                ", physicianQualCert='" + physicianQualCert + '\'' +
                ", au_PhysicianCert=" + au_PhysicianCert +
                ", au_PhysicianQualCert=" + au_PhysicianQualCert +
                ", authTime='" + authTime + '\'' +
                ", authStat=" + authStat +
                ", unAuthRemark='" + unAuthRemark + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public static class SalesmanBean implements Serializable{
        /**
         * id : 0
         * userId : 0
         * inviteCode :
         * name : 张军杰
         * phone : 18937630677
         */

        private int id;
        private int userId;
        private String inviteCode;
        private String name;
        private String phone;


        @Override
        public String toString() {
            return "SalesmanBean{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", inviteCode='" + inviteCode + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}

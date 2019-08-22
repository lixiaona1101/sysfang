package com.jiuhao.jhjk.bean;

public class LoginBean {

    /**
     * id : 1078
     * userId : 3348
     * hospital : null
     * departmentId : 1
     * titles : 涓讳换鍖荤敓
     * label : 涓嶆檿涓嶈偛
     * avatar : http://123
     * sex : 1
     * birthday : null
     * authStat : 1
     * fees : null
     * factoryId : null
     * businessCard : null
     * inviteCode : null
     * clinicTime : [[1,1],[1,2]]
     * areaId : null
     * createTime : null
     * updateTime : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NvdW50IjoiMzM0OCJ9.kO0AIXEcY8Aw-NxnuXGYd2uWMIa6E1ZW0B4kyOnqYiA
     * resume : 鎴戞槸绠€浠?
     * phone : null
     * password : null
     * unionId : null
     * departmentName : 娑堝寲鍐呯
     */

    private int id;
    private int userId;
    private Object hospital;
    private int departmentId;
    private String titles;
    private String label;
    private String avatar;
    private int sex;
    private Object birthday;
    private int authStat;
    private Object fees;
    private Object factoryId;
    private Object businessCard;
    private Object inviteCode;
    private String clinicTime;
    private Object areaId;
    private Object createTime;
    private Object updateTime;
    private String token;
    private String resume;
    private Object phone;
    private Object password;
    private Object unionId;
    private String departmentName;

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

    public Object getHospital() {
        return hospital;
    }

    public void setHospital(Object hospital) {
        this.hospital = hospital;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public int getAuthStat() {
        return authStat;
    }

    public void setAuthStat(int authStat) {
        this.authStat = authStat;
    }

    public Object getFees() {
        return fees;
    }

    public void setFees(Object fees) {
        this.fees = fees;
    }

    public Object getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Object factoryId) {
        this.factoryId = factoryId;
    }

    public Object getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(Object businessCard) {
        this.businessCard = businessCard;
    }

    public Object getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(Object inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }

    public Object getAreaId() {
        return areaId;
    }

    public void setAreaId(Object areaId) {
        this.areaId = areaId;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Object getUnionId() {
        return unionId;
    }

    public void setUnionId(Object unionId) {
        this.unionId = unionId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", hospital=" + hospital +
                ", departmentId=" + departmentId +
                ", titles='" + titles + '\'' +
                ", label='" + label + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", authStat=" + authStat +
                ", fees=" + fees +
                ", factoryId=" + factoryId +
                ", businessCard=" + businessCard +
                ", inviteCode=" + inviteCode +
                ", clinicTime='" + clinicTime + '\'' +
                ", areaId=" + areaId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", token='" + token + '\'' +
                ", resume='" + resume + '\'' +
                ", phone=" + phone +
                ", password=" + password +
                ", unionId=" + unionId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

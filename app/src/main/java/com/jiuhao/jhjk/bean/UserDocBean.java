package com.jiuhao.jhjk.bean;

/**
 * 医生信息
 */
public class UserDocBean {

    /**
     * id : 273
     * userId : 206
     * hospital :
     * departmentId : 18
     * titles : 住院医师
     * label : 脂肪肝、胆囊炎、肠易激综合征、功能性消化不良、
     * avatar : http://jhwj.oss-cn-hangzhou.aliyuncs.com/jhjk//1551146669209.jpg
     * sex : 2
     * birthday : 2000-01-01 00:00:00
     * authStat : 3
     * fees : 10
     * factoryId : 2
     * businessCard : https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHD8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyWHhtc3doUGNjZWsxMDAwMGcwN0EAAgSDPNdbAwQAAAAA
     * inviteCode : 100273
     * clinicTime : 1.2,1.2,1.3,3.2
     * areaId : 12
     * name : 李贝贝
     * registrationId : 1104a89792f95917912
     * createTime : 1550899356000
     * updateTime : 1559198060000
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjdXJyZW50VGltZU1pbGxpcyI6IjE1NjcxNTY5NjM4MTQiLCJleHAiOjE1Njk3NDg5NjMsImFjY291bnQiOiIyMDYifQ.tvzcRnNy7O58RdpxWJC2nM9Ph7_LBPY72YmbCdV_7hM
     * resume : 暂无信息
     * phone : 15658002082
     * password : e10adc3949ba59abbe56e057f20f883e
     * unionId :
     * departmentName : 中医科
     */

    private int id;
    private int userId;
    private String hospital;
    private int departmentId;
    private String titles;
    private String label;
    private String avatar;
    private int sex;
    private String birthday;
    private int authStat;
    private int fees;
    private int factoryId;
    private String businessCard;
    private String inviteCode;
    private String clinicTime;
    private int areaId;
    private String name;
    private String registrationId;
    private long createTime;
    private long updateTime;
    private String token;
    private String resume;
    private String phone;
    private String password;
    private String unionId;
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAuthStat() {
        return authStat;
    }

    public void setAuthStat(int authStat) {
        this.authStat = authStat;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(String businessCard) {
        this.businessCard = businessCard;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
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
        return "UserDocBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", hospital='" + hospital + '\'' +
                ", departmentId=" + departmentId +
                ", titles='" + titles + '\'' +
                ", label='" + label + '\'' +
                ", avatar='" + avatar + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", authStat=" + authStat +
                ", fees=" + fees +
                ", factoryId=" + factoryId +
                ", businessCard='" + businessCard + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", clinicTime='" + clinicTime + '\'' +
                ", areaId=" + areaId +
                ", name='" + name + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", token='" + token + '\'' +
                ", resume='" + resume + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", unionId='" + unionId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

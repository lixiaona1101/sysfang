package com.jiuhao.jhjk.APP;

public class ConfigKeys {

    private static final String BASE_URL = "http://192.168.0.104:8090/sysf/";
    //    private static final String BASE_URL = "http://www.hzsysf.com/sysf/";
    private static final String BASE_SERVER = "http://www.hzsysf.com/";

    /**
     * -------------------------微信APPID appSecret--------------------
     */
    public static final String WE_CHAT_APP_ID = "appId";
    public static final String WE_CHAT_APP_SECRET = "appSecret";

    //定位成功
    public static final String EB_location = "location";

    /**
     * ----------------------------保存基本医生信息---------------------
     */


    public static final String ID = "id";
    public static final String USERID = "userId";
    //医生医院
    public static final String HOSPITAL = "hospital";
    //科室编号
    public static final String DEPARTMENTID = "departmentId";
    //职称
    public static final String TITLES = "titles";
    //专业标签
    public static final String LABEL = "label";
    //头像
    public static final String AVATAR = "avatar";
    //性别
    public static final String SEX = "sex";
    //生日
    public static final String BIRTHDAY = "birthday";
    //认证
    public static final String AUTHSTAT = "authStat";
    //诊金
    public static final String FEES = "fees";
    //厂家id
    public static final String FACTORYID = "factoryId";
    //微信名片
    public static final String BUSINESSCARD = "businessCard";
    //邀请码
    public static final String INVITECODE = "inviteCode";
    //坐诊时间
    public static final String CLINICTIME = "clinicTime";
    //所属业务区
    public static final String AREAID = "areaId";
    public static final String CREATETIME = "createTime";
    public static final String UPDATETIME = "updateTime";
    //token
    public static final String TOKEN = "token";
    //简介
    public static final String RESUME = "resume";
    //电话号
    public static final String PHONE = "phone";
    //密码
    public static final String PASSWORD = "password";
    public static final String UNIONID = "unionId";
    public static final String DEPARTMENTNAME = "departmentName";
    //登录状态
    public static final String LOGIN_STATE = "login_state";


    /**
     * ---------------------------------接口------------------------------------
     */

    //医生登录
    public static final String LOGIN = BASE_URL + "user/login";

    //获取验证码
    public static final String GET_CODE = BASE_URL + "phone/code";

    //使用声明
    public static final String USER_INSTRUCTIONS = BASE_SERVER + "instructions.html";


}

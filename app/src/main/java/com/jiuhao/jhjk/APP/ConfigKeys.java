package com.jiuhao.jhjk.APP;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ConfigKeys {

    private static final String BASE_URL = "http://192.168.0.104:8090/sysf/";
    //    private static final String BASE_URL = "http://www.hzsysf.com/sysf/";
    private static final String BASE_SERVER = "http://www.hzsysf.com/";


    //glide
    public static final RequestOptions REQUEST_OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter();
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
    //科室名称
    public static final String DEPARTMENTNAME = "departmentName";
    //医生名字
    public static final String NAME = "name";
    //登录状态
    public static final String LOGIN_STATE = "login_state";
    //智能填写状态
    public static final String ZHI_FLAG = "zhiflag";

    /**
     * ---------------------------------接口------------------------------------
     */

    //医生登录
    public static final String LOGIN = BASE_URL + "user/login";

    //获取验证码
    public static final String GET_CODE = BASE_URL + "phone/code";

    //获取所有科室
    public static final String DEPARTMENT = BASE_URL + "department";

    //按照科室id查询标签
    public static final String DOCLABEL = BASE_URL + "doclabel";

    //完善医生信息
    public static final String DOC = BASE_URL + "doc";

    //修改密码
    public static final String PASSWORDD = BASE_URL + "password";

    //退出登录
    public static final String LOGINOUT = BASE_URL + "loginout";

    //更换手机号
    public static final String CHANGEPHONE = BASE_URL + "changephone";

    //问题库
    public static final String IGQUESTIONSTORE = BASE_URL + "igquestionStore";

    //编辑问诊单put请求 /问诊单列表get请求
    public static final String INTERROGATION = BASE_URL + "interrogation";

    //更新医生坐诊时间
    public static final String CLINIC = BASE_URL + "user/clinic";

    //获取医生信息
    public static final String USERDOC = BASE_URL + "user/doc";

    //邀请医生
    public static final String DSIMG = BASE_URL + "dsimg";

    //咨询费列表get  选择付费列表put
    public static final String DOCPRICEITEM = BASE_URL + "docPriceItem";

    //咨询费设置
    public static final String DOCPRICEITEMCHOOSED = BASE_URL + "docPriceItem/choosed";


    /**
     * -----------------------------------HTML-----------------------------
     */
    //法律声明
    public static final String USER_INSTRUCTIONS = BASE_SERVER + "instructions.html";

    //使用帮助
    public static final String USER_HELP = BASE_SERVER + "help.html";
}

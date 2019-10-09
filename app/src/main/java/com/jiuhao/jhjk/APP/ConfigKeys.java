package com.jiuhao.jhjk.APP;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ConfigKeys {

    //    private static final String BASE_URL = "http://192.168.0.104:8090/sysf/";//何
    private static final String BASE_URL = "http://www.bbtian.cn/sysf/";//线上
    private static final String BASE_S_URL = "http://www.bbtian.cn/sysf/";//线上
//    private static final String BASE_S_URL = "http://192.168.0.131:8092/sysf/";//苏
    private static final String BASE_SERVER = "http://www.hzsysf.com/";//网页


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
    //医生id
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
    //认证状态
    public static final String AUTHSTAT = "authStat";
    public static final String INVITE_STATUS = "invite_status";
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

    //编辑问诊单put请求 /问诊单列表get请求 /新增问诊单
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

    //查看认证get 医生认证post
    public static final String DOCAUTH = BASE_URL + "docAuth";

    //患者分组列表GET 修改分组PUT 删除分组delete 新增分组post
    public static final String DCGROUP = BASE_URL + "dcgroup";

    //查询分组用户
    public static final String INDEX = BASE_URL + "customer/index";

    //问诊单问题添加post 修改put 删除del  问诊单--问题列表--按问诊单ID展示问题get
    public static final String IGQUESTION = BASE_URL + "igquestion";

    //按科室编号查询问题库
    public static final String IDIGQUESTION = BASE_URL + "igquestionStore/findByDepartmentId";

    //问诊单从问题库导入
    public static final String BATCHCREATE = BASE_URL + "igquestionStore/batchCreate";

    //问诊单 问题列表 更新排序
    public static final String ORDERBY = BASE_URL + "igquestion/orderBy";

    //患者详情
    public static final String CUSTOMER = BASE_URL + "customer";

    //取消关注
    public static final String WATCH = BASE_URL + "customer/watch";

    //更新用户分组
    public static final String DCGROUPDETAIL = BASE_URL + "dcgroupdetail";

    //设置患者收费
    public static final String PRICE = BASE_URL + "price/";

    //查询收费
    public static final String PRICEC = BASE_URL + "price";

    //批量设置患者收费
    public static final String PRICEBATCH = BASE_URL + "price/batch";

    //分组管理(不含关注)
    public static final String DCGROUPIDX = BASE_URL + "dcgroup/idx";

    //历史记录
    public static final String MESSAGEHIS = BASE_URL + "dcMessage/his";

    //消息重发
    public static final String MESSAGERESEND = BASE_URL + "dcMessage/reSend";

    //群发消息get  群发消息发送 post
    public static final String DCMESSAGE = BASE_URL + "dcMessage";


    /**
     * --------------------------------消息 开方------------------------------
     */

    //我的消息
    public static final String SELECTCHATLIST = BASE_S_URL + "docPatientMsg/selectChatList";

    //系统消息和处方消息的最新消息
    public static final String LASTMESSAGE = BASE_S_URL + "sysMessage/lastMessage";

    //一键已读
    public static final String ONEKEYREADED = BASE_S_URL + "docPatientMsg/oneKeyReaded";

    //消息免打扰
    public static final String AVOIDANCE = BASE_S_URL + "docPatientMsg/avoidance";

    //查询系统消息1 查询处方消息2
    public static final String SELECT = BASE_S_URL + "sysMessage/select";

    //快递信息查询
    public static final String EXPRESS = BASE_S_URL + "express/select";

    //最近5条
    public static final String SELECTCASELAST = BASE_S_URL + "docCase/selectCaseLast";

    //开方记录详情查看
    public static final String SELECTDETAIL = BASE_S_URL + "docCase/selectDetail";

    //查询药剂列表
    public static final String SELECTFORMULATION = BASE_S_URL + "formulation/selectFormulation";

    //查询膏方
    public static final String SELECTINGREDIENTS = BASE_S_URL + "ingredients/selectIngredients";

    //处方记录列表查询（历史处方）
    public static final String SELECTCASEHISTROY = BASE_S_URL + "docCase/selectCaseHistroy";

    //开方记录详情(返回数据)
    public static final String SELECYBYID = BASE_S_URL + "docCase/selectById";

    //查询协定方列表
    public static final String SELECTPROMISECASE = BASE_S_URL + "promiseCase/selectPromiseCase";

    //联想 下拉   协定方或者 模板详情
    public static final String SELECTTEMPORPROMISE = BASE_S_URL + "caseTemplate/selectTempOrPromise";

    //查看banner图片
    public static final String GETIMAGES = BASE_S_URL + "appImg/getImages";

    //开方记录
    public static final String SELECTCASE = BASE_S_URL + "docCase/selectCase";

    //专方名医查询
    public static final String SELECTDOCTOR = BASE_S_URL + "specialFamousDoctor/select";

    //我的收藏
    public static final String SELECTCOLLECT = BASE_S_URL + "specialFamousDoctor/selectCollect";

    //使用专方名医
    public static final String SELECTCOLLECTADD = BASE_S_URL + "specialFamousDoctor/add";

    //专方名医收藏（收藏、取消收藏操作）
    public static final String SELECTDOCTORCOLLECT = BASE_S_URL + "specialFamousDoctor/collect";

    //查询模板列表
    public static final String CASETEMPLATE = BASE_S_URL + "caseTemplate/select";

    //模板详情
    public static final String CASETEMPLATESELECTBYID = BASE_S_URL + "caseTemplate/selectById";

    //删除模板
    public static final String TEMPLATEDELETE = BASE_S_URL + "caseTemplate/delete";

    //新建模板
    public static final String TEMPLATEADD = BASE_S_URL + "caseTemplate/add";

    //处方记录添加模板
    public static final String TEMPLATEADDBYCASE = BASE_S_URL + "caseTemplate/addByCase";

    //获取一个患者消息（进入聊天室）
    public static final String DOCPATIENTMSGREADED = BASE_S_URL + "docPatientMsg/readed";

    //根据患者id患者获取聊天室（进入聊天室）
    public static final String DOCPATIENTMSGBYCUSTOMER = BASE_S_URL + "docPatientMsg/readedByCustomerId";

    //微信发送模板消息（继续服药提醒，复诊提醒）
    public static final String WXSEND = BASE_S_URL + "wxSend/templateSend";

    //医生给患者留言
    public static final String SENDTOPATIENT = BASE_S_URL + "docPatientMsg/sendToPatient";

    //发送问诊单
    public static final String SENDDOCINTERROGATION = BASE_S_URL + "interrogation/sendDocInterrogationToCustomer";

    //删除聊天室
    public static final String DELETEDOCPATIENTMAG = BASE_S_URL + "docPatientMsg/delete";

    //清空聊天记录
    public static final String DOCPATIENTMSG=BASE_S_URL+"docPatientMsg/emptyChat";

    //结束咨询
    public static final String ENDCONSULTATION = BASE_S_URL + "priceDetail/endConsultation";

    //药品查询
    public static final String SHOPMEDSELECT=BASE_S_URL+"shopMed/select";

    //煎法下拉选择
    public static final String MEDFRYING=BASE_S_URL+"docCase/selectMedFrying";

    //病症联想下拉（智能填写）
    public static final String SELECTSUGGEST=BASE_S_URL+"caseTemplate/selectSuggest";

    //拍方上传
    public static final String SAVEIMAGE=BASE_S_URL+"docCase/saveImage";

    //在线开方
    public static final String SAVECASE=BASE_S_URL+"docCase/saveCase";

    //患者查询 按名字 模糊
    public static final String FINDBYNAME=BASE_S_URL+"customer/findByName";

    //发送处方给患者
    public static final String SENDCASE=BASE_S_URL+"docCase/sendCase";


    /**
     * -----------------------------------HTML-----------------------------
     */
    //法律声明
    public static final String USER_INSTRUCTIONS = BASE_SERVER + "instructions.html";

    //使用帮助
    public static final String USER_HELP = BASE_SERVER + "help.html";
}

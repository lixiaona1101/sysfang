package com.jiuhao.jhjk.Receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.jiuhao.jhjk.APP.ConfigKeys;
import com.jiuhao.jhjk.activity.MainActivity;
import com.jiuhao.jhjk.activity.patient.DialogueActivity;
import com.jiuhao.jhjk.activity.patient.QuestionAnswerActivity;
import com.jiuhao.jhjk.bean.AnswerBean;
import com.jiuhao.jhjk.bean.CustomerBean;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.jiuhao.jhjk.utils.net.Json;
import com.jiuhao.jhjk.utils.net.OkHttpUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * 1.接入极光推送
 * 2.正常的接收到后台的消息
 * 3.处理推送过来的消息逻辑
 *
 * 1507bfd3f7b03692148
 *
 * 1507bfd3f7ac90572bf
 */
public class JPushMessageReceiverEx extends JPushMessageReceiver{
    private static final String TAG = "PushMessageReceiver";
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG,"[onMessage] "+customMessage);
        processCustomMessage(context,customMessage);
    }
    /**
     *    // 点赞
     *     public static final Integer LIKE_TYPE = 1001;
     *     // 评论
     *     public static final Integer COMMENT_TYPE = 1002;
     *     // 留言message_type
     *     public static final Integer MESSAGE_TYPE = 1003;
     *     // 审核通知
     *     public static final Integer AUTH_TYPE = 1004;
     *     // 积分兑换
     *     public static final Integer SCORE_TYPE = 1005;
     *     // 支付成功积分通知
     *     public static final Integer PAY_SCORE_TYPE = 1006;
     *     // 订单支付成功通知
     *     public static final Integer ORDER_SCORE_TYPE = 1007;
     *     // 病历获取通知
     *     public static final Integer CASE_SUCCESS = 1008;
     *     // 合价成功通知
     *     public static final Integer PRICE_SUCCESS = 1009;
     *     //问诊单回复
     *     public static final Integer INTERROGATION_REPLY = 1010;
     *     // 患者关注成功通知
     *     public static final Integer DCRELATION_SUCCESS = 1011;
     *     // 结束咨询
     *     public static final Integer FINISH_CONSULTATION = 1012;
     *     聊天室消息是1003
     *     问诊单回复是1010
     *     问诊单回复  type 1010，
     *     detail_id 患者id ，
     *     chat_id  回调问题的批次
     * @param context
     * @param message
     */
    //打开消息通知
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        Log.e(TAG,"[onNotifyMessageOpened] "+message);

        String notificationExtras=message.notificationExtras;
        ArrayList<String> stringArrayList = new ArrayList<>();
//        {"chat_id":"175","detail_id":200000014,"type":1003}
        String replace = notificationExtras.replace("{", "");
        String replace1 = replace.replace("}", "");
        String replace2 = replace1.replace("\"", "");
        String[] split = replace2.split(",");
        for (int i = 0; i < split.length; i++) {
            String strings=split[i];
            String[] split1 = strings.split(":");
            for (int j = 0; j < split1.length; j++) {
                if(j%2==1){
                   String strings1= split1[j];
                    stringArrayList.add(strings1);
                }
            }
        }

//        类型  type 1010，stringArrayList.get(2)
//     *     detail_id 患者id ，stringArrayList.get(1)
//     *     chat_id  回调问题的批次 stringArrayList.get(0)

        if(stringArrayList.get(2).contains("1001")){//点赞

        }else if(stringArrayList.get(2).contains("1002")){//评论

        }else if(stringArrayList.get(2).contains("1003")){//留言
            getData(stringArrayList.get(1),context);
        }else if(stringArrayList.get(2).contains("1004")){//审核通知

        }else if(stringArrayList.get(2).contains("1005")){//积分兑换

        }else if(stringArrayList.get(2).contains("1006")){//支付成功积分通知

        }else if(stringArrayList.get(2).contains("1007")){//订单支付成功通知

        }else if(stringArrayList.get(2).contains("1008")){ //病历获取通知

        }else if(stringArrayList.get(2).contains("1009")){//合价成功通知

        }else if(stringArrayList.get(2).contains("1010")){//问诊单回复
            getQuestionData(stringArrayList.get(0),stringArrayList.get(1),context);
        }else if(stringArrayList.get(2).contains("1011")){//患者关注成功通知

        }else if(stringArrayList.get(2).contains("1012")){//结束咨询

        }
    }

    private void getQuestionData(String s,String personId,Context context) {
        String url =ConfigKeys.ANSWER+"?batchNO="+s;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                AnswerBean answerBean = Json.parseObj(response, AnswerBean.class);
                Intent intent = new Intent(context, QuestionAnswerActivity.class);
                intent.putExtra("answerBean",answerBean);//问诊单答案
                intent.putExtra("personId",personId);//患者id
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }

    public void getData(String id,Context context) {
        String url = ConfigKeys.CUSTOMER + "?id=" + id;
        OkHttpUtils.get(url, null, new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(int code, String response) {
                Logger.e(response);
                CustomerBean customerBean = Json.parseObj(response, CustomerBean.class);

                Intent intent = new Intent(context, DialogueActivity.class);
                intent.putExtra("nickName",customerBean.getUserName());//患者姓名
                intent.putExtra("id", customerBean.getId());//患者id
                intent.putExtra("img", customerBean.getAvatar());//患者头像
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);

            }

            @Override
            public void onFailure(int code, Exception e) {
                Logger.e(e.getMessage());
                ToastUtils.show(e.getMessage());
            }
        });
    }
    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if(nActionExtra==null){
            Log.d(TAG,"ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }


    //接收到消息的时候处理逻辑
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        Log.e(TAG,"[onNotifyMessageArrived] "+message);
    }

    //关于解除通知消息
    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        Log.e(TAG,"[onNotifyMessageDismiss] "+message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG,"[onRegister] "+registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e(TAG,"[onConnected] "+isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG,"[onCommandResult] "+cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context,JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    //发送消息到main页面
    private void processCustomMessage(Context context, CustomMessage customMessage) {
        if (MainActivity.isForeground) {
            String message = customMessage.message;
            String extras = customMessage.extra;
            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
            if (!ExampleUtil.isEmpty(extras)) {
                try {
                    JSONObject extraJson = new JSONObject(extras);
                    if (extraJson.length() > 0) {
                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
                    }
                } catch (JSONException e) {

                }

            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
        }
    }
}

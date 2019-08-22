package com.jiuhao.jhjk.wechat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.jiuhao.jhjk.APP.App;
import com.jiuhao.jhjk.APP.WeChat;
import com.jiuhao.jhjk.utils.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * Created by Administrator on 2018/5/14.
 */

@SuppressWarnings("unused")
public class JHJKWeChat {

    static final String APP_ID = new WeChat().getAppId();
    static final String APP_SECRET = new WeChat().getAppSecret();
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final JHJKWeChat INSTANCE = new JHJKWeChat();
    }

    public static JHJKWeChat getInstance() {
        return Holder.INSTANCE;
    }

    private JHJKWeChat() {
        WXAPI = WXAPIFactory.createWXAPI(App.getContext(), APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public JHJKWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);

    }


    public final void shareImg(final int type, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;
                try {
                    Bitmap bmp = BitmapFactory.decodeStream(new URL(url).openStream());

                    WXImageObject imgObj = new WXImageObject(bmp);
                    WXMediaMessage msg = new WXMediaMessage();
                    msg.mediaObject = imgObj;

                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                    bmp.recycle();
                    msg.thumbData = bmpToByteArray(thumbBmp, true);

                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = buildTransaction("img");
                    req.message = msg;
                    req.scene = type == 1 ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

                    WXAPI.sendReq(req);

                } catch (Exception e) {
                    ToastUtils.showLong(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public final void shareUrl(final int type, final String title, final String desc, final String webUrl, final String imgUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //初始化一个WXWebpageObject，填写url
                try {
                    WXWebpageObject webpage = new WXWebpageObject();
                    webpage.webpageUrl = webUrl;
                    Bitmap bmp = BitmapFactory.decodeStream(new URL(imgUrl).openStream());
                    //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
                    WXMediaMessage msg = new WXMediaMessage(webpage);
                    msg.title = title;
                    msg.description = desc;
                    Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                    bmp.recycle();
                    msg.thumbData = bmpToByteArray(thumbBmp, true);

                    //构造一个Req
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = buildTransaction("webpage");
                    req.message = msg;
                    req.scene = type == 1 ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                    //调用api接口，发送数据到微信
                    WXAPI.sendReq(req);
                } catch (Exception e) {
                    ToastUtils.showLong(e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 获取分享类别
     *
     * @param type
     * @return
     */
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                // F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }


    /**
     * 微信分享图片尺寸
     */
    public static class bitmapOption {
        private static int bitmapWith = 150;
        private static int bitmapHeight = 150;

        public static int getBitmapWith() {
            return bitmapWith;
        }

        public void setBitmapWith(int bitmapWith) {
            this.bitmapWith = bitmapWith;
        }

        public static int getBitmapHeight() {
            return bitmapHeight;
        }

        public void setBitmapHeight(int bitmapHeight) {
            this.bitmapHeight = bitmapHeight;
        }

    }


}

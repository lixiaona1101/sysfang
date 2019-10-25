package com.jiuhao.jhjk.Receiver;


import cn.jpush.android.service.WakedResultReceiver;

public class WakedResultReceiverEx extends WakedResultReceiver {

    /**
     * wakeType值	拉起方式
     * 1	        START_SERVICE
     * 2	        BIND_SERVICE
     * 4	        CONTENTPROVIDER
     */
    @Override
    public void onWake(int i) {
        super.onWake(i);
    }
}

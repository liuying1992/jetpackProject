package com.liuying.jetpackproject.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

/**
 * 自定义LiveData
 */
public class CustomLiveData extends LiveData<String> {
    private Context mContext = null;
    static CustomLiveData customLiveData;
    private NetWorkReceiver netWorkReceiver;
    private IntentFilter intentFilter;

    public CustomLiveData(Context context) {
        mContext = context;
        netWorkReceiver = new NetWorkReceiver();
        intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }


    public static CustomLiveData getInstance(Context context) {
        if (customLiveData == null) {
            synchronized (CustomLiveData.class) {
                if (customLiveData == null) {
                    customLiveData = new CustomLiveData(context);
                }
            }
        }
        return customLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        mContext.registerReceiver(netWorkReceiver, intentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        mContext.unregisterReceiver(netWorkReceiver);
    }

    private static class NetWorkReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            //通知更新数据
            getInstance(context).postValue((activeNetwork != null && activeNetwork.isAvailable()) ? "网络已连接" : "网络已断开");
        }
    }
}

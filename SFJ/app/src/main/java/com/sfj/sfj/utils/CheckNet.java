package com.sfj.sfj.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by new on 2018/3/9.
 */

public class CheckNet {
    public static final int TYPE_NONE = -1;
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_WIFI = 1;

    public static int getNetType(Activity activity){
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {//没网
            ToastUtils.showShortToast("亲，网络断开了");
            return TYPE_NONE;
        }

        int type = activeNetworkInfo.getType();
        switch (type) {
            case ConnectivityManager.TYPE_MOBILE://移动数据
                return TYPE_MOBILE;
            case ConnectivityManager.TYPE_WIFI://WIFI
                return TYPE_WIFI;
            default:
                break;
        }
        return TYPE_NONE;
    }
}

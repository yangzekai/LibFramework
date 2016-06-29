package com.movitech.library.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者：Yann.Yang on 2016/6/24 16:11
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：判断网络是否有效工具类
 */
public class NetworkUtils {
    /**
     * 判断网络是否链接成功
     * @param context
     * @return
     */
    public static boolean isNetWorkConnected(Context context){
        if (context == null)
            return false;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    /**
     * 是否是WIFI连接
     * @param context
     * @return
     */
    public static boolean isWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 获取网络信息
     * @param context
     * @return
     */
    public static String getNetworkInfo(Context context) {
        if (context == null)
            return "";
        StringBuilder builder = new StringBuilder();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivity.getActiveNetworkInfo();
        builder.append(info == null ? "" : info.toString());
        return builder.toString();
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }
}

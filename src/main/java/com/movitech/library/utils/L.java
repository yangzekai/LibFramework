package com.movitech.library.utils;

import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * 作者：Yann.Yang on 2016/6/23 11:13
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：Log
 */
public class L {
    public static void init(String YOUR_TAG) {
        Logger
                .init(YOUR_TAG)                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2); //default AndroidLogAdapter
    }

    public static void d(String tag, String message) {
        Logger.t(tag).d(message);
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void e(String exception) {
        Logger.e(exception, "Error");
    }

    public static void json(String json) {
        Logger.json(json);
    }
}

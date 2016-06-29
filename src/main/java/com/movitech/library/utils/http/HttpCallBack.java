package com.movitech.library.utils.http;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 作者：Yann.Yang on 2016/6/29 10:22
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：请求的反馈 内容
 */
public abstract class HttpCallBack {
    private static final String TAG = HttpCallBack.class.getSimpleName();

    /**
     * 普通报错
     */
    public final static int ERROR_NORMAL = 0;
    /**
     * token 出现问题的
     */
    public final static int ERROR_TOKEN = 1;
    /**
     * 已经删除了的
     */
    public final static int ERROR_DELETED = 2;

    protected Context mContext;

    public HttpCallBack(Context context) {
        mContext = context;
    }

    public abstract void onError(String errorMessage, int type, Exception e);
    public abstract void onSuccess(String response);
}

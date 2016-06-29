package com.movitech.library;

import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.movitech.library.model.HttpResult;
import com.movitech.library.utils.Utils;
import com.movitech.library.utils.http.HttpCallBack;
import com.movitech.library.utils.http.MEDIATYPE;
import com.movitech.library.utils.http.okhttp.OkHttpUtils;
import com.movitech.library.utils.http.okhttp.callback.StringCallback;
import com.movitech.library.utils.http.okhttp.request.RequestCall;
import com.movitech.library.utils.json.JsonValidator;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 作者：Yann.Yang on 2016/6/27 17:50
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：Http接口类
 * GET POST
 */
public class MTHttp {
    public static final String TAG = "MTHttp";

    /**
     * 默认的请求时间
     */
    public static final long DEFAULT_TIME_OUT = OkHttpUtils.DEFAULT_MILLISECONDS;

    public static Map<String, String> headers;

    static {
        headers = new HashMap<>();
        headers.put("RecContentType", "json");
        headers.put("content-type", "application/json");
        headers.put("Accept", "application/json");
    }
    public static void get(String url, final HttpCallBack callBack) {
        get(url, DEFAULT_TIME_OUT, callBack);
    }

    public static void get(String url, long timeout, final HttpCallBack callBack) {
        get(TAG, null, url, timeout, callBack);
    }

    public static void get(String debugTag, String url, final HttpCallBack callBack) {
        get(debugTag, null, url, DEFAULT_TIME_OUT, callBack);
    }

    public static void get(String debugTag, Object httpTag, String url, long timeout, final HttpCallBack callBack) {
        final String tag = TextUtils.isEmpty(debugTag) ? TAG : debugTag;
        Log.i(tag, "================get================ ");
        Log.i(tag, "get url = " + url);
        Log.i(tag, "get headers = " + headers);
        Log.i(tag, "get timeout = " + timeout);

        RequestCall call = OkHttpUtils
                .get()
                .url(url)
                .headers(headers)
                .tag(httpTag)
                .build();

        if (timeout > 0 && timeout != DEFAULT_TIME_OUT) {
            call.connTimeOut(timeout);
            call.readTimeOut(timeout);
            call.writeTimeOut(timeout);
        }

        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Logger.d("e = " + e.toString());
                if (callBack != null) {
                    callBack.onError(null, HttpCallBack.ERROR_NORMAL, e);
                }
            }

            @Override
            public void onResponse(String response) {
                Log.i(tag, "result = " + response);
                if (callBack == null) {
                    return;
                }

                // 检测json是否有效！如果无效不进行返回
                JsonValidator validator = new JsonValidator();
                boolean valid = validator.validate(response);
                if (!valid) {
                    callBack.onError(Utils.EMPTY, 0, new JSONException("error"));
                    return;
                }

                HttpResult result = JSON.parseObject(response, HttpResult.class);
                if (!result.result) {
                    callBack.onError(result.message, result.type, null);
                    return;
                }

                callBack.onSuccess(result.value);
            }
        });
    }

    /**
     * Post请求 Key Values
     */
    public static void post(String url, String params, final HttpCallBack callBack) {
        post(url, params, DEFAULT_TIME_OUT, callBack);
    }


    public static void post(String url, String params, long timeout, final HttpCallBack callBack) {
        post(TAG, null, url, params, timeout, callBack);
    }

    public static void post(String debugTag, String url, String params, final HttpCallBack callBack) {
        post(debugTag, null, url, params, DEFAULT_TIME_OUT, callBack);
    }

    /**
     * @param debugTag 打印log使用的
     * @param httpTag  取消请求使用的
     * @param url      请求的地址
     * @param params   请求的参数
     * @param timeout  超时时间
     * @param callBack 回调
     */
    public static void post(String debugTag, Object httpTag, String url, String params, long timeout, final HttpCallBack callBack) {
        final String tag = TextUtils.isEmpty(debugTag) ? TAG : debugTag;

        Log.i(tag, "================post================ ");
        Log.i(tag, "post url = " + url);
        Log.i(tag, "param = " + params);
        Log.i(tag, "header = " + headers);
        Log.i(tag, "timeout = " + timeout);

        RequestCall call = OkHttpUtils.postString()
                .url(url)
                .content(params)
                .headers(headers)
                .mediaType(MEDIATYPE.JSON)
                .tag(httpTag)
                .build();

        if (timeout > 0 && timeout != DEFAULT_TIME_OUT) {
            call.connTimeOut(timeout);
            call.readTimeOut(timeout);
            call.writeTimeOut(timeout);
        }

        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(tag, "onError result = " + call);
                Log.i(tag, "onError Exception e = " + e);

                if (callBack != null) {
                    callBack.onError(null, HttpCallBack.ERROR_NORMAL, e);
                }
            }

            @Override
            public void onResponse(String response) {
                Log.i(tag, "result = " + response);
                if (callBack == null) {
                    return;
                }

                // 检测json是否有效！如果无效不进行返回
                JsonValidator validator = new JsonValidator();
                boolean valid = validator.validate(response);
                if (!valid) {
                    callBack.onError(Utils.EMPTY, 0, new JSONException("error"));
                    return;
                }

                HttpResult result = JSON.parseObject(response, HttpResult.class);
                if (!result.result) {
                    callBack.onError(result.message, result.type, null);
                    return;
                }

                callBack.onSuccess(result.value);
            }
        });
    }


    /**
     * Post请求 Key Values
     */
    public static void post(String url, Map<String, String> params, final HttpCallBack callBack) {
        post(url, params, DEFAULT_TIME_OUT, callBack);
    }


    public static void post(String url, Map<String, String> params, long timeout, final HttpCallBack callBack) {
        post(TAG, null, url, params, timeout, callBack);
    }

    public static void post(String debugTag, String url, Map<String, String> params, final HttpCallBack callBack) {
        post(debugTag, null, url, params, DEFAULT_TIME_OUT, callBack);
    }

    /**
     * @param debugTag 打印log使用的
     * @param httpTag  取消请求使用的
     * @param url      请求的地址
     * @param params   请求的参数
     * @param timeout  超时时间
     * @param callBack 回调
     */
    public static void post(String debugTag, Object httpTag, String url, Map<String, String> params, long timeout, final HttpCallBack callBack) {
        final String tag = TextUtils.isEmpty(debugTag) ? TAG : debugTag;

        Log.i(tag, "================post================ ");
        Log.i(tag, "post url = " + url);
        Log.i(tag, "param = " + params);
        Log.i(tag, "header = " + headers);
        Log.i(tag, "timeout = " + timeout);

        RequestCall call = OkHttpUtils.post()
                .url(url)
                .params(params)
                .tag(httpTag)
                .build();

        if (timeout > 0 && timeout != DEFAULT_TIME_OUT) {
            call.connTimeOut(timeout);
            call.readTimeOut(timeout);
            call.writeTimeOut(timeout);
        }

        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(tag, "onError result = " + call);
                Log.i(tag, "onError Exception e = " + e);

                if (callBack != null) {
                    callBack.onError(null, HttpCallBack.ERROR_NORMAL, e);
                }
            }

            @Override
            public void onResponse(String response) {
                Log.i(tag, "result = " + response);
                if (callBack == null) {
                    return;
                }

                // 检测json是否有效！如果无效不进行返回
                JsonValidator validator = new JsonValidator();
                boolean valid = validator.validate(response);
                if (!valid) {
                    callBack.onError(Utils.EMPTY, 0, new JSONException("error"));
                    return;
                }

                HttpResult result = JSON.parseObject(response, HttpResult.class);
                if (!result.result) {
                    callBack.onError(result.message, result.type, null);
                    return;
                }

                callBack.onSuccess(result.value);
            }
        });
    }
}

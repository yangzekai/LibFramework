package com.movitech.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 提示信息工具类
 * Created by Nick on 2016/6/21.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showToast(Context context, CharSequence text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

}

package com.movitech.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;

/**
 * 作者：Yann.Yang on 2016/6/24 16:10
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：px 和dip之间转换方法
 */
public class DimUtil {

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int pxToDip(int px){
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static float dipToPx(float dip){
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return  (dip * scale + 0.5f);
    }


    public static int pxToSp(float px) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }

    public static int spToPx( float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}

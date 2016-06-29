package com.movitech.library;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.movitech.library.utils.glide.GlideUtils;

/**
 * 作者：Yann.Yang on 2016/6/27 17:51
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：Imageloader接口类
 */
public class MTImageLoader {

    /**
     * 加载网络库
     * @param context
     * @param imageView
     * @param url
     */
    public static void loadImage(Context context, ImageView imageView, String url) {
        GlideUtils.load(context, url, imageView);
    }

    /**
     * 加载本地库
     * @param context
     * @param imageView
     * @param res
     */
    public static void loadImage(Context context, ImageView imageView, @DrawableRes int res) {
        GlideUtils.load(context, res, imageView);
    }
}

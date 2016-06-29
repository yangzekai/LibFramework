/**
 * Copyright (C) 2015  Haiyang Yu Android Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.movitech.library.utils.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movitech.library.R;
import com.movitech.library.utils.glide.transform.GlideCircleTransform;


/**
 * 图片加载的封装
 */
public class GlideUtils {

    public static void load(Context context, String url, ImageView view) {
        load(context, url, view, true);
    }

    public static void load(Context context, String url, ImageView view, boolean isCenterCrop) {
        if (isCenterCrop) {
            Glide.with(context)
                    .load(url)
                    .crossFade()
                    .centerCrop()
                    .into(view);
        } else {
            Glide.with(context)
                    .load(url)
                    .crossFade()
                    .into(view);
        }
    }

    public static void load(Context context, @DrawableRes int url, ImageView view) {
        Glide.with(context)
                .load(url)
                .crossFade()
                .into(view);
    }

    public static void load(Context context, @DrawableRes int url, @DrawableRes int holder, ImageView view) {
        Glide.with(context)
                .load(url)
                .placeholder(holder)
                .crossFade()
                .into(view);
    }
}

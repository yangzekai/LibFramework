package com.movitech.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * 作者：Yann.Yang on 2016/6/28 17:44
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：模糊效果
 */
public class BlurUtils {
    /**
     * 使bitmap模糊化
     *
     * @param context
     * @param bm
     * @param radius
     * @return
     */
    public static Bitmap fastBlur(Context context, Bitmap bm, int radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Bitmap bitmap = bm.copy(bm.getConfig(), true);
            final RenderScript rs = RenderScript.create(context);
            final Allocation input = Allocation.createFromBitmap(rs,
                    bm, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs,
                    input.getType());
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs,
                    Element.U8_4(rs));
            script.setRadius(radius);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);

            // clean up renderscript resources
            rs.destroy();
            input.destroy();
            output.destroy();
            script.destroy();

            return bitmap;
        }
        return null;
    }
}

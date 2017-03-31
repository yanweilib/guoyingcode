package com.yw.gril.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * <code>DensityUtils</code>
 * 单位转换类 dp,px,sp
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class DensityUtils {

    private DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static float defaultDensity = 1.5f;
    private static float mDensity = DisplayMetrics.DENSITY_DEFAULT;

    public static void setDensity(float density) {
        mDensity = density;
    }

    public static float getDensity() {
        return mDensity;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        int px;
        final float scale = context.getResources().getDisplayMetrics().density;
        if (scale == 0) {
            px = (int) (dpValue * defaultDensity + 0.5f);
        } else {
            px = (int) (dpValue * scale + 0.5f);
        }
        return px;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int dp;
        if (scale == 0) {
            dp = (int) (pxValue / defaultDensity + 0.5f);
        } else {
            dp = (int) (pxValue / scale + 0.5f);
        }
        return dp;
    }

    /**
     * 从 px(像素) 的单位 转成为 sp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 从 sp 转成为 px(像素) 的单位
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}

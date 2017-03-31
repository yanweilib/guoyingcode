package com.yw.gril.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * <code>ScreenUtil</code>
 * 屏幕相关工具类
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class ScreenUtil {
    private ScreenUtil() {
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
     * 获取屏幕信息
     *
     * @param ctx
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context ctx) {
        DisplayMetrics outMetrics = ctx.getResources().getDisplayMetrics();
        return outMetrics;
    }
    /**
     * 获取屏幕宽度
     *
     * @param ctx
     * @return
     */
    public static int getScreenWidth(Context ctx) {
        int mScreenWidth = getDisplayMetrics(ctx).widthPixels;// 获取屏幕分辨率宽度
        return mScreenWidth;
    }
    /**
     * 获取屏幕高度
     *
     * @param ctx
     * @return
     */
    public static int getScreenHeight(Context ctx) {
        int mScreenHeight = getDisplayMetrics(ctx).heightPixels;// 获取屏幕分辨率宽度
        return mScreenHeight;
    }

    /**
     * 屏幕密度（0.75 / 1.0 / 1.5）
     *
     * @param ctx
     * @return
     */
    public static float getScreenDensity(Context ctx) {
        float mScreenHeight = getDisplayMetrics(ctx).density;// 获取屏幕分辨率宽度
        return mScreenHeight;
    }

    /**
     * 屏幕密度DPI（120 / 160 / 240）
     *
     * @param ctx
     * @return
     */
    public static int getScreenDensityDpi(Context ctx) {
        int mScreenHeight = getDisplayMetrics(ctx).densityDpi;// 获取屏幕分辨率宽度
        return mScreenHeight;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;

    }
}

package com.example.androidcode.Units;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 跟显示有关的工具类.比如屏幕相关的尺寸,宽高和像素和dp之间的转换等.
 * 
 */
public class DisplayUtil {

	private static float DEFAULT_DENSITY = 1.5f;
	private static float DENSITY = DisplayMetrics.DENSITY_DEFAULT;

	public static void setDensity(float density) {
		DENSITY = density;
	}

	public static float getDensity() {
		return DENSITY;
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
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		int px;
		final float scale = context.getResources().getDisplayMetrics().density;
		if (scale == 0) {
			px = (int) (dpValue * DEFAULT_DENSITY + 0.5f);
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
			dp = (int) (pxValue / DEFAULT_DENSITY + 0.5f);
		} else {
			dp = (int) (pxValue / scale + 0.5f);
		}
		return dp;
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
	 * @param context
	 * @param pxValue
     * @return
     */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 sp 的单位 转成为 px
	 * @param context
	 * @param spValue
     * @return
     */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
}

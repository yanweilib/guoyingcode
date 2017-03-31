package com.yw.gril.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * <code>ToastUtils</code>
 * Tost统一管理类
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class ToastUtils {
    private static Toast t;
    private static int duration;

    public static boolean isShow=true;

    private ToastUtils()
    {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 显示Toast
     * @param context
     * @param msg
     * @param duration
     */
    private static void makeText(Context context, CharSequence msg,int duration) {
        if (!isShow) {
            return;
        }
        if (!TextUtils.isEmpty(msg) && context != null) {
            if (ToastUtils.duration != duration) {
                if (t != null) {
                    t.cancel();
                }
                t = Toast.makeText(context, msg, duration);
            } else {
                if (t == null) {
                    t = Toast.makeText(context, msg, duration);
                } else {
                    t.setText(msg);
                }
            }
            ToastUtils.duration = duration;
            t.show();
        }
    }

    /**
     *
     * @param context
     * @param resId
     * @param duration
     */
    public static void makeText(Context context, @StringRes int resId, int duration) {
        makeText(context, context.getResources().getString(resId), duration);
    }

    /**
     * 短时间显示
     * @param context
     * @param msg
     */
    public static void makeShortText(Context context, CharSequence  msg) {
        makeText(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示
     * @param context
     * @param resId
     */
    public static void makeShortText(Context context, @StringRes int resId) {
        makeText(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示
     * @param context
     * @param msg
     */
    public static void makeLongText(Context context, CharSequence msg) {
        makeText(context, msg, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示
     * @param context
     * @param resId
     */
    public static void makeLongText(Context context, @StringRes int resId) {
        makeText(context, resId, Toast.LENGTH_LONG);
    }
}

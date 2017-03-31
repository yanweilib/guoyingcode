package com.yw.gril.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * <code>StatusbarUtil</code>
 * 状态栏工具类
 * @author YANWEI
 * @version 1.0.0
 * @see Class
 * @since 2017/3/31 18:21
 */
public class StatusbarUtil {

    /**
     * 设置状态栏
     *
     *      设置状态栏的颜色
     android:fitsSystemWindows="true"
     android:clipToPadding="true"
     由于我们要实现的是状态栏和顶部的控件是同一个颜色，同时，控件内容也不和状态栏重复，所以只要把那两行代码放到我们顶部的控件就可以了。

     //透明状态栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     //透明导航栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
     */
    public static void initSystemBar(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(activity, true);

        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        tintManager.setStatusBarTintEnabled(true);

        // 使用颜色资源c1996D0
        tintManager.setStatusBarTintResource(color);

    }

    @TargetApi(19)

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);
    }
}

package com.yw.gril.Units;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 伟 on 2016/12/12.
 * 程序打开和关闭软键盘
 */

public class SoftKeyboard {
    /**
     * 切换键盘状态
     * @param view
     */
    public static void switchInput(Context context,View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打开键盘
     * @param v
     */
    public static void openInput(Context context,View v){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 几秒后打开软键盘
     * @param view
     */
    public static void openInput(final View view, final Context context, long time){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openInput(context,view);
            }
        }, time);
    }

    /**
     * 关闭键盘
     * @param v
     */
    public static void closeInput(Context context,View v){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
    }
}

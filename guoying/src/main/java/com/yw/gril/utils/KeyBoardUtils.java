package com.yw.gril.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <code>KeyBoardUtils</code>
 * 软键盘工具类 打开，关闭，切换
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class KeyBoardUtils {

    /**
     * 切换键盘状态
     * @param view
     */
    public static void switchInput(Context context,View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 打卡软键盘
     * @param view 输入框
     * @param mContext 上下文
     */
    public static void openKeybord(Context mContext,View view )
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 在多久之后打开软键盘
     * @param view
     */
    public static void openInput(final View view, final Context context, long time){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openKeybord(context,view);
            }
        }, time);
    }

    /**
     * 关闭软键盘
     * @param view  输入框
     * @param mContext 上下文
     */
    public static void closeKeybord(Context mContext,View view)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

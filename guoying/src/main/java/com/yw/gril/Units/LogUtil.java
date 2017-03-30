package com.example.androidcode.Units;

import android.util.Log;

/**
 * Created by 伟 on 2016/5/19.
 */
public class LogUtil {
    public static int LOG_LEVEL=6;
    public final static int ERROR=1;
    public final static int WARN=2;
    public final static int INFO=3;
    public final static int DEBUG=4;
    public final static int VERBOS=5;

    /**
     * 错误信息
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg){
        if(LOG_LEVEL>ERROR) {
            Log.e(tag, msg);
        }
    }

    /**
     * 警告信息
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg){
        if(LOG_LEVEL>WARN)
            Log.w(tag, msg);
    }

    /**
     * 一般提示性信息
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg){
        if(LOG_LEVEL>INFO) {
            Log.i(tag, msg);
        }
    }

    /**
     * 输出debug调试
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg){
        if(LOG_LEVEL>DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 任何信息
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg){
        if(LOG_LEVEL>VERBOS) {
            Log.v(tag, msg);
        }
    }
}

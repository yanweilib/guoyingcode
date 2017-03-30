package com.example.androidcode.Units;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaoxiaoc on 2016/7/30.
 */
public class SharePreferenceUtil {

    public static void saveStringData(Context context, String key, String val){
        SharedPreferences spf = context.getSharedPreferences(AppManger.VTAIQIU, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString(key,val);
        editor.commit();
    }

    public static String getStringData(Context context, String key){
        SharedPreferences spf= context.getSharedPreferences(AppManger.VTAIQIU, Context.MODE_PRIVATE);
        return spf.getString(key,"");
    }

    public static void saveBooleanData(Context context, String key, boolean val){
        SharedPreferences spf = context.getSharedPreferences(AppManger.VTAIQIU, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putBoolean(key,val);
        editor.commit();
    }

    public static boolean getBooleanData(Context context, String key){
        SharedPreferences spf= context.getSharedPreferences(AppManger.VTAIQIU, Context.MODE_PRIVATE);
        return spf.getBoolean(key,false);
    }
}

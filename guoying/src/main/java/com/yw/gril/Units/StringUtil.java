package com.example.androidcode.Units;

/**
 * Created by xiaoxiaoc on 2016/9/8.
 */
public class StringUtil {

    public static boolean isNotEmpty(String str){
        return !(str==null||"".equals(str));
    }
    public static boolean isEmpty(String str){
        return str==null||"".equals(str);
    }
}

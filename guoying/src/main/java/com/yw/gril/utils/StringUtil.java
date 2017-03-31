package com.yw.gril.utils;

/**
 * <code>StringUtil</code>
 * String工具类
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str==null||"".equals(str);
    }

    public static String getString(String str){
        if(str==null)
        {
            str="";
        }
        if("null".equals(str))
        {
            str="";
        }
        return str;
    }
}

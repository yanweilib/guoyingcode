package com.yw.gril.utils;

import android.os.Handler;
import android.os.Message;

import java.io.CharArrayReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <code>DateTimeUtils</code>
 * 时间处理
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class DateTimeUtils {

    /**
     * timeToDate
     * @param strTime
     * @param strFormat
     * @return
     */
    public static Date timeToDate(String strTime,String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.CHINA);
        try {
            return format.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * timeToString
     * @param millis
     * @param strFormat
     * @return
     */
    public static String timeToString(long millis,String strFormat)
    {
        SimpleDateFormat sdf= new SimpleDateFormat(strFormat);
        java.util.Date dt = new Date(millis);
        return sdf.format(dt);
    }

    /**
     * timeToString
     * @param strTime
     * @param strFormat
     * @return
     */
    public static String timeToString(String strTime,String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.CHINA);
        try {
            return timeToString(format.parse(strTime).getTime(),strFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.androidcode.Units;

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
 * Created by 伟 on 2016/12/12.
 */

public class DateTimeUtil {
    public static final String TIME_FORMAT_1="yyyy-MM-dd";
    public static final String TIME_FORMAT_2="yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_3="yyyy-MM-dd HH:mm";

    /**
     * 获取北京时间
     *
     * @return
     */
    public void getDefaultDate(final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); // 时区设置
                URL url = null;//取得资源对象
                URLConnection uc = null;
                try {
                    url = new URL("http://www.bjtime.cn");
                    uc = url.openConnection();//生成连接对象
                    uc.connect(); //发出连接
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                long ld = uc.getDate(); //取得网站日期时间（时间戳）
                Date date = new Date(ld); //转换为标准时间对象
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制,12小时制则HH为hh
                String UserTime = sdformat.format(date);
                //通知主线程更新
                int oper = 5;
                Message msg = new Message();
                msg.what = oper;
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * string时间转换成date
     *
     * @return
     */
    public static Date StringToDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * string时间转换成yyyy-MM-dd格式
     *
     * @return
     */
    public static String StringTimeChange(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            return LongTimeChange(format.parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * long时间转换成yyyy.MM.dd格式
     *
     * @return
     */
    public static String LongTimeChange(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String date = format.format(new Date(time));
        return date;
    }

    /**
     * long时间转换成yyyy-MM-dd HH:mm:ss格式
     *
     * @return
     */
    public static String LongTimeChangeTwo(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String date = format.format(new Date(time));
        return date;
    }

    public static String getDateFormatNoYear(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm", Locale.CHINA);
        try {
            Date date = format.parse(time);
            String format2 = formatter.format(date);
            return format2;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取日期
     * @return
     */
    public static Calendar getCalendarFromString(String strFormat,String time,int type)
    {
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.CHINA);

        Date  pushDate = null;
        try {
            pushDate = format.parse(time);//eg:"2014-12-21 05:49:00"
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar  calendar= Calendar.getInstance();
        calendar.setTime(pushDate);

        //使用
//        calendar.get(Calendar.YEAR);
        return calendar;
    }

    public static String GetStringFromLong(long millis)
    {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = new Date(millis);
        return sdf.format(dt);
    }

    public static String GetStringFromLong(long millis,String strFormat)
    {
        SimpleDateFormat sdf= new SimpleDateFormat(strFormat);
        java.util.Date dt = new Date(millis);
        return sdf.format(dt);
    }
}

package com.yw.gril.Units;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yw.gril.Units.StringUtil.isEmpty;

/**
 * Created by 伟 on 2016/12/12.
 */

public class Utils {
    private static long lastClickTime;
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 密码的显示与隐藏
     */
    private static boolean isHidden=true;
    public static void passwordHiddenShow(TextView textView)
    {

        if(isHidden)
        {
            textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else
        {
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        isHidden=!isHidden;
    }

    /**
     * 去掉url中的空格
     *
     * @param data
     * @return
     */
    public static String ChangeKong(String data) {
        if (data.contains(" ")) {
            data = data.replace(" ", "%20");
        }
        return data;
    }

    public static void extractUrl2Link(TextView v) {
        Pattern wikiWordMatcher = Pattern
                .compile("((http[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)");
        String mentionsScheme = String.format("%s/?%s=", "llkj://url", "url");
        Linkify.addLinks(v, wikiWordMatcher, mentionsScheme);

    }

    /**
     * 掉起打电话
     * @param context
     * @param phone
     */
    public static void takePhone1(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 掉起打电话
     * @param context
     * @param phone
     */
    public static void takePhone2(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context
     * @return 当前应用的版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证邮箱输入是否合法
     *
     * @param strEmail
     * @return
     */
    public static boolean isEmail(String strEmail) {
        // String strPattern =
        // "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 验证手机号
     * @param phoneNumber
     * @return
     */
    public static boolean validatePhone(String phoneNumber){
        if(phoneNumber==null)
        {
            return false;
        }
        if(phoneNumber.trim().equals(""))
        {
            return false;
        }
        Pattern p = Pattern.compile("^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    /**
     * 验证密码格式
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        boolean isValid = false;
        String expression = "^[A-Za-z0-9]{6,20}$";
        CharSequence inputStr = password;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 验身份证号格式
     *
     * @param idcard
     * @return
     */
    public static boolean isIDCard(String idcard) {
        boolean isValid = false;
        // String expression =
        // "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        String expression = "^\\d{17}[0-9a-zA-Z]|\\d{14}[0-9a-zA-Z]$";
        CharSequence inputStr = idcard;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 判断数组非空
     *
     * @author lvliuyan
     * */

    public static boolean noArrayNull(String... str) {
        boolean isNull = true;
        for (int i = 0; i < str.length; i++) {
            if (isEmpty(str[i])) {
                isNull = false;
            }
        }
        return isNull;
    }
}

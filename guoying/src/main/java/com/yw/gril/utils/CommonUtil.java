package com.yw.gril.utils;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>CommonUtil</code>
 * 公共工具类
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class CommonUtil {

    private static long lastClickTime;//最后点击的时间

    /**
     * 避免短时间内重复点击
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 密码的显示与隐藏
     */
    private static boolean isHidden = true;

    /**
     * 密码的显示与隐藏
     *
     * @param textView
     */
    public static void passwordHiddenShow(TextView textView) {
        if (isHidden) {
            textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        isHidden = !isHidden;
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
     *
     * @param phoneNumber
     * @return
     */
    public static boolean validatePhone(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        if (phoneNumber.trim().equals("")) {
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
     */

    public static boolean noArrayNull(String... str) {
        boolean isNull = true;
        for (int i = 0; i < str.length; i++) {
            String mStr = str[i];
            if (mStr != null && !"".equals(mStr)) {
                isNull = false;
            }
        }
        return isNull;
    }
}

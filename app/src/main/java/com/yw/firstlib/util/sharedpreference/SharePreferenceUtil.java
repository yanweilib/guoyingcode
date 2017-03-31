package com.yw.firstlib.util.sharedpreference;

import android.content.Context;

import com.yw.gril.utils.SPUtil;

/**
 * <code>SharePreferenceUtil</code>
 *
 * @author YANWEI
 * @version 1.0.0
 * @see Class
 * @since 2017/4/1 0:02
 */
public class SharePreferenceUtil extends SPUtil {
    /**
     * 保存用户信息
     * @param context
     * @param resultBean
     */
    public static void saveUserDate(Context context, LoginParentModel.ReturnResultBean resultBean)
    {
        put(context,SharePreferenceKeys.USER_AUTOLOGIN,true);
        put(context,SharePreferenceKeys.USER_REALNAME,resultBean.getRealname());
        put(context,SharePreferenceKeys.USER_USERNAME,resultBean.getUsername());
        put(context,SharePreferenceKeys.USER_USERNICKNAME,resultBean.getNickname());
        put(context,SharePreferenceKeys.USER_ID,resultBean.getId()+"");
        put(context,SharePreferenceKeys.USER_USERPASSWORD,resultBean.getPassword());
        put(context,SharePreferenceKeys.USER_BITTHDAY,resultBean.getBirthday()+"");
        put(context,SharePreferenceKeys.USER_SEX,resultBean.getSex()+"");
        put(context,SharePreferenceKeys.USER_PROVINCE,resultBean.getProvince());
        put(context,SharePreferenceKeys.USER_CITY,resultBean.getCity());
        put(context,SharePreferenceKeys.USER_PHONE,resultBean.getTel());
        put(context,SharePreferenceKeys.USER_HEADIMG,resultBean.getNickimg());
        put(context,SharePreferenceKeys.USERNAME_KEY_BEFORE,resultBean.getUsername());
    }

    /**
     * 获取用户信息
     * @param context
     */
    public static LoginParentModel.ReturnResultBean getUserDate(Context context)
    {
        LoginParentModel.ReturnResultBean resultBean=new LoginParentModel.ReturnResultBean();
        resultBean.setUsername((String) get(context,SharePreferenceKeys.USER_USERNAME,""));
        resultBean.setRealname((String) get(context,SharePreferenceKeys.USER_REALNAME,""));
        resultBean.setNickname((String) get(context,SharePreferenceKeys.USER_USERNICKNAME,""));
        resultBean.setId((String) get(context,SharePreferenceKeys.USER_ID,0));
        resultBean.setPassword((String) get(context,SharePreferenceKeys.USER_USERPASSWORD,""));
        resultBean.setBirthday((String) get(context,SharePreferenceKeys.USER_BITTHDAY,""));
        resultBean.setSex((String) get(context,SharePreferenceKeys.USER_SEX,""));
        resultBean.setProvince((String) get(context,SharePreferenceKeys.USER_PROVINCE,""));
        resultBean.setCity((String) get(context,SharePreferenceKeys.USER_CITY,""));
        resultBean.setTel((String) get(context,SharePreferenceKeys.USER_PHONE,""));
        resultBean.setNickimg((String) get(context,SharePreferenceKeys.USER_HEADIMG,""));
        resultBean.setAutologin((Boolean) get(context,SharePreferenceKeys.USER_AUTOLOGIN,""));
        return resultBean;
    }
}

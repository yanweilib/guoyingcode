package com.example.androidcode.Units;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 用户信息管理
 * 
 * @author lv.ly
 * 
 * */
public class UserInfoUtil {
	public SharedPreferences sp = null;
	public Editor editor;
	private static UserInfoUtil userInfoUtil = null;
	public static String SP_NAME = "user_info_sp";
	private UserInfoBean userinfo;

	public static UserInfoUtil init(Context context) {
		if (userInfoUtil == null) {
			userInfoUtil = new UserInfoUtil(context, SP_NAME,
					Context.MODE_PRIVATE);
		}
		return userInfoUtil;
	}

	private UserInfoUtil(Context context, String sp_name, int mode) {
		super();
		sp = context.getSharedPreferences(sp_name, mode);
		editor = sp.edit();
	}

	public boolean getIsLogin(Context context) {
		String uid = sp.getString("userId", null);
		if (StringUtil.isEmpty(uid)) {
			return false;
		}
		return true;
	}

	public void setIsFirstOpen(Context context, boolean state) {
		editor.putBoolean("is_first", state);
		editor.commit();
	}

	public boolean getIsFirstOpen(Context context) {
		return sp.getBoolean("is_first", true);
	}
	
	public void setIsFirstDoor(Context context, boolean state) {
		editor.putBoolean("is_first_door", state);
		editor.commit();
	}

	public boolean getIsFirstDoor(Context context) {
		return sp.getBoolean("is_first_door", true);
	}

	public UserInfoBean getUserInfo() {
		if (userinfo == null) {
			userinfo = new UserInfoBean();
		}

		return userinfo;
	}

	public void putValue(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}
	
	public void putValues(HashMap<String,String> values){
		Iterator<String> iterator = values.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			editor.putString(key,values.get(key));
		}
		editor.commit();
	}

	public void exit() {
		boolean isfirst = sp.getBoolean("is_first", true);
		String isAcceptMessage = sp.getString("isAcceptMessage", "0");
		editor.clear().commit();
		editor.putBoolean("is_first", isfirst).commit();
		editor.putString("isAcceptMessage", isAcceptMessage);
	}

	class UserInfoBean
	{

	}
}

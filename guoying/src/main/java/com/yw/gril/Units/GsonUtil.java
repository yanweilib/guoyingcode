package com.example.androidcode.Units;

import com.google.gson.Gson;

public class GsonUtil {

	public static <T> T GsonToBean(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		return gson.fromJson(gsonString, cls);
	}

}
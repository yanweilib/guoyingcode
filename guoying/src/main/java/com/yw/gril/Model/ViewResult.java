package com.example.androidcode.Model;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ViewResult<T> {
	public final static String STATE_SUCCESS="1";
	public final static String STATE_ERROR="0";

	private String state;
	private String showMessage;
	private int rowsCount;
	private int pageIndex;
	private int pageCount;
	private List<T> rowsData;
	private T returnResult;
	public int getRowsCount() {
		return rowsCount;
	}
	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getRowsData() {
		return rowsData;
	}
	public void setRowsData(List<T> rowsData) {
		this.rowsData = rowsData;
	}
	public T getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(T returnResult) {
		this.returnResult = returnResult;
	}
	public String getShowMessage() {
		return showMessage;
	}
	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public static ViewResult fromJson(String json, Class clazz) {
		Gson gson = new Gson();
		Type objectType = type(ViewResult.class, clazz);
		return gson.fromJson(json, objectType);
	}

	public String toJson(Class<T> clazz) {
		Gson gson = new Gson();
		Type objectType = type(ViewResult.class, clazz);
		return gson.toJson(this, objectType);
	}

	public static ParameterizedType type(final Class raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}
}

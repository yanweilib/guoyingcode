package com.example.androidcode.Model;

public class ViewResultState {
	public final static String STATE_SUCCESS="1";
	public final static String STATE_ERROR="0";

	private String state;
	private String context;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}

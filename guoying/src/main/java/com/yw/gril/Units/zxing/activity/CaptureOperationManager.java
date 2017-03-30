package com.example.androidcode.Units.zxing.activity;

import android.content.Context;

import com.google.gson.Gson;

/**
 * Created by 伟 on 2016/6/27.
 */
public abstract class CaptureOperationManager {
    protected Gson gson=new Gson();
    protected Context context;

    public abstract void start(Context context, String result) ;
}

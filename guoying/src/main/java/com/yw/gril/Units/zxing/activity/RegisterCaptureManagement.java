package com.example.androidcode.Units.zxing.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 伟 on 2016/6/23.
 */
public class RegisterCaptureManagement  {
    final private static String TAG = "RegisterCaptureManagement";
    private Gson gson = new Gson();
    private CaptureActivity captureActivity;


    private static RegisterCaptureManagement instance = null;

    public static RegisterCaptureManagement getInstance() {
        if (instance == null) {
            instance = new RegisterCaptureManagement();
        }
        return instance;
    }

    /**
     * 开始处理
     */
    public void start(CaptureActivity captureActivity, String result) {
        this.captureActivity = captureActivity;

    }

    /**
     * Handler
     */
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

        }
    };
}

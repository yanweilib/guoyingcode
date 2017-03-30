package com.yw.gril.Units.Volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.yw.gril.Units.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoxiaoc on 2016/8/1.
 */
public class VolleyReqUtil {
    private static String TAG="VolleyReqUtil";
    private VolleyResultListener mVolleyResult;
    public interface VolleyResultListener{
        void onRequestSuccess(String msg, String tag);
        void onRequestError(String msg, String tag);
    }

    public void registerListener(VolleyResultListener mVolleyResult){
        this.mVolleyResult = mVolleyResult;
    }

    public void unregisterListener(){
        this.mVolleyResult = null;
    }

    public void post(String url, final Map mapParams, final String tag){
        LogUtil.e(TAG,url+":"+mapParams.toString());
        StringRequest strReq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String msg) {
                LogUtil.e(TAG,msg);
                mVolleyResult.onRequestSuccess(msg,tag);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String errormsg = volleyError.networkResponse + volleyError.getMessage();
                LogUtil.e(TAG,errormsg);
                mVolleyResult.onRequestError(errormsg,tag);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return mapParams;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Basic YWRtaW46MTAw");
                return map;
            }
        };
        strReq.setRetryPolicy( new
            DefaultRetryPolicy(
                500000,//默认超时时间，应设置一个稍微大点儿的，例如本处的500000
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        );
        strReq.setTag(tag);
    }

    public void get(String url, final String tag){
        LogUtil.e(TAG,url);
        StringRequest strReq=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                mVolleyResult.onRequestSuccess(s,tag);
                LogUtil.e(TAG,s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                String errormsg = volleyError.networkResponse + volleyError.getMessage();
                mVolleyResult.onRequestError(errormsg,tag);
                LogUtil.e(TAG,errormsg);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("Authorization", "Basic YWRtaW46MTAw");
                return map;
            }
        };

        strReq.setRetryPolicy( new
            DefaultRetryPolicy(
                500000,//默认超时时间，应设置一个稍微大点儿的，例如本处的500000
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,//默认最大尝试次数
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        );
        strReq.setTag(tag);
    }
}

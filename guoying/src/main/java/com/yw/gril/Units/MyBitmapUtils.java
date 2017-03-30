package com.example.androidcode.Units;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by YSB on 2016/11/28.
 */
public class MyBitmapUtils extends BitmapUtils {

    private static BitmapUtils bitmapUtils;
    public static BitmapUtils instance(Context context)
    {
        if(bitmapUtils==null)
        {
            bitmapUtils=new BitmapUtils(context);
        }
        return bitmapUtils;
    }

    public MyBitmapUtils(Context context, String diskCachePath, float memoryCachePercent, int diskCacheSize) {
        super(context, diskCachePath, memoryCachePercent, diskCacheSize);
    }

    public MyBitmapUtils(Context context) {
        super(context);
    }

    public MyBitmapUtils(Context context, String diskCachePath) {
        super(context, diskCachePath);
    }

    public MyBitmapUtils(Context context, String diskCachePath, int memoryCacheSize) {
        super(context, diskCachePath, memoryCacheSize);
    }

    public MyBitmapUtils(Context context, String diskCachePath, int memoryCacheSize, int diskCacheSize) {
        super(context, diskCachePath, memoryCacheSize, diskCacheSize);
    }

    public MyBitmapUtils(Context context, String diskCachePath, float memoryCachePercent) {
        super(context, diskCachePath, memoryCachePercent);
    }
}

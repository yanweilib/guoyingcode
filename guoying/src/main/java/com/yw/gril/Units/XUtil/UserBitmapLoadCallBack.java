package com.yw.gril.Units.XUtil;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.yw.gril.Units.BitmapUtil;
import com.yw.gril.Units.ImageUtil;

/**
 * Created by 伟 on 2016/11/26.
 */

public class UserBitmapLoadCallBack extends BitmapLoadCallBack<ImageView> {

    private boolean isGrayscale=false;//是否去色
    private boolean isResize=false;//是否裁剪
    private boolean isFastBlur=false;//是否高斯模糊
    private int blurRadius=8;//模糊度

    private int defaultImg=0;   //默认图片

    /**
     * 设置默认图片
     * @param defaultImg
     * @return
     */
    public UserBitmapLoadCallBack setDefaultImg(int defaultImg) {
        this.defaultImg = defaultImg;
        return this;
    }

    /**
     *
     * @param grayscale
     * @return
     */
    public UserBitmapLoadCallBack setGrayscale(boolean grayscale) {
        isGrayscale = grayscale;
        return this;
    }

    public UserBitmapLoadCallBack setResize(boolean resize) {
        isResize = resize;
        return this;
    }

    public UserBitmapLoadCallBack setFastBlur(boolean fastBlur) {
        isFastBlur = fastBlur;

        return this;
    }

    public UserBitmapLoadCallBack setBlurRadius(int blurRadius) {
        this.blurRadius = blurRadius;
        return this;
    }

    public UserBitmapLoadCallBack() {

    }

    /**
     * Call back when bitmap has loaded.
     *
     * @param container
     * @param uri
     * @param bitmap
     * @param config
     * @param from
     */
    @Override
    public void onLoadCompleted(ImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
        if (bitmap == null) {
//            container.setImageResource(R.drawable.ball_myevent_nouser);
        } else {
            if(isResize)
            {
                if (bitmap.getHeight() > bitmap.getWidth()) {
                    bitmap=  BitmapUtil.resizeImageBitmap(bitmap,bitmap.getWidth(),bitmap.getWidth());
                } else if(bitmap.getHeight()<bitmap.getWidth()){
                    bitmap= BitmapUtil.resizeImageBitmap(bitmap,bitmap.getHeight(),bitmap.getHeight());
                }
            }
            if (isGrayscale) {
                bitmap = ImageUtil.getGrayscale(bitmap);
            }

            if(isFastBlur)
            {
                bitmap=  FastBlur.doBlur(bitmap,8,true);
            }
            container.setImageBitmap(bitmap);
        }
    }

    /**
     * Call back when bitmap failed to load.
     *
     * @param container
     * @param uri
     * @param drawable
     */
    @Override
    public void onLoadFailed(ImageView container, String uri, Drawable drawable) {
        container.setImageResource(defaultImg);
    }
}

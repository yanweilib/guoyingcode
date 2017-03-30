package com.example.androidcode.Widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lidroid.xutils.bitmap.core.AsyncDrawable;

/**
 * Created by Administrator on 2015/12/17.
 */
public class XCRoundImageView extends ImageView {
    private Paint paint;

    public XCRoundImageView(Context context) {
        this(context, null);
    }

    public XCRoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XCRoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint = new Paint();
    }

    /**
     * 绘制圆形图片
     *
     * @author caizhiming
     */
    @Override
    protected void onDraw(Canvas canvas) {

        try {
            Drawable drawable = getDrawable();
            if (null != drawable) {
                Bitmap bitmap = null;
                if(drawable instanceof AsyncDrawable){//加此判断使其支持xutils
                    bitmap = Bitmap
                            .createBitmap(
                                    getWidth(),
                                    getHeight(),
                                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                            : Bitmap.Config.RGB_565);
                    Canvas canvas1 = new Canvas(bitmap);
                    // canvas.setBitmap(bitmap);
                    drawable.setBounds(0, 0, getWidth(),
                            getHeight());
                    drawable.draw(canvas1);
                }else{
                    bitmap = ((BitmapDrawable) drawable).getBitmap();
                }

                Bitmap b = getCircleBitmap(bitmap, 32);
                final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
                final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
                paint.reset();
                canvas.drawBitmap(b, rectSrc, rectDest, paint);

            } else {
                super.onDraw(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取圆形图片方法
     *
     * @param bitmap
     * @param pixels
     * @return Bitmap
     * @author caizhiming
     */
    private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
        if(bitmap==null){
            return null;
        }
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int x = bitmap.getWidth();

        canvas.drawCircle(x / 2, x / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 图片去色
     */
    public void setGrayscale()
    {
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        setColorFilter(filter);
    }
}

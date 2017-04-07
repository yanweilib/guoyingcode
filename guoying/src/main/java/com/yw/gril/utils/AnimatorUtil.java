/**
 * Copyright (c) 2017/4/6. 闫伟 Inc. All rights reserved.
 */
package com.yw.gril.utils;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * <code>AnimatorUtil</code>
 * 动画帮助类
 * @author YANWEI
 * @version 1.0.0
 * @see java.lang.Class
 * @since 2017/3/31 18:21
 */
public class AnimatorUtil {
    /**
     * 上移下移 动画
     * @param view
     * @param disStart
     * @param disEnd
     * @param viewType 0:RelativeLayout;1: LinearLayout;2:FrameLayout
     */
    public static void DisValueAnimator(final View view, final int viewType, float disStart, float disEnd)
    {
        ValueAnimator animatordis = ValueAnimator.ofFloat(disStart, disEnd);
        animatordis.setTarget(view);
        animatordis.setDuration(500).start();
        animatordis.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                switch (viewType)
                {
                    case 0:
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(view.getLayoutParams());
                        lp.setMargins(0, (int) (float) animation.getAnimatedValue(), 0, 0);
                        view.setLayoutParams(lp);
                        break;
                    case 1:
                        LinearLayout.LayoutParams lpLinearLayout = new LinearLayout.LayoutParams(view.getLayoutParams());
                        lpLinearLayout.setMargins(0, (int) (float) animation.getAnimatedValue(), 0, 0);
                        view.setLayoutParams(lpLinearLayout);
                        break;
                    case 2:
                        FrameLayout.LayoutParams lpFrameLayout = new FrameLayout.LayoutParams(view.getLayoutParams());
                        lpFrameLayout.setMargins(0, (int) (float) animation.getAnimatedValue(), 0, 0);
                        view.setLayoutParams(lpFrameLayout);
                        break;
                }
            }
        });
    }

    /**
     * 隐藏显示 动画
     * @param view
     * @param visiStart
     * @param visiEnd
     */
    public static void VisiValueAnimator(final View view, float visiStart, float visiEnd)
    {
        ValueAnimator animator = ValueAnimator.ofFloat(visiStart, visiEnd);
        animator.setTarget(view);
        animator.setDuration(500).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                view.setAlpha((Float) animation.getAnimatedValue());
            }
        });
    }

    /**
     * 旋转 动画
     * @param fromDegrees 角度
     * @param toDegrees 角度
     * @param pivotXValue   旋转x轴
     * @param pivotYValue   旋转y轴
     */
    public static void RotateAnimation(View view,float fromDegrees, float toDegrees, float pivotXValue, float pivotYValue)
    {
        //X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF:正常无伸缩、RELATIVE_TO_PARENT。
        //Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF:正常无伸缩、RELATIVE_TO_PARENT。
        RotateAnimation animation =new RotateAnimation(fromDegrees,toDegrees,Animation.RELATIVE_TO_SELF,
                pivotXValue, Animation.RELATIVE_TO_SELF,pivotYValue);
        animation.setDuration(2000);
        animation.setStartOffset(0);
        animation.setRepeatCount(1000);
        animation.setInterpolator(new LinearInterpolator());
        view.setAnimation(animation);
        animation.start();
    }
}

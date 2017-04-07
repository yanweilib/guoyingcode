package com.yw.gril.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>FlowView</code>
 * 标签控件
 * @author YANWEI
 * @version 1.0.0
 * @see Class
 * @since 2017/4/7 16:45
 */
public class FlowView  extends ViewGroup{
    public FlowView(Context context) {
        super(context);
    }

    public FlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FlowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    List<List<View>> mAllViews =new ArrayList<>();//每行每列孩子
    List<Integer> mLineHeight =new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();;
        mLineHeight.clear();

        int width=getWidth();
        int lineWidth=0;
        int lineHeight=0;

        //储存每一行所有的childview
        List<View> lineViews=new ArrayList<>();
        int cCount=getChildCount();

        //遍历所有孩子
        for (int i = 0; i < cCount; i++) {
            View child=getChildAt(i);
            MarginLayoutParams lp= (MarginLayoutParams) child.getLayoutParams();
            int childWidth=child.getMeasuredWidth();
            int childHeight=child.getMeasuredHeight();

            if(childWidth+lp.leftMargin+lp.rightMargin+lineWidth>width)
            {
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                lineWidth=0;
                lineViews=new ArrayList<>();
            }
            lineWidth+=childWidth+lp.leftMargin+lp.rightMargin;
            lineHeight=Math.max(lineHeight,childHeight+lp.topMargin+lp.bottomMargin);
            lineViews.add(child);
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int left=0;
        int top=0;
        int lineNums=mAllViews.size();
        for (int i = 0; i < lineNums; i++) {
            lineViews=mAllViews.get(i);
            lineHeight=mLineHeight.get(i);

            for (int j = 0; j < lineViews.size(); j++) {
                View child=lineViews.get(j);
                if(child.getVisibility()==View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp= (MarginLayoutParams) child.getLayoutParams();
                int lc=left+lp.leftMargin;
                int tc=top+lp.topMargin;
                int rc=lc+child.getMeasuredWidth();
                int bc=tc+child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);

                left+=child.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
            }
            left=0;
            top+=lineHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth=MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight=MeasureSpec.getMode(heightMeasureSpec);

//        如果是warp_content情况下，记录宽和高
        int width=0;
        int height=0;

        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth=0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight=0;

        int cCount=getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child=getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp= (MarginLayoutParams) child.getLayoutParams();
//            当前子空间占据的的宽度
            int childWidth=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
//            当前子空间占据的高度
            int childHeight=child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            if(lineWidth+childWidth>sizeWidth)
            {
                width=Math.max(lineWidth,childWidth);//取最大的
                lineWidth=childWidth;//重新开启新行，开始记录
                height+=lineHeight;
                lineHeight=childHeight;
            }
            else
            {
                lineWidth+=childWidth;
                lineHeight=Math.max(lineHeight,childHeight);
            }

            if(i==cCount-1)
            {
                width=Math.max(width,lineWidth);
                height+=lineHeight;
            }
        }
        setMeasuredDimension((modeWidth==MeasureSpec.EXACTLY)?sizeWidth:width,(modeHeight==MeasureSpec.EXACTLY)?sizeHeight:height);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void addView(final View view, int l, int t, int r, int b)
    {
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.MarginLayoutParams marginLayoutParams=new ViewGroup.MarginLayoutParams(layoutParams);
        marginLayoutParams.setMargins(l,t,r,b);
        view.setLayoutParams(marginLayoutParams);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)view).setText("click me");
            }
        });
        addView(view);
        invalidate();
    }

    public void addViews(List<View> list,int l,int t,int r,int b) {
        for (int i = 0; i < list.size(); i++) {
            final View child = list.get(i);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
            marginLayoutParams.setMargins(l, t, r, b);
            child.setLayoutParams(marginLayoutParams);
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((TextView) child).setText("click me");
                }
            });
            addView(child);
        }
    }
}

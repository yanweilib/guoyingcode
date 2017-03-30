package com.yw.gril.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by 伟 on 2017/3/30.
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener{
    private SparseArray<View> mViews;

    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void initListener();
    public abstract void initData();
    public abstract void processClick(View v);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews=new SparseArray<>();
        setContentView(getLayoutId());
        initViews();
        initListener();
        initData();
    }

    /**
     * 通过ID找到View
     * @param viewId
     * @param <E>
     * @return
     */
    public <E extends View> E findView(int viewId)
    {
        E view= (E) mViews.get(viewId);
        if(view==null)
        {
            view= (E) findViewById(viewId);
            mViews.put(viewId,view);
        }
        return view;
    }

    /**
     * View设置OnClick事件
     * @param view
     * @param <E>
     */
    public <E extends View> void setOnclick(E view)
    {
        view.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        processClick(v);
    }
}

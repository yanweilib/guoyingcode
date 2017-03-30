package com.yw.gril.basepages;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 伟 on 2017/3/30.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;
    public View convertView;
    private SparseArray<View> mViews;

    public abstract int getLayoutId();
    public abstract void initViews();
    public abstract void initListener();
    public abstract void initData();
    public abstract void processClick(View v);

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        processClick(v);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
        {
            isVisible=true;
            lazyLoad();
        }
        else
        {
            isVisible=false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViews=new SparseArray<>();
        convertView=inflater.inflate(getLayoutId(),container,false);
        initViews();

        isInitView=true;
        lazyLoad();
        return convertView;
    }

    /**
     * 懒加载
     */
    private void lazyLoad()
    {
        if(!isFirstLoad||!isVisible||!isInitView)
        {
            //如果不是第一次加载、不是可见的、不是初始化View，则不加载数据
            return;
        }
        initListener();
        initData();
        //设置已经不是第一次加载
        isFirstLoad=false;
    }

    /**
     * 通过ID找到View
     * @param viewId
     * @param <E>
     * @return
     */
    public <E extends View> E findView(int viewId)
    {
        if(convertView!=null)
        {
            E view= (E) mViews.get(viewId);
            if(view==null)
            {
                view= (E) convertView.findViewById(viewId);
                mViews.put(viewId,view);
            }
            return view;
        }
        return null;
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
}

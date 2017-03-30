package com.yw.gril.Units;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yw.gril.R;
import com.yw.gril.Units.Statusbar.SystemBarTintManager;

import java.util.ArrayList;

/**
 * Created by 伟 on 2016/12/12.
 */

public class ViewUtil {

    /**
     * 设置Margins
     * @param view
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins (View view, int l, int t, int r, int b) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(l, t, r, b);
            view.requestLayout();
        }
    }

    /**
     * 计算ListView 整体的高度，包括内容
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        Log.d("数量", "" + listAdapter.getCount());
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); //计算子项View的宽高
            //统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
            System.out.println(listItem.getMeasuredHeight() + "===========" + i);
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);

    }

    /**
     * 计算Gridview 整体的高度，包括内容
     *
     * @param listView
     */
    public static void setGridviewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 4;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置参数
        listView.setLayoutParams(params);
    }

    /**
     * 设置文字大小
     * @param context
     * @param tv
     * @param str
     * @param text_size_list
     */
    public static void setTextSize(Context context, TextView tv,
                                   ArrayList<String> str, ArrayList<Float> text_size_list) {
        // 累加数组所有的字符串为一个字符串
        StringBuffer long_str = new StringBuffer();
        for (int i = 0; i < str.size(); i++) {
            long_str.append(str.get(i));
        }
        SpannableString builder = new SpannableString(long_str.toString());
        // 设置不同字符串的字号
        ArrayList<AbsoluteSizeSpan> absoluteSizeSpans = new ArrayList<AbsoluteSizeSpan>();
        for (int i = 0; i < text_size_list.size(); i++) {
            absoluteSizeSpans.add(new AbsoluteSizeSpan(DisplayUtil.sp2px(
                    context, text_size_list.get(i))));
        }
        for (int i = 0; i < str.size(); i++) {
            int star = long_str.toString().indexOf(str.get(i));
            int end = star + str.get(i).length();
            builder.setSpan(absoluteSizeSpans.get(i), star, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(builder);
    }

    /**
     * 设置状态栏
     *
     *      设置状态栏的颜色
            android:fitsSystemWindows="true"
            android:clipToPadding="true"
            由于我们要实现的是状态栏和顶部的控件是同一个颜色，同时，控件内容也不和状态栏重复，所以只要把那两行代码放到我们顶部的控件就可以了。

            //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
             //透明导航栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
     */
    public static void initSystemBar(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            setTranslucentStatus(activity, true);

        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);

        tintManager.setStatusBarTintEnabled(true);

        // 使用颜色资源
        tintManager.setStatusBarTintResource(R.color.c1996D0);

    }

    @TargetApi(19)
    /**
     * 设置状态栏
     */
    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();

        WindowManager.LayoutParams winParams = win.getAttributes();

        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

        if (on) {

            winParams.flags |= bits;

        } else {

            winParams.flags &= ~bits;

        }

        win.setAttributes(winParams);
    }

    /**
     * Fragment跳转
     * @param fromFragmentClass
     * @param toFragmentClass
     * @param args
     */
    public void turnToFragment(Class<? extends Fragment> fromFragmentClass, Class<? extends Fragment> toFragmentClass, Bundle args) {

//        FragmentManager fm = getSupportFragmentManager();
          FragmentManager fm = null;
        //被切换的Fragment标签
        String fromTag = fromFragmentClass.getSimpleName();
        //切换到的Fragment标签
        String toTag = toFragmentClass.getSimpleName();
        //查找切换的Fragment
        Fragment fromFragment = fm.findFragmentByTag(fromTag);
        Fragment toFragment = fm.findFragmentByTag(toTag);
        //如果要切换到的Fragment不存在，则创建
        if (toFragment == null) {
            try {
                toFragment = toFragmentClass.newInstance();
                toFragment.setArguments(args);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //如果有参数传递，
        if( args != null && !args.isEmpty() ) {
            toFragment.getArguments().putAll(args);
        }
        //Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        //设置Fragment切换效果
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        /**
         * 如果要切换到的Fragment没有被Fragment事务添加，则隐藏被切换的Fragment，添加要切换的Fragment
         * 否则，则隐藏被切换的Fragment，显示要切换的Fragment
         */
        if( !toFragment.isAdded()) {
//            ft.hide(fromFragment).add(R.id.content_frame, toFragment, toTag);
            ft.hide(fromFragment).add(0, toFragment, toTag);
        } else {
            ft.hide(fromFragment).show(toFragment);
        }
        //添加到返回堆栈
//        ft.addToBackStack(tag);
        //不保留状态提交事务
        ft.commitAllowingStateLoss();
    }


    /**
     * Fragment跳转
     *     private List<Fragment> fragmentList=new ArrayList<>();
     private int currentIndex;
     private Fragment currentFragment;

     * @param index
     */
    private void changeTab(int index) {
//        currentIndex = index;
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        //判断当前的Fragment是否为空，不为空则隐藏
//        if (null != currentFragment) {
//            ft.hide(currentFragment);
//        }
//        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentList.get(currentIndex).getClass().getName());
//
//        if (null == fragment) {
//            //如fragment为空，则之前未添加此Fragment。便从集合中取出
//            fragment = fragmentList.get(index);
//        }
//        currentFragment = fragment;
//
//        //判断此Fragment是否已经添加到FragmentTransaction事物中
//        if (!fragment.isAdded()) {
//            ft.add(R.id.fragment_content, fragment, fragment.getClass().getName());
//        } else {
//            ft.show(fragment);
//        }
//        ft.commit();
    }
}

package com.yw.gril.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 伟 on 2016/5/19.
 * Activity管理
 */
public class ActivityCollector {
    private static final String TAG="ActivityCollector";
    public static List<Activity> activityList = new ArrayList<Activity>();

    /**
     * 添加 Activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 移除 Activity
     * @param activity
     */
    public static void removeActivity(Activity activity)
    {
        if(activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    /**
     * 结束所有的 Activity
     */
    public static void finishAll()
    {
        for (Activity activity:activityList)
        {
            if(!activity.isFinishing())
            {
                activity.finish();
            }
        }
    }

    /**
     * 查询Activity是否打开
     * @param activityName
     * @return
     */
    public static boolean seleteActivityIsOpen(String activityName)
    {
        for (Activity activity:activityList)
        {
            if(activityName.equals(activity.getClass().getSimpleName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询Activity是否打开
     * @param activity
     * @return
     */
    public static boolean seleteActivityIsOpen(Activity activity)
    {
        for (Activity mactivity:activityList)
        {
            if(activity.equals(mactivity))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 关闭某一个Activity
     * @param className
     */
    public static void finishOneActivity(String className)
    {
        for (Activity activity:activityList)
        {
            if(!activity.isFinishing())
            {
                if(className.equals(activity.getClass().getSimpleName()))
                {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 获取正在运行的Activity
     * @return
     */
    public static Activity getTopActivity()
    {
        if(activityList.size()!=0)
        {
            return activityList.get(0);
        }
        return null;
    }
    /**
     * 获取Activity
     * @return
     */
    public static Activity getActivity(String activitySimpleName )
    {
        for (int i = 0; i < activityList.size(); i++) {
            if(activityList.get(i).getClass().getSimpleName().equals(activitySimpleName))
            {
                return activityList.get(i);
            }
        }
        return null;
    }
}

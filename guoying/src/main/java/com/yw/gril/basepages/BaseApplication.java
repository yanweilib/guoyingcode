package com.yw.gril.basepages;

import android.app.Application;

/**
 * Created by 伟 on 2017/3/30.
 */

public abstract class BaseApplication extends Application {

    public abstract void initConfigs();

    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();
    }
}

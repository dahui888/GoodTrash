package com.llw.mvplibrary;

import android.app.Application;
import android.content.Context;

/**
 * 基类Application
 * @author i小灰
 */
public class BaseApplication extends Application {

    private static ActivityManager activityManager;

    private static BaseApplication application;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //声明Activity管理
        activityManager = new ActivityManager();
        context = getApplicationContext();
        application = this;
    }


    public static BaseApplication getApplication() {
        return application;
    }

    public static Context getContext() {
        return context;
    }

    public static ActivityManager getActivityManager() {
        return activityManager;
    }
}

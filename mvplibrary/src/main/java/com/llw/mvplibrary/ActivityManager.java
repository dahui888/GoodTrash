package com.llw.mvplibrary;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理Activity
 * @author i小灰
 */
public class ActivityManager {

    //保存所有创建的Activity
    private List<Activity> activityList = new ArrayList<>();

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if(activity != null){
            activityList.add(activity);
        }
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if(activity != null){
            activityList.remove(activity);
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishAllActivity(){
        for (Activity activity : activityList) {
            activity.finish();
        }
    }
}

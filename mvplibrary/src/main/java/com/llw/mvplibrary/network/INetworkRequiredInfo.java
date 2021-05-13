package com.llw.mvplibrary.network;

import android.app.Application;

/**
 * App运行信息接口
 * @author i小灰
 */
public interface INetworkRequiredInfo {

    /**
     * 获取App版本名
     */
    String getAppVersionName();

    /**
     * 获取App版本号
     */
    String getAppVersionCode();

    /**
     * 判断是否为Debug模式
     */
    boolean isDebug();

    /**
     * 获取全局上下文参数
     */
    Application getApplicationContext();

}

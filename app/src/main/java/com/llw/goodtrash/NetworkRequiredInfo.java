package com.llw.goodtrash;

import android.app.Application;

import com.llw.mvplibrary.BuildConfig;
import com.llw.mvplibrary.network.INetworkRequiredInfo;


/**
 * 网络访问信息
 * @author i小灰
 */
public class NetworkRequiredInfo implements INetworkRequiredInfo {

    private Application application;

    public NetworkRequiredInfo(Application application){
        this.application = application;
    }

    /**
     * 版本名
     */
    @Override
    public String getAppVersionName() {
        return "1.0";
    }
    /**
     * 版本号
     */
    @Override
    public String getAppVersionCode() {
        return "1.0";
    }

    /**
     * 是否为debug
     */
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * 应用全局上下文
     */
    @Override
    public Application getApplicationContext() {
        return application;
    }
}

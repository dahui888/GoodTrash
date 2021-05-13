package com.llw.mvplibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.llw.mvplibrary.BaseApplication;
import com.llw.mvplibrary.R;
import com.llw.mvplibrary.network.NetworkUtils;
import com.llw.mvplibrary.network.utils.StatusBarUtil;

import java.util.Objects;

/**
 * 基类Activity，普通Activity继承即可。
 *
 * @author i小灰
 */
public abstract class BaseActivity extends AppCompatActivity implements IUiCallback {

    //Activity 上下文
    protected Activity context;
    //弹窗
    private Dialog mDialog;

    private static final int FAST_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView(savedInstanceState);
        this.context = this;
        //添加继承这个BaseActivity的Activity
        BaseApplication.getActivityManager().addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }

        initData(savedInstanceState);
    }

    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }

    /**
     * 检查当前是否打开网络
     */
    protected boolean hasNetwork() {
        return (NetworkUtils.isNetWorkAvailable(context));
    }

    /**
     * 页面返回
     *
     * @param toolbar   标题
     * @param isDestroy 是否销毁
     */
    protected void back(Toolbar toolbar, boolean isDestroy) {
        toolbar.setNavigationOnClickListener(v -> {
            if (isDestroy) {
                finish();
            } else {
                onBackPressed();
            }
        });
    }


    /**
     * 设置页面状态栏
     *
     * @param activity    目标Activity
     * @param bgColorId   状态栏背景颜色
     * @param isDarkTheme 状态栏主题  深色则为黑色图标和文字，浅色则为白色图标和文字
     */
    protected void setStatubar(AppCompatActivity activity, int bgColorId, boolean isDarkTheme) {
        StatusBarUtil.setStatusBarColor(activity, bgColorId);
        if (isDarkTheme) {
            //深色
            StatusBarUtil.StatusBarLightMode(this);
        }
    }

    /**
     * Toast消息提示  字符
     *
     * @param llw
     */
    protected void showMsg(CharSequence llw) {
        Toast.makeText(context, llw, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast消息提示  资源ID
     *
     * @param resourceId
     */
    protected void showMsg(int resourceId) {
        Toast.makeText(context, resourceId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹窗出现
     */
    protected void showLoadingDialog() {
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.loading_dialog);
        }
        mDialog.setContentView(R.layout.dialog_loading);
        mDialog.setCancelable(false);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.show();
    }

    /**
     * 弹窗隐藏
     */
    protected void hideLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    /**
     * 返回 不需要参数
     */
    protected void Back() {
        context.finish();
        if (!isFastClick()) {
            context.finish();
        }
    }

    /**
     * 返回 toolbar控件点击
     *
     * @param toolbar
     */
    protected void Back(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
                if (!isFastClick()) {
                    context.finish();
                }
            }
        });
    }


    /**
     * 两次点击间隔不能少于500ms
     *
     * @return flag
     */
    protected static boolean isFastClick() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = currentClickTime;

        return flag;
    }


}

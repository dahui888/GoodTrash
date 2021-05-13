package com.llw.mvplibrary.mvp;

import android.os.Bundle;

import com.llw.mvplibrary.base.BaseActivity;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;

/**
 * 适用于需要访问网络接口的Activity
 *
 * @author i小灰
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {


    protected P mPresenter;

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        //创建
        mPresenter = createPresenter();
        //绑定View
        mPresenter.attachView((BaseView) this);
    }

    /**
     * 页面销毁时解除绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

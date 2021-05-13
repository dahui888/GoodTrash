package com.llw.mvplibrary.base;

import java.lang.ref.WeakReference;

/**
 * Presenter基类 操作视图View
 *
 * @param <V>
 * @author i小灰
 */
public class BasePresenter<V extends BaseView> {

    //弱引用View
    protected WeakReference<V> mWeakReference;
    private V mView;

    /**
     * 绑定View
     *
     * @param view
     */
    public void attachView(V view) {
        mView = view;
        mWeakReference = new WeakReference<V>(view);
    }

    /**
     * 解绑View
     */
    public void detachView() {
        mView = null;
        if (mWeakReference != null) {
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

    /**
     * 获取view
     *
     * @return
     */
    public V getView() {
        if (mWeakReference != null) {
            return mWeakReference.get();
        }
        return null;
    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}

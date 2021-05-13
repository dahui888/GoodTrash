package com.llw.goodtrash.contract;

import android.annotation.SuppressLint;

import com.llw.goodtrash.api.ApiService;
import com.llw.goodtrash.bean.TrashNewsResponse;
import com.llw.mvplibrary.base.BasePresenter;
import com.llw.mvplibrary.base.BaseView;
import com.llw.mvplibrary.network.NetworkApi;
import com.llw.mvplibrary.network.observer.BaseObserver;

/**
 * 主页面访问网络
 *
 * @author i小灰
 * @date 2021/3/30 15:28
 */
public class MainContract {

    public static class MainPresenter extends BasePresenter<MainView> {
        /**
         * 垃圾分类新闻
         *
         * @param num 数量
         */
        @SuppressLint("CheckResult")
        public void getTrashNews(Integer num) {
            ApiService service = NetworkApi.createService(ApiService.class,0);
            service.getTrashNews(num).compose(NetworkApi.applySchedulers(new BaseObserver<TrashNewsResponse>() {
                @Override
                public void onSuccess(TrashNewsResponse trashNewsResponse) {
                    if (getView() != null) {
                        getView().getTrashNewsResponse(trashNewsResponse);
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    if (getView() != null) {
                        getView().getTrashNewsFailed(e);
                    }
                }
            }));
        }
    }

    public interface MainView extends BaseView {
        /**
         * 获取垃圾分类新闻返回
         *
         * @param response
         */
        void getTrashNewsResponse(TrashNewsResponse response);

        /**
         * 搜索物品异常返回
         *
         * @param throwable
         */
        void getTrashNewsFailed(Throwable throwable);
    }
}

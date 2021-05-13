package com.llw.goodtrash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.llw.goodtrash.adapter.TrashNewsAdapter;
import com.llw.goodtrash.contract.MainContract;
import com.llw.goodtrash.bean.TrashNewsResponse;
import com.llw.goodtrash.ui.HistoryActivity;
import com.llw.goodtrash.ui.ImageInputActivity;
import com.llw.goodtrash.ui.NewsDetailsActivity;
import com.llw.goodtrash.ui.TextInputActivity;
import com.llw.goodtrash.ui.VoiceInputActivity;
import com.llw.goodtrash.utils.AppStartUpUtils;
import com.llw.goodtrash.utils.Constant;
import com.llw.goodtrash.utils.NewsHelper;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 *
 * @author i小灰
 */
public class MainActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.MainView {

    private static final String TAG = "MainActivity";
    //轮播
    private Banner banner;
    private RecyclerView rvNews;
    private List<TrashNewsResponse.NewslistBean> mList = new ArrayList<>();
    private TrashNewsAdapter mAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;

    @Override
    public void initData(Bundle savedInstanceState) {
        //页面初始化
        initView();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        banner = findViewById(R.id.banner);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        appBarLayout = findViewById(R.id.appbar_layout);
        rvNews = findViewById(R.id.rv_news);

        //伸缩偏移量监听
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {//收缩时
                    collapsingToolbarLayout.setTitle("垃圾分类");
                    isShow = true;
                } else if (isShow) {//展开时
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
        //设置列表
        mAdapter = new TrashNewsAdapter(R.layout.item_trash_new_rv, mList);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //跳转到新闻详情页面
            Intent intent = new Intent(context, NewsDetailsActivity.class);
            intent.putExtra("url", mList.get(position).getUrl());
            startActivity(intent);
        });
        rvNews.setLayoutManager(new LinearLayoutManager(context));
        rvNews.setAdapter(mAdapter);

        if (hasNetwork()) {//有网络
            if (AppStartUpUtils.isTodayFirstStartApp(context)) {
                //今天第一次启动
                //请求垃圾分类新闻数据
                mPresenter.getTrashNews(10);
            } else {
                //今天后续启动
                //读取本地数据库数据
                List<TrashNewsResponse.NewslistBean> list = NewsHelper.queryAllNews();
                if (list.size() <= 0) {
                    mPresenter.getTrashNews(10);
                } else {
                    showBanner(list);//轮播显示
                    showList(list);//新闻列表显示
                }
            }
        } else {//无网络
            //加载默认数据
            TrashNewsResponse response = new Gson().fromJson(Constant.LOCAL_NEWS_DATA, TrashNewsResponse.class);
            mList.clear();
            mList.addAll(response.getNewslist());
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }


    /**
     * 进入文字输入页面
     */
    public void jumpTextInput(View view) {
        gotoActivity(TextInputActivity.class);
    }

    /**
     * 进入声音输入页面
     */
    public void jumpVoiceInput(View view) {
        gotoActivity(VoiceInputActivity.class);
    }

    /**
     * 进入图像输入页面
     */
    public void jumpImageInput(View view) {
        gotoActivity(ImageInputActivity.class);
    }

    /**
     * 进入历史记录页面
     */
    public void jumpHistory(View view) {
        gotoActivity(HistoryActivity.class);
    }

    /**
     * 进入Activity
     *
     * @param clazz 目标Activity
     */
    private void gotoActivity(Class<?> clazz) {
        startActivity(new Intent(MainActivity.this, clazz));
    }

    /**
     * 获取垃圾分类新闻成功返回
     *
     * @param response
     */
    @Override
    public void getTrashNewsResponse(TrashNewsResponse response) {
        if (response.getCode() == Constant.SUCCESS_CODE) {
            List<TrashNewsResponse.NewslistBean> list = response.getNewslist();
            if (list.size() > 0) {
                //数据显示
                showBanner(list);//轮播显示
                showList(list);//新闻列表显示
                //保存新闻数据
                NewsHelper.saveNews(list);
            } else {
                showMsg("垃圾分类新闻为空");
            }
        } else {
            showMsg(response.getMsg());
        }
    }

    /**
     * 显示新闻列表
     *
     * @param list
     */
    private void showList(List<TrashNewsResponse.NewslistBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 显示轮播图
     *
     * @param list
     */
    public void showBanner(List<TrashNewsResponse.NewslistBean> list) {
        banner.setAdapter(new BannerImageAdapter<TrashNewsResponse.NewslistBean>(list) {
            @Override
            public void onBindView(BannerImageHolder holder, TrashNewsResponse.NewslistBean data, int position, int size) {
                //显示轮播图片
                Glide.with(holder.itemView)
                        .load(data.getPicUrl())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                        .into(holder.imageView);
            }
        })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this));
    }

    /**
     * 获取垃圾分类新闻失败返回
     *
     * @param throwable 异常
     */
    @Override
    public void getTrashNewsFailed(Throwable throwable) {
        Log.d(TAG, "获取垃圾分类新闻失败：" + throwable.toString());
    }


}

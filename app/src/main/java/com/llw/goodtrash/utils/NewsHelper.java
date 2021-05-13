package com.llw.goodtrash.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.llw.goodtrash.bean.News;
import com.llw.goodtrash.bean.TrashNewsResponse;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻表帮助类
 *
 * @author i小灰
 */
public class NewsHelper {

    private static final String TAG = "NewsHelper";

    /**
     * 保存新闻数据
     *
     * @param newsList
     * @return
     */
    public static boolean saveNews(List<TrashNewsResponse.NewslistBean> newsList) {
        LitePal.deleteAll(News.class);
        boolean result = false;
        for (TrashNewsResponse.NewslistBean bean : newsList) {
            News news = new News();
            news.setTitle(bean.getTitle());
            news.setDescription(bean.getDescription());
            news.setCtime(bean.getCtime());
            news.setPicUrl(bean.getPicUrl());
            news.setSource(bean.getSource());
            news.setUrl(bean.getUrl());
            news.save();
            if (news.save()) {
                result = true;
                Log.d(TAG, "保存新闻数据成功");
            } else {
                result = false;
                Log.d(TAG, "保存新闻数据失败");
            }
        }
        Log.d(TAG, "保存的数据；" + new Gson().toJson(LitePal.findAll(News.class)));

        return result;
    }

    /**
     * 查询新闻表中所有新闻数据
     *
     * @return List<TrashNewsResponse.NewslistBean>
     */
    public static List<TrashNewsResponse.NewslistBean> queryAllNews() {
        List<TrashNewsResponse.NewslistBean> newsList = new ArrayList<>();
        List<News> news = LitePal.findAll(News.class);
        Log.d(TAG, String.valueOf(news.size()));
        if (news != null && news.size() > 0) {
            for (int i = 0; i < news.size(); i++) {
                TrashNewsResponse.NewslistBean newslistBean = new TrashNewsResponse.NewslistBean();
                newslistBean.setCtime(news.get(i).getCtime());
                newslistBean.setDescription(news.get(i).getDescription());
                newslistBean.setPicUrl(news.get(i).getPicUrl());
                newslistBean.setSource(news.get(i).getSource());
                newslistBean.setTitle(news.get(i).getTitle());
                newslistBean.setUrl(news.get(i).getUrl());
                newsList.add(newslistBean);
            }
        }
        return newsList;
    }


}

package com.llw.goodtrash.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.llw.goodtrash.bean.History;
import com.llw.goodtrash.bean.TrashResponse;
import com.llw.mvplibrary.network.utils.DateUtil;

import org.litepal.LitePal;

import java.util.List;

/**
 * 历史记录帮助类
 *
 * @author i小灰
 */
public class HistoryHelper {

    public static final String TAG = "HistoryHelper";

    /**
     * 查询所有历史记录
     *
     * @return 结果列表
     */
    public static List<History> queryAllHistory() {
        return LitePal.findAll(History.class);
    }

    /**
     * 是否存在历史记录
     *
     * @param name 物品名
     * @return true or false
     */
    public static boolean isHaveHistory(String name) {
        List<History> histories = LitePal.where("name = ?", name).find(History.class);
        return histories.size() > 0;
    }


    /**
     * 根据id删除数据
     * @param id id
     */
    public static void deleteHistoryById(long id){
        LitePal.delete(History.class,id);
    }

    /**
     * 根据所有历史记录
     */
    public static void deleteAllHistory() {
        LitePal.deleteAll(History.class);
    }

    /**
     * 保存历史记录
     *
     * @param list 需要保存的数据
     * @param word 物品名称
     */
    public static void saveHistory(List<TrashResponse.NewslistBean> list, String word) {
        for (TrashResponse.NewslistBean bean : list) {
            //遍历返回数据，找出返回结果中与搜索内容一致的数据，保存到数据表中
            if (bean.getName().equals(word)) {
                //保存数据前先查询是否存在数据
                List<History> historyList = queryAllHistory();
                //有数据则遍历检查保存
                if (historyList.size() > 0) {
                    if (!isHaveHistory(bean.getName())) {
                        //不存在则直接保存
                        saveHistory(bean);
                    } else {
                        Log.d(TAG, "记录已存在");
                    }
                } else {
                    //没有数据则直接保存
                    saveHistory(bean);
                }
            } else {
                Log.d(TAG, "没有匹配到相关结果，无法保存");
            }
        }

        Log.d(TAG, new Gson().toJson(queryAllHistory()));
    }

    /**
     * 保存历史
     *
     * @param bean
     */
    private static void saveHistory(TrashResponse.NewslistBean bean) {
        History historyBean = new History();
        historyBean.setName(bean.getName());
        historyBean.setType(bean.getType());
        historyBean.setAipre(bean.getAipre());
        historyBean.setExplain(bean.getExplain());
        historyBean.setContain(bean.getContain());
        historyBean.setTip(bean.getTip());
        //添加历史记录的保存时间
        historyBean.setDateTime(DateUtil.getDateTime());
        historyBean.save();
        if (historyBean.save()) {
            Log.d(TAG, "保存历史记录成功");
        } else {
            Log.d(TAG, "保存历史记录失败");
        }
    }
}

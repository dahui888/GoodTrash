package com.llw.goodtrash.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodtrash.R;
import com.llw.goodtrash.bean.TrashNewsResponse;

import java.util.List;

/**
 * 垃圾分类新闻列表适配器
 *
 * @author i小灰
 * @date 2021/4/16 10:36
 */
public class TrashNewsAdapter extends BaseQuickAdapter<TrashNewsResponse.NewslistBean, BaseViewHolder> {

    public TrashNewsAdapter(int layoutResId, @Nullable List<TrashNewsResponse.NewslistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrashNewsResponse.NewslistBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_description, item.getDescription())
                .addOnClickListener(R.id.item_trash_news);
    }
}

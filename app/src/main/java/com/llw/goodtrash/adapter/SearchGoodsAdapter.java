package com.llw.goodtrash.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llw.goodtrash.R;
import com.llw.goodtrash.bean.TrashResponse;

import java.util.List;

/**
 * 搜索物品结果列表适配器
 *
 * @author i小灰
 * @date 2021/3/30 15:06
 */
public class SearchGoodsAdapter extends BaseQuickAdapter<TrashResponse.NewslistBean, BaseViewHolder> {

    public SearchGoodsAdapter(int layoutResId, @Nullable List<TrashResponse.NewslistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrashResponse.NewslistBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_explain, item.getExplain())
                .addOnClickListener(R.id.item_search_goods);

        TextView tvType = helper.getView(R.id.tv_type);
        switch (item.getType()) {
            case 0:
                tvType.setText("可回收垃圾");
                break;
            case 1:
                tvType.setText("有害垃圾");
                break;
            case 2:
                tvType.setText("厨余垃圾");
                break;
            case 3:
                //干垃圾即其他垃圾
                tvType.setText("干垃圾");
                break;
            default:
                break;
        }
    }
}

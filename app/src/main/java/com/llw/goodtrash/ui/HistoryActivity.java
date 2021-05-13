package com.llw.goodtrash.ui;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.llw.goodtrash.R;
import com.llw.goodtrash.adapter.HistoryAdapter;
import com.llw.goodtrash.bean.History;
import com.llw.goodtrash.utils.HistoryHelper;
import com.llw.mvplibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 历史记录
 *
 * @author i小灰
 */
public class HistoryActivity extends BaseActivity {

    //工具栏
    private Toolbar toolbar;
    //空数据布局
    private LinearLayout layEmptyData;
    //历史列表
    private RecyclerView rvHistory;
    //适配器
    private HistoryAdapter mAdapter;
    //历史数据列表
    private List<History> mList = new ArrayList<>();
    //全删
    private TextView tvAllDelete;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        showListData();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        //设置页面状态栏
        setStatubar(this, R.color.white, true);
        back(toolbar, false);
        layEmptyData = findViewById(R.id.lay_empty_data);
        rvHistory = findViewById(R.id.rv_history);
        tvAllDelete = findViewById(R.id.tv_all_delete);

        mAdapter = new HistoryAdapter(R.layout.item_history_rv, mList);
        rvHistory.setLayoutManager(new LinearLayoutManager(context));
        rvHistory.setAdapter(mAdapter);
        //列表item点击事件
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            //删除数据
            HistoryHelper.deleteHistoryById(mList.get(position).getId());
            showListData();
        });
    }

    /**
     * 显示列表
     */
    private void showListData() {
        List<History> historyList = HistoryHelper.queryAllHistory();
        if (historyList.size() > 0) {
            //设置列表的数据
            mList.clear();
            mList.addAll(historyList);
            mAdapter.notifyDataSetChanged();
            layEmptyData.setVisibility(View.GONE);
            rvHistory.setVisibility(View.VISIBLE);
            tvAllDelete.setVisibility(View.VISIBLE);
        } else {
            //隐藏列表
            layEmptyData.setVisibility(View.VISIBLE);
            rvHistory.setVisibility(View.GONE);
            tvAllDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    /**
     * 全删
     * @param view
     */
    public void deleteAll(View view) {
        HistoryHelper.deleteAllHistory();
        showListData();
    }
}
package com.llw.goodtrash.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.llw.goodtrash.R;
import com.llw.goodtrash.adapter.SearchGoodsAdapter;
import com.llw.goodtrash.contract.TextContract;
import com.llw.goodtrash.bean.TrashResponse;
import com.llw.goodtrash.utils.Constant;
import com.llw.goodtrash.utils.HistoryHelper;
import com.llw.mvplibrary.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 手动输入物品进行垃圾分类
 *
 * @author i小灰
 * @date 2021/3/31 17:58
 */
public class TextInputActivity extends MvpActivity<TextContract.TextPresenter> implements TextContract.TextView {

    private static final String TAG = "TextInputActivity";
    private EditText etGoods;//输入框
    private ImageView ivClear;//清空输入框
    private RecyclerView rvResult;//结果显示列表
    private List<TrashResponse.NewslistBean> newslistBeanList = new ArrayList<>();//数据列表
    private SearchGoodsAdapter searchGoodsAdapter;//结果列表适配器
    private MaterialToolbar toolbar;//工具栏
    private String word;//输入的物品

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_text_input;
    }

    @Override
    protected TextContract.TextPresenter createPresenter() {
        return new TextContract.TextPresenter();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        //设置状态栏
        setStatubar(this, R.color.white, true);
        toolbar = findViewById(R.id.toolbar);
        back(toolbar, false);

        etGoods = findViewById(R.id.et_goods);
        ivClear = findViewById(R.id.iv_clear);
        rvResult = findViewById(R.id.rv_result);
        //配置适配器  设置布局和数据源
        searchGoodsAdapter = new SearchGoodsAdapter(R.layout.item_search_rv, newslistBeanList);
        //设置列表的布局管理器
        rvResult.setLayoutManager(new LinearLayoutManager(this));
        //列表item点击事件
        searchGoodsAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            showMsg("点击了" + newslistBeanList.get(position).getName());
        });
        //设置列表适配器
        rvResult.setAdapter(searchGoodsAdapter);

        //设置输入监听
        etGoods.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() < 1) {
                    ivClear.setVisibility(View.INVISIBLE);
                } else {
                    ivClear.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //设置动作监听
        etGoods.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                word = etGoods.getText().toString().trim();
                if (word.isEmpty()) {
                    showMsg("请输入物品名");
                } else {
                    if (!hasNetwork()) {
                        showMsg("请联网使用");
                        return false;
                    }
                    //显示加载弹窗
                    showLoadingDialog();
                    //控制输入法
                    controlInputMethod();
                    //请求接口
                    mPresenter.searchGoods(word);
                }
                return true;
            }
            return false;
        });
        //清空输入框内容
        ivClear.setOnClickListener(v -> {
            controlInputMethod();
            etGoods.setText("");
        });
    }

    /**
     * 控制输入法
     * 当输入法打开时关闭，关闭时弹出
     */
    private void controlInputMethod() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 搜索物品返回数据
     *
     * @param response
     */
    @Override
    public void getSearchResponse(TrashResponse response) {
        //隐藏加载弹窗
        hideLoadingDialog();
        if (response.getCode() == Constant.SUCCESS_CODE) {
            //请求成功  进行数据的渲染
            if (response.getNewslist() != null && response.getNewslist().size() > 0) {
                newslistBeanList.clear();
                newslistBeanList.addAll(response.getNewslist());
                //刷新适配器
                searchGoodsAdapter.notifyDataSetChanged();
                //保存到历史记录里
                HistoryHelper.saveHistory(response.getNewslist(), word);
            } else {
                showMsg("触及到了知识盲区");
            }
        } else {
            //显示请求接口失败的原因
            showMsg(response.getMsg());
        }
    }

    /**
     * 搜索物品失败返回
     *
     * @param throwable 异常信息
     */
    @Override
    public void getSearchResponseFailed(Throwable throwable) {
        hideLoadingDialog();
        Log.e(TAG, throwable.toString());
    }


}

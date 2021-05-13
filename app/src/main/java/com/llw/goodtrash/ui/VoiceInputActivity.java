package com.llw.goodtrash.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.llw.goodtrash.R;
import com.llw.goodtrash.adapter.SearchGoodsAdapter;
import com.llw.goodtrash.contract.VoiceContract;
import com.llw.goodtrash.bean.TrashResponse;
import com.llw.goodtrash.utils.Constant;
import com.llw.goodtrash.utils.HistoryHelper;
import com.llw.goodtrash.utils.SpeechUtil;
import com.llw.mvplibrary.mvp.MvpActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

/**
 * 语音输入物品进行垃圾分类
 *
 * @author i小灰
 * @date 2021/4/1 11:10
 */
public class VoiceInputActivity extends MvpActivity<VoiceContract.VoicePresenter> implements VoiceContract.VoiceView {

    private static final String TAG = "VoiceInputActivity";
    private MaterialToolbar toolbar;//工具栏
    private RxPermissions rxPermissions;//异步权限请求框架
    private List<TrashResponse.NewslistBean> newslistBeanList = new ArrayList<>();//数据列表
    private SearchGoodsAdapter searchGoodsAdapter;//结果列表适配器
    private RecyclerView rvResult;//结果列表
    private String word;//输入的物品

    @Override
    public void initData(Bundle savedInstanceState) {
        //页面初始化
        initView();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        rvResult = findViewById(R.id.rv_result);
        //设置页面状态栏
        setStatubar(this, R.color.white, true);
        //设置返回工具栏图标点击事件
        back(toolbar, false);
        //实例化
        rxPermissions = new RxPermissions(this);

        //初始化语音
        SpeechUtil.init(context);

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
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_voice_input;
    }

    @Override
    protected VoiceContract.VoicePresenter createPresenter() {
        return new VoiceContract.VoicePresenter();
    }

    /**
     * 悬浮按钮点击
     */
    public void voiceInput(View view) {
        //请求权限
        requestPermission();
    }

    /**
     * 请求权限
     */
    @SuppressLint("CheckResult")
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Android6.0及以上版本
            rxPermissions.request(Manifest.permission.RECORD_AUDIO).subscribe(granted -> {
                if (granted) {//权限通过
                    //开始语音监听
                    startVoiceListener();
                } else {//权限未通过
                    showMsg("权限未通过，你不能使用该功能");
                }
            });
        } else {
            //Android6.0以下无须动态请求
            //开始语音监听
            startVoiceListener();
        }
    }

    /**
     * 开始语音监听
     */
    private void startVoiceListener() {
        SpeechUtil.startDictation(goodsName -> {
            if (goodsName.isEmpty()) {
                return;
            }
            //判断字符串是否包含句号
            if (goodsName.contains("。")) {
                return;
            }
            //请求接口搜索物品的垃圾分类
            showMsg("正在搜索物品：" + goodsName);
            //语音识别结果赋值
            word = goodsName;
            mPresenter.searchGoods(goodsName);
        });
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

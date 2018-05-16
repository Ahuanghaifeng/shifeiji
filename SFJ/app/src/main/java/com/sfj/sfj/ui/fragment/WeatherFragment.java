package com.sfj.sfj.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.widget.CommentCardLayout;
import com.sfj.sfj.widget.EmptyLayout;

public class WeatherFragment extends BaseDetailFragment {

    private CommentCardLayout mWeather;

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        mWeather = (CommentCardLayout) view.findViewById(R.id.card_weather);
        mWeather.setTvName("温度");
        mWeather.setIconHead(R.mipmap.icon_temperature);
        mWeather.setHorizontalBarChart();
        //设置数据
        mWeather.setData(39,"当前温度-摄氏度");
    }
}

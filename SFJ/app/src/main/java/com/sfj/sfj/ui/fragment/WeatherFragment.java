package com.sfj.sfj.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.widget.CommentCardLayout;
import com.sfj.sfj.widget.EmptyLayout;

public class WeatherFragment extends BaseDetailFragment {

    private CommentCardLayout mTemperature,mHumidity,mSun,mAirPressure,mRain,mSoilTemperature,mSoilHumidity;
    private ScrollView mScrollView;

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,"气象站");
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
        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (mScrollView != null) {
            mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setEnabled(mScrollView.getScrollY() == 0);
                    }
                }
            });
        }
        mTemperature = (CommentCardLayout) view.findViewById(R.id.card_temperature);
        mTemperature.setTvName("温度");
        mTemperature.setIconHead(R.mipmap.icon_temperature);
        mTemperature.setData(39,"当前温度-℃");
        mTemperature.setOnClickListener(onClickListener);

        mHumidity = (CommentCardLayout) view.findViewById(R.id.card_humidity);
        mHumidity.setTvName("湿度");
        mHumidity.setIconHead(R.mipmap.icon_humidity);
        mHumidity.setData(50.5f,"当前湿度-%");

        mSun = (CommentCardLayout) view.findViewById(R.id.card_sun);
        mSun.setTvName("光照");
        mSun.setIconHead(R.mipmap.icon_sun);
        mSun.setData(13.5f,"当前光照-LUX");

        mAirPressure = (CommentCardLayout) view.findViewById(R.id.card_air_pressure);
        mAirPressure.setTvName("风速-西北风");
        mAirPressure.setIconHead(R.mipmap.icon_air_pressure);
        mAirPressure.setData(13.5f,"当前风速-m/s");

        mRain = (CommentCardLayout) view.findViewById(R.id.card_rain);
        mRain.setTvName("雨量");
        mRain.setIconHead(R.mipmap.icon_rain);
        mRain.setData(20f,"当前雨量-mm/d");

        mSoilTemperature = (CommentCardLayout) view.findViewById(R.id.card_soil_temperature);
        mSoilTemperature.setTvName("土壤温度");
        mSoilTemperature.setIconHead(R.mipmap.icon_soil_temperature);
        mSoilTemperature.setData(20f,"土壤温度-℃");

        mSoilHumidity = (CommentCardLayout) view.findViewById(R.id.card_soil_humidity);
        mSoilHumidity.setTvName("土壤湿度");
        mSoilHumidity.setIconHead(R.mipmap.icon_soil_humidity);
        mSoilHumidity.setData(20f,"土壤温度-%");
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.card_temperature:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("温度"));
                    break;
                case R.id.card_humidity:
                    break;
            }
        }
    };

    @Override
    protected void sendRequestData() {
        super.sendRequestData();
        TGBApi.doRegister("hhf","H9009910480","123",new CloudSDKHttpHandler(new ICloudSDKHttpHandler() {
            @Override
            public void onSuccess(int statusCode, String mjson) {
                Log.i("aaaaaaaaaa",mjson);
            }

            @Override
            public void onFailure(int statusCode, String responseBody, Throwable error) {

            }
        }));
    }
}

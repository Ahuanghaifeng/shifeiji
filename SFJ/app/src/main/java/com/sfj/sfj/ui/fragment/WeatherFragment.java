package com.sfj.sfj.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.bean.WeatherBean;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.ui.LoginActivity;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.CommentCardLayout;
import com.sfj.sfj.widget.EmptyLayout;

public class WeatherFragment extends BaseDetailFragment<WeatherBean> {

    private CommentCardLayout mTemperature,mHumidity,mSun,mAirPressure,mRain,mSoilTemperature,mSoilHumidity;
    private ScrollView mScrollView;
    private WeatherBean mData;

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
    protected boolean initTitle(AppToolbar appBar) {
        appBar.showBottomLine(true);
        TextView tvTitle = appBar.creatCenterView(TextView.class);
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle != null) {
                tvTitle.setText(bundle.getString(BUNDLE_KEY_FRAGMENT_TITLE, ""));
            }
        }
        Drawable drawable= getResources().getDrawable(R.mipmap.left_arrow_back);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        TextView leftTextView = appBar.creatLeftView(TextView.class);
        leftTextView.setCompoundDrawables(drawable, null, null, null);
        leftTextView.setCompoundDrawablePadding(10);
        leftTextView.setText("返回");
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        appBar.build();
        return true;

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
        mTemperature.setMaxMin(70,-30);
        mTemperature.setOnClickListener(onClickListener);

        mHumidity = (CommentCardLayout) view.findViewById(R.id.card_humidity);
        mHumidity.setTvName("湿度");
        mHumidity.setIconHead(R.mipmap.icon_humidity);
        mHumidity.setOnClickListener(onClickListener);

        mSun = (CommentCardLayout) view.findViewById(R.id.card_sun);
        mSun.setTvName("光照");
        mSun.setIconHead(R.mipmap.icon_sun);
        mSun.setMaxMin(200000,0);
        mSun.setLabelCount(5);
        mSun.setOnClickListener(onClickListener);

        mAirPressure = (CommentCardLayout) view.findViewById(R.id.card_air_pressure);
        mAirPressure.setMaxMin(30,0);
        mAirPressure.setIconHead(R.mipmap.icon_air_pressure);
        mAirPressure.setOnClickListener(onClickListener);

        mRain = (CommentCardLayout) view.findViewById(R.id.card_rain);
        mRain.setTvName("雨量");
        mRain.setIconHead(R.mipmap.icon_rain);
        mRain.setOnClickListener(onClickListener);

        mSoilTemperature = (CommentCardLayout) view.findViewById(R.id.card_soil_temperature);
        mSoilTemperature.setTvName("土壤温度");
        mSoilTemperature.setIconHead(R.mipmap.icon_soil_temperature);
        mSoilTemperature.setMaxMin(70,-30);
        mSoilTemperature.setOnClickListener(onClickListener);

        mSoilHumidity = (CommentCardLayout) view.findViewById(R.id.card_soil_humidity);
        mSoilHumidity.setTvName("土壤湿度");
        mSoilHumidity.setIconHead(R.mipmap.icon_soil_humidity);
        mSoilHumidity.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.card_temperature:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("温度","temperature"));
                    break;
                case R.id.card_humidity:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("湿度","humidity"));
                    break;
                case R.id.card_sun:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("光照","lighting"));
                    break;
                case R.id.card_air_pressure:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("风速-风向","windSpeed"));
                    break;
                case R.id.card_rain:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("雨量","rainFall"));
                    break;
                case R.id.card_soil_temperature:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("土壤温度","soilTemperature"));
                    break;
                case R.id.card_soil_humidity:
                    ((HomeFragment)getParentFragment()).start(CurveFragment.newInstance("土壤湿度","soilHumidity"));
                    break;
            }
        }
    };

    @Override
    protected void sendRequestData() {
        super.sendRequestData();
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doFertilizerWeather(username,password,mHandler);
    }

    @Override
    protected WeatherBean parseItem(String mjson) {
        mData = JSON.parseObject(mjson,WeatherBean.class);
        return mData;
    }

    @Override
    protected void executeOnLoadDataSuccess(WeatherBean item) {

        mTemperature.setTvNumber(String.valueOf(item.getClimatic().getTemperature())+"℃");
        mTemperature.setData((float) item.getClimatic().getTemperature(),"当前温度-℃");

        mHumidity.setTvNumber(String.valueOf(item.getClimatic().getHumidity())+"%");
        mHumidity.setData(item.getClimatic().getHumidity(),"当前湿度-%");

        mSun.setTvNumber(String.valueOf(item.getClimatic().getLighting())+"LUX");
        mSun.setData(item.getClimatic().getLighting(),"当前光照-LUX");

        mAirPressure.setData((float)item.getClimatic().getWindSpeed(),"当前风速-m/s");
        mAirPressure.setTvNumber(String.valueOf(item.getClimatic().getWindDirection()+item.getClimatic().getWindSpeed())+"m/s");
        mAirPressure.setTvName("风速-"+item.getClimatic().getWindDirection());

        mRain.setTvNumber(String.valueOf(item.getClimatic().getRainFall())+"mm/d");
        mRain.setData((float)item.getClimatic().getRainFall(),"当前雨量-mm/d");

        mSoilTemperature.setTvNumber(String.valueOf(item.getClimatic().getSoilTemperature())+"℃");
        mSoilTemperature.setData((float) item.getClimatic().getSoilTemperature(),"土壤温度-℃");

        mSoilHumidity.setTvNumber(String.valueOf(item.getClimatic().getSoilHumidity())+"%");
        mSoilHumidity.setData((float) item.getClimatic().getSoilHumidity(),"土壤湿度-%");
    }
}

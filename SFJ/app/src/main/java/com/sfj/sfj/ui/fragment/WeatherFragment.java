package com.sfj.sfj.ui.fragment;

import android.os.Bundle;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;

public class WeatherFragment extends BaseDetailFragment {

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
}

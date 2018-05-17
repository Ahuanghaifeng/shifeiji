package com.sfj.sfj.ui.fragment;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.widget.EmptyLayout;
import com.sfj.sfj.widget.LineChartManager;

import java.util.ArrayList;
import java.util.List;

public class CurveFragment extends BaseDetailFragment {

    private LineChart mChart;

    public static CurveFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,title);
        CurveFragment fragment = new CurveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_curve;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mChart = (LineChart) view.findViewById(R.id.spread_line_chart);
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        LineChartManager lineChartManager1 = new LineChartManager(mChart);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            xValues.add((float) i);
        }

        //设置y轴的数据()
        List<Float> yValues = new ArrayList<>();
        for (int j = 0; j <= 24; j++) {
            yValues.add((float) (Math.random() * 50));
        }


        //创建多条折线的图表
        lineChartManager1.showLineChart(xValues, yValues, "温度", Color.BLUE);
        lineChartManager1.setDescription("温度");
        lineChartManager1.setYAxis(50, 0, 11);

        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
    }

    @Override
    protected void sendRequestData() {
        super.sendRequestData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("aaaaaaaaaa","aaa");
        getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}

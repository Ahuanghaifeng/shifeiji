package com.sfj.sfj.ui.fragment;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.github.mikephil.charting.charts.LineChart;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.bean.XYBean;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.widget.EmptyLayout;
import com.sfj.sfj.widget.LineChartManager;

import java.util.ArrayList;
import java.util.List;

public class CurveFragment extends BaseDetailFragment<XYBean> {

    private LineChart mChart;
    LineChartManager lineChartManager1;
    private String mTitle;

    private static final String TYPE = "type";
    public static CurveFragment newInstance(String title,String type) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,title);
        args.putString(TYPE,type);
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
        mTitle = getArguments().getString(BUNDLE_KEY_FRAGMENT_TITLE);
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        lineChartManager1 = new LineChartManager(mChart);
    }

    @Override
    protected void sendRequestData() {
        super.sendRequestData();
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doFertilizerWeatherCharts(username,password,getArguments().getString(TYPE),mHandler);
    }

    @Override
    protected XYBean parseItem(String mjson) {
        XYBean bean = JSON.parseObject(mjson,XYBean.class);
        return bean;
    }

    @Override
    protected void executeOnLoadDataSuccess(XYBean item) {
        mSwipeRefreshLayout.setEnabled(false);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        //设置y轴的数据()
        List<Float> yValues = new ArrayList<>();
        float max = (float)item.getPoints().get(0).getY(),min = (float)item.getPoints().get(0).getY();
        for (int i=0;i<item.getPoints().size();i++){
            if (max<(float)item.getPoints().get(i).getY()){
                max = (float)item.getPoints().get(i).getY();
            }
            if (min>=(float)item.getPoints().get(i).getY()){
                min = (float)item.getPoints().get(i).getY();
            }
            lineChartManager1.setTime(item.getPoints().get(i).getX());
            xValues.add((float) i);
            yValues.add((float)item.getPoints().get(i).getY());
        }
        //创建多条折线的图表
        lineChartManager1.showLineChart(xValues, yValues, mTitle, Color.BLUE);
        lineChartManager1.setDescription(mTitle);
        if (min == max){
            min = max - max*0.05f;
            max = max + max*0.05f;
        }
        lineChartManager1.setYAxis(max, min, 8);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}

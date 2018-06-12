package com.sfj.sfj.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.sfj.sfj.R;

import java.util.ArrayList;

public class CommentCardLayout extends RelativeLayout implements OnChartValueSelectedListener{

    private TextView tvName;
    private HorizontalBarChart mBarChat;
    private ImageView mHeadImage;
    private TextView tvNumber;

    public CommentCardLayout(Context context) {
        super(context,null);
    }

    public CommentCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.view_commont_layout,null);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        mBarChat = (HorizontalBarChart) view.findViewById(R.id.mHorizontalBarChart);
        mHeadImage = (ImageView) view.findViewById(R.id.iv_head);
        tvNumber = (TextView) view.findViewById(R.id.tv_number);
        addView(view);
        setHorizontalBarChart();
    }

    public void setHorizontalBarChart(){
        //设置相关属性
        mBarChat.setOnChartValueSelectedListener(this);
        mBarChat.setDrawBarShadow(false);
        mBarChat.setDrawValueAboveBar(true);
        mBarChat.getDescription().setEnabled(false);
        mBarChat.setMaxVisibleValueCount(60);
        mBarChat.setPinchZoom(false);
        mBarChat.setDrawGridBackground(false);

        //x轴
        XAxis xl = mBarChat.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true); //不显示x轴
        xl.setDrawGridLines(false); //不绘制网格
        xl.setEnabled(false);
        mBarChat.getAxisRight().setEnabled(false);
        mBarChat.setFitBars(true);
        mBarChat.animateY(2000);

        YAxis y1 = mBarChat.getAxisLeft();
        y1.setAxisMinimum(0f);//设置x轴的最小值
        y1.setAxisMaximum(100f);//设置最大值
        y1.setGranularity(10f); //最小间隔
        y1.setLabelCount(10);  //设置X轴的显示个数

        Legend l = mBarChat.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public void setData(float number,String label){
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            entries.add(new BarEntry(0, number));
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        barDataSet.setColor(Color.parseColor("#0a94ff"));
        barDataSet.setValueTextSize(9f);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(12.0f);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);
        mBarChat.setData(data);
    }

    public void setTvName(String str){
        tvName.setText(str);
    }

    public void setIconHead(int resouce){
        mHeadImage.setImageResource(resouce);
    }

    public void setMaxMin(float max, float min){
        YAxis y1 = mBarChat.getAxisLeft();
        y1.setAxisMinimum(min);//设置x轴的最小值
        y1.setAxisMaximum(max);//设置最大值
    }

    public void setTvNumber(String str){
        tvNumber.setText(str);
    }
}

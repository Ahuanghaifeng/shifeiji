package com.sfj.sfj.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.base.BaseListFragment;
import com.sfj.sfj.bean.SoilMoisture;
import com.sfj.sfj.bean.WeatherBean;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.ui.LoginActivity;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.CommentCardLayout;
import com.sfj.sfj.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuRangFragment extends BaseDetailFragment<SoilMoisture> {

    private List<HashMap.Entry<String,Map<String, SoilMoisture.TempAndHum>>> mData = new ArrayList<>();

    public static TuRangFragment newInstance() {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,"土壤墒情");
        TuRangFragment fragment = new TuRangFragment();
        fragment.setArguments(args);
        return fragment;
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

    TRAdapter mTrAdapter;

    @Override
    protected void initView(View view) {
        super.initView(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTrAdapter = new TRAdapter();
        recyclerView.setAdapter(mTrAdapter);
        mTrAdapter.loadMoreEnd(false);
    }

    @Override
    protected SoilMoisture parseItem(String mjson) {
        SoilMoisture soilMoisture = JSON.parseObject(mjson,SoilMoisture.class);
        return soilMoisture;
    }

    @Override
    protected void sendRequestData() {
        super.sendRequestData();
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doGetSoilMoisture(username,password,mHandler);
    }

    @Override
    protected void executeOnLoadDataSuccess(SoilMoisture item) {
//        super.executeOnLoadDataSuccess(item);
        mData.clear();
        mData = item.parseList(item.getSites());
//        for(HashMap.Entry<String,Map<String, SoilMoisture.TempAndHum>> mapping:mData){
//            System.out.println(mapping.getKey()+":"+mapping.getValue());
//        }
        mTrAdapter.replaceData(mData);
//        mTrAdapter.replaceData(mData);
    }

    public class TRAdapter extends BaseQuickAdapter<HashMap.Entry<String,Map<String, SoilMoisture.TempAndHum>>,BaseViewHolder>{

        public TRAdapter() {
            super(R.layout.item_turang);
        }

        @Override
        protected void convert(BaseViewHolder helper, Map.Entry<String, Map<String, SoilMoisture.TempAndHum>> item) {
            helper.setIsRecyclable(false);
            helper.setText(R.id.tv_name,(helper.getLayoutPosition()+1)+"#");
            for (Map.Entry<String,SoilMoisture.TempAndHum> map:item.getValue().entrySet()){
//                String number = "<big>"+map.getValue().getTemperature()+"</big>";
//                String number2 = "<big>"+map.getValue().getHumidity()+"</big>";
                String number = map.getValue().getTemperature()+"";
                String number2 = map.getValue().getHumidity()+"";
                if("1层".equals(map.getKey())){
                    String str = "1层     温度"+number+"℃    湿度"+number2+"%";
                    helper.setText(R.id.tv_1, Html.fromHtml(str));
                }
                if ("2层".equals(map.getKey())){
                    String str = "2层     温度"+number+"℃    湿度"+number2+"%";
                    helper.setText(R.id.tv_2,Html.fromHtml(str));
                }
                if ("3层".equals(map.getKey())){
                    String str = "3层     温度"+number+"℃    湿度"+number2+"%";
                    helper.setText(R.id.tv_3,Html.fromHtml(str));
                }
            }
        }
    }

    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_turang;
    }
}

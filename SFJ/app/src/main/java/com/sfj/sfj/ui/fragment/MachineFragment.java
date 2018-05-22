package com.sfj.sfj.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.bean.Sfj_Bean;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.utils.ToastUtils;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.CommentCardLayoutSHJ;
import com.sfj.sfj.widget.EmptyLayout;
import com.sfj.sfj.widget.SwitchGameDialog;

public class MachineFragment extends BaseDetailFragment<Sfj_Bean> {

    SwitchGameDialog gameDialog;
    private TextView ecData,phData,shllData,ljllData,gdylData,ywData;

    public static MachineFragment newInstance() {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,"施肥机");
        MachineFragment fragment = new MachineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean initTitle(AppToolbar appBar) {

        TextView rightText = appBar.creatRightView(TextView.class,new int[]{0,0,0,0},new int[]{0,0,10,0});
        rightText.setTextColor(Color.parseColor("#000000"));
        rightText.setText("切换");
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameDialog==null){
                    gameDialog = new SwitchGameDialog(getContext());
                }
                gameDialog.show();
            }
        });
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
                getActivity().finish();
            }
        });
        appBar.build();
        return true;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ecData = (TextView) view.findViewById(R.id.tv_ec_data);
        phData = (TextView) view.findViewById(R.id.tv_ph_data);
        shllData = (TextView) view.findViewById(R.id.tv_ssll_data);
        ljllData = (TextView) view.findViewById(R.id.tv_ljll_data);
        gdylData = (TextView) view.findViewById(R.id.tv_gdyl_data);
        ywData = (TextView) view.findViewById(R.id.tv_yw_data);
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
    }

    @Override
    protected void sendRequestData() {
        String username = AppInfoManager.getInstance().getUserInfo().getUsername();
        String password = AppInfoManager.getInstance().getUserInfo().getPassword();
        TGBApi.doFertilizerInfo(username,password,"",mHandler);
    }

    @Override
    protected void executeOnLoadDataSuccess(Sfj_Bean item) {
        refreshUi(item);
    }

    @Override
    protected Sfj_Bean parseItem(String mjson) {
        Sfj_Bean sfjData = JSON.parseObject(mjson,Sfj_Bean.class);
        return sfjData;
    }

    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_machine;
    }

    public void refreshUi(Sfj_Bean bean){
        if(bean!=null&&bean.getTimeData()!=null){
            ecData.setText(String.valueOf(bean.getTimeData().getEc()));
            phData.setText(String.valueOf(bean.getTimeData().getPh()));
            shllData.setText(String.valueOf(bean.getTimeData().getRateFlow()));
            ljllData.setText(String.valueOf(bean.getTimeData().getTotalIrrigation()));
            gdylData.setText(bean.getTimeData().getPipePressure());
            ywData.setText(String.valueOf(bean.getTimeData().getLiquidLevel()));
        }
    }
}

package com.sfj.sfj.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.widget.AppToolbar;
import com.sfj.sfj.widget.CommentCardLayoutSHJ;
import com.sfj.sfj.widget.EmptyLayout;
import com.sfj.sfj.widget.SwitchGameDialog;

public class MachineFragment extends BaseDetailFragment {

    SwitchGameDialog gameDialog;
    private CommentCardLayoutSHJ mEc,mPh,mSsLl,mLjLl,mKpa,mYw;

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
        mEc = (CommentCardLayoutSHJ) view.findViewById(R.id.sfj_ec);
        mEc.setData(30,"ec-us/cm");

        mPh = (CommentCardLayoutSHJ) view.findViewById(R.id.sfj_ph);
        mPh.setData(7.9f,"ph");

        mSsLl = (CommentCardLayoutSHJ) view.findViewById(R.id.sfj_ssll);
        mSsLl.setData(30,"实时流量-m³/h");

        mLjLl = (CommentCardLayoutSHJ) view.findViewById(R.id.sfj_ljll);
        mLjLl.setData(90,"累积流量-m³/h");

        mKpa = (CommentCardLayoutSHJ) view.findViewById(R.id.sfj_kpa);
        mKpa.setData(70,"管道压力-kpa");

        mYw = (CommentCardLayoutSHJ) view.findViewById(R.id.sfj_yw);
        mYw.setData(40,"液位-mm");
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        mSwipeRefreshLayout.setRefreshing(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
    }

    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_machine;
    }
}

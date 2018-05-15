package com.sfj.sfj.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfj.sfj.R;
import com.sfj.sfj.widget.AppToolbar;

import me.yokeyword.fragmentation.app.SupportFragment;

/**
 * Created by wangyu on 2018/1/8.
 */

public abstract class BaseFragment extends SupportFragment {


    protected RelativeLayout mRootView;

    public static final String BUNDLE_KEY_FRAGMENT_TITLE = "";

    protected abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = new RelativeLayout(_mActivity);

        View view = inflater.inflate(getLayoutId(), container, false);
        mRootView.addView(view);
        AppToolbar appBar = new AppToolbar(getContext());
        appBar.attachFragment(mRootView);
        if (!initTitle(appBar)) {
            mRootView.removeView(appBar);
        } else {
            appBar.setFitsSystemWindows(true);
        }
        return mRootView;
    }

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
                onBackEvent();
            }
        });
        appBar.build();
        return true;
    }

    protected void onBackEvent() {
        pop();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        if (getArguments() == null) {
            initData(new Bundle());
        } else {
            initData(getArguments());
        }
    }

    protected void initData(Bundle bundle) {

    }

    protected void initView(View view) {
    }


}

package com.sfj.sfj.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;
import com.sfj.sfj.widget.EmptyLayout;

public class ControlFragment extends BaseDetailFragment {

    public static ControlFragment newInstance() {

        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,"控制");
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_control;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(false);
        mEmptyLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
    }
}

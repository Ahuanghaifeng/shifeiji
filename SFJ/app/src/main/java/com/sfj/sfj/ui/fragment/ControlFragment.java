package com.sfj.sfj.ui.fragment;

import android.os.Bundle;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;

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
}

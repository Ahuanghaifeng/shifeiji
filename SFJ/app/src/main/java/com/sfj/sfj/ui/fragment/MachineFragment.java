package com.sfj.sfj.ui.fragment;

import android.os.Bundle;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailFragment;

public class MachineFragment extends BaseDetailFragment {


    public static MachineFragment newInstance() {
        Bundle args = new Bundle();
        args.putString(BUNDLE_KEY_FRAGMENT_TITLE,"施肥机");
        MachineFragment fragment = new MachineFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getDetailLayoutId() {
        return R.layout.fragment_machine;
    }
}

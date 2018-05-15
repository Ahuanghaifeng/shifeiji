package com.sfj.sfj.ui;

import android.os.Bundle;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseActivity;
import com.sfj.sfj.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance(""));
        }
    }
}

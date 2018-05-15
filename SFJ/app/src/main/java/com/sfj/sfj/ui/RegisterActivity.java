package com.sfj.sfj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseActivity;
import com.sfj.sfj.base.BaseDetailActivity;
import com.sfj.sfj.widget.AppToolbar;

/**
 * Created by 黄海峰 on 2018/5/15.
 */

public class RegisterActivity extends BaseDetailActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        super.initView();
        AppToolbar toolbar = (AppToolbar) findViewById(R.id.toolbar);
        initTitle(toolbar,"注册");
    }
}

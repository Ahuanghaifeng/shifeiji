package com.sfj.sfj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailActivity;
import com.sfj.sfj.widget.AppToolbar;

/**
 * Created by 黄海峰 on 2018/5/15.
 */

public class LoginActivity extends BaseDetailActivity{

    private AppToolbar toolbar;
    private TextView textView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        toolbar = (AppToolbar) findViewById(R.id.toolbar);
        initTitle(toolbar,"登录");
    }
}

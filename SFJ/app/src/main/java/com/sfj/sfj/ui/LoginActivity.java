package com.sfj.sfj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseDetailActivity;
import com.sfj.sfj.widget.AppToolbar;

/**
 * Created by 黄海峰 on 2018/5/15.
 */

public class LoginActivity extends BaseDetailActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        AppToolbar toolbar = (AppToolbar) findViewById(R.id.toolbar);
        initTitle(toolbar,"登录");
        TextView register = (TextView) findViewById(R.id.tv_register);
        register.setOnClickListener(onClickListener);
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_register:
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bt_login:
                    break;
            }
        }
    };
}

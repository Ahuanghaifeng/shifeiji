package com.sfj.sfj.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sfj.sfj.R;
import com.sfj.sfj.base.AppInfoManager;
import com.sfj.sfj.base.BaseDetailActivity;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.bean.UserInfo;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.utils.ToastUtils;
import com.sfj.sfj.widget.AppToolbar;

/**
 * Created by 黄海峰 on 2018/5/15.
 */

public class LoginActivity extends BaseDetailActivity{

    private EditText username,password;

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
        Button login = (Button) findViewById(R.id.bt_login);
        login.setOnClickListener(onClickListener);

        username = (EditText) findViewById(R.id.et_account);
        password = (EditText) findViewById(R.id.et_password);
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
                    login();
                    break;
            }
        }
    };

    public void login(){
        final String name = username.getText().toString();
        final String pw = password.getText().toString();
        if (TextUtils.isEmpty(name)){
            ToastUtils.showShortToast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pw)){
            ToastUtils.showShortToast("密码不能为空");
            return;
        }
        TGBApi.doLogin(name,pw,new CloudSDKHttpHandler(new ICloudSDKHttpHandler() {
            @Override
            public void onSuccess(int statusCode, String mjson) {
                ApiBean apiBean = JSON.parseObject(mjson,ApiBean.class);
                if ("200".equals(apiBean.getCode())){
                    AppInfoManager.getInstance().setLogin(true);
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUsername(name);
                    userInfo.setPassword(pw);
                    AppInfoManager.getInstance().saveUserInfo(userInfo);
                    ToastUtils.showShortToast("登录成功");
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                ToastUtils.showShortToast(apiBean.getMsg());
            }

            @Override
            public void onFailure(int statusCode, String responseBody, Throwable error) {
                ToastUtils.showShortToast(R.string.error_view_network);
            }
        }));
    }
}

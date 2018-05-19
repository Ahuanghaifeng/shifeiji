package com.sfj.sfj.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.sfj.sfj.R;
import com.sfj.sfj.base.BaseActivity;
import com.sfj.sfj.base.BaseDetailActivity;
import com.sfj.sfj.bean.ApiBean;
import com.sfj.sfj.net.CloudSDKHttpHandler;
import com.sfj.sfj.net.ICloudSDKHttpHandler;
import com.sfj.sfj.net.TGBApi;
import com.sfj.sfj.utils.ToastUtils;
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
        final EditText username = (EditText) findViewById(R.id.et_account);
        final EditText password = (EditText) findViewById(R.id.et_password);
        final EditText code = (EditText) findViewById(R.id.et_code);
        Button register = (Button) findViewById(R.id.bt_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pw = password.getText().toString();
                String co = code.getText().toString();
                if (TextUtils.isEmpty(name)){
                    ToastUtils.showShortToast("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pw)){
                    ToastUtils.showShortToast("密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(co)){
                    ToastUtils.showShortToast("注册码不能为空");
                    return;
                }
                TGBApi.doRegister(name,pw,co,new CloudSDKHttpHandler(new ICloudSDKHttpHandler() {
                    @Override
                    public void onSuccess(int statusCode, String mjson) {
                        ApiBean mApiBean = JSON.parseObject(mjson,ApiBean.class);
                        if ("200".equals(mApiBean.getCode())){
                            ToastUtils.showShortToast("注册成功,请登录");
                            finish();
                        }else{
                            ToastUtils.showShortToast(mApiBean.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String responseBody, Throwable error) {
                        ToastUtils.showShortToast(R.string.error_view_network);
                    }
                }));
            }
        });


    }


}

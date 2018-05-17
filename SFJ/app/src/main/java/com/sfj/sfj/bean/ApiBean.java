package com.sfj.sfj.bean;

import android.text.TextUtils;

/**
 * Created by wangyu on 2018/1/10.
 */

public class ApiBean {

    public final static String OK = "200";


    private String code = OK;
    private String msg = "";
    private String data;
    public static boolean isCheckOk(String code) {
        return TextUtils.equals(code, OK) ? true : false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

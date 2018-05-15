package com.sfj.sfj.base;

import com.sfj.sfj.base.manager.SharedPreferencesManager;

/**
 * Created by wangyu on 2017/7/20.
 */

public class AppInfoManager {

    private static AppInfoManager _instance = null;

    public static AppInfoManager getInstance() {
        if (_instance == null) {
            _instance = new AppInfoManager();
        }
        return _instance;
    }

    private boolean isLogin = false;
    private String IS_LOGIN = "is_login";

    public boolean isLogin(){
        isLogin =  SharedPreferencesManager.getInstance().getBoolean(IS_LOGIN, false,true);
        return isLogin;
    }

    public void setLogin(boolean login){
        SharedPreferencesManager.getInstance().saveValue(IS_LOGIN,login,true);
    }

}

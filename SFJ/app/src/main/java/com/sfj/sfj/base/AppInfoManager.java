package com.sfj.sfj.base;

import com.sfj.sfj.base.manager.SharedPreferencesManager;
import com.sfj.sfj.bean.UserInfo;

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
    private UserInfo userInfo;
    private String USER_INFO = "user_info";

    public boolean isLogin(){
        isLogin =  SharedPreferencesManager.getInstance().getBoolean(IS_LOGIN, false,true);
        return isLogin;
    }

    public void setLogin(boolean login){
        SharedPreferencesManager.getInstance().saveValue(IS_LOGIN,login,true);
    }

    public void saveUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
        SharedPreferencesManager.getInstance().saveObject(USER_INFO,userInfo,true);
    }

    public UserInfo getUserInfo(){
        if (userInfo == null){
            userInfo = (UserInfo) SharedPreferencesManager.getInstance().getObject(USER_INFO,true);
        }
        return userInfo;
    }

}

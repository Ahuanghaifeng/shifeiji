package com.sfj.sfj.base;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sfj.sfj.base.manager.SharedPreferencesManager;
import com.sfj.sfj.net.ApiServiceItem;


/**
 * Created by wangyu on 2017/4/18.
 */

public class AppContext extends BaseApplication {


    private static AppContext instance;


    public static AppContext getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }



    private void init() {
        Fresco.initialize(this);
        SharedPreferencesManager.getInstance().init(context());
        ApiServiceItem.getInstance().init();
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler(this));
    }

}

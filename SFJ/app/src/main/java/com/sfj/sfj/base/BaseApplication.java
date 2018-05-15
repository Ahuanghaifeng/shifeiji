package com.sfj.sfj.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by wangyu on 2017/4/19.
 */

public class BaseApplication extends Application {
    static Context _context;
    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();

    }

    public static  Context context() {
        return _context;
    }
}

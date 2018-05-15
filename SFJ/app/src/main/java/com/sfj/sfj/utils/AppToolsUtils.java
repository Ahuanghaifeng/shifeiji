package com.sfj.sfj.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by haifeng on 2018/3/5.
 */

public class AppToolsUtils {
    /**
     * 安装应用.
     *
     * @param context
     * @param file
     */
    public static void installLoadedApkFile(Context context, File file) {
        Intent installIntent = new Intent();
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.setAction(Intent.ACTION_VIEW);
        if(Build.VERSION.SDK_INT>=24){ //如果是安卓7.0及以上
            //在androidmanifest里面配置的路径
            Uri apkUri = FileProvider.getUriForFile(context, "com.yxsq.fuyun.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        }else{
            installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(installIntent);
    }

    /**
     *
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     *
     */
    public static String getCurrentTime() {
        try {
            Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sDateFormat.format(curDate);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1970-01-01 00:00:00";
    }
}

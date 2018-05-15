package com.sfj.sfj.utils;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.sfj.sfj.base.BaseApplication;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * Created by wangyu on 2017/4/19.
 */

public class TDevice {

    /**
     * 没有网络
     */
    public static final int NETWORKTYPE_INVALID = 0;
    /**
     * wap网络
     */
    public static final int NETWORKTYPE_WAP = 1;
    /**
     * wifi网络
     */
    public static final int NETWORKTYPE_WIFI = 4;
    public static boolean GTE_HC;
    public static boolean GTE_ICS;
    public static boolean PRE_HC;
    public static float displayDensity = 0.0F;
    private static Boolean _hasBigScreen = null;
    private static Boolean _hasCamera = null;
    private static Boolean _isTablet = null;
    private static Integer _loadFactor = null;
    private static int _pageSize = -1;

    public static final String NETTYPE_WIFI = "WIFI";
    public static final String NETTYPE_4G = "4G";
    public static final String NETTYPE_3G = "3G";
    public static final String NETTYPE_2G = "2G";

    static {
        GTE_ICS = Build.VERSION.SDK_INT >= 14;
        GTE_HC = Build.VERSION.SDK_INT >= 11;
        PRE_HC = Build.VERSION.SDK_INT < 11;
    }

    public TDevice() {
    }

    public static float getDensity() {
        if (displayDensity == 0.0)
            displayDensity = getDisplayMetrics().density;
        return displayDensity;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) BaseApplication.context().getSystemService(
                Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(
                displaymetrics);
        return displaymetrics;
    }

    //获取ｃｐｕ信息
    public static String getCpuName(){

        try{
            FileReader fr= new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+",2);
            fr.close();
            return array[1];
        }catch (Exception e){

            e.printStackTrace();
        }
        return "";
    }
    public static String getCpuProduct(){
        return   TDevice.getprop("ro.product.cpu.abi", "");
    }


    /**
     * 获取系统指定属性值
     * @param key
     * @param defaultValue
     * @return 调用方法 getprop("ro.hardware","")
     */
    public static String getprop(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String) (get.invoke(c, key, "unknown"));
        } catch (Exception e) {
//            Log.d("OSUtil", "get property error, " + e.getMessage());
        }
//        Log.d("OSUtil", "get property, " + key + " = " + value);
        return value;
    }

    public static boolean isPackageExist(String pckName) {
        try {
            PackageInfo pckInfo = BaseApplication.context().getPackageManager()
                    .getPackageInfo(pckName, 0);
            if (pckInfo != null)
                return true;
        } catch (PackageManager.NameNotFoundException e) {
            TLog.e(e.getMessage());
        }
        return false;
    }

    public static PackageInfo getPackageInfo(String pckName) {
        try {
            return BaseApplication.context().getPackageManager()
                    .getPackageInfo(pckName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            TLog.e(e.getMessage());
        }
        return null;
    }

    public static int getVersionCode() {
        int versionCode = 0;
        try {
            versionCode = BaseApplication.context().getPackageManager()
                    .getPackageInfo(BaseApplication.context().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            versionCode = 0;
        }
        return versionCode;
    }

    public static String getVersionName() {
        String name = "";
        try {
            name = BaseApplication
                    .context()
                    .getPackageManager()
                    .getPackageInfo(BaseApplication.context().getPackageName(),
                            0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            name = "";
        }
        return name;
    }

    public static String getIMEI() {
        String reslut="";
        try {
            TelephonyManager tel = (TelephonyManager) BaseApplication.context()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if(tel!=null){
                String deviceId = tel.getDeviceId();
                if(!TextUtils.isEmpty(deviceId)){
                    reslut= URLEncoder.encode(deviceId,"utf-8");
                }
            }



        } catch (Exception e) {

            e.printStackTrace();
        }
        return reslut;
    }

    public static String getIMEIEncode() {
        String reslut="";
        try {
            TelephonyManager tel = (TelephonyManager) BaseApplication.context()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if(tel!=null){
                reslut = tel.getDeviceId();
            }



        } catch (Exception e) {

            e.printStackTrace();
        }
        return reslut;
    }

    /**
     * 获取网络状态，wifi,wap,2g,3g.
     *
     * @return int 网络状态        *{@link #NETWORKTYPE_INVALID},{@link #NETWORKTYPE_WAP}* <p>{@link #NETWORKTYPE_WIFI}
     */
    public static int getNetWorkType() {
        int mNetWorkType = NETWORKTYPE_INVALID;
        ConnectivityManager manager = (ConnectivityManager) BaseApplication.context().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = NETWORKTYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                mNetWorkType = NETWORKTYPE_WAP;
            }
        } else {
            mNetWorkType = NETWORKTYPE_INVALID;
        }
        return mNetWorkType;
    }

    /**
     * 获取application中的指定meta-data值
     *
     * @param ctx
     * @param key
     * @return
     */
    public static String getAppMetaData(Context ctx, String key) {
        String resultData = "";
        if (null == ctx || TextUtils.isEmpty(key))
            return resultData;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (null != packageManager) {
                ApplicationInfo appInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
                if (null != appInfo && null != appInfo.metaData) {
                    resultData = appInfo.metaData.getString(key);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }


    /**
     * 网络类型
     *
     * @return
     */
    public static String getNetworkState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.context().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return info.getTypeName();
        }
        return null;
    }


    /**
     * 手机MAC地址
     *
     * @return
     */
//    public static String getMacAddress() {
//        WifiManager wifi = (WifiManager) BaseApplication.context().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo info = wifi.getConnectionInfo();
//        return info.getMacAddress();
//    }

    /**
     * 获取手机卡识别码
     *
     * @return
     */
    public static String getSimSerialNumber() {
        TelephonyManager tm = (TelephonyManager) BaseApplication.context().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String simSerialNumber = tm.getSubscriberId();//tm.getSimSerialNumber();
        return simSerialNumber;
    }

    public static String getAppName() {

        PackageManager manager = BaseApplication.context().getPackageManager();

        PackageInfo info;

        try {
            info = manager.getPackageInfo(BaseApplication.context().getPackageName(), 0);
            return info.applicationInfo.loadLabel(manager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "null";
    }

    // 手机系统版本
    public static String getSystemVersion() {

        return Build.VERSION.RELEASE;

    }

    // 手机系统信息
    public static String getSystemInfo() {

        return "android";

    }

    // 机器设备类别
    public static String getMachineSort() {

        return Build.MODEL;
    }

    // 机型信息（机器所有者名称）
    public static String getMachineInfo() {

        return "null";
    }

    // 获取手机运营商
    public static String getOperatorType() {

        TelephonyManager phoneManager = (TelephonyManager)BaseApplication
                .context().getSystemService(Context.TELEPHONY_SERVICE);

        String operatorCode = phoneManager.getSimOperator();

        String networkOperator = "N/A";

        if (operatorCode != null) {

            // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
            if (operatorCode.equals("46000") || operatorCode.equals("46002")) {

                networkOperator = "中国移动";

            }
            if (operatorCode.equals("46001")) {

                networkOperator = "中国联通";
            }

            if (operatorCode.equals("46003")) {

                networkOperator = "中国电信";
            }

        }

        return networkOperator;

    }

    // 获取手机分辨率
    public static String getResolution() {

        DisplayMetrics display = BaseApplication.context().getResources()
                .getDisplayMetrics();

        int screenWidthPx = display.widthPixels;
        int screenHeightPx = display.heightPixels;

        String resolutionString = String.format("%d*%d",screenWidthPx,screenHeightPx);

        return resolutionString;

    }

    public static String getBuildVersionName() {
        return "null";
    }

    /**
     * 获取当前网络类型
     *
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getNetType() {

        String strNetworkType = "NULL";

        ConnectivityManager connectivityManager = (ConnectivityManager)BaseApplication.context()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = NETTYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                // TD-SCDMA networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: // api<8 : replace by
                        // 11
                        strNetworkType = NETTYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: // api<9 : replace by
                        // 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD: // api<11 : replace by
                        // 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP: // api<13 : replace by
                        // 15
                        strNetworkType = NETTYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE: // api<11 : replace by
                        // 13
                        strNetworkType = NETTYPE_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA")
                                || _strSubTypeName.equalsIgnoreCase("WCDMA")
                                || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = NETTYPE_3G;
                        } else {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }
            }
        }

        return strNetworkType;
    }

    //判断手机是否有smartBar
    public static boolean hasSmartBar() {
        try {
            // 可用反射调用Build.hasSmartBar()
            Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
            return ((Boolean) method.invoke(null)).booleanValue();
        } catch (Exception e) {
        }
        return false;
    }
    public static int getSmartBarHeight(Context context, ActionBar actionbar)
    {
        if (actionbar != null)
            try {
                Class c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("mz_action_button_min_height");
                int height = Integer.parseInt(field.get(obj).toString());
                return context.getResources().getDimensionPixelSize(height);
            } catch (Exception e) {
                e.printStackTrace();

                actionbar.getHeight();
            }
        return 0;
    }

    public static boolean hasInternet() {
        ConnectivityManager cm = (ConnectivityManager) BaseApplication.context()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }

    /**
     * 清除应用缓存的用户数据，同时停止所有服务和Alarm定时task
     * String cmd = "pm clear " + packageName;
     * String cmd = "pm clear " + packageName  + " HERE";
     * Runtime.getRuntime().exec(cmd)
     * @param packageName
     * @return
     */
    public static Process clearAppUserData(String packageName) {
        Process p = execRuntimeProcess("pm clear " + packageName);
        if (p == null) {
            Log.i("aaaa","Clear app data packageName:" + packageName
                    + ", FAILED !");
        } else {
            Log.i("aaaa","Clear app data packageName:" + packageName
                    + ", SUCCESS !");
        }
        return p;
    }

    public static Process execRuntimeProcess(String commond) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(commond);
        } catch (IOException e) {
            Log.i("aaaaa","exec Runtime commond:" + commond + ", IOException" + e);
            e.printStackTrace();
        }
        Log.i("aaaaa","exec Runtime commond:" + commond + ", Process:" + p);
        return p;
    }

}

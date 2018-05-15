package com.sfj.sfj.utils;


/**
 * Created by wangyu on 2017/4/19.
 */

public class SignUtils {


    private static final String APP_KEY = "";


    public static String doSign(int unionID, int siteID, int gameID) {
        return String.format("%s%d%s%s%s%s%s%s%s%s%d%s%s%d%s", null, gameID, TDevice.getAppName()
                        + "-android", TDevice
                        .getVersionName(), TDevice.getIMEI(),
                TDevice.getMachineInfo(), TDevice
                        .getMachineSort(), TDevice
                        .getNetWorkType(), TDevice.getOperatorType(),
                TDevice.getDensity(), siteID, TDevice
                        .getSystemInfo(), TDevice.getSystemVersion(),
                unionID, APP_KEY);
    }

    public static String doSign(String userID, String payType, String rmb,String proID) {
        return String.format("%s%s%s%s%s", userID, payType, rmb, proID,
                APP_KEY);
    }

    public static String doSign(String str1, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s%s%s%s%s%s", str1,
                str2, str3, str4, str5,
                str6);
    }

    public static String doSign(String str1, String str2, String str3) {
        return String.format("%S%s%s", str1, str2,
                str3);
    }

    public static String doSign(String str1, int int1, String str2, String str3, String str4) {
        return String.format("%s%d%s%s%s", str1, int1, str2, str3, str4);
    }

    public static String doSign(String str1, String int1, String str2, String str3, String str4,
                                String str5, String str6, String str7) {
        return String.format("%s%s%s%s%s%s%s%s", str1, int1, str2, str3, str4, str5, str6, str7);
    }
}

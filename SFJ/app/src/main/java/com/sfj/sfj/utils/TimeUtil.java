package com.sfj.sfj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
//        long lt = new Long(s);
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }
}

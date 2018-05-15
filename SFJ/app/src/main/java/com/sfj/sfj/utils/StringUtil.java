package com.sfj.sfj.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wangyu on 2017/4/19.
 */

public class StringUtil {

    public static String encodeStringByUTF(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }

    /**
     * Get String of utf-8
     *
     * @return Formed string
     */
    public static String getUTF8XMLString(String text) {
        String xmString = "";
        String xmlUTF8="";
        try {
            xmString = new String(text.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return xmlUTF8;
    }
}

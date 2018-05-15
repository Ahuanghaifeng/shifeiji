package com.sfj.sfj.net;

/**
 * Created by wangyu on 2017/4/18.
 */

public interface ICloudSDKHttpHandler {
    void onSuccess(int statusCode, String mjson);

    void onFailure(int statusCode, String responseBody, Throwable error);
}

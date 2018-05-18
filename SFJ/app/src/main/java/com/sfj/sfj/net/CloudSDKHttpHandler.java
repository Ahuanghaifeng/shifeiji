package com.sfj.sfj.net;


import com.sfj.sfj.utils.Logger;
import com.zhy.http.okhttp.BuildConfig;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;



public class CloudSDKHttpHandler extends Callback<Response>{
    protected ICloudSDKHttpHandler mHttpHandler;
    protected String cacheKey;
    public CloudSDKHttpHandler(ICloudSDKHttpHandler mHttpHandler) {
        this.mHttpHandler = mHttpHandler;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        if (null != mHttpHandler) {
            mHttpHandler.onFailure(id, "", e);
        }
    }

    /**
     * BaseApiHelper調用執行反饋
     * @param id
     * @param mResponse
     */
    public void onResponse(String mjson, int id, Response mResponse) {
        mResponse.headers();
//        if (BuildConfig.DEBUG){
            Request request = mResponse.request();
            String msg = "%s\nurl->" + request.url()
                    + "\nheaders->" + request.headers()
                    + "\nresponse code->" + mResponse.code()
                    + "\nresponse headers->" + mResponse.headers()
                    + "\nbody->" + mjson;
            Logger.i(msg);
//        }
        callbackHandler(mjson, id);
    }

    private void callbackHandler(String mjson, int id){
        try {
            if (null != mHttpHandler)
                mHttpHandler.onSuccess(id, mjson);
        } catch (Exception ex) {
            Logger.e("GameCatHttpHandler", ex);
            if (null != mHttpHandler) {
                mHttpHandler.onFailure(id, "", new Exception("接口格式异常"));
            }
        }
    }

    @Override
    public void onResponse(Response response, int id) {
        String mjson = "";
        try {
            mjson = response.body().string();
            callbackHandler(mjson, id);
        } catch (IOException e) {
            Logger.e("GameCatHttpHandler", e);
            if (null != mHttpHandler) {
                mHttpHandler.onFailure(id, "", new Exception("接口格式异常"));
            }
        }
    }

    @Override
    public Response parseNetworkResponse(Response response, int id) throws IOException {
        return response;
    }

    public void onPostResponse(String response) {
        mHttpHandler.onSuccess(0, response);
    }
}

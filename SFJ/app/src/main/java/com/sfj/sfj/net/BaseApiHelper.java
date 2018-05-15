package com.sfj.sfj.net;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BaseApiHelper {
    private volatile static BaseApiHelper mInstance;
    final Map<String, Retrofit> serverList = new ConcurrentHashMap<String, Retrofit>();

    private static OkHttpClient okHttpClient;

    public static BaseApiHelper getInstance() {
        if (null == mInstance) {
            mInstance = new BaseApiHelper();
        }
        return mInstance;
    }

    private BaseApiHelper() {
        if (null == okHttpClient) {
            okHttpClient = ApiHttpClient.getInstance().getHttpClient();
        }
        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 新接口参数格式
     *
     * @param params
     * @param isEncrypt
     * @param handler
     * @return
     */
    public Map<String, String> getParams(Map<String, String> params, boolean isEncrypt, CloudSDKHttpHandler handler) {
//        Map<String, String> mparams = new LinkedHashMap<>();
//        mparams.put("token", AppContext.getInstance().getToKen());
//        mparams.put("apiVersion", ApiServiceBean.SERVICE_API_VERSION);
//        mparams.put("appKey", ApiServiceBean.SERVICE_APP_KEY);
//        mparams.put("devicStatue", ApiClientHelper.getDevicStatue(AppContext.getInstance()));
//        if (!isEncrypt) {//默认加密，加密则不用传此字段，不加密
//            mparams.put("isEncrypt", isEncrypt ? "1" : "0");    //是否进行加密（0:否 1:是）
//        }

//        String encryptJson = JSON.toJSONString(params);
//        try {
//            if (isEncrypt) {
//                mparams.put("data", EncrypAES.AesEncrypt(AppContext.getInstance().getApi().getResKey(), encryptJson));
//            } else {
//                mparams.put("data", encryptJson);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            handler.onError(null, new Exception("处理密文异常"), 0);
//        }
        return params;
    }


    /**
     * 初使化服务器列表
     */
    public void initHostList(Map<String, String> mDomainMap) {
        for (Map.Entry<String, String> entry : mDomainMap.entrySet()) {
            serverList.put(entry.getKey(), createRetrofit(entry.getValue()));
        }
    }

    public Retrofit createRetrofit(String host) {
        Retrofit retrofit = null;
        try {
            OkHttpClient client = okHttpClient;
            retrofit = new Retrofit.Builder()
                    .baseUrl(host)
                    .client(client)
                    .addConverterFactory(new ToStringConverterFactory())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrofit;
    }

    public <T> T getApiServer(Retrofit mRetrofit, Class<T> service) {
        return mRetrofit.create(service);
    }

    public <T> T getApiServer(String urlTag, Class<T> service) {
        Retrofit retrofit = serverList.get(urlTag);

        //for test
        if(retrofit == null){
            retrofit = createRetrofit(urlTag);
        }

        return retrofit.create(service);
    }

    public String getApiServerHost(String urlTag) {
        Retrofit retrofit = serverList.get(urlTag);
        return retrofit.baseUrl().toString();
    }

    public String getApiServerDomain(String urlTag) {
        Retrofit retrofit = serverList.get(urlTag);
        return retrofit.baseUrl().host();
    }


    public void callEnqueue(Call<ResponseBody> call, final CloudSDKHttpHandler handler) {

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                if (code == 200) {
                    try {
                        String mjson = response.body().string();
                        handler.onResponse(mjson, code, response.raw());
                    } catch (IOException e) {
                        handler.onError(null, e, code);
                    }
                } else {
                    handler.onError(null, new Exception("response error code"), code);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Exception exception = new Exception(t.getMessage());
                handler.onError(null, exception, 0);
            }
        });
    }

    @Deprecated
    public void postHttp(String url, Map<String, String> params, Map<String, String> headers,
                         CloudSDKHttpHandler handler) {
        OkHttpUtils.post().url(url).params(params).headers(headers).build().execute(handler);
    }

    @Deprecated
    public void getHttp(String url, Map<String, String> params, Map<String, String> headers,
                        CloudSDKHttpHandler handler) {
        OkHttpUtils.get().url(url).params(params).headers(headers).build().execute(handler);
    }
}

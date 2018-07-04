package com.sfj.sfj.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;

import com.sfj.sfj.base.AppContext;
import com.sfj.sfj.base.BaseApplication;
import com.sfj.sfj.utils.Logger;
import com.sfj.sfj.utils.TDevice;
import com.zhy.http.okhttp.BuildConfig;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by wangyu on 2017/4/18.
 */
public class ApiHttpClient {
    protected OkHttpClient okHttpClient;
    private volatile static ApiHttpClient mApiHttpClient;

    public static ApiHttpClient getInstance(){
        if(mApiHttpClient == null){
            mApiHttpClient = new ApiHttpClient();
        }
        return mApiHttpClient;
    }

    private Headers getDefaultHeader() {
        return Headers.of(ApiHttpClient.getApiHeader()).newBuilder().build();
    }

    public OkHttpClient getHttpClient() {
        if (null == okHttpClient) {
            Interceptor mTokenInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    String sToken = "";
                    if (TextUtils.isEmpty(sToken)) {
                        sToken = "";

                    }
                    final Headers mHeaders = getDefaultHeader();
                    Request authorised = chain.request().newBuilder()
                            .headers(mHeaders)
                            .addHeader("token", sToken)
                            .build();
                    return chain.proceed(authorised);
                }
            };

            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                    .addInterceptor(ApiHttpClient.getInterceptor())
                    .addInterceptor(mTokenInterceptor)
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                    .cookieJar(CookiesManager.getInstance())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();
        }
        return okHttpClient;
    }

    public static Interceptor getInterceptor() {
        Interceptor okhttpLogInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!BuildConfig.DEBUG) {
                    String msg = "%s\nurl->" + request.url()
                            + "\nheaders->" + request.headers() + request.body();

                    if (request.method().equals("GET")) {
                        Logger.i(msg + "GET");
                    } else if (request.method().equals("POST")) {
                        Request copyRequest = request.newBuilder().build();
                        if (copyRequest.body() instanceof RequestBody) {
                            Buffer buffer = new Buffer();
                            copyRequest.body().writeTo(buffer);
                            Logger.i("request params:" + buffer.readUtf8());
                        }
                        Logger.i(msg + " POST");
                    } else if (request.method().equals("PUT")) {
                        Logger.i(msg + " PUT");
                    } else if (request.method().equals("DELETE")) {
                        Logger.i(msg + " DELETE");
                    }
                }
                return chain.proceed(request);
            }
        };
        return okhttpLogInterceptor;
    }

    public static class CookiesManager implements CookieJar {

        private final PersistentCookieStore cookieStore = new PersistentCookieStore(AppContext.context());
        private static CookiesManager instance;

        public static   CookiesManager getInstance() {
            if (instance == null) {
                instance = new CookiesManager();
            }
            return instance;
        }

        public  PersistentCookieStore getCookieStore() {
            return cookieStore;
        }

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }

    /**
     * 获得请求的服务端数据的userAgent
     *
     * @param appContext
     * @return
     */
    private static String getUserAgent(Context appContext) {
        StringBuilder ua = new StringBuilder("YOUXIMAO.COM");
        PackageInfo mInfo = TDevice.getPackageInfo(appContext.getPackageName());
        ua.append('/' + mInfo.versionName + '_'
                + mInfo.versionCode);// app版本信息
        ua.append("/Android");// 手机系统平台
        ua.append("/" + Build.VERSION.RELEASE);// 手机系统版本
        ua.append("/" + Build.DEVICE); // 手机型号
//        ua.append("/" + appContext.getAppId());// 客户端唯一标识
        return ua.toString();
    }


    /**
     * 获取HTTP header信息
     *
     * @return
     */
    private static Map<String, String> getApiHeader() {
        LinkedHashMap apiHeaders = new LinkedHashMap<String, String>();
        apiHeaders.put("Accept-Language", Locale.getDefault().toString() + "");
        // client.addHeader("Host", HOST);
        apiHeaders.put("Connection", "Keep-Alive");
        apiHeaders.put("UserAgent", getUserAgent(BaseApplication.context()) + "");
        apiHeaders.put("cpu", TDevice.getCpuName());
        apiHeaders.put("hardware", TDevice.getprop("ro.hardware", ""));
        apiHeaders.put("cpu_abi", Build.CPU_ABI);
        apiHeaders.put("product_cpu_abi", TDevice.getCpuProduct());
        apiHeaders.put("terminalType", "2"); // 1:ios; 2:android
        apiHeaders.put("terminalName", Build.DEVICE + "");
//        apiHeaders.put("channelId", AppContext.getInstance().getChannel() + "");
        apiHeaders.put("version", TDevice.getVersionName() + "");
        apiHeaders.put("devicesId", TDevice.getIMEI());
//        apiHeaders.put("sid", AppContext.getInstance().getDeviceCode());
        apiHeaders.put("apiVersion", "1.0");
        return apiHeaders;
    }
}

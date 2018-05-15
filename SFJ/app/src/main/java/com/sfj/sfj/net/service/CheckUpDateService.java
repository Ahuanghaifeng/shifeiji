package com.sfj.sfj.net.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by haifeng on 2018/1/22.
 */

public interface CheckUpDateService {
    @GET("http://phone.91y.com/do.aspx?action=checkgameupdate")
    Call<ResponseBody> getDate(@QueryMap Map<String, String> map);
}

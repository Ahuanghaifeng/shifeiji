package com.sfj.sfj.net.service;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wangyu on 2018/1/22.
 */

public interface LoginService {

    @FormUrlEncoded
    @POST("userController/{action}")
    Call<ResponseBody>
    postData(
            @Path("action") String action,
            @FieldMap Map<String, String> map
    );

    /**
     * @param route
     * @return
     */
    @Headers({"Content-type:application/json;charset=utf-8"})
    @POST("client/promoters/{action}")
    Call<ResponseBody> createCommit(@Path("action") String action, @Body RequestBody route);
}

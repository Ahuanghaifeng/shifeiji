package com.sfj.sfj.net;


import android.text.TextUtils;

import com.sfj.sfj.net.service.FertilizerService;
import com.sfj.sfj.net.service.LoginService;
import com.sfj.sfj.net.service.UserConfigService;

import java.util.HashMap;
import java.util.Map;

public class TGBApi {

    //注册接口
    public static void doRegister(String username,String password,String registerCode,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        postData.put("registerCode",registerCode);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, LoginService.class).
                postData("register.do",postData), handler);
    }

    //登录接口
    public static void doLogin(String username,String password,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, LoginService.class).
                postData("appLogin.do",postData), handler);
    }

    public static void doFertilizerInfo(String username,String password,String fertilizerId,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        if (!TextUtils.isEmpty(fertilizerId)){
            postData.put("fertilizerId",fertilizerId);
        }
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, FertilizerService.class).
                postData("appTimeData.do",postData), handler);
    }

    public static void doFertilizerChangeCharts(String username,String password,String fertilizerId,String type,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        postData.put("fertilizerId",fertilizerId);
        postData.put("type",type);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, FertilizerService.class).
                postData("appChangeCharts.do",postData), handler);
    }

    //控制界面接口
    public static void doFertilizerToControl(String username,String password,String fertilizerId,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        postData.put("fertilizerId",fertilizerId);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, FertilizerService.class).
                postData("appToControl.do",postData), handler);

    }

    //控制灌溉机接口和控制灌溉机接口
    public static void doFertilizerControl(String username,String password,String fertilizerId,Map<String,String> data,String action,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        postData.put("fertilizerId",fertilizerId);
        postData.putAll(data);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, FertilizerService.class).
                postData(action,postData), handler);

    }


    //气象站接口
    public static void doFertilizerWeather(String username,String password,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, FertilizerService.class).
                postData("appGetClimatic.do",postData), handler);
    }

    //气象站折线图接口
    public static void doFertilizerWeatherCharts(String username,String password,String type,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        postData.put("type",type);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, FertilizerService.class).
                postData("appChangeClimaticCharts.do",postData), handler);
    }

}

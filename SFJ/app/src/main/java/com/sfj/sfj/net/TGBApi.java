package com.sfj.sfj.net;


import android.text.TextUtils;

import com.sfj.sfj.net.service.FertilizerService;
import com.sfj.sfj.net.service.LoginService;
import com.sfj.sfj.net.service.UserConfigService;

import java.util.HashMap;
import java.util.Map;

public class TGBApi {



//    //微信帐号登录接口
//    public static void doGetLoginInfo(String accesstoken, String openid, CloudSDKHttpHandler handler) {
//        String action = "wxlogin";
//        String token = String.valueOf(System.currentTimeMillis());
//
//
//        AppConfigInfo.setTimestamp(token);//设置时间戳
//        AppConfigInfo.setAction(action);//设置执行动作
//        HashMap<String, String> params = new HashMap<>();
//        params.put("code", accesstoken);
//        params.put("openId", openid);
//        AppConfigInfo.setExtraStr(params);//设置额外参数
//        Map<String, String> postData = AppConfigInfo.converToString();//转化最终格式
////        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), postData);
//        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, UserConfigService.class).
//                postData(action, postData), handler);
//
//    }

    public static void doRegister(String username,String password,String registerCode,CloudSDKHttpHandler handler){
        Map<String,String> postData = new HashMap<>();
        postData.put("username",username);
        postData.put("password",password);
        postData.put("registerCode",registerCode);
        BaseApiHelper.getInstance().callEnqueue(BaseApiHelper.getInstance().getApiServer(ApiServiceBean.PAY_DOMAIN_KEY, LoginService.class).
                postData("register.do",postData), handler);
    }

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
}

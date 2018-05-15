package com.sfj.sfj.net;


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

}

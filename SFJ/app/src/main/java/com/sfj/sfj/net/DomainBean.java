package com.sfj.sfj.net;


public class DomainBean {
    public static final String WEB_DOMAIN_KEY = "web_domain_key";
    private String pay;//服务站点
    private String userInfo;//用户信息
    private String userCenter;//用户中心
    private String serverCenter;//服务中心
    private String uploadFile;
    private String questionFeed;
    private String cloudMessage;

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserCenter() {
        return userCenter;
    }

    public void setUserCenter(String userCenter) {
        this.userCenter = userCenter;
    }

    public String getServerCenter() {
        return serverCenter;
    }

    public void setServerCenter(String serverCenter) {
        this.serverCenter = serverCenter;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getQuestionFeed() {
        return questionFeed;
    }

    public void setQuestionFeed(String questionFeed) {
        this.questionFeed = questionFeed;
    }

    public String getCloudMessage() {
        return cloudMessage;
    }

    public void setCloudMessage(String cloudMessage) {
        this.cloudMessage = cloudMessage;
    }
}

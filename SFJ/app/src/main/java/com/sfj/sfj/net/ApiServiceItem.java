package com.sfj.sfj.net;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangyu on 2017/4/18.
 */

public class ApiServiceItem {

    public static final int DEV = 1;
    public static final int LINE = 2;
    private static volatile ApiServiceItem mInstance;
    /**
     * 当前环境 key, url 数据容器
     */
    private Map<String, String> mDomainMap = new ConcurrentHashMap<>();

    /**
     * 当前默认环境
     */
    public int currentType = LINE;
    private int mPlatform;

    public static ApiServiceItem getInstance() {
        if (null == mInstance) {
            mInstance = new ApiServiceItem();
        }
        return mInstance;
    }

    public void init() {
        init(currentType);
    }

    public void init(int type) {
        currentType = type;
        initData(currentType);
        //初使化网络请求
        BaseApiHelper.getInstance().initHostList(mDomainMap);
    }

    private void initData(int type) {
        switch (type) {
            case DEV: {
                /** 开发 **/
                setDomain(getTestDomainBean());
            }
            break;
            case LINE:
                setDomain(getDevDomainBean());
                break;
        }
    }

    private DomainBean getDevDomainBean() {
        DomainBean devDomainBean = new DomainBean();
        devDomainBean.setPay("http://123.112.22.130:9090/");
//        devDomainBean.setUserInfo("http://phone.91y.com/");//
//        devDomainBean.setUserCenter("http://phone.91y.com/center.aspx");//
//        devDomainBean.setServerCenter("http://phone.91y.com/callcenter.aspx");//
//        devDomainBean.setUploadFile("http://phone.91y.com/picshow.aspx");//
//        devDomainBean.setQuestionFeed("http://phone.91y.com/");//
//        devDomainBean.setCloudMessage("http://phone.91y.com/ReadMsg.aspx");//
        return devDomainBean;
    }

    /**
     * 获取测试服务器
     *
     * @return
     */
    private DomainBean getTestDomainBean() {
        DomainBean devDomainBean = new DomainBean();
        devDomainBean.setPay("http://123.112.22.130:9090/");
//        devDomainBean.setUserInfo("http://phonetest.91y.com/");//
//        devDomainBean.setUserCenter("http://phonetest.91y.com/center.aspx");//
//        devDomainBean.setServerCenter("http://phonetest.91y.com/callcenter.aspx");//
//        devDomainBean.setUploadFile("http://phonetest.91y.com/picshow.aspx");//
//        devDomainBean.setQuestionFeed("http://phonetest.91y.com/");//
//        devDomainBean.setCloudMessage("http://phonetest.91y.com/ReadMsg.aspx");//
        return devDomainBean;
    }

    private void setDomain(DomainBean domainBean) {
        mDomainMap.put(ApiServiceBean.PAY_DOMAIN_KEY, domainBean.getPay());  //支付
//        mDomainMap.put(ApiServiceBean.USER_INFO_DOMAIN_KEY, domainBean.getUserInfo());//用户信息
//        mDomainMap.put(ApiServiceBean.QUESTIONFEED_KEY, domainBean.getQuestionFeed());
    }
}

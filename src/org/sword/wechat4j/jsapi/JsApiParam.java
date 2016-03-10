package org.sword.wechat4j.jsapi;

import org.sword.wechat4j.common.Config;

/**
 * JS API 配置对象
 * <p>参考<a href="http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95">开发文档</a></p>
 * Created by xuwen on 2015-12-11.
 */
public class JsApiParam {

    private String appid = Config.instance().getAppid();
    private String url;
    private String jsapiTicket;
    private String nonceStr;
    private String timeStamp;
    private String signature;

    public JsApiParam(String url, String jsapiTicket, String nonceStr, String timeStamp, String signature) {
        this.url = url;
        this.jsapiTicket = jsapiTicket;
        this.nonceStr = nonceStr;
        this.timeStamp = timeStamp;
        this.signature = signature;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "JsApiConfig{" +
                "appid='" + appid + '\'' +
                ", url='" + url + '\'' +
                ", jsapiTicket='" + jsapiTicket + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}

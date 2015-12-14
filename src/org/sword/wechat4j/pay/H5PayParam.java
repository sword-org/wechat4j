package org.sword.wechat4j.pay;

import org.sword.wechat4j.common.Config;

import javax.xml.bind.annotation.XmlElement;

/**
 * H5调起支付API的参数对象
 * <p><a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6">开发文档</p>
 * <p/>
 * Created by xuwen on 2015-12-10.
 */
public class H5PayParam {
    private String appid = Config.instance().getAppid();
    private String timeStamp;
    private String nonceStr;
    private String packageWithPrepayId; // 参数名package
    private String signType = "MD5";
    private String paySign;

    @XmlElement(name = "appId")
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    @XmlElement(name = "package")
    public String getPackageWithPrepayId() {
        return packageWithPrepayId;
    }

    public void setPackageWithPrepayId(String packageWithPrepayId) {
        this.packageWithPrepayId = packageWithPrepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    @Override
    public String toString() {
        return "H5PayConfig{" +
                "appid='" + appid + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", packageWithPrepayId='" + packageWithPrepayId + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                '}';
    }
}

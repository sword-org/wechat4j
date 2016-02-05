package org.sword.wechat4j.pay.protocol.downloadbill;

import org.sword.wechat4j.common.Config;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 下载对账单请求对象
 * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_6">开发文档</p>
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class DownloadbillRequest {

    private String appid = Config.instance().getAppid();
    private String mch_id = Config.instance().getMchId();
    private String device_info;
    private String nonce_str;
    private String sign;
    private String bill_date;
    private String bill_type;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBill_date() {
        return bill_date;
    }

    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    @Override
    public String toString() {
        return "DownloadbillRequest{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", bill_date='" + bill_date + '\'' +
                ", bill_type='" + bill_type + '\'' +
                '}';
    }
}

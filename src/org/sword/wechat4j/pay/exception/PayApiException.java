package org.sword.wechat4j.pay.exception;


import org.sword.wechat4j.pay.PayCode;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 支付API调用异常
 * return_code=FAIL
 * <p/>
 * Created by xuwen on 2015-12-11.
 */
@XmlRootElement(name = "xml")
public class PayApiException extends Exception {

    public PayApiException() {
    }



    public PayApiException(PayCode payCode, String return_msg) {
        this.return_code = payCode.toString();
        this.return_msg = return_msg;
    }

    public PayApiException(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    private String return_code;
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {
        return "PayApiException{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                '}';
    }
}

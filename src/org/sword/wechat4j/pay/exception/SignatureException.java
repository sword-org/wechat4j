package org.sword.wechat4j.pay.exception;

/**
 * 签名异常
 * Created by xuwen on 2015-12-12.
 */
public class SignatureException extends Exception {

    public SignatureException() {
        super("返回结果的签名校验失败，数据可能已经被篡改");
    }

}

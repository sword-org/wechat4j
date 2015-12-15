package org.sword.wechat4j.pay;

/**
 * 支付的结果
 * 对应以下2项返回数据
 * return_code
 * result_code
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
public enum PayCode {

    SUCCESS,
    FAIL;

    public boolean equals(String payCode) {
        return this.toString().equals(payCode);
    }

}

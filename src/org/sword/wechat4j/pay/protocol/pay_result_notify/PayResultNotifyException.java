package org.sword.wechat4j.pay.protocol.pay_result_notify;


import org.sword.wechat4j.pay.exception.PayBusinessException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 支付回调通知业务异常
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class PayResultNotifyException extends PayBusinessException {
}

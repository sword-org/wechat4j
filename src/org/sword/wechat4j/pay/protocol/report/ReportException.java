package org.sword.wechat4j.pay.protocol.report;


import org.sword.wechat4j.pay.exception.PayBusinessException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 测速上报业务异常
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class ReportException extends PayBusinessException {
}

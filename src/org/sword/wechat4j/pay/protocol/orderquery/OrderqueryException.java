package org.sword.wechat4j.pay.protocol.orderquery;


import org.sword.wechat4j.pay.exception.PayBusinessException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 查询订单业务异常
 * <p/>
 * <table class="table">
 * <thead>
 * <tr>
 * <th width="13%">名称</th>
 * <th width="14%">描述</th>
 * <th width="16%">原因</th>
 * <th>解决方案</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <td>ORDERNOTEXIST </td>
 * <td>此交易订单号不存在 </td>
 * <td>查询系统中不存在此交易订单号 </td>
 * <td>该API只能查提交支付交易返回成功的订单，请商户检查需要查询的订单号是否正确 </td>
 * </tr>
 * <tr>
 * <td>SYSTEMERROR </td>
 * <td>系统错误 </td>
 * <td>后台系统返回错误 </td>
 * <td>系统异常，请再调用发起查询 </td>
 * </tr>
 * </tbody>
 * </table>
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class OrderqueryException extends PayBusinessException {
}

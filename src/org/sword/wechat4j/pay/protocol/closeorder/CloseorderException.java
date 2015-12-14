package org.sword.wechat4j.pay.protocol.closeorder;


import org.sword.wechat4j.pay.exception.PayBusinessException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 关闭订单业务异常
 * <p/>
 * <table class="table">
 * <tbody><tr>
 * <th width="150px">名称</th>
 * <th width="100px">描述</th>
 * <th width="200px">原因</th>
 * <th>解决方案</th>
 * </tr>
 * <tr>
 * <td>ORDERPAID</td>
 * <td>订单已支付</td>
 * <td>订单已支付，不能发起关单</td>
 * <td>订单已支付，不能发起关单，请当作已支付的正常交易</td>
 * </tr>
 * <tr>
 * <td>SYSTEMERROR</td>
 * <td>系统错误</td>
 * <td>系统错误</td>
 * <td>系统异常，请重新调用该API</td>
 * </tr>
 * <tr>
 * <td>ORDERNOTEXIST</td>
 * <td>订单不存在</td>
 * <td>订单系统不存在此订单</td>
 * <td>不需要关单，当作未提交的支付的订单</td>
 * </tr>
 * <tr>
 * <td>ORDERCLOSED</td>
 * <td>订单已关闭</td>
 * <td>订单已关闭，无法重复关闭</td>
 * <td>订单已关闭，无需继续调用</td>
 * </tr>
 * <tr>
 * <td>SIGNERROR</td>
 * <td>签名错误</td>
 * <td>参数签名结果不正确</td>
 * <td>请检查签名参数和方法是否都符合签名算法要求</td>
 * </tr>
 * <tr>
 * <td>REQUIRE_POST_METHOD</td>
 * <td>请使用post方法</td>
 * <td>未使用post传递参数&nbsp;</td>
 * <td>请检查请求参数是否通过post方法提交</td>
 * </tr>
 * <tr>
 * <td>XML_FORMAT_ERROR</td>
 * <td>XML格式错误</td>
 * <td>XML格式错误</td>
 * <td>请检查XML参数格式是否正确</td>
 * </tr>
 * </tbody></table>
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class CloseorderException extends PayBusinessException {
}

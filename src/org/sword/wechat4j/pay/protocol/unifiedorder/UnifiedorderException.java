package org.sword.wechat4j.pay.protocol.unifiedorder;


import org.sword.wechat4j.pay.exception.PayBusinessException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 统一下单业务异常
 * <p/>
 * <table class="table">
 * <thead>
 * <tr>
 * <th width="22%">名称</th>
 * <th width="16%">描述</th>
 * <th>原因</th>
 * <th>解决方案</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <td>NOAUTH</td>
 * <td>商户无此接口权限</td>
 * <td>商户未开通此接口权限</td>
 * <td>请商户前往申请此接口权限</td>
 * </tr>
 * <tr>
 * <td>NOTENOUGH</td>
 * <td>余额不足</td>
 * <td>用户帐号余额不足</td>
 * <td>用户帐号余额不足，请用户充值或更换支付卡后再支付</td>
 * </tr>
 * <tr>
 * <td>ORDERPAID</td>
 * <td>商户订单已支付</td>
 * <td>商户订单已支付，无需重复操作</td>
 * <td>商户订单已支付，无需更多操作</td>
 * </tr>
 * <tr>
 * <td>ORDERCLOSED</td>
 * <td>订单已关闭</td>
 * <td>当前订单已关闭，无法支付</td>
 * <td>当前订单已关闭，请重新下单</td>
 * </tr>
 * <tr>
 * <td>SYSTEMERROR</td>
 * <td>系统错误</td>
 * <td>系统超时</td>
 * <td>系统异常，请用相同参数重新调用</td>
 * </tr>
 * <tr>
 * <td>APPID_NOT_EXIST</td>
 * <td>APPID不存在</td>
 * <td>参数中缺少APPID</td>
 * <td>请检查APPID是否正确</td>
 * </tr>
 * <tr>
 * <td>MCHID_NOT_EXIST</td>
 * <td>MCHID不存在</td>
 * <td>参数中缺少MCHID</td>
 * <td>请检查MCHID是否正确</td>
 * </tr>
 * <tr>
 * <td>APPID_MCHID_NOT_MATCH</td>
 * <td>appid和mch_id不匹配</td>
 * <td>appid和mch_id不匹配</td>
 * <td>请确认appid和mch_id是否匹配</td>
 * </tr>
 * <tr>
 * <td>LACK_PARAMS</td>
 * <td>缺少参数</td>
 * <td>缺少必要的请求参数</td>
 * <td>请检查参数是否齐全</td>
 * </tr>
 * <tr>
 * <td>OUT_TRADE_NO_USED</td>
 * <td>商户订单号重复</td>
 * <td>同一笔交易不能多次提交</td>
 * <td>请核实商户订单号是否重复提交</td>
 * </tr>
 * <tr>
 * <td>SIGNERROR</td>
 * <td>签名错误</td>
 * <td>参数签名结果不正确</td>
 * <td>请检查签名参数和方法是否都符合签名算法要求</td>
 * </tr>
 * <tr>
 * <td>XML_FORMAT_ERROR</td>
 * <td>XML格式错误</td>
 * <td>XML格式错误</td>
 * <td>请检查XML参数格式是否正确</td>
 * </tr>
 * <tr>
 * <td>REQUIRE_POST_METHOD</td>
 * <td>请使用post方法</td>
 * <td>未使用post传递参数&nbsp;</td>
 * <td>请检查请求参数是否通过post方法提交</td>
 * </tr>
 * <tr>
 * <td>POST_DATA_EMPTY</td>
 * <td>post数据为空</td>
 * <td>post数据不能为空</td>
 * <td>请检查post数据是否为空</td>
 * </tr>
 * <tr>
 * <td>NOT_UTF8</td>
 * <td>编码格式错误</td>
 * <td>未使用指定编码格式</td>
 * <td>请使用NOT_UTF8编码格式</td>
 * </tr>
 * </tbody>
 * </table>
 * <p/>
 * Created by xuwen on 2015-12-11.
 */
@XmlRootElement(name = "xml")
public class UnifiedorderException extends PayBusinessException {
}

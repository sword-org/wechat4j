package org.sword.wechat4j.pay.protocol.refundquery;


import org.sword.wechat4j.pay.exception.PayBusinessException;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 退款查询业务异常
 * <p/>
 * <table class="table">
 * <tbody><tr>
 * <th width="200">名称</th>
 * <th width="160">描述</th>
 * <th width="200">原因</th>
 * <th width="">解决方案</th>
 * </tr>
 * <tr>
 * <td>SYSTEMERROR</td>
 * <td>接口返回错误</td>
 * <td>系统超时</td>
 * <td>请尝试再次掉调用API。</td>
 * </tr>
 * <tr>
 * <td>INVALID_TRANSACTIONID</td>
 * <td>无效transaction_id</td>
 * <td>请求参数未按指引进行填写</td>
 * <td>请求参数错误，检查原交易号是否存在或发起支付交易接口返回失败</td>
 * </tr>
 * <tr>
 * <td>PARAM_ERROR</td>
 * <td>参数错误</td>
 * <td>请求参数未按指引进行填写</td>
 * <td>请求参数错误，请检查参数再调用退款申请</td>
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
 * <td>REQUIRE_POST_METHOD</td>
 * <td>请使用post方法</td>
 * <td>未使用post传递参数&nbsp;</td>
 * <td>请检查请求参数是否通过post方法提交</td>
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
 * </tbody></table>
 * <p/>
 * Created by xuwen on 2015-12-11.
 */
@XmlRootElement(name = "xml")
public class RefundqueryException extends PayBusinessException {
}

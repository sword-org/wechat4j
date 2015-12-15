package org.sword.wechat4j.pay;

import org.junit.Test;
import org.sword.wechat4j.pay.exception.PayBusinessException;
import org.sword.wechat4j.pay.exception.PayApiException;
import org.sword.wechat4j.pay.exception.SignatureException;
import org.sword.wechat4j.pay.protocol.closeorder.CloseorderRequest;
import org.sword.wechat4j.pay.protocol.closeorder.CloseorderResponse;
import org.sword.wechat4j.pay.protocol.downloadbill.DownloadbillRequest;
import org.sword.wechat4j.pay.protocol.orderquery.OrderqueryRequest;
import org.sword.wechat4j.pay.protocol.orderquery.OrderqueryResponse;
import org.sword.wechat4j.pay.protocol.refund.RefundRequest;
import org.sword.wechat4j.pay.protocol.refund.RefundResponse;
import org.sword.wechat4j.pay.protocol.refundquery.RefundqueryRequest;
import org.sword.wechat4j.pay.protocol.refundquery.RefundqueryResponse;
import org.sword.wechat4j.pay.protocol.report.ReportRequest;
import org.sword.wechat4j.pay.protocol.report.ReportResponse;
import org.sword.wechat4j.pay.protocol.unifiedorder.UnifiedorderRequest;
import org.sword.wechat4j.pay.protocol.unifiedorder.UnifiedorderResponse;
import org.sword.wechat4j.util.RandomStringGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付API测试
 * 所有测试用例都需要配置以下项
 * wechat.mch.id    商户ID
 * wechat.mch.key   商户API KEY
 * Created by xuwen on 2015-12-13.
 */
public class PayTest {

    @Test
    public void testOrder() throws SignatureException, PayApiException, PayBusinessException {
        String outTradeNo = RandomStringGenerator.generate();
        // 统一下单
        UnifiedorderRequest unifiedorderRequest = new UnifiedorderRequest();
        unifiedorderRequest.setNonce_str(RandomStringGenerator.generate());
        unifiedorderRequest.setBody("iPhone100 S PLUS");
        unifiedorderRequest.setDetail("黑科技产品");
        unifiedorderRequest.setAttach("我是商户"); // 随便写，会原样带回
        unifiedorderRequest.setOut_trade_no(outTradeNo); // 我们自己的交易订单号
        unifiedorderRequest.setTotal_fee(1); // 钱，单位是分
        unifiedorderRequest.setSpbill_create_ip("127.0.0.1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        unifiedorderRequest.setTime_start(sdf.format(new Date()));
        unifiedorderRequest.setTrade_type("JSAPI");
        unifiedorderRequest.setOpenid("o40dZwL6Ik_ZsDfANjkAqd5MPpN4");
        unifiedorderRequest.setNotify_url("http://uncoseason.xicp.net/eduboss/WeiXinController/payCallback.do");
        UnifiedorderResponse unifiedorderResponse = PayManager.unifiedorder(unifiedorderRequest);
        System.out.println(unifiedorderResponse);
        // 查询订单
        OrderqueryRequest orderqueryRequest = new OrderqueryRequest();
        orderqueryRequest.setAppid(unifiedorderResponse.getAppid());
        orderqueryRequest.setOut_trade_no(outTradeNo);
        orderqueryRequest.setNonce_str(RandomStringGenerator.generate());
        OrderqueryResponse orderqueryResponse = PayManager.orderquery(orderqueryRequest);
        System.out.println(orderqueryResponse);
        // 关闭订单
        CloseorderRequest closeorderRequest = new CloseorderRequest();
        closeorderRequest.setAppid(unifiedorderResponse.getAppid());
        closeorderRequest.setOut_trade_no(outTradeNo);
        closeorderRequest.setNonce_str(RandomStringGenerator.generate());
        CloseorderResponse closeorderResponse = PayManager.closeorder(closeorderRequest);
        System.out.println(closeorderResponse);
    }

//    @Test
    public void testReport() throws SignatureException, PayApiException, PayBusinessException {
        ReportRequest reportRequest = new ReportRequest();
        reportRequest.setNonce_str(RandomStringGenerator.generate());
        reportRequest.setInterface_url("https://api.mch.weixin.qq.com/pay/unifiedorder");
        reportRequest.setExecute_time_(1000);
        reportRequest.setReturn_code(PayCode.SUCCESS.toString());
        reportRequest.setReturn_msg("成功");
        reportRequest.setResult_code(PayCode.SUCCESS.toString());
        reportRequest.setOut_trade_no(RandomStringGenerator.generate());
        reportRequest.setUser_ip("127.0.0.1");
        reportRequest.setTime("201512130235");
        ReportResponse reportResponse = PayManager.report(reportRequest);
        System.out.println(reportResponse);
    }

//    @Test
    public void testDownloadbill() throws PayApiException {
        DownloadbillRequest downloadbillRequest = new DownloadbillRequest();
        downloadbillRequest.setNonce_str(RandomStringGenerator.generate());
        downloadbillRequest.setBill_date("20151211");
        downloadbillRequest.setBill_type("ALL");
        System.out.println(PayManager.downloadbill(downloadbillRequest));
    }

//    @Test
    public void testRefund() throws SignatureException, PayApiException, PayBusinessException {
        String transactionId = "1000080905201512112018328757";
        // 此用例需要安装商户证书，并获取一个真实的交易ID
        String outRefundNo = RandomStringGenerator.generate();
        // 申请退款
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setNonce_str(RandomStringGenerator.generate());
        refundRequest.setTransaction_id(transactionId);
        refundRequest.setOut_refund_no(outRefundNo);
        refundRequest.setTotal_fee(1);
        refundRequest.setRefund_fee(1);
        RefundResponse refundResponse = PayManager.refund(refundRequest);
        System.out.println(refundResponse);
        // 查询退款
        RefundqueryRequest refundqueryRequest = new RefundqueryRequest();
        refundqueryRequest.setNonce_str(RandomStringGenerator.generate());
        refundqueryRequest.setTransaction_id(transactionId);
        RefundqueryResponse refundqueryResponse = PayManager.refundquery(refundqueryRequest);
        System.out.println(refundqueryResponse);
    }

}

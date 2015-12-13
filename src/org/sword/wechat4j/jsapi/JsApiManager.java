package org.sword.wechat4j.jsapi;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.token.TokenProxy;
import org.sword.wechat4j.util.RandomStringGenerator;

/**
 * JS SDK 管理类
 * <p>参考<a href="http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html">开发文档</a></p>
 * Created by xuwen on 2015-12-11.
 */
public class JsApiManager {

    private static Logger logger = Logger.getLogger(JsApiManager.class);

    /**
     * 给需要调用JS SDK的URL签名
     *
     * @param url
     * @return
     */
    public static JsApiParam signature(String url) {
        StringBuffer signatureSource = new StringBuffer();
        String nonceStr = RandomStringGenerator.generate();
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String jsapiTicket = TokenProxy.jsApiTicket();
        signatureSource.append("jsapi_ticket=").append(jsapiTicket);
        signatureSource.append("&noncestr=").append(nonceStr);
        signatureSource.append("&timestamp=").append(timestamp);
        signatureSource.append("&url=").append(url);
        logger.info("sign source : " + signatureSource);
        String signature = DigestUtils.sha1Hex(signatureSource.toString());
        logger.info("sign : " + signature);
        return new JsApiParam(url, jsapiTicket, nonceStr, timestamp, signature);
    }

}

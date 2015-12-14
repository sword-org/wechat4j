package org.sword.wechat4j.pay.protocol.report;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 测速上报请求对象
 * <p>参考<a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_8">开发文档</p>
 * <p/>
 * Created by xuwen on 2015-12-13.
 */
@XmlRootElement(name = "xml")
public class ReportResponse {

    private String result_code;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    @Override
    public String toString() {
        return "ReportResponse{" +
                "result_code='" + result_code + '\'' +
                '}';
    }
}

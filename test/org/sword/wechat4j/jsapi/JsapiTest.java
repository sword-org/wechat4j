package org.sword.wechat4j.jsapi;

import org.junit.Test;

/**
 * Created by xuwen on 2015-12-13.
 */
public class JsapiTest {

    @Test
    public void testSign() {
        JsApiParam param = JsApiManager.signature("http://uncoseason.xicp.net/");
        System.out.println(param);
    }

}

package org.sword.wechat4j.oauth;

import org.junit.Test;
import org.sword.wechat4j.oauth.protocol.getAccessToken.GetAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.getAccessToken.GetAccessTokenResponse;
import org.sword.wechat4j.oauth.protocol.getUserinfo.GetUserinfoRequest;
import org.sword.wechat4j.oauth.protocol.getUserinfo.GetUserinfoResponse;
import org.sword.wechat4j.oauth.protocol.refreshAccessToken.RefreshAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.refreshAccessToken.RefreshAccessTokenResponse;
import org.sword.wechat4j.oauth.protocol.validAccessToken.ValidAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.validAccessToken.ValidAccessTokenResponse;

/**
 * Created by xuwen on 2015-12-13.
 */
public class OAuthTest {

    @Test
    public void testGenerateRedirectURI(){
        System.out.println(OAuthManager.generateRedirectURI("http://uncoseason.xicp.net/", "snsapi_userinfo", "test info"));
    }

//    @Test
    public void testOAuth() throws OAuthException {
        // 这个用例需要重定向URI带回的code参数
        String code = "重定向URI带回的code参数";
        GetAccessTokenResponse getAccessTokenResponse = OAuthManager.getAccessToken(new GetAccessTokenRequest(code));
        System.out.println(getAccessTokenResponse);
        GetUserinfoResponse getUserinfoResponse = OAuthManager.getUserinfo(new GetUserinfoRequest(getAccessTokenResponse.getAccess_token(),getAccessTokenResponse.getOpenid()));
        System.out.println(getUserinfoResponse);
        ValidAccessTokenResponse validAccessTokenResponse = OAuthManager.validAccessToken(new ValidAccessTokenRequest(getAccessTokenResponse.getAccess_token(),getAccessTokenResponse.getOpenid()));
        System.out.println(validAccessTokenResponse);
        RefreshAccessTokenResponse refreshAccessTokenResponse = OAuthManager.refreshAccessToken(new RefreshAccessTokenRequest(getAccessTokenResponse.getRefresh_token()));
        System.out.println(refreshAccessTokenResponse);
    }

}

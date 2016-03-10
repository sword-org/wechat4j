package org.sword.wechat4j.oauth.protocol.refresh_access_token;

import org.sword.wechat4j.common.Config;

/**
 * 响应：刷新access_token（如果需要）
 * Created by xuwen on 2015-12-11.
 */
public class RefreshAccessTokenRequest {

    public RefreshAccessTokenRequest(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    private String appid = Config.instance().getAppid();
    private String grant_type = "refresh_token";
    private String refresh_token;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public String toString() {
        return "RefreshAccessTokenRequest{" +
                "appid='" + appid + '\'' +
                ", grant_type='" + grant_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }
}

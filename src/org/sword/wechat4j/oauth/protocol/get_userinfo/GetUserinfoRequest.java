package org.sword.wechat4j.oauth.protocol.get_userinfo;

/**
 * 请求：拉取用户信息(需scope为 snsapi_userinfo)
 * Created by xuwen on 2015-12-11.
 */
public class GetUserinfoRequest {

    public GetUserinfoRequest(String access_token, String openid) {
        this.access_token = access_token;
        this.openid = openid;
    }

    public GetUserinfoRequest(String access_token, String openid, String lang) {
        this.access_token = access_token;
        this.openid = openid;
        this.lang = lang;
    }

    private String access_token;
    private String openid;
    private String lang = "zh_CN";

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return "GetUserinfoRequest{" +
                "access_token='" + access_token + '\'' +
                ", openid='" + openid + '\'' +
                ", lang='" + lang + '\'' +
                '}';
    }
}

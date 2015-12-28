package org.sword.wechat4j.oauth.protocol.valid_access_token;

/**
 * 响应：检验授权凭证（access_token）是否有效
 * Created by xuwen on 2015-12-11.
 */
public class ValidAccessTokenResponse {

    private String errcode;
    private String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public boolean ok(){
        return this.errmsg != null && "ok".equals(this.errmsg);
    }

    @Override
    public String toString() {
        return "ValidAccessTokenResponse{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}

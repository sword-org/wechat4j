package org.sword.wechat4j.oauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.common.Config;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.get_access_token.GetAccessTokenResponse;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoRequest;
import org.sword.wechat4j.oauth.protocol.get_userinfo.GetUserinfoResponse;
import org.sword.wechat4j.oauth.protocol.refresh_access_token.RefreshAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.refresh_access_token.RefreshAccessTokenResponse;
import org.sword.wechat4j.oauth.protocol.valid_access_token.ValidAccessTokenRequest;
import org.sword.wechat4j.oauth.protocol.valid_access_token.ValidAccessTokenResponse;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 网页授权获取用户基本信息
 * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html">开发文档</a></p>
 * Created by xuwen on 2015/12/11.
 */
public class OAuthManager {

    private static Logger logger = Logger.getLogger(OAuthManager.class);

    /*生成OAuth重定向URI（用户同意授权，获取code）*/
    private static final String HTTPS_OPEN_WEIXIN_QQ_COM_CONNECT_OAUTH2_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /*通过code换取网页授权access_token*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /*刷新access_token（如果需要）*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /*拉取用户信息(需scope为 snsapi_userinfo)*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_USERINFO = "https://api.weixin.qq.com/sns/userinfo";
    /*检验授权凭证（access_token）是否有效*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_AUTH = "https://api.weixin.qq.com/sns/auth";

    /**
     * 生成OAuth重定向URI（用户同意授权，获取code）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.94.A8.E6.88.B7.E5.90.8C.E6.84.8F.E6.8E.88.E6.9D.83.EF.BC.8C.E8.8E.B7.E5.8F.96code">开发文档</a></p>
     *
     * @param redirectURI
     * @param scope
     * @param state
     * @return
     */
    public static String generateRedirectURI(String redirectURI, String scope, String state) {
        StringBuffer url = new StringBuffer();
        url.append(HTTPS_OPEN_WEIXIN_QQ_COM_CONNECT_OAUTH2_AUTHORIZE);
        url.append("?appid=").append(urlEncode(Config.instance().getAppid()));
        url.append("&redirect_uri=").append(urlEncode(redirectURI));
        url.append("&response_type=code");
        url.append("&scope=").append(urlEncode(scope));
        url.append("&state=").append(urlEncode(state));
        url.append("#wechat_redirect");
        return url.toString();
    }

    /**
     * 通过code换取网页授权access_token
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E9.80.9A.E8.BF.87code.E6.8D.A2.E5.8F.96.E7.BD.91.E9.A1.B5.E6.8E.88.E6.9D.83access_token">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static GetAccessTokenResponse getAccessToken(GetAccessTokenRequest request) throws OAuthException {
        String response = post(HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_ACCESS_TOKEN, request);
        check(response);
        return JSONObject.parseObject(response, GetAccessTokenResponse.class);
    }

    /**
     * 刷新access_token（如果需要）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.89.E6.AD.A5.EF.BC.9A.E5.88.B7.E6.96.B0access_token.EF.BC.88.E5.A6.82.E6.9E.9C.E9.9C.80.E8.A6.81.EF.BC.89">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenRequest request) throws OAuthException {
        String response = post(HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_REFRESH_TOKEN, request);
        check(response);
        return JSONObject.parseObject(response, RefreshAccessTokenResponse.class);
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E5.9B.9B.E6.AD.A5.EF.BC.9A.E6.8B.89.E5.8F.96.E7.94.A8.E6.88.B7.E4.BF.A1.E6.81.AF.28.E9.9C.80scope.E4.B8.BA_snsapi_userinfo.29">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static GetUserinfoResponse getUserinfo(GetUserinfoRequest request) throws OAuthException {
        String response = post(HTTPS_API_WEIXIN_QQ_COM_SNS_USERINFO, request);
        check(response);
        return JSONObject.parseObject(response, GetUserinfoResponse.class);
    }


    /**
     * 检验授权凭证（access_token）是否有效
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E9.99.84.EF.BC.9A.E6.A3.80.E9.AA.8C.E6.8E.88.E6.9D.83.E5.87.AD.E8.AF.81.EF.BC.88access_token.EF.BC.89.E6.98.AF.E5.90.A6.E6.9C.89.E6.95.88">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static ValidAccessTokenResponse validAccessToken(ValidAccessTokenRequest request) throws OAuthException {
        String response = post(HTTPS_API_WEIXIN_QQ_COM_SNS_AUTH, request);
        check(response);
        return JSONObject.parseObject(response, ValidAccessTokenResponse.class);
    }

    /**
     * 使用UTF-8进行URL编码
     *
     * @param str
     * @return
     */
    private static String urlEncode(String str) {
        String result = null;
        try {
            result = URLEncoder.encode(str, Consts.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // never throws
        }
        return result;
    }

    /**
     * 检查响应结果是否正确
     *
     * @param response
     */
    private static void check(String response) throws OAuthException {
        JSONObject exception = JSON.parseObject(response);
        String errcode = exception.getString("errcode");
        String errmsg = exception.getString("errmsg");
        if (errmsg != null && !"ok".equals(errmsg)) {
            throw new OAuthException(errcode, errmsg);
        }
    }

    /**
     * post 请求
     *
     * @param url
     * @param data
     * @return
     */
    private static String post(String url, Object data) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (data != null) {
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(data);
                } catch (IllegalAccessException e) {
                    // never throws
                }
                if (value != null) {
                    params.add(new BasicNameValuePair(field.getName(), value.toString()));
                }
            }
        }
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyForm(params.toArray(new NameValuePair[params.size()]))
                    .execute().returnResponse().getEntity();
            return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
        } catch (Exception e) {
            logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post 请求
     *
     * @param url
     * @return
     */
    private static String post(String url) {
        return post(url, null);
    }

}

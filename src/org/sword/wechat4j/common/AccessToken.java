/**
 * 
 */
package org.sword.wechat4j.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.wechat4j.util.HttpUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Access token处理
 * @author ChengNing
 * @date   2014年12月12日
 */
public class AccessToken {
	
	private static Logger logger = Logger.getLogger(AccessToken.class);
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	private String accessToken;
	private long expires;
	
	/**
	 * 请求信的access token
	 * http请求方式: GET
https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET

{"access_token":"ACCESS_TOKEN","expires_in":7200}

{"errcode":40013,"errmsg":"invalid appid"}
	 * @return
	 */
	public void request(){
		String url = accessTokenUrl();
		String result = HttpUtils.get(url);
		if(StringUtils.isBlank(result))
			return;
		JSONObject jsonObject = JSONObject.parseObject(result);
		String accessToken = jsonObject.get("access_token").toString();
		if(StringUtils.isBlank(accessToken)){
			logger.error("access_token获取失败,获取结果" + result);
		}
		this.accessToken = accessToken;
		String expiresIn = jsonObject.get("expires_in").toString();
		if(StringUtils.isBlank(expiresIn)){
			logger.error("access_token获取失败,获取结果" + expiresIn);
		}
		else{
			this.expires = Long.valueOf(expiresIn);
		}
	}
	
	/**
	 * 得到access token
	 * @return
	 */
	public String getAccessToken(){
		return this.accessToken;
	}
	
	/**
	 * 得到有效时间
	 * @return
	 */
	public long getExpires() {
		return expires;
	}

	/**
	 * 组织accesstoken的请求utl
	 * @return
	 */
	private String accessTokenUrl(){
		String appid = Config.instance().getAppid();
		String appsecret = Config.instance().getAppSecret();
		String url = ACCESS_TOKEN_URL + "&appid=" + appid + "&secret=" + appsecret;
		return url;
		
	}
	
	
}

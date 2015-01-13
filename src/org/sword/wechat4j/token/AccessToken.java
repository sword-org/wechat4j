/**
 * 
 */
package org.sword.wechat4j.token;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.common.Config;

import com.alibaba.fastjson.JSONObject;

/**
 * Access token实体模型
 * @author ChengNing
 * @date   2014年12月12日
 */
public class AccessToken {
	
	private static Logger logger = Logger.getLogger(AccessToken.class);
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	private String accessToken;   //token
	private long expires;         //token有效时间
	private long tokenTime;       //token产生时间
	
	private int redundance = 10*1000;  //冗余时间，提前10秒就去请求新的token
	
	/**
	 * 请求信的access token
	 * http请求方式: GET
		https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
		{"access_token":"ACCESS_TOKEN","expires_in":7200}
		{"errcode":40013,"errmsg":"invalid appid"}
	 * @return
	 */
	public boolean request(){
		String url = accessTokenUrl();
		String result = HttpUtils.get(url);
		if(StringUtils.isBlank(result))
			return false;
		JSONObject jsonObject = JSONObject.parseObject(result);
		String accessToken = jsonObject.get("access_token").toString();
		if(StringUtils.isBlank(accessToken)){
			logger.error("access_token获取失败,获取结果" + result);
			return false;
		}
		this.accessToken = accessToken;
		this.tokenTime = (new Date()).getTime();
		String expiresIn = jsonObject.get("expires_in").toString();
		if(StringUtils.isBlank(expiresIn)){
			logger.error("access_token获取失败,获取结果" + expiresIn);
			return false;
		}
		else{
			this.expires = Long.valueOf(expiresIn);
		}
		logger.info("access_token获取成功");
		return true;
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
	
	/**
	 * accessToken 是否有效
	 * @return true:有效，false: 无效
	 */
	public boolean isValid(){
		//黑名单判定法
		if(StringUtils.isBlank(this.accessToken))
			return false;
		if(this.expires <= 0)
			return false;
		//过期
		if(isExpire())
			return false;
		return true;
	}
	
	/**
	 * 是否过期
	 * @return true 过期 false：有效
	 */
	private boolean isExpire(){
		Date currentDate = new Date();
		long currentTime = currentDate.getTime();
		long expiresTime = expires * 1000 - redundance;
		//判断是否过期
		if((tokenTime + expiresTime) > currentTime)
			return false;
		return true;
	}
	
	
}

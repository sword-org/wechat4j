/**
 * 
 */
package org.sword.wechat4j.token;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.sword.lang.HttpUtils;


import com.alibaba.fastjson.JSONObject;

/**
 * @author ChengNing
 * @date   2015年1月29日
 */
public abstract class Token {
	private static Logger logger = Logger.getLogger(Token.class);
	
	private String token;   //token
	private long expires;         //token有效时间
	
	private long tokenTime;       //token产生时间
	private int redundance = 10*1000;  //冗余时间，提前10秒就去请求新的token
	
	/**
	 * 得到access token
	 */
	public String getToken(){
		return this.token;
	}
	
	/**
	 * 得到有效时间
	 */
	public long getExpires() {
		return expires;
	}
	
	/**
	 * 请求信的access token
	 * http请求方式: GET
		https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
		{"access_token":"ACCESS_TOKEN","expires_in":7200}
		{"errcode":40013,"errmsg":"invalid appid"}
	 */
	public boolean request(){
		String url = accessTokenUrl();
		String result = HttpUtils.get(url);
		if(StringUtils.isBlank(result))
			return false;
		if(!parseData(result)){
			return false;
		}
		logger.info("token获取成功");
		return true;
	}
	
	/**
	 * 解析token数据
	 * @param data
	 * @return
	 */
	private boolean parseData(String data){
		JSONObject jsonObject = JSONObject.parseObject(data);
		String tokenName = tokenName();
		String expiresInName = expiresInName();
		try {
			String token = jsonObject.get(tokenName).toString();
			if(StringUtils.isBlank(token)){
				logger.error("token获取失败,获取结果" + data);
				return false;
			}
			this.token = token;
			this.tokenTime = (new Date()).getTime();
			String expiresIn = jsonObject.get(expiresInName).toString();
			if(StringUtils.isBlank(expiresIn)){
				logger.error("token获取失败,获取结果" + expiresIn);
				return false;
			}
			else{
				this.expires = Long.valueOf(expiresIn);
			}
		} catch (Exception e) {
			logger.error("token 结果解析失败，token参数名称: " + tokenName 
					+ "有效期参数名称:" + expiresInName
					+ "token请求结果:" + data);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * token的参数名称
	 * @return
	 */
	protected abstract String tokenName(); 
	/**
	 * expireIn的参数名称
	 * @return
	 */
	protected abstract String expiresInName(); 

	/**
	 * 组织accesstoken的请求utl
	 * @return
	 */
	protected abstract String accessTokenUrl();
	
	/**
	 * accessToken 是否有效
	 * @return true:有效，false: 无效
	 */
	public boolean isValid(){
		//黑名单判定法
		if(StringUtils.isBlank(this.token))
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

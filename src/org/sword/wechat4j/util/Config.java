/**
 * 
 */
package org.sword.wechat4j.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author ChengNing
 * @date   2014年12月8日
 */
public class Config {
	
	private static Logger logger = Logger.getLogger(Config.class);
	
	private String configFile = "/wechat4j.properties";
	private String url;
	private String token;
	private String appid;
	private String appSecret;
	private static Config config = new Config();
	
	private Config(){
		Properties p = new Properties();
		InputStream inStream = this.getClass().getResourceAsStream(configFile);
		try {
			p.load(inStream);
			this.url = p.getProperty("wechat.url");
			this.token = p.getProperty("wechat.token");
			this.appid = p.getProperty("wechat.appid");
			this.appSecret = p.getProperty("wechat.appsecret");
			inStream.close();
		} catch (IOException e) {
			logger.error("load wechat4j.properties error");
			e.printStackTrace();
		}
		logger.error("load wechat4j.properties success");
	}
	
	public static Config instance(){
		return config;
	}
	
	public String getUrl() {
		return url;
	}
	public String getToken() {
		return token;
	}
	public String getAppid() {
		return appid;
	}
	public String getAppSecret() {
		return appSecret;
	}
	
	
}

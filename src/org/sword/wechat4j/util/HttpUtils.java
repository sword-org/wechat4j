/**
 * 
 */
package org.sword.wechat4j.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author ChengNing
 * @date   2014年12月12日
 */
public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	
	
	/**
	 * post 请求
	 * @param url 
	 * @return
	 */
	public static String post(String url){
		return post(url,null);
	}
	
	/**
	 * post 请求
	 * @param url
	 * @param data 
	 * @return
	 */
	public static String post(String url,String data){
		HttpClient client = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		if(StringUtils.isNotBlank(data)){
			postMethod.setRequestBody(data);
		}
		try {
			client.executeMethod(postMethod);
			String result = postMethod.getResponseBodyAsString();
			return result;
		} catch (Exception e) {
			logger.error("post请求异常，post url:" + url);
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * 发送get请求
	 * @param url 
	 * @return
	 */
	public static String get(String url){
		HttpClient client = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			client.executeMethod(getMethod);
			String result = getMethod.getResponseBodyAsString();
			return result;
		} catch (Exception e) {
			logger.error("get请求异常，get url:" + url);
			e.printStackTrace();
		} 
		return null;
	}
}

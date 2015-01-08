/**
 * 
 */
package org.sword.wechat4j.util;


import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author ChengNing
 * @date 2014年12月12日
 */
public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	public static final int timeout = 10;

	/**
	 * post 请求
	 * 
	 * @param url
	 * @return
	 */
	public static String post(String url) {
		return post(url, null);
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, String data) {
		try {
			HttpEntity entity = Request.Post(url)
					.bodyString(data,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		try {
			HttpEntity entity = Request.Post(url).
					execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}

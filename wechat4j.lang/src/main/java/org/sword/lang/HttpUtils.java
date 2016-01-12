/**
 * 
 */
package org.sword.lang;



import java.io.File;
import java.io.InputStream;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


/**
 * 
 * @author chengn
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
		return post(url, "");
	}
	
	/**
	 * post请求
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, String data){
		return httpPost(url, data);
	}
	
	/**
	 * 发送http post请求
	 * @param url       url
	 * @param instream  post数据的字节流
	 * @return
	 */
	public static String post(String url, InputStream instream){
		try {
			HttpEntity entity = Request.Post(url)
					.bodyStream(instream,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public static String get(String url){
		return httpGet(url);
	}

	/**
	 * post 请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	private static String httpPost(String url, String data) {
		try {
			HttpEntity entity = Request.Post(url)
					.bodyString(data,ContentType.create("text/html", Consts.UTF_8))
					.execute().returnResponse().getEntity();
			// TODO: 16-1-11 Neil:需要加上默认编码要不然会乱码
			return entity != null ? EntityUtils.toString(entity,Consts.UTF_8) : null;
		} catch (Exception e) {
			logger.error("post请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 上传文件
	 * @param url    URL
	 * @param file   需要上传的文件
	 * @return
	 */
	public static String postFile(String url,File file){
		return postFile(url, null, file);
	}
	
	/**
	 * 上传文件
	 * @param url    URL
	 * @param name   文件的post参数名称
	 * @param file   上传的文件
	 * @return
	 */
	public static String postFile(String url,String name,File file){
		try {
			HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(name, file).build();
			Request request = Request.Post(url);
			request.body(reqEntity);
			HttpEntity resEntity = request.execute().returnResponse().getEntity();
			return resEntity != null ? EntityUtils.toString(resEntity) : null;
		} catch (Exception e) {
			logger.error("postFile请求异常，" + e.getMessage() + "\n post url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 下载文件
	 * @param url   URL
	 * @return      文件的二进制流，客户端使用outputStream输出为文件
	 */
	public static byte[] getFile(String url){
		try {
			Request request = Request.Get(url);
			HttpEntity resEntity = request.execute().returnResponse().getEntity();
			return EntityUtils.toByteArray(resEntity);
		} catch (Exception e) {
			logger.error("postFile请求异常，" + e.getMessage() + "\n post url:" + url);
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
	private static String httpGet(String url) {
		try {
			HttpEntity entity = Request.Get(url).
					execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
}

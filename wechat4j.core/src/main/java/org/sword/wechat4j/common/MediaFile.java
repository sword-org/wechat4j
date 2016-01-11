/**
 * 
 */
package org.sword.wechat4j.common;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.sword.lang.HttpUtils;
import org.sword.wechat4j.token.TokenProxy;

import com.alibaba.fastjson.JSONObject;

/**
 * 上传下载多媒体文件
 * @author ChengNing
 * @date   2015年1月6日
 */
public class MediaFile {
	
	private static final String UPLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	private static final String DOWNLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="; 
	private static final String PARAM_FILE = "media";
	private static final String PARAM_MEDIA_ID = "media_id";
	private static final String PARAM_TYPE = "type";
	private static final String PARAM_CREATE_TIME = "created_at";
	
	private MediaType type;
	private File file;
	private String mediaId;  //3天失效
	private String createdTimestamp;//文件创建时间戳，上传之后返回
	
	
	/**
	 * 文件上传url
	 * @return
	 */
	private String uploadUrl(){
		String url = UPLOAD + TokenProxy.accessToken() + "&" 
	              + PARAM_TYPE + "=" + this.type.name();
		return url;
	}
	
	/**
	 * 文件下载url
	 * @return
	 */
	private String downloadUrl(){
		String url = DOWNLOAD + TokenProxy.accessToken() + "&" 
	         + PARAM_MEDIA_ID + "=" + this.mediaId;
		return url;
	}
	
	/**
	 * 文件上传
	 * success: {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 * error:   {"errcode":40004,"errmsg":"invalid media type"}
	 * @return  media_id  成功返回 media_id, 失败返回null
	 */
	public String upload(File file,MediaType type){
		this.file = file;
		this.type = type;
		String url = uploadUrl();
		String result = HttpUtils.postFile(url,PARAM_FILE, file);
		parseUploadResult(result);
		if(StringUtils.isNotBlank(this.mediaId))
			return this.mediaId;
		return null;
	}
	
	private void parseUploadResult(String result){
		JSONObject jsonObject = JSONObject.parseObject(result);
		if(jsonObject.containsKey(PARAM_MEDIA_ID)){
			this.mediaId = jsonObject.getString(PARAM_MEDIA_ID);
			this.createdTimestamp = jsonObject.getString(PARAM_CREATE_TIME);
		}
		else{
			this.mediaId = null;
			this.createdTimestamp = null;
		}
	}
	
	/**
	 * 文件下载
	 * @return  byte[]
	 */
	public byte[] download(String mediaId){
		this.mediaId = mediaId;
		String url = downloadUrl();
		byte[] fb = HttpUtils.getFile(url);
		if(fb == null || fb.length == 0)
			return null;
		return fb;
	}

	public MediaType getType() {
		return type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}
	
	
}

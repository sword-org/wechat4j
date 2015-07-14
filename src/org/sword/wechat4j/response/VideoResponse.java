/**
 * 
 */
package org.sword.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * 视频消息
 * @author ChengNing
 * @date   2014年12月7日
 */
public class VideoResponse {

	private String MediaId;     //通过上传多媒体文件，得到的id
	private String Title;       //视频消息的标题
	private String Description; //视频消息的描述
	private String ThumbMediaId;

	@XmlElement(name="MediaId")
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	@XmlElement(name="Title")
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	@XmlElement(name="Description")
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@XmlElement(name="ThumbMediaId")
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
	
	
}

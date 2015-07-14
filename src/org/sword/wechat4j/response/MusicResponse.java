/**
 * 
 */
package org.sword.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * 音乐消息类型
 * @author ChengNing
 * @date   2014-12-4
 */
public class MusicResponse {

	private String Title;        //音乐标题
	private String Description;  //音乐描述
	private String MusicURL;     //音乐链接
	private String HQMusicUrl;   //高质量音乐链接，WIFI环境优先使用该链接播放音乐
	private String ThumbMediaId; //缩略图的媒体id，通过上传多媒体文件，得到的id
	

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
	@XmlElement(name="MusicURL")
	public String getMusicURL() {
		return MusicURL;
	}
	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}
	@XmlElement(name="HQMusicUrl")
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
	@XmlElement(name="ThumbMediaId")
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
}

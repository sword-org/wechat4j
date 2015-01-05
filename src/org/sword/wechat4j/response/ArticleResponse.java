/**
 * 
 */
package org.sword.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * 图文消息体
 * @author ChengNing
 * @date   2014年12月7日
 */
public class ArticleResponse {

	private String Title;        //图文消息标题
	private String Description;  //图文消息描述
	private String PicUrl;       //图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	private String Url;          //点击图文消息跳转链接
	
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
	@XmlElement(name="PicUrl")
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	@XmlElement(name="Url")
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	
	
}

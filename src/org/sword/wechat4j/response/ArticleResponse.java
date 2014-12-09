/**
 * 
 */
package org.sword.wechat4j.response;

/**
 * @author ChengNing
 * @date   2014年12月7日
 */
public class ArticleResponse {

	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	
	
}

/**
 * 
 */
package org.sword.wechat4j.response;

/**
 * 音乐消息类型
 * @author ChengNing
 * @date   2014-12-4
 */
public class MusicResponse {

	private String Title;
	private String Description;
	private String MusicURL;
	private String HQMusicUrl;
	
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
	public String getMusicURL() {
		return MusicURL;
	}
	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}
	public String getHQMusicUrl() {
		return HQMusicUrl;
	}
	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}
}

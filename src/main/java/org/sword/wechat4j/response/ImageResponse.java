/**
 * 
 */
package org.sword.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author ChengNing
 * @date   2014年12月7日
 */
public class ImageResponse {
	private String MediaId;

	@XmlElement(name="MediaId")
	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
}

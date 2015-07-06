package org.sword.wechat4j.user;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 二维码
 * @author Zhangxs
 * @version 2015-7-5
 */
public class Qrcode {
	private String ticket;//	获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	private Integer expireSeconds;//	二维码的有效时间，以秒为单位。最大不超过1800。
	private String url;//	二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@JSONField(name="expire_seconds")
	public Integer getExpireSeconds() {
		return expireSeconds;
	}
	@JSONField(name="expire_seconds")
	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

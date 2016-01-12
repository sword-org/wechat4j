package org.sword.wechat4j.csc;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 客服基本信息
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class CustomerServices {
	private String kfAccount;//完整客服账号，格式为：账号前缀@公众号微信号
	private String kfId;//	客服工号
	private String kfHeadimgurl;//客服头像url
	private String kfNick;//	客服昵称
	private Integer status;//	客服在线状态
	private Integer autoAccept;//	客服设置的最大自动接入数
	private Integer acceptedCase;//	客服当前正在接待的会话数
	
	/**
	 * 完整客服账号<br/>
	 * 格式为：账号前缀@公众号微信号
	 * @return
	 */
	@JSONField(name="kf_account")
	public String getKfAccount() {
		return kfAccount;
	}
	@JSONField(name="kf_account")
	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
	/**
	 * 客服工号
	 * @return
	 */
	@JSONField(name="kf_id")
	public String getKfId() {
		return kfId;
	}
	@JSONField(name="kf_id")
	public void setKfId(String kfId) {
		this.kfId = kfId;
	}
	/**
	 * 客服头像url
	 * @return
	 */
	@JSONField(name="kf_headimgurl")
	public String getKfHeadimgurl() {
		return kfHeadimgurl;
	}
	@JSONField(name="kf_headimgurl")
	public void setKfHeadimgurl(String kfHeadimgurl) {
		this.kfHeadimgurl = kfHeadimgurl;
	}
	/**
	 * 客服昵称
	 * @return
	 */
	@JSONField(name="kf_nick")
	public String getKfNick() {
		return kfNick;
	}
	@JSONField(name="kf_nick")
	public void setKfNick(String kfNick) {
		this.kfNick = kfNick;
	}
	/**
	 * 客服在线状态</br>
	 *  1:pc在线</br>
	 *  2:手机在线</br>
	 *  3:pc和手机同时在线
	 * @return
	 */
	@JSONField(name="status")
	public Integer getStatus() {
		return status;
	}
	@JSONField(name="status")
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 客服设置的最大自动接入数
	 * @return
	 */
	@JSONField(name="auto_accept")
	public Integer getAutoAccept() {
		return autoAccept;
	}
	@JSONField(name="auto_accept")
	public void setAutoAccept(Integer autoAccept) {
		this.autoAccept = autoAccept;
	}
	/**
	 * 客服当前正在接待的会话数
	 * @return
	 */
	@JSONField(name="accepted_case")
	public Integer getAcceptedCase() {
		return acceptedCase;
	}
	@JSONField(name="accepted_case")
	public void setAcceptedCase(Integer acceptedCase) {
		this.acceptedCase = acceptedCase;
	}
	
	
}

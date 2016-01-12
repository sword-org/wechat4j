package org.sword.wechat4j.csc;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 会话状态
 * @author Zhangxs
 * @date 2015-7-8 
 * @version
 */
public class CustomerServicesSession {
	
	private int createTime;//会话接入的时间
    private String kfAccount;//客服
    private String openId;//客户openid
	/**
	 * 会话接入的时间
	 * @return
	 */
	@JSONField(name="createtime")
	public int getCreateTime() {
		return createTime;
	}
	@JSONField(name="createtime")
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	/**
	 * 客服
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
	 * 客户openid
	 * @return
	 */
	@JSONField(name="openid")
	public String getOpenId() {
		return openId;
	}
	@JSONField(name="openid")
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
     
     

}

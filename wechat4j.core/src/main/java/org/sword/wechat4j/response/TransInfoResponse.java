package org.sword.wechat4j.response;

import javax.xml.bind.annotation.XmlElement;

/**
 * 指定客服
 * @author Zhangxs
 * @version 2015-7-7
 */

public class TransInfoResponse {
	private String KfAccount;//指定会话接入的客服账号
	
	public TransInfoResponse() {
		super();
	}

	public TransInfoResponse(String kfAccount) {
		super();
		KfAccount = kfAccount;
	}

	@XmlElement(name="KfAccount")
	public String getKfAccount() {
		return KfAccount;
	}

	public void setKfAccount(String kfAccount) {
		KfAccount = kfAccount;
	}
	
}

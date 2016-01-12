package org.sword.wechat4j.user;

import java.util.List;
/**
 * 关注者列表
 * @author Zhangxs
 * @version 2015-7-5
 */
public class Data {
	/**
	 * 用户列表
	 */
	private List<String> openid;

	public List<String> getOpenid() {
		return openid;
	}

	public void setOpenid(List<String> openid) {
		this.openid = openid;
	}
}
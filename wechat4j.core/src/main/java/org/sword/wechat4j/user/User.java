package org.sword.wechat4j.user;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户实体类
 * @author Zhangxs
 * @version 2015-7-5
 */
public class User {
	private int subscribe;//	用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String openId;//	用户的标识，对当前公众号唯一
	private String nickName;//	用户的昵称
	private int sex;//	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String city;//	用户所在城市
	private String country;//	用户所在国家
	private String province;//	用户所在省份
	private LanguageType language;//	用户的语言，简体中文为zh_CN
	private String headimgUrl;//	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	private String subscribeTime;//	用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private String unionId;//	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	private String remark;//	公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	private int groupId;//	用户所在的分组ID
	
	/**
	 * 用户是否订阅该公众号标识
	 * 值为0时，代表此用户没有关注该公众号，拉取不到其余信息
	 */
	public int getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	/**
	 * 用户的标识，对当前公众号唯一
	 */
	@JSONField(name="openid")
	public String getOpenId() {
		return openId;
	}
	
	@JSONField(name="openid")
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	/**
	 * 用户的昵称
	 */
	@JSONField(name="nickname")
	public String getNickName() {
		return nickName;
	}
	@JSONField(name="nickname")
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 用户的性别，
	 * 值为1时是男性，
	 * 值为2时是女性，
	 * 值为0时是未知
	 */
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * 用户所在城市
	 */
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 用户所在国家
	 */
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * 用户所在省份
	 */
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 用户的语言
	 */
	public void setLanguage(LanguageType language) {
		this.language = language;
	}
	public LanguageType getLanguage() {
		return language;
	}
	/**
	 * 用户头像，
	 * 最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
	 * 用户没有头像时该项为空。
	 * 若用户更换头像，原有头像URL将失效。
	 */
	@JSONField(name="headimgurl")
	public String getHeadimgUrl() {
		return headimgUrl;
	}
	@JSONField(name="headimgurl")
	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}
	/**
	 * 用户关注时间，为时间戳。
	 * 如果用户曾多次关注，则取最后关注时间
	 */
	
	@JSONField(name="subscribe_time")
	public String getSubscribeTime() {
		return subscribeTime;
	}
	@JSONField(name="subscribe_time")
	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 * 详见：获取用户个人信息（UnionID机制）
	 */
	
	@JSONField(name="unionid")
	public String getUnionId() {
		return unionId;
	}
	@JSONField(name="unionid")
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	/**
	 * 公众号运营者对粉丝的备注，
	 * 公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	 */
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 用户所在的分组ID
	 */
	@JSONField(name="groupid")	
	public int getGroupId() {
		return groupId;
	}
	@JSONField(name="groupid")
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public String toString() {
		return "User [subscribe=" + subscribe + ", openId=" + openId
				+ ", nickName=" + nickName + ", sex=" + sex + ", city=" + city
				+ ", country=" + country + ", province=" + province
				+ ", language=" + language + ", headimgUrl=" + headimgUrl
				+ ", subscribeTime=" + subscribeTime + ", unionId=" + unionId
				+ ", remark=" + remark + ", groupId=" + groupId + "]";
	}
	
	
	
}

package org.sword.wechat4j.menu;

/**
 * 菜单按钮类型
 * @author Zhangxs
 * @version 2015-7-4
 */
public enum MenuButtonType {
	/** 点击  */
	click,
	/** 跳转URL */
	view,
	/** 扫码推事件 */
	scancode_push,
	/** 扫码推事件且弹出“消息接收中”提示框 */
	scancode_waitmsg,
	/** 弹出系统拍照发图 */
	pic_sysphoto,
	/** 弹出拍照或者相册发图 */
	pic_photo_or_album,
	/** 弹出微信相册发图器 */
	pic_weixin,
	/** 弹出地理位置选择器 */
	location_select,
	/** //下发消息（除文本消息） */
	media_id,
	/** 跳转图文消息URL */
	view_limited;
}

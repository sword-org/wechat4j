package org.sword.wechat4j.user;
/**
 * 二维码类型
 * @author Zhangxs
 * @version 2015-7-5
 */
public enum QrcodeType {
	/** 临时二维码 */
	QR_SCENE,
	/** 永久二维码 */
	QR_LIMIT_SCENE,
	/** 永久的字符串参数值 */
	QR_LIMIT_STR_SCENE;
}

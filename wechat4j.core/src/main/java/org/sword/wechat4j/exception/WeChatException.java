package org.sword.wechat4j.exception;

/**
 * 异常处理
 * @author Zhangxs
 * @version 2015-7-4
 */
public class WeChatException extends Exception {
	private static final long serialVersionUID = 1L;
	public WeChatException(String msg){
		super(msg);
	}
}
